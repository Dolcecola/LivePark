package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.util.List;

public class VentanaPrincipal extends Application {

    Color azul = Color.rgb(150,238,175);

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
        CapaIntermedia ci = new CapaIntermedia();
        stage.setResizable(false);

        BackgroundFill colorFondo = new BackgroundFill(azul,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();

        Button perfil = new Button("Editar Perfil");
        Button plaza = new Button("Buscar Plaza");
        Button detalles = new Button("Detalles De Mi Plaza");

        int ancho = 270;
        int alto = 40;
        int x = 50;
        int y = 350;

        perfil.setLayoutX(x);
        perfil.setLayoutY(y);
        perfil.setPrefWidth(ancho);
        perfil.setPrefHeight(alto);
        perfil.setOnMouseClicked(event -> {
            VentanaPerfil vp = new VentanaPerfil();
            vp.InitComponents();
        });

        plaza.setLayoutX(x);
        plaza.setLayoutY(y+=50);
        plaza.setPrefWidth(ancho);
        plaza.setPrefHeight(alto);
        plaza.setOnMouseClicked(event -> {
            boolean comp = ci.comprobarReserva();
            if(!comp){
                VentanaBuscarPlaza vbp = new VentanaBuscarPlaza();
                vbp.InitComponents();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Ya ha reserva estacionamiento");
                alert.showAndWait();
            }
        });

        detalles.setLayoutX(x);
        detalles.setLayoutY(y+=50);
        detalles.setPrefWidth(ancho);
        detalles.setPrefHeight(alto);
        detalles.setOnMouseClicked(event -> {

            boolean comp = ci.comprobarReserva();
            if(comp){
                VentanaDetallesPlaza vdp = new VentanaDetallesPlaza();
                vdp.InitComponents();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Debe realizar una reserva para \n" + "tener acceso a esta ventana");
                alert.showAndWait();
            }

        });

        List<Tabla> temp = ci.leerImagenes();
        ByteArrayInputStream imagen1 = temp.get(2).getImagen();

        Image image = new Image(imagen1);
        Circle circle = new Circle(185,150,120);
        Circle fondo_perfil = new Circle(185,150,120);
        fondo_perfil.setFill(Color.WHITE);

        Rectangle rectangulo = new Rectangle(300, 300);
        rectangulo.setArcWidth(55);
        rectangulo.setArcHeight(55);
        rectangulo.setX(35);
        rectangulo.setY(300);
        rectangulo.setFill(Color.WHITE);

        ImageView imageView = new ImageView(image);
        imageView.setClip(circle);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setX(85);
        imageView.setY(45);

        layout.setBackground(fondo);
        layout.getChildren().addAll(fondo_perfil,imageView,rectangulo,perfil,plaza,detalles);

        Scene ventana = new Scene(layout, 370,620);
        stage.setScene(ventana);
        stage.show();
    }
}
