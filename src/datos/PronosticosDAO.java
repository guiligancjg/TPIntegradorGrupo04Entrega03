package datos;

import static datos.Conexion.*;
import dominio.Pronosticos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PronosticosDAO {
    public static final String SQL_SELECT = "SELECT NOMBRE, FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GANADOR FROM PRONOSTICOS P JOIN RESULTADOS R on P.ID_RESULTADO = R.ID_RESULTADO JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO";

    public static List<Pronosticos> leerPronosticos(){
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<Pronosticos> pronosticos = new ArrayList<>();

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
                Pronosticos pronostico = new Pronosticos(
                        rs.getString("NOMBRE"),
                        rs.getString("FASE"),
                        rs.getString("RONDA"),
                        rs.getString("EQUIPO_1"),
                        rs.getString("EQUIPO_2"),
                        rs.getInt("GANADOR")
                );
                        pronosticos.add(pronostico);
            }

        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }finally {
            try{
                close(rs);
                close(stmt);
                close(conn);
            }catch (SQLException ex){
                ex.printStackTrace(System.out);
            }
        }
        return pronosticos;
    }
}
