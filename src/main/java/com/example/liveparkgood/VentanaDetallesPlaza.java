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

import java.util.List;

public class VentanaDetallesPlaza extends Application {
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

        CapaIntermedia ci= new CapaIntermedia();
        String[] vec = ci.leerReserva();
        List<List<String>> infoPlazas = ci.leerInfoPlazas();

        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();

        int x = 150;
        int x2 = 150;
        int y = 150;

        String id = vec[0];
        String fec = vec[1];
        String hi = vec[2];
        String hf = vec[3];

        for(int i=0;i<infoPlazas.size();i++){
            String dir = infoPlazas.get(i).get(0);
            String cos = infoPlazas.get(i).get(2);
            String cont = infoPlazas.get(i).get(3);

            String[] partes = dir.split(",");
            String primeraParte = partes[0].trim();
            if(primeraParte.contains(id)){

                Text direccion = new Text(dir);
                Text fecha = new Text(fec);
                Text coste = new Text(cos + "€/h");
                Text contacto = new Text(cont);
                Text horas = new Text(hi + ":00 - " + hf + ":00");

                direccion.setId("miTexto");
                direccion.setLayoutX(x-115);
                direccion.setLayoutY(y+40);

                fecha.setId("miTexto");
                fecha.setLayoutX(x+5);
                fecha.setLayoutY(y+140);

                coste.setId("miTexto");
                coste.setLayoutX(x+18);
                coste.setLayoutY(y+240);

                contacto.setId("miTexto");
                contacto.setLayoutX(x);
                contacto.setLayoutY(y+340);

                horas.setId("miTexto");
                horas.setLayoutX(x-5);
                horas.setLayoutY(y+440);

                layout.getChildren().addAll(direccion,fecha,coste,contacto,horas);
            }
        }


        Text detalles = new Text("Detalles");
        Text direccion1 = new Text("Dirección");
        Text fecha1 = new Text("Fecha de reserva");
        Text coste1 = new Text("Coste por hora");
        Text contacto1 = new Text("Contacto");
        Text duracion = new Text("Duración");

        direccion1.setId("miTexto");
        direccion1.setLayoutX(x2);
        direccion1.setLayoutY(y);

        fecha1.setId("miTexto");
        fecha1.setLayoutX(x2-17);
        fecha1.setLayoutY(y+100);

        coste1.setId("miTexto");
        coste1.setLayoutX(x2-12);
        coste1.setLayoutY(y+200);

        contacto1.setId("miTexto");
        contacto1.setLayoutX(x2+5);
        contacto1.setLayoutY(y+300);

        duracion.setId("miTexto");
        duracion.setLayoutX(x2+5);
        duracion.setLayoutY(y+400);

        detalles.setId("reserva");
        detalles.setLayoutX(90);
        detalles.setLayoutY(80);

        layout.setBackground(fondo);
        layout.getChildren().addAll(detalles,direccion1,fecha1,coste1,contacto1,duracion);

        Scene ventana = new Scene(layout, 370,620);
        ventana.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(ventana);
        stage.show();
    }
}
