package dominio;

import datos.FasesDAO;
import datos.RondasDAO;
import pronostico.Archivo;

import java.util.*;

public class AnalisisDeportivo {
    private int punto;
    private int extraRonda;
    private int extraFase;

    public AnalisisDeportivo() {
        this.punto = 0;
        this.extraRonda = 0;
        this.extraFase = 0;
    }

    public void deportivo(String resultados, String configuracion) {
        List<String> longitud = new ArrayList<>();
        List<String> listaRonda = new ArrayList<>();
        Archivo leer = new Archivo();
        int totalPuntos = 0;


        for (String[] config : leer.archivo(configuracion)) {//leer configuracion
            punto = Integer.parseInt(config[0]);
            extraRonda = Integer.parseInt(config[1]);
            extraFase = Integer.parseInt(config[2]);
        }

        for (String[] filas : leer.archivo(resultados)) {//cantidad de personas
            if(!filas[1].equals("nombre")){
                longitud.add(filas[1]);
            }
        }
        Set<String> personas = new LinkedHashSet<>(longitud);
        String[] persona = personas.toArray(new String[personas.size()]);

        int indice = 0;
        //int indicePersona = 0;
        for (String[] resultado : leer.archivo(resultados)) {//contar puntos
            if (resultado[1].equals(persona[indice])) {
                totalPuntos += punto;
                listaRonda.add(resultado[4]);
            } else {

                int aciertosRondas = grupo(listaRonda);//Se suman puntos extraRonda cuando se aciertan todos los resultados de una ronda.
                int aciertosFases = fase(listaRonda);
                listaRonda.clear();

                System.out.format("\n" + "%-10s%-10s%-10s%-10s%-10s%1s\n",persona[indice], totalPuntos, "Rondas acertadas puntos:", aciertosRondas, "Fases acertadas puntos:", aciertosFases);
                totalPuntos += aciertosRondas + aciertosFases;
                System.out.println("Total de puntos: " + totalPuntos);
                totalPuntos = 0;
                indice++;
            }

        }
    }

    public int grupo(List<String> listaRonda) {
        RondasDAO registros = new RondasDAO();
        int rondaAciertos = 0;

        Map<String, Integer> counts = new HashMap<>();

        for (String item : listaRonda) {//cuantas veces se repite el Grupo
            if (counts.containsKey(item)) {
                counts.put(item, counts.get(item) + 1);
            } else {
                counts.put(item, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            for (Rondas lista : registros.leerRondas()) {
                if (entry.getKey().equals(lista.getRonda()) && entry.getValue() == lista.getCantidad()) {
                    rondaAciertos += extraRonda;
                }

            }


        }//cierre Map
        return rondaAciertos;
    }//cierre public int grupo()

    public int fase(List<String> listaRonda) {
        List<String> listaRondaMayusculas = new ArrayList<>();

        for (String elemento : listaRonda) {//saco los espacion en blanco y pongo todo a mayusculas
            String elementoSinEspacios = elemento.replaceAll("\\s+", "");
            String elementoEnMayusculas = elementoSinEspacios.toUpperCase();
            listaRondaMayusculas.add(elementoEnMayusculas);
        }

        int faseAciertos = 0;

        Set<String> grupos = new LinkedHashSet<>(listaRondaMayusculas);

        int contFase1 = 0;
        int contFase2 = 0;
        for (String grupo : grupos) {
            if (grupo.equals(RondasEnum.GRUPOA.toString()) ||
                    grupo.equals(RondasEnum.GRUPOB.toString()) ||
                    grupo.equals(RondasEnum.GRUPOC.toString()) ||
                    grupo.equals(RondasEnum.GRUPOD.toString()) ||
                    grupo.equals(RondasEnum.GRUPOE.toString()) ||
                    grupo.equals(RondasEnum.GRUPOF.toString()) ||
                    grupo.equals(RondasEnum.GRUPOG.toString()) ||
                    grupo.equals(RondasEnum.GRUPOH.toString())) {
                contFase1++;
            }
            if (grupo.equals(RondasEnum.OCTAVOS.toString()) ||
                    grupo.equals(RondasEnum.CUARTOS.toString()) ||
                    grupo.equals(RondasEnum.SEMIS.toString()) ||
                    grupo.equals(RondasEnum.FINAL.toString())) {
                contFase2++;
            }
        }//cierre for

        //Hay 8 Rondas en Fase1, si Mariana acierta un equipo en todos las Rondas tiene puntos extraFase
        //Hay 4 Rondas en Fase2, si Mariana acierta un equipo en todos las Rondas tiene puntos extraFase
        if (contFase1 == 8) {
            faseAciertos += extraFase;
        }
        if (contFase2 == 4) {
            faseAciertos += extraFase;
        }

        return faseAciertos;
    }
}
