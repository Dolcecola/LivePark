package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VentanaDetallesPlaza extends Application {
    Color verde = Color.rgb(0,233,168);
    @Override
    public void start(Stage stage) throws Exception {

        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();

        int x = 30;
        int y = 150;

        Text detalles = new Text("Detalles");
        Text direccion1 = new Text("Direccion");
        Text fecha1 = new Text("Fecha de reserva");
        Text coste1 = new Text("Coste por hora");
        Text contacto1 = new Text("Contacto");
        Text direccion = new Text("Calle de Génova, 17, Chamberí, 28004 Madrid");
        Text fecha = new Text("12/04/2024");
        Text coste = new Text("16€/h");
        Text contacto = new Text("913 766 213");
        Text duracion = new Text("Duración");
        Text horas = new Text("10:00 - 14:00");

        direccion1.setId("miTexto");
        direccion1.setLayoutX(x);
        direccion1.setLayoutY(y);

        direccion.setId("miTexto");
        direccion.setLayoutX(x);
        direccion.setLayoutY(y+40);

        fecha1.setId("miTexto");
        fecha1.setLayoutX(x);
        fecha1.setLayoutY(y+100);

        fecha.setId("miTexto");
        fecha.setLayoutX(x);
        fecha.setLayoutY(y+140);

        coste1.setId("miTexto");
        coste1.setLayoutX(x);
        coste1.setLayoutY(y+200);

        coste.setId("miTexto");
        coste.setLayoutX(x);
        coste.setLayoutY(y+240);

        contacto1.setId("miTexto");
        contacto1.setLayoutX(x);
        contacto1.setLayoutY(y+300);

        contacto.setId("miTexto");
        contacto.setLayoutX(x);
        contacto.setLayoutY(y+340);

        duracion.setId("miTexto");
        duracion.setLayoutX(x);
        duracion.setLayoutY(y+400);

        horas.setId("miTexto");
        horas.setLayoutX(x);
        horas.setLayoutY(y+440);

        detalles.setId("reserva");
        detalles.setLayoutX(90);
        detalles.setLayoutY(80);


        layout.setBackground(fondo);
        layout.getChildren().addAll(detalles,direccion1,direccion,fecha1,fecha,coste1,coste,contacto1,contacto,duracion,horas);

        Scene ventana = new Scene(layout, 370,620);
        ventana.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(ventana);
        stage.show();
    }
}
