package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
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

public class VentanaPerfil extends Application {

    Color verde = Color.rgb(0,233,168);
    @Override
    public void start(Stage stage) throws Exception {

        BackgroundFill colorFondo = new BackgroundFill(verde,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();

        TextField email = new TextField();
        TextField telefono = new TextField();
        Button actualizar = new Button("Actualizar");

        Image image = new Image("C:\\ProgramacionAvanzada\\LiveParkGood\\imagenes\\monstruo.png");
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
