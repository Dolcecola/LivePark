package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VentanaReserva extends Application {
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
        stage.setResizable(false);

        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();

        DatePicker calendario = new DatePicker();
        calendario.setId("custom-date-picker");
        calendario.setLayoutX(30);
        calendario.setLayoutY(100);
        calendario.getEditor().setDisable(true);
        Pane root = new Pane(calendario);

        TextField hora_incio = new TextField();
        TextField hora_fin = new TextField();
        Button finalizar = new Button("Pagar");
        Text titulo = new Text("Reserva");

        hora_incio.setLayoutX(25);
        hora_incio.setLayoutY(450);
        hora_incio.setPrefWidth(200);
        hora_incio.setPrefHeight(35);
        hora_incio.setPromptText("Hora de inicio: (p.e. 10:00)");

        hora_fin.setLayoutX(25);
        hora_fin.setLayoutY(500);
        hora_fin.setPrefWidth(200);
        hora_fin.setPrefHeight(35);
        hora_fin.setPromptText("Hora final: (p.e. 14:00)");

        finalizar.setId("miBoton");
        finalizar.setLayoutX(250);
        finalizar.setLayoutY(550);
        finalizar.setOnMouseClicked(event -> {

            LocalDate fechaActual = LocalDate.now();

            String fechaSeleccionada = calendario.getEditor().getText();

            String h_inicio = hora_incio.getText();
            String h_final = hora_fin.getText();

            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate fechaSe = LocalDate.parse(fechaSeleccionada, formatter);

                if (fechaSe.isAfter(fechaActual) || fechaSe.isEqual(fechaActual)) {

                    String[] partes1 = h_inicio.split(":");
                    String[] partes2 = h_final.split(":");

                    // Verificar si se dividió correctamente
                    if (partes1.length == 2 && partes2.length == 2) {

                        if (partes1[0].length() > 2 || partes1[1].length() > 2 || partes2[0].length() > 2 || partes2[1].length() > 2){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Fatal Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Error: Número de horas/minutos incorrecto!");
                            alert.showAndWait();
                        } else {

                            int hi = Integer.parseInt(partes1[0]);
                            int mi = Integer.parseInt(partes1[1]);
                            int hf = Integer.parseInt(partes2[0]);
                            int mf = Integer.parseInt(partes2[1]);

                            if((hf-hi) <= 0 || mi != 0 || mf != 0){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Fatal Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Error: horas/minutos inválidos! \n" + "Los minutos deben ser 00 o 30");
                                alert.showAndWait();
                            } else{

                                DatosCompartidos.setFecha(fechaSeleccionada);
                                DatosCompartidos.setHora_inicio(hi);
                                DatosCompartidos.setHora_final(hf);
                                VentanaPagar vp = new VentanaPagar();
                                vp.InitComponents();
                            }

                        }

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Fatal Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: Formato de hora incorrecto!");
                        alert.showAndWait();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fatal Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Fecha inválida!");
                    alert.showAndWait();
                }
            }catch (DateTimeParseException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fatal Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Seleccione una fecha");
                alert.showAndWait();
            }
        });

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
