package com.example.liveparkgood;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que se encarga únicamente de establecer la conexión entre java y la base de datos MySQL y que corresponde
 * a la parte de "Controlador" dentro de nuestro patrón diseño.
 */

public class Conexion{
    Connection con;

    /**
     * Método que sirve para acceder a la base de datos correspondiente
     * @return devuelve la conexión si se ha podido acceder y null si ha habido algún error en la conexión
     */

    public Connection getConnection() {
        try {
            String BD = "jdbc:mysql://localhost:3306/livepark?serverTimezone=UTC";
            con = DriverManager.getConnection(BD, "root", "cocinero11");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args){
        Conexion x = new Conexion();
        x.getConnection();
    }
}
