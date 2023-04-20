package dominio;

import pronostico.Archivo;
import java.io.File;
import java.util.*;

public class Pronosticos extends Resultados {
    private int idPronostico;
    private String nombre;
    private int ganador;
    private int idResultado;
    ResultadoEnum resultado;
    private int puntaje;

    public Pronosticos() {
    }

    public Pronosticos(int idPronostico) {
        this.idPronostico = idPronostico;
    }

    public Pronosticos(String nombre, String fase, String ronda, String idEquipo1, String idEquipo2, int ganador) {
        super(fase, ronda, idEquipo1, idEquipo2);
        this.nombre = nombre;
        this.ganador = ganador;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }

    public int getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(int idPronostico) {
        this.idPronostico = idPronostico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int puntos(int ganador){
        if(resultado.equals(ResultadoEnum.GANADOR) && ganador == 1 || resultado.equals(ResultadoEnum.EMPATE) && ganador == 0 || resultado.equals(ResultadoEnum.GANADOR) && ganador == 2){
            return 1;
        }
        return 0;
    }

    public void puntuacion(List<Pronosticos> pronosticos, List<Resultados> resultados) {
        String archivo = "src/dominio/resultados.csv";

        File fichero = new File(archivo);
        if(fichero.length() == 0){
                int i = 0;
                int j = 0;

              for (int x = 0; x < pronosticos.size() / resultados.size(); x++) {
                    while (i < resultados.size()) {
                                    int golesEquipo1 = resultados.get(i).getGoles1();
                                    int golesEquipo2 = resultados.get(i).getGoles2();
                                if (pronosticos.get(j).getGanador() == 2) {
                                    golesEquipo1 = resultados.get(i).getGoles2();
                                    golesEquipo2 = resultados.get(i).getGoles1();
                                }
                                setResultado(resultados.get(i).resultado(golesEquipo1, golesEquipo2));

                                if(puntos(pronosticos.get(j).getGanador()) > 0){
                                String nuevoTexto = x + ";" +
                                                    pronosticos.get(j).getNombre() + ";" +
                                                    getResultado() + ";" +
                                                    pronosticos.get(j).getFase() + ";" +
                                                    pronosticos.get(j).getRonda() + ";" +
                                                    pronosticos.get(j).getIdEquipo1() + ";" +
                                                    pronosticos.get(j).getIdEquipo2()+ ";" +
                                                    pronosticos.get(j).getGanador();

                                           new Archivo(archivo, nuevoTexto);//escribo el archivo
                                }//cierre if, filtro solamente los partidos acertados
                             i++;
                             j++;
                    }//cierre while
                            i = 0;
              }//cierre for

        }//cierre if, se verifica si el archivo esta vacio
    }
}
