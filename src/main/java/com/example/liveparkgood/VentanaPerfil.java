package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.util.List;

public class VentanaPerfil extends Application {

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

        TextField email = new TextField();
        TextField telefono = new TextField();
        Button actualizar = new Button("Actualizar");

        CapaIntermedia ci = new CapaIntermedia();
        List<Tabla> temp = ci.leerImagenes();
        ByteArrayInputStream imagen1 = temp.get(2).getImagen();

        Image image = new Image(imagen1);
        Circle circle = new Circle(185,150,120);
        Circle fondo_perfil = new Circle(185,150,120);
        fondo_perfil.setFill(Color.WHITE);

        int x = 30;
        int y = 50;
        int w = 310;
        int h = 35;

        email.setPromptText("Nuevo email");
        email.setLayoutX(x);
        email.setLayoutY(y+300);
        email.setPrefWidth(w);
        email.setPrefHeight(h);

        telefono.setPromptText("Nuevo teléfono");
        telefono.setLayoutX(x);
        telefono.setLayoutY(y+350);
        telefono.setPrefWidth(w);
        telefono.setPrefHeight(h);

        actualizar.setLayoutX(x);
        actualizar.setLayoutY(y+500);
        actualizar.setPrefWidth(w);
        actualizar.setPrefHeight(h);
        actualizar.setOnMouseClicked(event -> {
            String e = email.getText();
            String t = telefono.getText();

            boolean comp = ci.actualizarDatosUsuario(e,t);
            if(comp){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Campos actualizados");
                alert.showAndWait();
            }

        });

        ImageView imageView = new ImageView(image);
        imageView.setClip(circle);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setX(85);
        imageView.setY(45);

        layout.setBackground(fondo);
        layout.getChildren().addAll(email,telefono,actualizar,fondo_perfil,imageView);

        Scene ventana = new Scene(layout, 370,620);
        ventana.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(ventana);
        stage.show();
    }
}
