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


        String configuracion = "src/dominio/configuracion.csv";
        String resultados = "src/dominio/resultados.csv";
        AnalisisDeportivo analisis = new AnalisisDeportivo();
        analisis.deportivo(resultados,configuracion);

    }
}
