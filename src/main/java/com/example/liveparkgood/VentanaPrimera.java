package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class VentanaPrimera extends Application {

    public static void main(String[] args){
        launch(args);
    }
    private Text revelar = new Text();
    Color verde = Color.rgb(0,233,168);

    @Override
    public void start(Stage stage){

        stage.setTitle("LivePark");
        stage.setResizable(false);
        Pane layout = new Pane();
        Text texto = new Text("Bienvenido!");
        TextField nombre = new TextField();
        PasswordField contraseña = new PasswordField();
        Button inicio = new Button("Iniciar Sesión");
        Button crear_sesion = new Button("Crear cuenta");
        ImageView logo = new ImageView();
        ImageView esconder = new ImageView();

        CapaIntermedia ci = new CapaIntermedia();
        List<Tabla> temp = ci.leerImagenes();
        ByteArrayInputStream imagen1 = temp.get(0).getImagen();
        ByteArrayInputStream imagen2 = temp.get(1).getImagen();

        Image imagen = new Image(imagen1);
        logo.setImage(imagen);
        logo.setX(-5);
        logo.setY(-200);

        Image ojo = new Image(imagen2);
        esconder.setImage(ojo);
        esconder.setX(310);
        esconder.setY(305);
        esconder.setFitWidth(25);
        esconder.setFitHeight(25);
        Rectangle rectangle = new Rectangle(25,305,285,25);

        esconder.setOnMousePressed(event -> {
            if (!layout.getChildren().contains(revelar)) {
                String aux = contraseña.getText();

                if(aux.isEmpty()){
                    return;
                }

                rectangle.setFill(Color.WHITE);
                revelar.setText(aux);
                revelar.setFont(Font.font("Arial",14));

                revelar.setX(30);
                revelar.setY(325);

                layout.getChildren().add(rectangle);
                layout.getChildren().add(revelar);
            }
        });

        esconder.setOnMouseReleased(event -> {
            revelar.setText(""); // Limpiar el texto cuando se suelta el botón
            layout.getChildren().remove(revelar);
            layout.getChildren().remove(rectangle);
        });

        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);

        texto.setFont(Font.font("Montserrat",40));
        texto.setX(300);

        nombre.setLayoutX(20);
        nombre.setLayoutY(250);
        nombre.setPrefWidth(325);
        nombre.setPrefHeight(35);
        nombre.setPromptText("Nombre");

        contraseña.setLayoutX(20);
        contraseña.setLayoutY(300);
        contraseña.setPrefWidth(325);
        contraseña.setPrefHeight(35);
        contraseña.setPromptText("Contraseña");

        inicio.setLayoutX(20);
        inicio.setLayoutY(350);
        inicio.setPrefWidth(160);
        inicio.setPrefHeight(40);

        inicio.setOnMouseClicked(event ->{

            String n = nombre.getText();
            String pass = contraseña.getText();

            if(n.isEmpty() || pass.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fatal Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Complete los campos");
                alert.showAndWait();

            } else {

                boolean comp = ci.leerDatosUsuario(n,pass);
                if(comp){

                    DatosCompartidos.setNombre(n);
                    VentanaPrincipal x = new VentanaPrincipal();
                    x.InitComponents();
                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fatal Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Usuario no encontrado!");
                    alert.showAndWait();
                }
            }
        });

        crear_sesion.setLayoutX(185);
        crear_sesion.setLayoutY(350);
        crear_sesion.setPrefWidth(160);
        crear_sesion.setPrefHeight(40);

        crear_sesion.setOnMouseClicked(event ->{

            VentanaCrearSesion x = new VentanaCrearSesion();
            x.InitComponents();
        });

        layout.setBackground(fondo);
        layout.getChildren().addAll(logo,nombre,contraseña,esconder,inicio,crear_sesion);

        Scene scene = new Scene(layout,370,620);
        stage.setScene(scene);
        stage.show();
    }
}