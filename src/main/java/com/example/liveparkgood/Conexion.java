package com.example.liveparkgood;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

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

    public List<Tabla> leerImagenes(){

        String sql = "SELECT * FROM imagenes";
        List<Tabla> imagenes = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                Blob imagen = rs.getBlob(2);

                Tabla tabla = new Tabla(id, imagen);
                imagenes.add(tabla);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return imagenes;
    }

    public List<List<String>> leerInfoPlazas(){

        String sql = "SELECT * FROM plaza";
        List<List<String>> aux = new ArrayList<>();

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String direccion = rs.getString(1);
                String  plazas = String.valueOf(rs.getInt(2));
                String coste = String.valueOf(rs.getInt(3));
                String contacto = String.valueOf(rs.getInt(4));

                List<String> ffaux = new ArrayList<>();
                ffaux.add(direccion);
                ffaux.add(plazas);
                ffaux.add(coste);
                ffaux.add(contacto);

                aux.add(ffaux);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return aux;
    }

    public boolean añadirReserva(){

        String fecha = DatosCompartidos.getFecha();
        int hora_inicio = DatosCompartidos.getHora_inicio();
        int hora_final = DatosCompartidos.getHora_final();

        String id = DatosCompartidos.getId();
        String nombre = DatosCompartidos.getNombre();

        String sql = "INSERT INTO reserva (id, fecha, horainicio, horafinal, nombre) VALUES (?,?,?,?,?)";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2, fecha);
            ps.setInt(3, hora_inicio);
            ps.setInt(4, hora_final);
            ps.setString(5,nombre);

            int comp = ps.executeUpdate();
            if(comp==0){
                return false;
            }

            actualizarUsuario();

            if(id.equals("Génova")){

                String sql2 = "UPDATE plaza SET Plazas = Plazas - 1 WHERE Contacto = 913766213";

                ps = con.prepareStatement(sql2);
                ps.executeUpdate();

            } else if (id.equals("Monte")) {

                String sql2 = "UPDATE plaza SET Plazas = Plazas - 1 WHERE Contacto = 913081486";

                ps = con.prepareStatement(sql2);
                ps.executeUpdate();

            } else if (id.equals("Santa")) {

                String sql2 = "UPDATE plaza SET Plazas = Plazas - 1 WHERE Contacto = 915221089";

                ps = con.prepareStatement(sql2);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public void actualizarUsuario(){

        String nombre = DatosCompartidos.getNombre();
        String id = DatosCompartidos.getId();

        String sql = "UPDATE usuarios SET reserva = ?, id = ? WHERE Nombre = ?";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setBoolean(1, true);
            ps.setString(2,id);
            ps.setString(3,nombre);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean crearUsuario(String nombre, String contraseña, String email, int telefono){

        String sql = "INSERT INTO usuarios (Nombre,Contraseña,Email,Telefono,reserva) VALUES (?,?,?,?,?)";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1,nombre);
            ps.setString(2,contraseña);
            ps.setString(3,email);
            ps.setInt(4,telefono);
            ps.setBoolean(5, false);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean leerDatosUsuario(String nombre, String contraseña){

        String sql = "SELECT * FROM usuarios WHERE Nombre = ? AND Contraseña = ?";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2,contraseña);

            rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public boolean actualizarDatosUsuario(String email, String telefono){

        String sql = "UPDATE usuarios SET Email = ?, Telefono = ? WHERE Nombre = ?";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2,telefono);
            ps.setString(3,DatosCompartidos.getNombre());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public String[] leerReserva(){

        String[] vec = new String[4];
        String nombre = DatosCompartidos.getNombre();

        String sql = "SELECT * FROM reserva WHERE nombre = ?";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, nombre);

            rs = ps.executeQuery();

            if(rs.next()){

                String id = rs.getString(1);
                String fecha = rs.getString(2);
                String hi = String.valueOf(rs.getInt(3));
                String hf = String.valueOf(rs.getInt(4));

                vec[0] = id;
                vec[1] = fecha;
                vec[2] = hi;
                vec[3] = hf;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vec;
    }

    public boolean comprobarReserva(){

        String nombre = DatosCompartidos.getNombre();

        String sql = "SELECT reserva FROM usuarios WHERE Nombre = ?";

        try {
            con = getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, nombre);

            rs = ps.executeQuery();

            if(rs.next()){
                boolean comp = rs.getBoolean("reserva");
                if(comp){
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }
}
