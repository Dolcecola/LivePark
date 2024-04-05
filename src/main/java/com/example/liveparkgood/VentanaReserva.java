package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaReserva extends Application {

    Color verde = Color.rgb(0,233,168);

    @Override
    public void start(Stage stage) throws Exception {

        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();


        DatePicker calendario = new DatePicker();
        calendario.setId("custom-date-picker");
        calendario.setPromptText("Fecha (p.e. 02/04/2024)");
        calendario.setLayoutX(30);
        calendario.setLayoutY(100);
        Pane root = new Pane(calendario);

        TextField hora_incio = new TextField();
        TextField hora_fin = new TextField();
        Button finalizar = new Button("Pagar");
        Text titulo = new Text("Reserva");

        hora_incio.setLayoutX(25);
        hora_incio.setLayoutY(450);
        hora_incio.setPrefWidth(250);
        hora_incio.setPrefHeight(35);

        hora_fin.setLayoutX(25);
        hora_fin.setLayoutY(500);
        hora_fin.setPrefWidth(250);
        hora_fin.setPrefHeight(35);

        finalizar.setId("miBoton");
        finalizar.setLayoutX(250);
        finalizar.setLayoutY(550);

        titulo.setId("reserva");
        titulo.setLayoutX(90);
        titulo.setLayoutY(70);

        layout.setBackground(fondo);
        layout.getChildren().addAll(root,titulo,finalizar,hora_incio,hora_fin);

        Scene ventana = new Scene(layout, 370,620);
        ventana.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(ventana);
        stage.show();
    }
}
