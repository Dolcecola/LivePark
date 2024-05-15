package com.example.liveparkgood;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapaIntermedia {

    Conexion conexion = new Conexion();

    public List<Tabla> leerImagenes(){
        return(conexion.leerImagenes());
    }

    public List<List<String>> leerInfoPlazas(){
        return(conexion.leerInfoPlazas());
    }

    public boolean añadirReserva(String tar, String mm, String yy,String codigo){

        if(tar.isEmpty() || mm.isEmpty() || yy.isEmpty() || codigo.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Rellene todos los campos");
            alert.showAndWait();

        } else {
            if(tar.length() != 16 || mm.length() > 2 || yy.length() > 2 || codigo.length() != 3){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fatal Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Compruebe los campos");
                alert.showAndWait();

            } else {
                return (conexion.añadirReserva());
            }
        }

        return false;
    }

    public boolean crearUsuario(String n, String c, String e, String t, CheckBox box){

        if(n.isEmpty() || c.isEmpty() || e.isEmpty() || t.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Complete todos los campos");
            alert.showAndWait();

        } else if(t.length()>9) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Número de teléfono inválido!");
            alert.showAndWait();

        } else if(!box.isSelected()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Debe aceptar los términos y condiciones");
            alert.showAndWait();

        } else {

            return(conexion.crearUsuario(n,c,e, Integer.parseInt(t)));
        }

        return false;
    }

    public boolean leerDatosUsuario(String nombre, String contraseña){

        return (conexion.leerDatosUsuario(nombre,contraseña));
    }

    public boolean actualizarDatosUsuario(String email, String telefono){

        if(email.isEmpty() || telefono.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Rellene los campos");
            alert.showAndWait();

        } else if(telefono.length()>9){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fatal Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Número de teléfono inválido!");
            alert.showAndWait();

        } else {
            return (conexion.actualizarDatosUsuario(email,telefono));
        }

        return false;

    }

    public String[] leerReserva(){

        return (conexion.leerReserva());
    }

    public boolean comprobarReserva(){

        return (conexion.comprobarReserva());
    }

}
