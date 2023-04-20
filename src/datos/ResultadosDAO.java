package datos;

import dominio.Resultados;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static datos.Conexion.close;

public class ResultadosDAO {
    public static final String SQL_SELECT = "SELECT FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GOLES_1, GOLES_2 FROM RESULTADOS R JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO";

    public static List<Resultados> leerResultados() {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<Resultados> resultados = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            conn = Conexion.getConnection();
            stmt = conn.createStatement();

            // El Query que vamos a correr
            rs = stmt.executeQuery(SQL_SELECT);
            while (rs.next()) {
                Resultados resultado = new Resultados(
                        rs.getString("FASE"),
                        rs.getString("RONDA"),
                        rs.getString("EQUIPO_1"),
                        rs.getString("EQUIPO_2"),
                        rs.getInt("GOLES_1"),
                        rs.getInt("GOLES_2")
                );
                resultados.add(resultado);
            }

        } catch (SQLException e) {
            System.out.println("Error con SQL");
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

            return resultados;
        }
    }

}
