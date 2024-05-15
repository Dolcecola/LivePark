package com.example.liveparkgood;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class VentanaBuscarPlaza extends Application {

    Color azul = Color.rgb(150,238,175);
    private ImageView imageView;
    private ScrollPane scrollPane;
    private Pane imagePane;

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

        CapaIntermedia ci = new CapaIntermedia();
        List<List<String>> infoPlazas = ci.leerInfoPlazas();

        Text calle = new Text();
        Text plazas = new Text();
        Text coste = new Text();
        Button reservar = new Button();
        Rectangle info = new Rectangle();
        Rectangle salir = new Rectangle();

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

        List<Tabla> temp = ci.leerImagenes();
        ByteArrayInputStream imagen1 = temp.get(3).getImagen();
        ByteArrayInputStream imagen2 = temp.get(4).getImagen();
        ByteArrayInputStream imagen3 = temp.get(4).getImagen();
        ByteArrayInputStream imagen4 = temp.get(4).getImagen();

        Image glass = new Image(imagen1);
        ImageView lupa = new ImageView(glass);
        lupa.setX(310);
        lupa.setY(40);
        lupa.setFitWidth(20);
        lupa.setFitHeight(20);

        Image ubicacion = new Image(imagen2);
        ImageView location = new ImageView(ubicacion);
        location.setX(1450);
        location.setY(1060);
        location.setFitWidth(50);
        location.setFitHeight(50);
        location.setId("Génova");

        Image ubicacion2 = new Image(imagen3);
        ImageView location2 = new ImageView(ubicacion2);
        location2.setX(760);
        location2.setY(1400);
        location2.setFitWidth(50);
        location2.setFitHeight(50);
        location2.setId("Santa");

        Image ubicacion3 = new Image(imagen4);
        ImageView location3 = new ImageView(ubicacion3);
        location3.setX(1645);
        location3.setY(575);
        location3.setFitWidth(50);
        location3.setFitHeight(50);
        location3.setId("Monte");

        lupa.setOnMouseClicked(event -> {

            String buscar = busqueda.getText();
            if(buscar!=null){
                String[] aux = buscar.split("\\s+");//Separar las palabras de la cadena
                String id = aux[0];
                if(buscar.contains("Génova")){
                    layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);

                    info.setWidth(370); // Establecer el ancho del rectángulo
                    info.setHeight(400); // Establecer la altura del rectángulo
                    info.setFill(Color.rgb(0, 0, 0, 0.5)); // Establecer el color del rectángulo con opacidad
                    info.setArcWidth(90);
                    info.setArcHeight(90);
                    info.setY(620);
                    layout.getChildren().add(info);

                    salir.setWidth(200);
                    salir.setHeight(5);
                    salir.setFill(Color.rgb(255, 255, 255, 0.6));
                    salir.setArcWidth(10);
                    salir.setArcHeight(10);
                    salir.setLayoutX(85);
                    salir.setLayoutY(280);

                    salir.setOnMouseClicked(event1 -> {
                        layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);
                    });

                    layout.getChildren().add(salir);

                    for(int i=0;i<infoPlazas.size();i++){
                        String dir = infoPlazas.get(i).get(0);
                        String pla = infoPlazas.get(i).get(1);
                        String cos = infoPlazas.get(i).get(2);

                        String[] partes = dir.split(",");
                        String primeraParte = partes[0].trim();
                        if(primeraParte.contains(id)){


                            calle.setText(dir);
                            plazas.setText("Plazas disponibles: " + pla);
                            coste.setText(cos + "€/h");

                            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
                            transition.setToY(-350); // Desplazar hacia arriba
                            info.setTranslateY(0);
                            transition.play(); // Reproducir la animación

                            reservar.setText("Reservar");
                            reservar.setId("miBoton");
                            calle.setId("miTexto");
                            plazas.setId("miTexto");
                            coste.setId("miTexto");
                            reservar.setVisible(false);
                            calle.setVisible(false);
                            plazas.setVisible(false);
                            coste.setVisible(false);
                            salir.setVisible(false);

                            Duration delay = Duration.seconds(0.5);
                            KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                                calle.setVisible(true);
                                plazas.setVisible(true);
                                reservar.setVisible(true);
                                coste.setVisible(true);
                                salir.setVisible(true);
                            });
                            Timeline timeline = new Timeline(keyFrame);
                            timeline.play();

                            plazas.setFill(Color.WHITE);
                            plazas.setLayoutX(30);
                            plazas.setLayoutY(450);
                            calle.setFill(Color.WHITE);
                            calle.setLayoutX(30);
                            calle.setLayoutY(400);
                            coste.setFill(Color.WHITE);
                            coste.setLayoutX(30);
                            coste.setLayoutY(500);
                            reservar.setLayoutX(250);
                            reservar.setLayoutY(550);
                            reservar.setOnMouseClicked(event1 -> {
                                DatosCompartidos.setId(id);
                                VentanaReserva vr = new VentanaReserva();
                                vr.InitComponents();
                            });

                            layout.getChildren().addAll(calle,plazas,reservar,coste);
                        }
                    }

                }else if(buscar.contains("Santa")){

                    layout.getChildren().removeAll(calle,plazas,reservar,coste,info, salir);

                    info.setWidth(370); // Establecer el ancho del rectángulo
                    info.setHeight(400); // Establecer la altura del rectángulo
                    info.setFill(Color.rgb(0, 0, 0, 0.5)); // Establecer el color del rectángulo con opacidad
                    info.setArcWidth(90);
                    info.setArcHeight(90);
                    info.setY(620);
                    layout.getChildren().add(info);

                    salir.setWidth(200);
                    salir.setHeight(5);
                    salir.setFill(Color.rgb(255, 255, 255, 0.6));
                    salir.setArcWidth(10);
                    salir.setArcHeight(10);
                    salir.setLayoutX(85);
                    salir.setLayoutY(280);

                    salir.setOnMouseClicked(event1 -> {
                        layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);
                    });

                    layout.getChildren().add(salir);

                    for(int i=0;i<infoPlazas.size();i++){
                        String dir = infoPlazas.get(i).get(0);
                        String pla = infoPlazas.get(i).get(1);
                        String cos = infoPlazas.get(i).get(2);

                        String[] partes = dir.split(",");
                        String primeraParte = partes[0].trim();
                        if(primeraParte.contains(id)){

                            calle.setText(dir);
                            plazas.setText("Plazas disponibles: " + pla);
                            coste.setText(cos + "€/h");

                            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
                            transition.setToY(-350); // Desplazar hacia arriba
                            info.setTranslateY(0);
                            transition.play(); // Reproducir la animación

                            reservar.setText("Reservar");
                            reservar.setId("miBoton");
                            calle.setId("miTexto");
                            plazas.setId("miTexto");
                            coste.setId("miTexto");
                            reservar.setVisible(false);
                            calle.setVisible(false);
                            plazas.setVisible(false);
                            coste.setVisible(false);
                            salir.setVisible(false);

                            Duration delay = Duration.seconds(0.5);
                            KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                                calle.setVisible(true);
                                plazas.setVisible(true);
                                reservar.setVisible(true);
                                coste.setVisible(true);
                                salir.setVisible(true);
                            });
                            Timeline timeline = new Timeline(keyFrame);
                            timeline.play();

                            plazas.setFill(Color.WHITE);
                            plazas.setLayoutX(30);
                            plazas.setLayoutY(450);
                            calle.setFill(Color.WHITE);
                            calle.setLayoutX(30);
                            calle.setLayoutY(400);
                            coste.setFill(Color.WHITE);
                            coste.setLayoutX(30);
                            coste.setLayoutY(500);
                            reservar.setLayoutX(250);
                            reservar.setLayoutY(550);
                            reservar.setOnMouseClicked(event1 -> {
                                DatosCompartidos.setId(id);
                                VentanaReserva vr = new VentanaReserva();
                                vr.InitComponents();
                            });

                            layout.getChildren().addAll(calle,plazas,reservar,coste);
                        }
                    }

                } else if(buscar.contains("Monte")){

                    layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);

                    info.setWidth(370); // Establecer el ancho del rectángulo
                    info.setHeight(400); // Establecer la altura del rectángulo
                    info.setFill(Color.rgb(0, 0, 0, 0.5)); // Establecer el color del rectángulo con opacidad
                    info.setArcWidth(90);
                    info.setArcHeight(90);
                    info.setY(620);
                    layout.getChildren().add(info);

                    salir.setWidth(200);
                    salir.setHeight(5);
                    salir.setFill(Color.rgb(255, 255, 255, 0.6));
                    salir.setArcWidth(10);
                    salir.setArcHeight(10);
                    salir.setLayoutX(85);
                    salir.setLayoutY(280);

                    salir.setOnMouseClicked(event1 -> {
                        layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);
                    });

                    layout.getChildren().add(salir);

                    for(int i=0;i<infoPlazas.size();i++){
                        String dir = infoPlazas.get(i).get(0);
                        String pla = infoPlazas.get(i).get(1);
                        String cos = infoPlazas.get(i).get(2);

                        String[] partes = dir.split(",");
                        String primeraParte = partes[0].trim();
                        if(primeraParte.contains(id)){

                            calle.setText(dir);
                            plazas.setText("Plazas disponibles: " + pla);
                            coste.setText(cos + "€/h");

                            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
                            transition.setToY(-350); // Desplazar hacia arriba
                            info.setTranslateY(0);
                            transition.play(); // Reproducir la animación

                            reservar.setText("Reservar");
                            reservar.setId("miBoton");
                            calle.setId("miTexto");
                            plazas.setId("miTexto");
                            coste.setId("miTexto");
                            reservar.setVisible(false);
                            calle.setVisible(false);
                            plazas.setVisible(false);
                            coste.setVisible(false);
                            salir.setVisible(false);

                            Duration delay = Duration.seconds(0.5);
                            KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                                calle.setVisible(true);
                                plazas.setVisible(true);
                                reservar.setVisible(true);
                                coste.setVisible(true);
                                salir.setVisible(true);
                            });
                            Timeline timeline = new Timeline(keyFrame);
                            timeline.play();

                            plazas.setFill(Color.WHITE);
                            plazas.setLayoutX(30);
                            plazas.setLayoutY(450);
                            calle.setFill(Color.WHITE);
                            calle.setLayoutX(30);
                            calle.setLayoutY(400);
                            coste.setFill(Color.WHITE);
                            coste.setLayoutX(30);
                            coste.setLayoutY(500);
                            reservar.setLayoutX(250);
                            reservar.setLayoutY(550);
                            reservar.setOnMouseClicked(event1 -> {
                                DatosCompartidos.setId(id);
                                VentanaReserva vr = new VentanaReserva();
                                vr.InitComponents();
                            });

                            layout.getChildren().addAll(calle,plazas,reservar,coste);
                        }
                    }
                }
            }
        });

        location.setOnMouseClicked(event -> {

            layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);

            info.setWidth(370); // Establecer el ancho del rectángulo
            info.setHeight(400); // Establecer la altura del rectángulo
            info.setFill(Color.rgb(0, 0, 0, 0.5)); // Establecer el color del rectángulo con opacidad
            info.setArcWidth(90);
            info.setArcHeight(90);
            info.setY(620);
            layout.getChildren().add(info);

            salir.setWidth(200);
            salir.setHeight(5);
            salir.setFill(Color.rgb(255, 255, 255, 0.6));
            salir.setArcWidth(10);
            salir.setArcHeight(10);
            salir.setLayoutX(85);
            salir.setLayoutY(280);

            salir.setOnMouseClicked(event1 -> {
                layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);
            });

            layout.getChildren().add(salir);

            String id = ((ImageView) event.getSource()).getId();
            for(int i=0;i<infoPlazas.size();i++){
                String dir = infoPlazas.get(i).get(0);
                String pla = infoPlazas.get(i).get(1);
                String cos = infoPlazas.get(i).get(2);

                String[] partes = dir.split(",");
                String primeraParte = partes[0].trim();
                if(primeraParte.contains(id)){

                    calle.setText(dir);
                    plazas.setText("Plazas disponibles: " + pla);
                    coste.setText(cos + "€/h");

                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
                    transition.setToY(-350); // Desplazar hacia arriba
                    info.setTranslateY(0);
                    transition.play(); // Reproducir la animación

                    reservar.setText("Reservar");
                    reservar.setId("miBoton");
                    calle.setId("miTexto");
                    plazas.setId("miTexto");
                    coste.setId("miTexto");
                    reservar.setVisible(false);
                    calle.setVisible(false);
                    plazas.setVisible(false);
                    coste.setVisible(false);
                    salir.setVisible(false);

                    Duration delay = Duration.seconds(0.5);
                    KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                        calle.setVisible(true);
                        plazas.setVisible(true);
                        reservar.setVisible(true);
                        coste.setVisible(true);
                        salir.setVisible(true);
                    });
                    Timeline timeline = new Timeline(keyFrame);
                    timeline.play();

                    plazas.setFill(Color.WHITE);
                    plazas.setLayoutX(30);
                    plazas.setLayoutY(450);
                    calle.setFill(Color.WHITE);
                    calle.setLayoutX(30);
                    calle.setLayoutY(400);
                    coste.setFill(Color.WHITE);
                    coste.setLayoutX(30);
                    coste.setLayoutY(500);
                    reservar.setLayoutX(250);
                    reservar.setLayoutY(550);
                    reservar.setOnMouseClicked(event1 -> {
                        DatosCompartidos.setId(id);
                        VentanaReserva vr = new VentanaReserva();
                        vr.InitComponents();
                    });

                    layout.getChildren().addAll(calle,plazas,reservar,coste);
                }
            }
        });

        location2.setOnMouseClicked(event -> {

            layout.getChildren().removeAll(calle,plazas,reservar,coste,info, salir);

            info.setWidth(370); // Establecer el ancho del rectángulo
            info.setHeight(400); // Establecer la altura del rectángulo
            info.setFill(Color.rgb(0, 0, 0, 0.5)); // Establecer el color del rectángulo con opacidad
            info.setArcWidth(90);
            info.setArcHeight(90);
            info.setY(620);
            layout.getChildren().add(info);

            salir.setWidth(200);
            salir.setHeight(5);
            salir.setFill(Color.rgb(255, 255, 255, 0.6));
            salir.setArcWidth(10);
            salir.setArcHeight(10);
            salir.setLayoutX(85);
            salir.setLayoutY(280);

            salir.setOnMouseClicked(event1 -> {
                layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);
            });

            layout.getChildren().add(salir);

            String id = ((ImageView) event.getSource()).getId();
            for(int i=0;i<infoPlazas.size();i++){
                String dir = infoPlazas.get(i).get(0);
                String pla = infoPlazas.get(i).get(1);
                String cos = infoPlazas.get(i).get(2);

                String[] partes = dir.split(",");
                String primeraParte = partes[0].trim();
                if(primeraParte.contains(id)){

                    calle.setText(dir);
                    plazas.setText("Plazas disponibles: " + pla);
                    coste.setText(cos + "€/h");

                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
                    transition.setToY(-350); // Desplazar hacia arriba
                    info.setTranslateY(0);
                    transition.play(); // Reproducir la animación

                    reservar.setText("Reservar");
                    reservar.setId("miBoton");
                    calle.setId("miTexto");
                    plazas.setId("miTexto");
                    coste.setId("miTexto");
                    reservar.setVisible(false);
                    calle.setVisible(false);
                    plazas.setVisible(false);
                    coste.setVisible(false);
                    salir.setVisible(false);

                    Duration delay = Duration.seconds(0.5);
                    KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                        calle.setVisible(true);
                        plazas.setVisible(true);
                        reservar.setVisible(true);
                        coste.setVisible(true);
                        salir.setVisible(true);
                    });
                    Timeline timeline = new Timeline(keyFrame);
                    timeline.play();

                    plazas.setFill(Color.WHITE);
                    plazas.setLayoutX(30);
                    plazas.setLayoutY(450);
                    calle.setFill(Color.WHITE);
                    calle.setLayoutX(30);
                    calle.setLayoutY(400);
                    coste.setFill(Color.WHITE);
                    coste.setLayoutX(30);
                    coste.setLayoutY(500);
                    reservar.setLayoutX(250);
                    reservar.setLayoutY(550);
                    reservar.setOnMouseClicked(event1 -> {
                        DatosCompartidos.setId(id);
                        VentanaReserva vr = new VentanaReserva();
                        vr.InitComponents();
                    });

                    layout.getChildren().addAll(calle,plazas,reservar,coste);
                }
            }
        });

        location3.setOnMouseClicked(event -> {
            layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);

            info.setWidth(370); // Establecer el ancho del rectángulo
            info.setHeight(400); // Establecer la altura del rectángulo
            info.setFill(Color.rgb(0, 0, 0, 0.5)); // Establecer el color del rectángulo con opacidad
            info.setArcWidth(90);
            info.setArcHeight(90);
            info.setY(620);
            layout.getChildren().add(info);

            salir.setWidth(200);
            salir.setHeight(5);
            salir.setFill(Color.rgb(255, 255, 255, 0.6));
            salir.setArcWidth(10);
            salir.setArcHeight(10);
            salir.setLayoutX(85);
            salir.setLayoutY(280);

            salir.setOnMouseClicked(event1 -> {
                layout.getChildren().removeAll(calle,plazas,reservar,coste,info,salir);
            });

            layout.getChildren().add(salir);

            String id = ((ImageView) event.getSource()).getId();
            for(int i=0;i<infoPlazas.size();i++){
                String dir = infoPlazas.get(i).get(0);
                String pla = infoPlazas.get(i).get(1);
                String cos = infoPlazas.get(i).get(2);

                String[] partes = dir.split(",");
                String primeraParte = partes[0].trim();
                if(primeraParte.contains(id)){

                    calle.setText(dir);
                    plazas.setText("Plazas disponibles: " + pla);
                    coste.setText(cos + "€/h");

                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), info);
                    transition.setToY(-350); // Desplazar hacia arriba
                    info.setTranslateY(0);
                    transition.play(); // Reproducir la animación

                    reservar.setText("Reservar");
                    reservar.setId("miBoton");
                    calle.setId("miTexto");
                    plazas.setId("miTexto");
                    coste.setId("miTexto");
                    reservar.setVisible(false);
                    calle.setVisible(false);
                    plazas.setVisible(false);
                    coste.setVisible(false);
                    salir.setVisible(false);

                    Duration delay = Duration.seconds(0.5);
                    KeyFrame keyFrame = new KeyFrame(delay, evento -> {
                        calle.setVisible(true);
                        plazas.setVisible(true);
                        reservar.setVisible(true);
                        coste.setVisible(true);
                        salir.setVisible(true);
                    });
                    Timeline timeline = new Timeline(keyFrame);
                    timeline.play();

                    plazas.setFill(Color.WHITE);
                    plazas.setLayoutX(30);
                    plazas.setLayoutY(450);
                    calle.setFill(Color.WHITE);
                    calle.setLayoutX(30);
                    calle.setLayoutY(400);
                    coste.setFill(Color.WHITE);
                    coste.setLayoutX(30);
                    coste.setLayoutY(500);
                    reservar.setLayoutX(250);
                    reservar.setLayoutY(550);
                    reservar.setOnMouseClicked(event1 -> {
                        DatosCompartidos.setId(id);
                        VentanaReserva vr = new VentanaReserva();
                        vr.InitComponents();
                    });

                    layout.getChildren().addAll(calle,plazas,reservar,coste);
                }
            }
        });

        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(370,620);

        double filas = 0;
        double columnas = 0;

        //Bucle para imprimir la imagenes que conforman el mapa

        for(int i=0;i<temp.size();i++){
            int id = temp.get(i).getId();

            if (String.valueOf(id).startsWith("6")) {
                ByteArrayInputStream imagen = temp.get(i).getImagen();

                Image image = new Image(imagen);
                imageView = new ImageView(image);

                imageView.setLayoutX(columnas);
                imageView.setLayoutY(filas);
                if(columnas==1726.0){
                    filas+=863.0;
                    columnas=0;
                }else{
                    columnas+=863.0;
                }

                imagePane.getChildren().add(imageView);
            }

        }

        imagePane.getChildren().add(location);
        imagePane.getChildren().add(location2);
        imagePane.getChildren().add(location3);

        scrollPane.setContent(imagePane);

        //imageView.setFitWidth(2589.0 * 0.7);
        imageView.setPreserveRatio(true); // Mantener la proporción de la imagen

        double anchoScrollPane = scrollPane.getPrefWidth();
        double altoScrollPane = scrollPane.getPrefHeight();
        double anchoImagen = 2589.0;
        double altoImagen = 2589.0;

        scrollPane.setHvalue((anchoImagen - anchoScrollPane) / (2 * anchoImagen));
        scrollPane.setVvalue((altoImagen - altoScrollPane) / (2 * altoImagen));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Oculta la barra de desplazamiento horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Oculta la barra de desplazamiento vertical


        layout.setBackground(fondo);
        layout.getChildren().addAll(scrollPane,busqueda,lupa);

        Scene ventana = new Scene(layout, 370,620);
        ventana.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(ventana);
        stage.show();
    }
}
