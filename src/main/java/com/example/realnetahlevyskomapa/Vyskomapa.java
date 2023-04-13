package com.example.realnetahlevyskomapa;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Vyskomapa extends Application {
    //velikost sceny

    public static int size = 800;
    public static int velikostMapy = 800;
    public static int bodSize = 2;
    public static int schodek = 500;
    public static int MAX = 10000;
    public static int MIN = 0;
    public static int velikostHorizontuY = 500;
    public static int velikostHorizontuX = velikostMapy;
    public static int opak = 0;

    /**
     * od chat gpt tohle
     */
    // Get the default toolkit
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    // Get the screen size
    Dimension screenSize = toolkit.getScreenSize();

    // Extract the height
    int screenHeight = screenSize.height;

    Bod[][] MAPA = new Bod[velikostMapy /bodSize][velikostMapy /bodSize];

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        Image tapeta = new Image("C:\\Users\\Felix\\OneDrive\\Obrázky\\odpad\\TapetaNaAplikaceRocnikovka2Rocnik.PNG");
        ImageView imageView = new ImageView(tapeta);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);


        Pane rootNastavovaciScena = new Pane();
        Scene sceneNastavovaciScena = new Scene(rootNastavovaciScena, size, size);

        Pane rootNastaveni = new Pane();
        Scene sceneNastaveni = new Scene(rootNastaveni, size, size);

        Pane rootPrvniOkno = new Pane();
        Scene scenaPrvniOkno = new Scene(rootPrvniOkno, size,size);

        Pane rootKonvertujMapu = new Pane();
        Scene scenaKonvertujMapu = new Scene(rootKonvertujMapu, size, size);


        Button buttonPrvniGenerujMapu = new Button("GENERUJ MAPU");
        buttonPrvniGenerujMapu.setPrefSize(size/2,size/6);
        buttonPrvniGenerujMapu.setLayoutY(size/2-buttonPrvniGenerujMapu.getPrefHeight()*2-20);
        buttonPrvniGenerujMapu.setLayoutX(size/2-buttonPrvniGenerujMapu.getPrefWidth()/2);

        Button buttonPrvniKonvertujMapu = new Button("KONVERTUJ MAPU");
        buttonPrvniKonvertujMapu.setPrefSize(size/2,size/6);
        buttonPrvniKonvertujMapu.setLayoutY(size/2-buttonPrvniKonvertujMapu.getPrefHeight());
        buttonPrvniKonvertujMapu.setLayoutX(size/2-buttonPrvniKonvertujMapu.getPrefWidth()/2);

        Button buttonNastaveni = new Button("NASTAVENI");
        buttonNastaveni.setPrefSize(size/2,size/6);
        buttonNastaveni.setLayoutY(size/2+20);
        buttonNastaveni.setLayoutX(size/2-buttonNastaveni.getPrefWidth()/2);


        buttonPrvniGenerujMapu.setOnMouseClicked(e->{

            oknoNaGenerovaniMapy(rootNastavovaciScena, stage, sceneNastavovaciScena, scenaPrvniOkno);
            stage.setScene(sceneNastavovaciScena);

        });

        buttonPrvniKonvertujMapu.setOnMouseClicked(e->{
            oknoKonvertujMapu(rootKonvertujMapu, stage, scenaKonvertujMapu, scenaPrvniOkno);
            stage.setScene(scenaKonvertujMapu);
        });

        buttonNastaveni.setOnMouseClicked(e->{
            oknoNastaveni(rootNastaveni, stage, sceneNastaveni, scenaPrvniOkno);
            stage.setScene(sceneNastaveni);
        });


        rootPrvniOkno.getChildren().addAll(imageView, buttonPrvniGenerujMapu, buttonPrvniKonvertujMapu, buttonNastaveni );



        rootPrvniOkno.setPrefSize(size,size);

        stage.setScene(scenaPrvniOkno);
        stage.show();

    }

    public void oknoKonvertujMapu(Pane root, Stage stage, Scene scene, Scene scenaPrvniOkno){

        Image tapeta = new Image("C:\\Users\\Felix\\OneDrive\\Obrázky\\odpad\\TapetaAmerikaHoryRocnikovka2Rocnik.PNG");
        ImageView imageView = new ImageView(tapeta);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        Hyperlink linkVyskomapa = new Hyperlink();
        linkVyskomapa.setText("vyskomapa ze ktere můžete brát snímky");
        linkVyskomapa.setPrefSize(size/4, size/6);
        linkVyskomapa.setLayoutX(size/2-linkVyskomapa.getPrefWidth()/2);
        linkVyskomapa.setLayoutY(50);
        linkVyskomapa.setOnAction(e->{
            getHostServices().showDocument("https://en-gb.topographic-map.com/map-4d9jnh/The-World/?center=50.04391%2C14.39484&zoom=10&base=2");
        });

        Button buttonZpet = new Button("ZPET");
        buttonZpet.setPrefSize(size/5, size/5);
        buttonZpet.setLayoutX(size-buttonZpet.getPrefWidth() - size/14);
        buttonZpet.setLayoutY(size/12);
        buttonZpet.setOnMouseClicked(e->{
            stage.setScene(scenaPrvniOkno);
        });

        root.getChildren().addAll(imageView, linkVyskomapa, buttonZpet);
        stage.setScene(scene);
        stage.show();

    }

    public void oknoNaGenerovaniMapy(Pane root, Stage stage, Scene scene, Scene scenaPrvniOkno){

        Image tapeta = new Image("C:\\Users\\Felix\\OneDrive\\Obrázky\\odpad\\TapetaHimalajeRocnikovaPrace2Rocnik.PNG");
        ImageView imageView = new ImageView(tapeta);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        Pane rootMapa2 = new Pane();
        Stage stageMapa2 = new Stage();
        stageMapa2.setResizable(false);
        Scene sceneMapa2 = new Scene(rootMapa2, velikostMapy+200, velikostMapy);

        Pane rootMapa = new Pane();
        Stage stageMapa = new Stage();
        stageMapa.setResizable(false);
        Scene sceneMapa = new Scene(rootMapa, velikostMapy+200, velikostMapy);

        Pane rootHorizont = new Pane();
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX, velikostHorizontuY);




        Button buttonGenerujMapu2 = new Button("Genruj Mapu");
        buttonGenerujMapu2.setPrefSize(size/4, size/6);
        buttonGenerujMapu2.setLayoutX(size/14);
        buttonGenerujMapu2.setLayoutY(size/12);
        buttonGenerujMapu2.setOnMouseClicked(e->{
            generujMapu2(rootMapa2);
            stageMapa2.show();
        });

        Button buttonGenerujMapu = new Button("GENERUJ MAPU");
        buttonGenerujMapu.setPrefSize(size/4, size/6);
        buttonGenerujMapu.setLayoutX(size/14);
        buttonGenerujMapu.setLayoutY(size/6 + buttonGenerujMapu.getPrefHeight());
        buttonGenerujMapu.setOnMouseClicked(e->{
            generujMapu(rootMapa);
            stageMapa.show();
        });


        Button buttonGenerujHorizont = new Button("generuj horizont");
        buttonGenerujHorizont.setPrefSize(size/4, size/6);
        buttonGenerujHorizont.setLayoutX(size/14);
        buttonGenerujHorizont.setLayoutY(size/6 + buttonGenerujHorizont.getPrefHeight() + buttonGenerujMapu.getPrefHeight() + 50);
        buttonGenerujHorizont.setOnMouseClicked(e->{
            generujHorizont(rootHorizont);
            stageHorizont.show();
        });

        Button buttonZpet = new Button("ZPET");
        buttonZpet.setPrefSize(size/5, size/5);
        buttonZpet.setLayoutX(size-buttonZpet.getPrefWidth() - size/14);
        buttonZpet.setLayoutY(size/12);
        buttonZpet.setOnMouseClicked(e->{
            stage.setScene(scenaPrvniOkno);
        });


        root.getChildren().addAll(imageView, buttonGenerujMapu2, buttonGenerujHorizont, buttonZpet, buttonGenerujMapu);

        rootMapa2.setPrefSize(velikostMapy, velikostMapy);
        stageMapa2.setScene(sceneMapa2);

        rootMapa.setPrefSize(velikostMapy, velikostMapy);
        stageMapa.setScene(sceneMapa);

        rootHorizont.setPrefSize(velikostHorizontuX,velikostHorizontuY);
        stageHorizont.setScene(sceneHorizont);

        stage.setScene(scene);
        stage.show();
    }

    public void oknoNastaveni(Pane root, Stage stage, Scene scene, Scene scenaPrvniOkno){
        final boolean plus = true;
        final boolean minus = false;
        double plusTlacitkaOsaX = 50+size/2;


        //Urcovani velikosti jednoho bodu
        Label labelVelikostBodu = new Label("Velikost Bodu (px): ");
        labelVelikostBodu.setLayoutX(size/8);
        labelVelikostBodu.setLayoutY(size/8);
        Font currentFont = labelVelikostBodu.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 5);
        labelVelikostBodu.setFont(newFont);

        Label hodnotaVelikostBodu = new Label(String.valueOf(bodSize));
        hodnotaVelikostBodu.setFont(newFont);
        hodnotaVelikostBodu.setLayoutY(size/8);
        hodnotaVelikostBodu.setLayoutX(size/2-50);

        Timeline timelineMinusVelikostBodu = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaVelikostBodu.setText(String.valueOf(zmenaVelikostiMapy(minus)));
        }));
        timelineMinusVelikostBodu.setCycleCount(Timeline.INDEFINITE);

        Timeline timelinePlusVelikostBodu = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaVelikostBodu.setText(String.valueOf(zmenaVelikostiMapy(plus)));
        }));
        timelinePlusVelikostBodu.setCycleCount(Timeline.INDEFINITE);

        Button plusVelikostBodu = new Button("+");
        plusVelikostBodu.setLayoutY(size/8);
        plusVelikostBodu.setLayoutX(plusTlacitkaOsaX);
        plusVelikostBodu.setPrefSize(30,30);
        plusVelikostBodu.setOnMouseClicked(e->{
            hodnotaVelikostBodu.setText(String.valueOf(pocitadloBodSize(plus)));
        });


        double minusTlacitkaOsaX = 50+size/2+plusVelikostBodu.getPrefWidth();
        Button minusVelikostBodu = new Button("-");
        minusVelikostBodu.setLayoutY(size/8);
        minusVelikostBodu.setLayoutX(minusTlacitkaOsaX);
        minusVelikostBodu.setPrefSize(30,30);
        minusVelikostBodu.setOnMouseClicked(e->{
            hodnotaVelikostBodu.setText(String.valueOf(pocitadloBodSize(minus)));
        });


        //Urcovani schodku
        Label labelVelikostSchodku = new Label("Koeficient strmosti: ");
        labelVelikostSchodku.setLayoutX(size/8);
        labelVelikostSchodku.setLayoutY(size/8 + labelVelikostSchodku.getHeight() + 50);
        labelVelikostSchodku.setFont(newFont);

        Label hodnotaVelikostSchodku = new Label(String.valueOf(schodek));
        hodnotaVelikostSchodku.setFont(newFont);
        hodnotaVelikostSchodku.setLayoutY(size/8 + labelVelikostSchodku.getHeight() + 50);
        hodnotaVelikostSchodku.setLayoutX(size/2-50);

        Timeline timelineMinusVelikostSchodku = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaVelikostSchodku.setText(String.valueOf(zmenaVelikostiMapy(minus)));
        }));
        timelineMinusVelikostSchodku.setCycleCount(Timeline.INDEFINITE);

        Timeline timelinePlusVelikostSchodku = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaVelikostSchodku.setText(String.valueOf(zmenaVelikostiMapy(plus)));
        }));
        timelinePlusVelikostSchodku.setCycleCount(Timeline.INDEFINITE);

        Button plusVelikostSchodku = new Button("+");
        plusVelikostSchodku.setLayoutY(size/8 + labelVelikostSchodku.getHeight() + 50);
        plusVelikostSchodku.setLayoutX(plusTlacitkaOsaX);
        plusVelikostSchodku.setPrefSize(30,30);
        plusVelikostSchodku.setOnMouseClicked(e->{
            hodnotaVelikostSchodku.setText(String.valueOf(zmenaSchodku(plus)));
        });

        Button minusVelikostSchodku = new Button("-");
        minusVelikostSchodku.setLayoutY(size/8 + labelVelikostSchodku.getHeight() + 50);
        minusVelikostSchodku.setLayoutX(minusTlacitkaOsaX);
        minusVelikostSchodku.setPrefSize(30,30);
        minusVelikostSchodku.setOnMouseClicked(e->{
            hodnotaVelikostSchodku.setText(String.valueOf(zmenaSchodku(minus)));
        });


        //Urcovani maximalni vysky
        Label labelMaxVyska = new Label("Maximalni Vyska: ");
        labelMaxVyska.setLayoutX(size/8);
        labelMaxVyska.setLayoutY(labelVelikostSchodku.getLayoutY() + labelMaxVyska.getHeight() + 50);
        labelMaxVyska.setFont(newFont);

        Label hodnotaMaxVyska = new Label(String.valueOf(MAX));
        hodnotaMaxVyska.setFont(newFont);
        hodnotaMaxVyska.setLayoutY(labelVelikostSchodku.getLayoutY() + labelMaxVyska.getHeight() + 50);
        hodnotaMaxVyska.setLayoutX(size/2-50);

        Timeline timelineMinusMaxVyska = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaMaxVyska.setText(String.valueOf(zmenaVelikostiMapy(minus)));
        }));
        timelineMinusMaxVyska.setCycleCount(Timeline.INDEFINITE);

        Timeline timelinePlusMaxVyska = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaMaxVyska.setText(String.valueOf(zmenaVelikostiMapy(plus)));
        }));
        timelinePlusMaxVyska.setCycleCount(Timeline.INDEFINITE);

        Button plusMaxVyska = new Button("+");
        plusMaxVyska.setLayoutY(labelVelikostSchodku.getLayoutY() + labelMaxVyska.getHeight() + 50);
        plusMaxVyska.setLayoutX(plusTlacitkaOsaX);
        plusMaxVyska.setPrefSize(30,30);
        plusMaxVyska.setOnMouseClicked(e->hodnotaMaxVyska.setText(String.valueOf(zmenaMAX(plus))));

        Button minusMaxVyska = new Button("-");
        minusMaxVyska.setLayoutY(labelVelikostSchodku.getLayoutY() + labelMaxVyska.getHeight() + 50);
        minusMaxVyska.setLayoutX(minusTlacitkaOsaX);
        minusMaxVyska.setPrefSize(30,30);
        minusMaxVyska.setOnMouseClicked(e->hodnotaMaxVyska.setText(String.valueOf(zmenaMAX(minus))));


        //Urcovani minimalni vysky
        Label labelMinVyska = new Label("Minimalni Vyska: ");
        labelMinVyska.setLayoutX(size/8);
        labelMinVyska.setLayoutY(labelMaxVyska.getLayoutY() + labelMinVyska.getHeight() + 50);
        labelMinVyska.setFont(newFont);

        Label hodnotaMinVyska = new Label(String.valueOf(MIN));
        hodnotaMinVyska.setFont(newFont);
        hodnotaMinVyska.setLayoutY(labelMaxVyska.getLayoutY() + labelMinVyska.getHeight() + 50);
        hodnotaMinVyska.setLayoutX(size/2-50);

        Timeline timelineMinusMinVyska = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaMinVyska.setText(String.valueOf(zmenaVelikostiMapy(minus)));
        }));
        timelineMinusMinVyska.setCycleCount(Timeline.INDEFINITE);

        Timeline timelinePlusMinVyska = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaMinVyska.setText(String.valueOf(zmenaVelikostiMapy(plus)));
        }));
        timelinePlusMinVyska.setCycleCount(Timeline.INDEFINITE);

        Button plusMinVyska = new Button("+");
        plusMinVyska.setLayoutY(labelMaxVyska.getLayoutY() + labelMinVyska.getHeight() + 50);
        plusMinVyska.setLayoutX(plusTlacitkaOsaX);
        plusMinVyska.setPrefSize(30,30);
        plusMinVyska.setOnMouseClicked(e->hodnotaMinVyska.setText(String.valueOf(zmenaMIN(plus))));


        Button minusMinVyska = new Button("-");
        minusMinVyska.setLayoutY(labelMaxVyska.getLayoutY() + labelMinVyska.getHeight() + 50);
        minusMinVyska.setLayoutX(minusTlacitkaOsaX);
        minusMinVyska.setPrefSize(30,30);
        minusMinVyska.setOnMouseClicked(e->hodnotaMinVyska.setText(String.valueOf(zmenaMIN(minus))));


        //Urcovani velikosti mapy
        Label labelVelikostMapy = new Label("Velikost mapy (bod): ");
        labelVelikostMapy.setLayoutX(size/8);
        labelVelikostMapy.setLayoutY(labelMinVyska.getLayoutY() + labelVelikostMapy.getHeight() + 50);
        labelVelikostMapy.setFont(newFont);

        Label hodnotaVelikostiMapy = new Label(String.valueOf(velikostMapy));
        hodnotaVelikostiMapy.setFont(newFont);
        hodnotaVelikostiMapy.setLayoutY(labelMinVyska.getLayoutY() + labelVelikostMapy.getHeight() + 50);
        hodnotaVelikostiMapy.setLayoutX(size/2-50);
