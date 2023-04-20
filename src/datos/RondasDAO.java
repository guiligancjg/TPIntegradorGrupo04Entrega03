package datos;

import dominio.Rondas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static datos.Conexion.close;

public class RondasDAO {
    public static final String SQL_SELECT = "SELECT RONDA, COUNT(*) AS CANTIDAD FROM RESULTADOS GROUP BY RONDA HAVING COUNT(*) > 1";

    public static List<Rondas> leerRondas() {
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<Rondas> rondas = new ArrayList<>();

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
                Rondas ronda = new Rondas(
                        rs.getString("RONDA"),
                        rs.getInt("CANTIDAD")
                );
                rondas.add(ronda);
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

            return rondas;
        }
    }

}
