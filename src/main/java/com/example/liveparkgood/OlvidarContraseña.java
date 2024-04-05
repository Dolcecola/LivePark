package com.example.liveparkgood;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OlvidarContraseña extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane layout = new Pane();

        Text text = new Text("");
        TextField email = new TextField();

        email.setLayoutX(20);
        email.setLayoutY(60);
        email.setPrefWidth(300);
        email.setPrefHeight(30);
        email.setPromptText("Email");

        layout.getChildren().add(email);

        Scene scene = new Scene(layout,370,620);
        stage.setScene(scene);
        stage.show();
    }
}