/*
        Timeline timelineMinusVelikostMapy = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaVelikostiMapy.setText(String.valueOf(zmenaVelikostiMapy(minus)));
        }));
        timelineMinusVelikostMapy.setCycleCount(Timeline.INDEFINITE);

        Timeline timelinePlusVelikostMapy = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
            hodnotaVelikostiMapy.setText(String.valueOf(zmenaVelikostiMapy(plus)));
        }));

        timelinePlusVelikostMapy.setCycleCount(Timeline.INDEFINITE);

 */

        Button plusVelikostMapy = new Button("+");
        plusVelikostMapy.setLayoutY(labelMinVyska.getLayoutY() + labelVelikostMapy.getHeight() + 50);
        plusVelikostMapy.setLayoutX(plusTlacitkaOsaX);
        plusVelikostMapy.setPrefSize(30,30);
        plusVelikostMapy.setOnMouseClicked(e->hodnotaVelikostiMapy.setText(String.valueOf(zmenaVelikostiMapy(plus))));
        /*
        plusVelikostMapy.setOnMousePressed(e-> timelinePlusVelikostMapy.play());
        plusVelikostMapy.setOnMouseReleased(event -> timelinePlusVelikostMapy.stop());
        */
        Button minusVelikostMapy = new Button("-");
        minusVelikostMapy.setLayoutY(labelMinVyska.getLayoutY() + labelVelikostMapy.getHeight() + 50);
        minusVelikostMapy.setLayoutX(minusTlacitkaOsaX);
        minusVelikostMapy.setPrefSize(30,30);
        minusVelikostMapy.setOnMouseClicked(e->hodnotaVelikostiMapy.setText(String.valueOf(zmenaVelikostiMapy(minus))));
        /*
        minusVelikostMapy.setOnMousePressed(e-> timelineMinusVelikostMapy.play());
        minusVelikostMapy.setOnMouseReleased(event -> timelineMinusVelikostMapy.stop());
        */



        //Tlacitko zpet na hlavni menu
        Button buttonZpet = new Button("ZPET");
        buttonZpet.setPrefSize(size/7, size/7);
        buttonZpet.setLayoutX(size-buttonZpet.getPrefWidth() - 30);
        buttonZpet.setLayoutY(30);
        buttonZpet.setOnMouseClicked(e->{
            root.getChildren().removeAll(hodnotaVelikostBodu, hodnotaVelikostSchodku, hodnotaMaxVyska, hodnotaMinVyska, hodnotaVelikostiMapy);
            stage.setScene(scenaPrvniOkno);

        });

        root.getChildren().addAll(buttonZpet, labelVelikostBodu, labelVelikostSchodku,
                labelMaxVyska, labelMinVyska, labelVelikostMapy, plusVelikostBodu, minusVelikostBodu, plusMaxVyska, plusMinVyska, plusVelikostMapy, plusVelikostSchodku,
                minusVelikostSchodku, minusMaxVyska, minusMinVyska, minusVelikostMapy
                ,hodnotaVelikostBodu, hodnotaVelikostSchodku, hodnotaMaxVyska, hodnotaMinVyska, hodnotaVelikostiMapy);

        stage.setScene(scene);
        stage.show();
    }

    public int zmenaSchodku(boolean jePlus){
        if (jePlus){
            if (schodek <= 950){
                schodek+=50;
            }
        }else{
            if (schodek>=150){
                schodek-=50;
            }
        }
        return schodek;
    }

    public int zmenaMAX(boolean jePlus){
        if (jePlus){
            if (MAX + 50<= 10000){
                MAX+=100;
            }
        }else{
            if (MAX-schodek>MIN)
                MAX-=100;
            }
        return MAX;
    }

    public int zmenaMIN(boolean jePlus){
        if (jePlus){
            if (MIN +100<=900){
                MIN+=100;
            }
        }else{
            if (MIN+50>=200)
                MIN-=100;
        }
        return MIN;
    }


    int pocitadloVelikostMapy = 14;
    public int zmenaVelikostiMapy(boolean jePlus){
        if (jePlus){
            if (pocitadloVelikostMapy!=15){
                pocitadloVelikostMapy++;
            }
        }else{
            if (pocitadloVelikostMapy!=0)
                pocitadloVelikostMapy--;
        }

        if (pocitadloVelikostMapy == 0){
            velikostMapy = 10;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 1){
            velikostMapy = 16;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 2){
            velikostMapy = 20;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 3){
            velikostMapy = 25;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 4){
            velikostMapy = 32;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 5){
            velikostMapy = 40;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 6){
            velikostMapy = 50;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 7){
            velikostMapy = 64;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 8){
            velikostMapy = 80;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 9){
            velikostMapy = 160;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 10){
            velikostMapy = 200;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 11){
            velikostMapy = 320;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 12){
            velikostMapy = 400;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 13){
            velikostMapy = 500;
            return velikostMapy;
        }else if (pocitadloVelikostMapy == 14){
            velikostMapy = 800;
            return velikostMapy;
        }else{
            velikostMapy = 1000;
            return velikostMapy;
        }
    }

    private int pocitadloBodSize = 0;
    public int pocitadloBodSize(boolean jePlus){

            if (jePlus){
                if (pocitadloBodSize != 8){
                    pocitadloBodSize++;
                }
            }else{
                if (pocitadloBodSize != 0){
                    pocitadloBodSize--;
                }
            }

        if (pocitadloBodSize == 0){
            bodSize = 1;
            return 1;
        }else if (pocitadloBodSize == 1){
            bodSize = 2;
            return 2;
        }else if (pocitadloBodSize == 2){
            bodSize = 4;
            return 4;
        }else if (pocitadloBodSize == 3){
            bodSize = 5;
            return 5;
        }else if (pocitadloBodSize == 4){
            bodSize = 8;
            return 8;
        }else if (pocitadloBodSize == 5){
            bodSize = 10;
            return 10;
        }else if (pocitadloBodSize == 6){
            bodSize = 16;
            return 16;
        }else if (pocitadloBodSize == 7){
            bodSize = 20;
            return 20;
        }else{
            bodSize = 25;
            return 25;
        }

    }

    public int vytvorVysku(int max, int min){
        Random random = new Random();
        return random.nextInt(min,max);
    }

    public void generujMapu(Pane root){
        Mapa mapa = new Mapa();
        mapa.generujMapu(root);
    }

    public void generujMapu2(Pane root)  {
        Mapa mapa = new Mapa();
        mapa.generujMapu2(root);
    }
