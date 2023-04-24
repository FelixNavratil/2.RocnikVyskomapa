package com.example.realnetahlevyskomapa;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Vyskomapa extends Application {
    //velikost sceny

    public static int size = 800;
    public static int velikostMapy = 800;
    public static int bodSize = 2;
    public static int schodek = 500;
    public static int MAX = 8800;
    public static int MIN = 0;
    public static int velikostHorizontuY = 226;
    public static int velikostHorizontuX = velikostMapy + 100;
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
    public String adresa;
    public String getAdresa(String adresa1){
        adresa = adresa1;
        return adresa;
    }

    public void oknoKonvertujMapu(Pane root, Stage stage, Scene scene, Scene scenaPrvniOkno){

        double odstaveni = 113.38582677;
        double velikostX = 200;
        double velikostY = 140;

        Pane rootKonvertujMapu = new Pane();
        rootKonvertujMapu.setPrefSize(size + 200,size);
        Scene sceneKonvertujMapu = new Scene(rootKonvertujMapu);


        Image tapeta = new Image("D:\\JavaProjekty\\RealneTahleVyskomapa\\TapetaOknoKonvertujMapu.PNG");
        ImageView imageView = new ImageView(tapeta);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);

        Button zdeBerteObrazky = new Button("Zde berte obrazky");
        zdeBerteObrazky.setPrefSize(velikostX,velikostY);
        zdeBerteObrazky.setLayoutY(113.38582677);
        zdeBerteObrazky.setLayoutX(odstaveni);
        zdeBerteObrazky.setOnMouseClicked(e->{
            getHostServices().showDocument("https://en-gb.topographic-map.com/map-4d9jnh/The-World/?center=50.04391%2C14.39484&zoom=10&base=2");
        });

        Label dragNDrop = new Label("Zde dej png složku");
        dragNDrop.setStyle("-fx-background-color: lightgray;");
        dragNDrop.setAlignment(Pos.CENTER);
        dragNDrop.setPrefSize(velikostX, velikostY);
        dragNDrop.setLayoutX(odstaveni);
        dragNDrop.setLayoutY((zdeBerteObrazky.getLayoutY() + velikostMapy - 113.38582677 - velikostY)/2 );

        dragNDrop.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
// Handle the dropped files

        // Enable the label to accept dragged files
        dragNDrop.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasFiles()) {
                // Check if any of the dropped files have the ".png" extension
                boolean hasPng = dragboard.getFiles().stream()
                        .anyMatch(file -> file.getName().toLowerCase().endsWith(".png"));
                if (hasPng) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
            }
            event.consume();
        });

