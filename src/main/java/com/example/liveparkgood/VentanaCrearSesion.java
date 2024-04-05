package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaCrearSesion extends Application {

    Color verde = Color.rgb(0,233,168);

    public void InitComponents(){
        Stage x = new Stage();
        try {
            start(x);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();

        int coordX = 20;
        int coordY = 200;
        int widht = 330;
        int height = 40;


        TextField nombre = new TextField();
        TextField email = new TextField();
        TextField contraseña = new TextField();
        TextField telefono = new TextField();
        Text bienvenido = new Text("Bienvenido");
        Text terminos_1 = new Text("Estoy de acuerdo");
        Text terminos_2 = new Text("con los términos y servicios");
        Button registrar = new Button("Registrarse");
        CheckBox box = new CheckBox();

        box.setLayoutX(coordX+5);
        box.setLayoutY(coordY+206.5);

        registrar.setLayoutX(coordX);
        registrar.setLayoutY(coordY+250);
        registrar.setPrefWidth(widht);
        registrar.setPrefHeight(height);

        bienvenido.setFont(Font.font("Montserrat",40));
        bienvenido.setX(85);
        bienvenido.setY(100);

        terminos_1.setFont(Font.font("Arial",13));
        terminos_1.setX(coordX+30);
        terminos_1.setY(coordY+220);

        terminos_2.setFont(Font.font("Arial", FontWeight.BOLD,13));
        terminos_2.setUnderline(true);
        terminos_2.setX(coordX+135);
        terminos_2.setY(coordY+220);

        nombre.setLayoutX(coordX);
        nombre.setLayoutY(coordY);
        nombre.setPrefWidth(widht);
        nombre.setPrefHeight(height);
        nombre.setPromptText("Nombre");

        contraseña.setLayoutX(coordX);
        contraseña.setLayoutY(coordY+50);
        contraseña.setPrefWidth(widht);
        contraseña.setPrefHeight(height);
        contraseña.setPromptText("Contraseña");

        email.setLayoutX(coordX);
        email.setLayoutY(coordY+100);
        email.setPrefWidth(widht);
        email.setPrefHeight(height);
        email.setPromptText("Email");

        telefono.setLayoutX(coordX);
        telefono.setLayoutY(coordY+150);
        telefono.setPrefWidth(widht);
        telefono.setPrefHeight(height);
        telefono.setPromptText("Teléfono");

        layout.setBackground(fondo);
        layout.getChildren().addAll(nombre,contraseña,email,telefono,bienvenido,terminos_1,terminos_2,registrar,box);

        Scene ventana = new Scene(layout, 370,620);
        stage.setScene(ventana);
        stage.show();
    }
}
