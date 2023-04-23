import dominio.AnalisisDeportivo;
import dominio.Pronosticos;
import datos.PronosticosDAO;
import datos.ResultadosDAO;

public class Main {
    public static void main(String[] args) {
        PronosticosDAO pronosticosDAO = new PronosticosDAO();
        ResultadosDAO resultadosDAO = new ResultadosDAO();
        Pronosticos pronostico = new Pronosticos();

        //creo archivo resultado para filtrar los pronosticos acertados
        pronostico.puntuacion(pronosticosDAO.leerPronosticos(), resultadosDAO.leerResultados());

        //Se suman puntos extraRonda cuando se aciertan todos los resultados de una ronda.
        //Hay 8 Rondas en Fase1, por ejemplo si Mariana acierta un solo partido en las 8 Rondas tiene puntos extraFase
        //Hay 4 Rondas en Fase1, por ejemplo si Mariana acierta un solo partido en las 4 Rondas tiene puntos extraFase

        String configuracion = "src/dominio/configuracion.csv";
        String resultados = "src/dominio/resultados.csv";
        AnalisisDeportivo analisis = new AnalisisDeportivo();
        analisis.deportivo(resultados,configuracion);

    }
}
