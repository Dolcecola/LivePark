package com.example.liveparkgood;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.ls.LSResourceResolver;

public class VentanaBuscarPlaza extends Application {

    Color azul = Color.rgb(150,238,175);
    private ImageView imageView;
    private ScrollPane scrollPane;
    private Pane imagePane;

    @Override
    public void start(Stage stage) throws Exception {

        BackgroundFill colorFondo = new BackgroundFill(azul,null,null);
        Background fondo = new Background(colorFondo);
        Pane layout = new Pane();
        imagePane = new Pane();
        TextField busqueda = new TextField();
        busqueda.setId("caja");
        busqueda.setPromptText("Buscar localización (p.e. Génova 17)");
        busqueda.setLayoutX(30);
        busqueda.setLayoutY(30);
        busqueda.setPrefWidth(310);
        busqueda.setPrefHeight(40);

        Rectangle info = new Rectangle(370,400,Color.rgb(0, 0, 0, 0.5));
        info.setArcWidth(90);
        info.setArcHeight(90);
        info.setY(620);

        Image glass = new Image("C:\\ProgramacionAvanzada\\LiveParkGood\\imagenes\\lupa.png");
        ImageView lupa = new ImageView(glass);
        lupa.setX(310);
        lupa.setY(40);
        lupa.setFitWidth(20);
        lupa.setFitHeight(20);

        Image ubicacion = new Image("C:\\ProgramacionAvanzada\\LiveParkGood\\imagenes\\marcador-de-posicion.png");
        ImageView location = new ImageView(ubicacion);
        location.setX(1530);
        location.setY(1580);
        location.setFitWidth(50);
        location.setFitHeight(50);

        location.setOnMouseClicked(event -> {

            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
            transition.setToY(-350); // Desplazar hacia arriba
            transition.play(); // Reproducir la animación

            Text calle = new Text("Calle de Génova, 17, Chamberí, 28004 Madrid");
            Text plazas = new Text("Plazas disponibles: 60");
            Button reservar = new Button("Reservar");
            reservar.setId("miBoton");
            calle.setId("miTexto");
            plazas.setId("miTexto");
            reservar.setVisible(false);
            calle.setVisible(false);
            plazas.setVisible(false);

            Duration delay = Duration.seconds(0.5);
            KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                calle.setVisible(true);
                plazas.setVisible(true);
                reservar.setVisible(true);
            });
            Timeline timeline = new Timeline(keyFrame);
            timeline.play();

            plazas.setFill(Color.WHITE);
            plazas.setLayoutX(30);
            plazas.setLayoutY(450);
            calle.setFill(Color.WHITE);
            calle.setLayoutX(30);
            calle.setLayoutY(400);
            reservar.setLayoutX(250);
            reservar.setLayoutY(550);

            layout.getChildren().add(calle);
            layout.getChildren().add(plazas);
            layout.getChildren().add(reservar);

        });

        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(370,620);

        Image image = new Image("C:\\ProgramacionAvanzada\\LiveParkGood\\imagenes\\mapaGood.png");
        imageView = new ImageView(image);

        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(location);

        scrollPane.setContent(imagePane);

        imageView.setFitWidth(image.getWidth() * 0.7);
        imageView.setPreserveRatio(true); // Mantener la proporción de la imagen

        double anchoScrollPane = scrollPane.getPrefWidth();
        double altoScrollPane = scrollPane.getPrefHeight();
        double anchoImagen = image.getWidth();
        double altoImagen = image.getHeight();

        scrollPane.setHvalue((anchoImagen - anchoScrollPane) / (2 * anchoImagen));
        scrollPane.setVvalue((altoImagen - altoScrollPane) / (2 * altoImagen));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Oculta la barra de desplazamiento horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Oculta la barra de desplazamiento vertical


        layout.setBackground(fondo);
        layout.getChildren().addAll(scrollPane,busqueda,lupa,info);

        Scene ventana = new Scene(layout, 370,620);
        ventana.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(ventana);
        stage.show();
    }
}