// Handle the dropped files
        dragNDrop.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                // Get the list of dropped files
                List<File> files = dragboard.getFiles();
                // Process the dropped files as needed
                for (File file : files) {
                    // Check if the dropped file has the ".png" extension
                    if (file.getName().toLowerCase().endsWith(".png")) {
                        getAdresa(file.getAbsolutePath());
                        System.out.println("Dropped PNG file:" + getAdresa(adresa));
                        // Add your custom logic to handle the dropped PNG file here
                    } else {
                        System.out.println("Dropped file is not a PNG:22222 " + file.getAbsolutePath());
                        // Add your custom logic for handling other file types here
                    }
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        Button konvertujMapu = new Button("Konvertuj Mapu");
        konvertujMapu.setPrefSize(velikostX,velikostY);
        konvertujMapu.setLayoutX(odstaveni);
        konvertujMapu.setLayoutY(velikostMapy - 113.38582677 - konvertujMapu.getPrefHeight());
        konvertujMapu.setOnMouseClicked(e->{
            stage.setScene(sceneKonvertujMapu);
            KonverujMapu k = new KonverujMapu();
            k.konvertujMapu(rootKonvertujMapu, sceneKonvertujMapu, adresa, stage, scene);
        });


        Button buttonZpet = new Button("ZPET");
        buttonZpet.setPrefSize(size/5, size/5);
        buttonZpet.setLayoutX(size-buttonZpet.getPrefWidth() - size/14);
        buttonZpet.setLayoutY(size/12);
        buttonZpet.setOnMouseClicked(e->{
            stage.setScene(scenaPrvniOkno);
        });

        Font font = new Font(20);

        Rectangle recNavod = new Rectangle(453.54330709,453.54330709);
        recNavod.setFill(Color.WHITE);
        recNavod.setLayoutY(113);
        recNavod.setLayoutX((size-recNavod.getWidth())/2);

        Label textTutotial = new Label("1. Klikněte na tlačítko ,,Zde berte obrázky\". \n" +
                "2. Skratkou WindowsKey + Shift + s.\n"+
                "3. Vyznačte část mapy, kterou chcete konvertovat\n a uložte obrázek.\n"+
                "4. Přetáhněte obrázek z úložiště na šedý obdelník\n s nápisem ,,Zde dejte png složku\"\n" +
                "5. Klikněte na tlačítko ,,Konveruj Mapu\"\n\n\n"+
                "!!!Zkontrolujte, že jste ořízli jenom mapu.\n Program jinak nemusí fungovat dobře!!!");
        textTutotial.setFont(font);
        textTutotial.setLayoutY(recNavod.getLayoutY() + 5);
        textTutotial.setLayoutX(recNavod.getLayoutX()+5);

        Button tutorial = new Button("Zobrazit návod");
        tutorial.setPrefSize(size/5,size/5);
        tutorial.setLayoutX(buttonZpet.getLayoutX());
        tutorial.setLayoutY(konvertujMapu.getLayoutY());
        tutorial.setOnMouseEntered(e->{
            root.getChildren().addAll(recNavod, textTutotial);
        });
        tutorial.setOnMouseExited(e->{
            root.getChildren().removeAll(recNavod, textTutotial);
        });



        root.getChildren().addAll(imageView, buttonZpet, dragNDrop, konvertujMapu, zdeBerteObrazky, tutorial);
        stage.setScene(scene);
        stage.show();

    }

    public void konverujMapu(){
        KonverujMapu konverujMapu = new KonverujMapu();
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
        stageMapa.setResizable(true);
        Scene sceneMapa = new Scene(rootMapa, velikostMapy+200, velikostMapy);

        Pane rootHorizont = new Pane();
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        Scene sceneHorizont = new Scene(rootHorizont, velikostMapy, velikostHorizontuY);




        Button buttonGenerujMapu2 = new Button("GENERUJ MAPU 2");
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
        buttonGenerujMapu.setLayoutY(buttonGenerujMapu2.getLayoutY() + buttonGenerujMapu2.getPrefHeight() + 20);
        buttonGenerujMapu.setOnMouseClicked(e->{
            generujMapu(rootMapa);
            stageMapa.show();
        });

        Button buttonVytvorMapu = new Button("VYTVOR MAPU");
        buttonVytvorMapu.setPrefSize(size/4, size/6);
        buttonVytvorMapu.setLayoutX(size/14);
        buttonVytvorMapu.setLayoutY(buttonGenerujMapu.getLayoutY() + 20 + buttonGenerujMapu.getPrefHeight());
        buttonVytvorMapu.setOnMouseClicked(e->{
            vytvorMapu(rootMapa);
            stageMapa.show();
        });

        Button buttonZpet = new Button("ZPET");
        buttonZpet.setPrefSize(size/5, size/5);
        buttonZpet.setLayoutX(size-buttonZpet.getPrefWidth() - size/14);
        buttonZpet.setLayoutY(size/12);
        buttonZpet.setOnMouseClicked(e->{
            stage.setScene(scenaPrvniOkno);
        });


        root.getChildren().addAll(imageView, buttonGenerujMapu2, buttonZpet, buttonGenerujMapu, buttonVytvorMapu);

        rootMapa2.setPrefSize(velikostMapy, velikostMapy);
        stageMapa2.setScene(sceneMapa2);

        rootMapa.setPrefSize(velikostMapy, velikostMapy);
        stageMapa.setScene(sceneMapa);

        rootHorizont.setPrefSize(velikostMapy,velikostHorizontuY);
        stageHorizont.setScene(sceneHorizont);

        stage.setScene(scene);
        stage.show();
    }

    public void vytvorMapu(Pane root){
        Mapa mapa = new Mapa();
        mapa.scenaVytvorMapu(root);
    }



    public void oknoNastaveni(Pane root, Stage stage, Scene scene, Scene scenaPrvniOkno){
        final boolean plus = true;
        final boolean minus = false;
        double plusTlacitkaOsaX = 50+size/2;

        Rectangle nalepka = new Rectangle(size,size);
        nalepka.setFill(Color.WHITE);
        root.getChildren().addAll(nalepka);


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
        Label labelVelikostMapy = new Label("Velikost mapy (pixel): ");
        labelVelikostMapy.setLayoutX(size/8);
        labelVelikostMapy.setLayoutY(labelMinVyska.getLayoutY() + labelVelikostMapy.getHeight() + 50);
        labelVelikostMapy.setFont(newFont);

        Label hodnotaVelikostiMapy = new Label(String.valueOf(velikostMapy));
        hodnotaVelikostiMapy.setFont(newFont);
        hodnotaVelikostiMapy.setLayoutY(labelMinVyska.getLayoutY() + labelVelikostMapy.getHeight() + 50);
        hodnotaVelikostiMapy.setLayoutX(size/2-50);






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
                labelMaxVyska, labelMinVyska, labelVelikostMapy, plusVelikostBodu, minusVelikostBodu, plusMaxVyska, plusMinVyska, plusVelikostSchodku,
                minusVelikostSchodku, minusMaxVyska, minusMinVyska
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
            if (MAX + 50<= MAX){
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

    int pocitadloVelikostMapy = 0;
    public int zmenaVelikostiMapy(boolean jePlus){
        if (jePlus){
            if (pocitadloVelikostMapy!=1){
                pocitadloVelikostMapy++;
            }
        }else{
            if (pocitadloVelikostMapy!=0)
                pocitadloVelikostMapy--;
        }


        if (pocitadloVelikostMapy == 0){
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
        r.setWidth(velikostMapy);
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