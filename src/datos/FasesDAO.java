package datos;

import dominio.Fases;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;

public class FasesDAO {
    public static final String SQL_SELECT = "SELECT FASE,RONDA, COUNT(*) AS CANTIDAD FROM RESULTADOS GROUP BY RONDA,FASE HAVING COUNT(*) > 1 ORDER BY FASE DESC , RONDA ASC";
    public static List<Fases> leerFases() {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<Fases> fases = new ArrayList<>();

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
                Fases fase = new Fases(
                        rs.getString("FASE"),
                        rs.getString("RONDA"),
                        rs.getInt("CANTIDAD")
                );
                fases.add(fase);
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

            return fases;
        }
    }

}
