package dominio;

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
        List<String> nombres = new ArrayList<>();
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
            longitud.add(filas[0]);
        }
        Set<String> personas = new LinkedHashSet<>(longitud);

        int aciertos = 0;
        for (int i = 0; i < personas.size(); i++) {
            for (String[] resultado : leer.archivo(resultados)) {//contar puntos
                if (Integer.parseInt(resultado[0]) == i) {
                    totalPuntos += punto;
                    nombres.add(resultado[1]);
                    listaRonda.add(resultado[4]);

                }

            }
            aciertos += grupo(listaRonda);//Se suman puntos extraRonda cuando se aciertan todos los resultados de una ronda.

            listaRonda.clear();

            Set<String> nombre = new LinkedHashSet<>(nombres);//me devuelve solo un nombre
            nombres.clear();
            System.out.format("\n" + "%-10s%-10s%-19s%-30s\n", nombre, totalPuntos, "Rondas acertadas:", aciertos);
            totalPuntos += aciertos;
            System.out.println("Total de puntos: "+ totalPuntos);
            totalPuntos = 0;
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
}