/*
    public void generujMapu(Pane root){

        int o2DoLeva;
        int nahoreVpravo;
        int nahore;
        int vlevo;
        int nahoreVlevo;
        int vpravo;
        int dole;
        int doleVpravo;
        int doleVlevo;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;

        Bod[][] mapa = new Bod[velikostMapy /bodSize][velikostMapy /bodSize];
        Vyska zakladniVyska = new Vyska(schodek);

        for (int k = 0; k<opak; k++){

            if (k==0){
                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                            mapa[i][j] = zakladniBod;

                        }else if (i==0){

                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= 1){

                                o2DoLeva = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                                prumer = (vlevo + o2DoLeva )/2;
                                max = prumer + schodek;
                                min = prumer - schodek;


                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }


                                vyskaI = vytvorVysku(max,min);

                            }else{
                                max = vlevo+schodek;
                                min = vlevo-schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }


                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            if (j!= mapa.length-1 && j!= mapa.length){
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahoreVpravo + nahore)/2;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }else{
                                prumer = nahore;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("-------------------------------------------------------------"+i);
                            //System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= mapa.length&& j!= mapa.length-1){

                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahore + vlevo + nahoreVlevo + nahoreVpravo)/4;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }
                                vyskaI = vytvorVysku(max,min);

                            }else{

                                prumer = (nahore + nahoreVlevo + vlevo)/3;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }else{

                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();

                            prumer = (dole + vpravo + doleVpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                        }else if (i==0){


                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            prumer = (dole + doleVlevo + vlevo)/3;

                            if (j!= mapa.length-1 && j!= mapa.length){

                                //vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (dole + doleVlevo + vlevo  + doleVpravo)/4;

                            }

                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize,j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                            System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVpravo + vpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (i!=mapa.length && i!= mapa.length-1){
                                dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (nahore + nahoreVpravo + vpravo + dole + doleVpravo)/5;
                                max = prumer + schodek;
                                min = prumer - schodek;
                            }

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            root.getChildren().addAll(bod);
                            System.out.println("-------------------------------------------------------------"+i);
                            System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (i!= mapa.length&& i!=mapa.length-1)  {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo)/5;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            if (j!= mapa.length-1 &&j!= mapa.length){

                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();

                                prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo + doleVpravo + nahoreVpravo + vpravo)/8;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = 0;
                                    count++;
                                }else if(max>MAX){
                                    max = 1000;
                                    count++;
                                }

                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            root.getChildren().addAll(bod);
                            System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }

        }
        System.out.println("konec " + "count = " + count);


    }
*/
    public void generujMapuSKontrolou(Pane root){

        int o2DoLeva;
        int nahoreVpravo;
        int nahore;
        int vlevo;
        int nahoreVlevo;
        int vpravo;
        int dole;
        int doleVpravo;
        int doleVlevo;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;


        Bod[][] mapa = new Bod[velikostMapy /bodSize][velikostMapy /bodSize];
        Vyska zakladniVyska = new Vyska(schodek);

        System.out.println("zacatek" + mapa.length);

        for (int k = 0; k<opak; k++){

            if (k==0){
                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                            mapa[i][j] = zakladniBod;

                        }else if (i==0){

                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= 1){

                                o2DoLeva = mapa[i][j-2].getVyska().getvalueOfVyska();  // o 2 doleva
                                prumer = (vlevo + o2DoLeva )/2;
                                max = prumer + schodek;
                                min = prumer - schodek;


                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }


                                vyskaI = vytvorVysku(max,min);

                            }else{
                                max = vlevo+schodek;
                                min = vlevo-schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }


                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            if (j!= mapa.length-1 && j!= mapa.length){
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahoreVpravo + nahore)/2;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }else{
                                prumer = nahore;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("-------------------------------------------------------------"+i);
                            //System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                            if (j!= mapa.length&& j!= mapa.length-1){

                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                                prumer = (nahore + vlevo + nahoreVlevo + nahoreVpravo)/4;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }
                                vyskaI = vytvorVysku(max,min);

                            }else{

                                prumer = (nahore + nahoreVlevo + vlevo)/3;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = MIN;
                                    count++;
                                }else if(max>MAX){
                                    max = MAX;
                                    count++;
                                }

                                vyskaI = vytvorVysku(max,min);
                            }

                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            //root.getChildren().addAll(bod);
                            //System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }else{

                for (int i = 0; i<mapa.length; i++){

                    for (int j =0; j<mapa.length; j++){

                        if (i==0 && j==0){

                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();

                            prumer = (dole + vpravo + doleVpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                        }else if (i==0){


                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            prumer = (dole + doleVlevo + vlevo)/3;

                            if (j!= mapa.length-1 && j!= mapa.length){

                                //vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (dole + doleVlevo + vlevo /*+ vpravo*/ + doleVpravo)/5;

                            }

                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize,j*bodSize,vyska);
                            mapa[i][j]=bod;
                            root.getChildren().add(bod);

                            System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (j==0){

                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                            vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVpravo + vpravo)/3;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (i!=mapa.length && i!= mapa.length-1){
                                dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (nahore + nahoreVpravo + vpravo + dole + doleVpravo)/5;
                                max = prumer + schodek;
                                min = prumer - schodek;
                            }

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            vyskaI = vytvorVysku(max,min);

                            Vyska vyska = new Vyska(vyskaI);

                            Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                            mapa[i][j] = bod;
                            root.getChildren().addAll(bod);
                            System.out.println("-------------------------------------------------------------"+i);
                            System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

                        }else if (i!= mapa.length&& i!=mapa.length-1)  {

                            nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                            nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                            vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                            dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                            doleVlevo = mapa[i+1][j-1].getVyska().getvalueOfVyska();


                            prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo)/5;
                            max = prumer + schodek;
                            min = prumer - schodek;

                            if (min<MIN){
                                min = MIN;
                                count++;
                            }else if(max>MAX){
                                max = MAX;
                                count++;
                            }

                            if (j!= mapa.length-1 &&j!= mapa.length){

                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();

                                prumer = (nahore + nahoreVlevo + dole + doleVlevo + vlevo + doleVpravo + nahoreVpravo + vpravo)/8;
                                max = prumer + schodek;
                                min = prumer - schodek;

                                if (min<MIN){
                                    min = 0;
                                    count++;
                                }else if(max>MAX){
                                    max = 1000;
                                    count++;
                                }

                            }

                            vyskaI = vytvorVysku(max,min);
                            Vyska vyska = new Vyska(vyskaI);
                            Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                            mapa[i][j]=bod;
                            root.getChildren().addAll(bod);
                            System.out.println("ostatvni body: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);
                        }
                    }
                }
            }

        }
    }

    public Bod[][] generujMapuHorizont(){

        //body ze kterych budu brat hodnoty
        int nahoreVlevo1;
        int nahoreVlevo2;
        int nahore;
        int nahorevpravo1;
        int nahoreVpravo2;
        int vlevo2;
        int vlevo;
        int vpravo1;
        int vpravo2;
        int doleVlevo1;
        int doleVlevo2;
        int dole;
        int doleVpravo1;
        int doleVpravo2;

        int vyskaI;
        int prumer;
        int max;
        int min;
        int count = 0;
        int pocet = 0;
        int x;
        int prumernaVyska = schodek;


        Bod[][] mapa = new Bod[velikostMapy /bodSize][velikostMapy /bodSize];
        Vyska zakladniVyska = new Vyska(schodek);
        int akt;
        //Projidzi okno na ose x
        for (int i = 0; i<mapa.length; i++){

            // projizdi pole na ose y
            for (int j =0; j<mapa.length; j++){

                //jestli je to uplne vlevo nahore
                if (i==0 && j==0){

                    //Zakladame bod a u mistujeme ho do pole
                    Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                    mapa[i][j] = zakladniBod;



                    //jestli to je uplne noahore
                }else if (i==0){

                    //zjisti jakou vysku ma bod nalevo
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= 1){

                        //zjisti jakou vysku ma bod o 2 doleva a udela prumer tech 2 vysek
                        vlevo2 = mapa[i][j-2].getVyska().getvalueOfVyska();
                        prumer = (vlevo + vlevo2 )/2;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        //vyhodi hodnoty ktere nejsou mozne
                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        //vytvori vysku
                        vyskaI = vytvorVysku(max,min);

                    }else{

                        max = vlevo+schodek;
                        min = vlevo-schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }


                    Vyska vyska = new Vyska(vyskaI);

                    Bod bod = new Bod(i*bodSize, j*bodSize, vyska);

                    prumernaVyska += bod.getVyska().getvalueOfVyska();
                    pocet++;

                    mapa[i][j] = bod;



                }else if (j==0){

                    prumernaVyska = schodek;
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    if (j!= mapa.length-1 && j!= mapa.length){

                        nahorevpravo1 = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahorevpravo1 + nahore)/2;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }else{
                        prumer = nahore;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }

                    Vyska vyska = new Vyska(vyskaI);

                    Bod bod = new Bod(i*bodSize,j*bodSize, vyska);
                    mapa[i][j] = bod;

                    prumernaVyska += bod.getVyska().getvalueOfVyska();
                    pocet++;



                }else {

                    nahoreVlevo1 = mapa[i-1][j-1].getVyska().getvalueOfVyska();//diagonalne nahore vlevo
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();  //nahore
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();  //nalevo

                    if (j!= mapa.length&& j!= mapa.length-1){

                        nahorevpravo1 = mapa[i-1][j+1].getVyska().getvalueOfVyska();//diagonalne nahore napravo
                        prumer = (nahore + vlevo + nahoreVlevo1 + nahorevpravo1)/4;

                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){

                            x = ThreadLocalRandom.current().nextInt(MIN,  max);

                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }
                        vyskaI = vytvorVysku(max,min);

                    }else{

                        prumer = (nahore + nahoreVlevo1 + vlevo)/3;
                        max = prumer + schodek;
                        min = prumer - schodek;

                        if (min<MIN){
                            min = MIN;
                            count++;
                        }else if(max>MAX){
                            max = MAX;
                            count++;
                        }

                        vyskaI = vytvorVysku(max,min);
                    }

                    Vyska vyska = new Vyska(vyskaI);
                    Bod bod = new Bod(i*bodSize, j*bodSize, vyska);
                    mapa[i][j]=bod;

                    prumernaVyska += bod.getVyska().getvalueOfVyska();
                    pocet++;




                }

            }

        }
        return mapa;
    }

    public void generujHorizont(Pane root){

        Rectangle r = new Rectangle();
        r.setX(0);
        r.setY(0);
        r.setWidth(velikostHorizontuX);
        r.setHeight(velikostHorizontuY);
        r.setFill(Color.WHITE);
        root.getChildren().add(r);


        Bod[] pole = new Bod[vytvorHorizont(MAPA).length];
        if (opak ==        0){
            pole = vytvorHorizont(generujMapuHorizont());
        }else{
            pole = vytvorHorizont(MAPA);
        }
        int max= 0;

        for (int i = 0; i<pole.length;i++){
            if (pole[i].getVyska().getvalueOfVyska()>=max){
                max = pole[i].getVyska().getvalueOfVyska();
            }
        }

        double dilek = (double)(velikostHorizontuY)/(double)(max);
        System.out.println(dilek + "    " + max + "  " + velikostHorizontuY/max);

        for (int i = 0;i<pole.length;i++){
            Line l = new Line();
            l.setStrokeWidth(bodSize);
            l.setStartX(bodSize*i);
            l.setStartY(velikostHorizontuY);
            l.setEndY(dilek*pole[i].getVyska().getvalueOfVyska());
            l.setEndX(bodSize*i);

            root.getChildren().addAll(l);

        }


    }

    public Bod[]  vytvorHorizont(Bod[][] mapa){
        Bod[] pole = new Bod[velikostMapy /bodSize];
        for (int i = 0;i<mapa.length;i++){
            for (int j = 0; j<mapa[i].length;j++){
                if (j==0){
                    pole[i] = mapa[j][i];
                }else if (pole[i].getVyska().getvalueOfVyska()<mapa[j][i].getVyska().getvalueOfVyska()){
                    pole[i] = mapa[j][i];
                }
            }
        }

        return pole;
    }
}