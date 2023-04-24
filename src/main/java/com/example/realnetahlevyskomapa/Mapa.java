package com.example.realnetahlevyskomapa;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Mapa extends Vyskomapa{


    public Bod[] poleVybranychBodu = new Bod[5];

    public void generujMapu2(Pane root)  {

        //body ze kterych budu brat hodnoty
        int nahoreVlevo1;
        int nahore;
        int nahorevpravo1;
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

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska zakladniVyska = new Vyska(schodek);
        Bod[] pole = new Bod[mapa.length];
        //Projidzi okno na ose x
        for (int i = 0; i<mapa.length; i++){
            //x jen abych vedel ze je to osa x
            x = i;
            // projizdi pole na ose y
            for (int j =0; j<mapa.length; j++){

                //jestli je to uplne vlevo nahore
                if (i==0 && j==0){

                    //Zakladame bod a u mistujeme ho do pole
                    Bod zakladniBod = new Bod(i*bodSize, j*bodSize, zakladniVyska);
                    mapa[i][j] = zakladniBod;
                    pole[j] = mapa[i][j];

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
                    root.getChildren().addAll(bod);

                    //System.out.println("body prvniho radku: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

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

                    root.getChildren().addAll(bod);
                    System.out.println("-------------------------------------------------------------"+i);
                    //System.out.println("body prvniho sloupce: " + bod.getVyska().getvalueOfVyska() + " min: " + min + " max: "+ max + " generovana vyska: " + vyskaI);

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

                    root.getChildren().addAll(bod);

                }
                pole[j] = mapa[i][j];

            }
            System.out.println(prumernaVyska);
        }

        MAPA = mapa;

    }

    public void generujMapu(Pane root){



        Rectangle r = new Rectangle();
        r.setHeight(velikostMapy);
        r.setWidth(200);
        r.setFill(Color.WHITE);
        r.setLayoutX(velikostMapy);
        r.setLayoutY(0);

        int vlevo;
        int nahoreVlevo;
        int nahoreVpravo;
        int nahore;
        int prumer;
        int max;
        int min;
        int vyska;

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska zakladniVyska = new Vyska(MIN + schodek);

        for (int i = 0; i<mapa.length;i++){
            for (int j = 0;j<mapa.length;j++){
                //Jestli je to prvni bod
                if (i==0 && j==0){
                    Bod zakladniBod = new Bod(0, 0, zakladniVyska);
                    mapa[i][j] = zakladniBod;
                    prumer = zakladniVyska.getvalueOfVyska();
                }
                //Jestli je to prvni radek
                else if (i==0){
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                    prumer = vlevo;
                }
                //jestli je to prvni bod jakehokoli radku
                else if (j==0){
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVpravo)/2;

                }
                //jestli je to posledni bod jakehokoli radku
                else if (j==mapa.length-1){
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo)/2;

                }
                //Jestli je to cokoli jineho
                else{
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo + nahoreVpravo)/3;
                }

                max = prumer + schodek;
                min = prumer - schodek;

                if (min<MIN){
                    min = MIN;
                }else if(max>MAX){
                    max = MAX;
                }
                vyska = vytvorVysku(max,min);
                Vyska v = new Vyska(vyska);
                Bod bod = new Bod(i*bodSize, j*bodSize, v);
                mapa[i][j]=bod;
                root.getChildren().addAll(bod);
            }

        }
        MAPA = mapa;

        Label yKoordinace = new Label();
        yKoordinace.setPrefSize(50, 150);
        yKoordinace.setLayoutX(velikostMapy + 70);
        yKoordinace.setLayoutY(100);
        Font currentFont = yKoordinace.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 10);
        yKoordinace.setFont(newFont);

        Label xKoordinace = new Label();
        xKoordinace.setPrefSize(50, 150);
        xKoordinace.setLayoutX(velikostMapy + 70);
        xKoordinace.setLayoutY(10);
        xKoordinace.setFont(newFont);

        Label labelVyska = new Label();
        labelVyska.prefHeight(50);
        labelVyska.setLayoutX(velikostMapy + 70);
        labelVyska.setLayoutY(266);
        labelVyska.setFont(newFont);

        Label textXKoordinace = new Label("Souřadnice X: ");
        textXKoordinace.setFont(newFont);
        textXKoordinace.setLayoutY(20);
        textXKoordinace.setLayoutX(velikostMapy + 20);

        Label textYKoordinace = new Label("Souřadnice Y: ");
        textYKoordinace.setFont(newFont);
        textYKoordinace.setLayoutY(113);
        textYKoordinace.setLayoutX(velikostMapy + 20);

        Label textVyska = new Label("Výška: ");
        textVyska.setFont(newFont);
        textVyska.setLayoutY(226);
        textVyska.setLayoutX(velikostMapy + 20);

        Pane rootHorizont = new Pane();
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX + 100, velikostHorizontuY );
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        stageHorizont.setScene(sceneHorizont);

        Button buttonGenerujHorizont = new Button("Pohled ze strany");
        buttonGenerujHorizont.setPrefSize(151, 113);
        buttonGenerujHorizont.setLayoutX(velikostMapy + 24.5);
        buttonGenerujHorizont.setLayoutY(velikostMapy - 18 - 113);
        buttonGenerujHorizont.setOnMouseClicked(e->{
            generujHorizont(mapa, rootHorizont, sceneHorizont);

            stageHorizont.show();
        });

        Button generujMapuZnovu = new Button("generuj jinou mapu");
        generujMapuZnovu.setPrefSize(151, 113);
        generujMapuZnovu.setLayoutX(velikostMapy + 24.5);
        generujMapuZnovu.setLayoutY(velikostMapy - 18 - 113 - 18 - buttonGenerujHorizont.getPrefWidth());
        generujMapuZnovu.setOnMouseClicked(e->{
             generujMapu(root);
        });


        Rectangle ctverec = new Rectangle();
        ctverec.setHeight(velikostMapy);
        ctverec.setWidth(velikostMapy);
        ctverec.setLayoutY(0);
        ctverec.setLayoutX(0);
        ctverec.setOpacity(0.0);
        ctverec.setOnMouseClicked((MouseEvent event)->{
            xKoordinace.setText(String.valueOf(getKordX(event.getX())));
            yKoordinace.setText(String.valueOf(getKordY(event.getY())));
            getKordY(event.getY());
            System.out.println(" x - " + x + "    " + " y - " + y);
            labelVyska.setText(String.valueOf(mapa[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska()));
        });

        root.getChildren().addAll(r, ctverec, yKoordinace, xKoordinace, labelVyska, textXKoordinace, textYKoordinace, textVyska, buttonGenerujHorizont, generujMapuZnovu);

    }

    /**
     *
     * @tohle chatgpt
     */
    public void generujHorizont(Bod [][] mapa, Pane root, Scene scene){

        Rectangle nalepka = new Rectangle();
        nalepka.setHeight(scene.getHeight());
        nalepka.setWidth(scene.getWidth());
        nalepka.setLayoutY(0);
        nalepka.setLayoutX(0);
        nalepka.setFill(Color.WHITE);
        root.getChildren().add(nalepka);


/**
 * tohle okomentuju
 */
        int[] horizont = new int[mapa.length]; // Create an array to store the max values
        for (int i = 0; i < mapa.length; i++) {
            int max = Integer.MIN_VALUE; // Initialize max to the smallest possible value  int max = Integer.MIN_VALUE;
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j].getVyska().getvalueOfVyska() > max) { // Compare each element in the row to find the max
                    max = mapa[i][j].getVyska().getvalueOfVyska(); // Update max if a bigger value is found
                }
            }
            horizont[i] = max; // Store the max value in the maxValues array
            System.out.println(i + "   " + horizont[i]);
        }

        Rectangle r = new Rectangle();
        r.setLayoutX(0);
        r.setLayoutY(0);
        r.setFill(Color.WHITE);
        r.setWidth(velikostMapy);
        r.setHeight(velikostHorizontuY);



        int m = 0;
        for (int i = 0;i<horizont.length; i++){
            if (horizont[i]>m){
                m=horizont[i];
            }
        }

        double dilek = (double)(velikostHorizontuY)/(double)(MAX);
        System.out.println("Dilek>  "+dilek);
        System.out.println("dilek * nejvyssi vyska   "+m*dilek);

        for (int i = 0;i<horizont.length;i++){
            Vyska vyska = new Vyska(horizont[i]);
            HBod hBod = new HBod(i,dilek, vyska);
            root.getChildren().addAll(hBod);
        }

        Line meritko = new Line();
        meritko.setStroke(Color.BLACK);
        meritko.setStrokeWidth(1);
        meritko.setStartX(velikostHorizontuX - meritko.getStrokeWidth() - 5 - 38);
        meritko.setStartY(velikostHorizontuY);
        meritko.setEndX(velikostHorizontuX - meritko.getStrokeWidth() - 5 - 38);
        meritko.setEndY(0);

        Text text1 = new Text("0");
        text1.setLayoutY(velikostHorizontuY - 5 + 4);
        text1.setLayoutX(meritko.getStartX()-20 - 20);

        Text text2 = new Text(String.valueOf(MAX/4));
        text2.setLayoutY(velikostHorizontuY - velikostHorizontuY/4 + 4);
        text2.setLayoutX(meritko.getStartX()-20 - 20);

        Text text3 = new Text(String.valueOf(MAX/2));
        text3.setLayoutY(velikostHorizontuY/2 + 4);
        text3.setLayoutX(meritko.getStartX()-20 - 20);

        Text text4 = new Text(String.valueOf(3*MAX/4));
        text4.setLayoutY(velikostHorizontuY/4 + 4);
        text4.setLayoutX(meritko.getStartX()-20 - 20);

        Text text5 = new Text(String.valueOf(MAX));
        text5.setLayoutY(14);
        text5.setLayoutX(meritko.getStartX()-20 - 20);

        Line odrazka1 = new Line();
        odrazka1.setStroke(Color.BLACK);
        odrazka1.setStrokeWidth(1);
        odrazka1.setStartX(meritko.getStartX()-5);
        odrazka1.setStartY(velikostHorizontuY - 5);
        odrazka1.setEndX(meritko.getStartX()+5);
        odrazka1.setEndY(velikostHorizontuY - 5);

        Line odrazka2 = new Line();
        odrazka2.setStroke(Color.BLACK);
        odrazka2.setStrokeWidth(1);
        odrazka2.setStartX(meritko.getStartX()-5);
        odrazka2.setStartY(velikostHorizontuY - velikostHorizontuY/4);
        odrazka2.setEndX(meritko.getStartX()+5);
        odrazka2.setEndY(velikostHorizontuY - velikostHorizontuY/4);

        Line odrazka3 = new Line();
        odrazka3.setStroke(Color.BLACK);
        odrazka3.setStrokeWidth(1);
        odrazka3.setStartX(meritko.getStartX()-5);
        odrazka3.setStartY(velikostHorizontuY/2);
        odrazka3.setEndX(meritko.getStartX()+5);
        odrazka3.setEndY(velikostHorizontuY/2);

        Line odrazka4 = new Line();
        odrazka4.setStroke(Color.BLACK);
        odrazka4.setStrokeWidth(1);
        odrazka4.setStartX(meritko.getStartX()-5);
        odrazka4.setStartY(velikostHorizontuY/4);
        odrazka4.setEndX(meritko.getStartX()+5);
        odrazka4.setEndY(velikostHorizontuY/4);

        Line odrazka5 = new Line();
        odrazka5.setStroke(Color.BLACK);
        odrazka5.setStrokeWidth(1);
        odrazka5.setStartX(meritko.getStartX()-5);
        odrazka5.setStartY(5);
        odrazka5.setEndX(meritko.getStartX()+5);
        odrazka5.setEndY(5);


        Label rada = new Label("Řada: ");
        rada.setLayoutX(scene.getWidth() -120);
        rada.setLayoutY(velikostHorizontuY/6);
        Font currentFont = rada.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 6);
        rada.setFont(newFont);

        Label textRada = new Label();
        textRada.setLayoutX(scene.getWidth()-70);
        textRada.setLayoutY(velikostHorizontuY/6);
        textRada.setFont(newFont);

        Label labelVyska1 = new Label("Výška: ");
        labelVyska1.setLayoutY(velikostHorizontuY/3);
        labelVyska1.setLayoutX(scene.getWidth() -120);
        labelVyska1.setFont(newFont);

        Label labelVyska = new Label();
        labelVyska.setLayoutY(velikostHorizontuY/3);
        labelVyska.setLayoutX(scene.getWidth()-70);
        labelVyska.setFont(newFont);

        Rectangle ctverec = new Rectangle();
        ctverec.setHeight(scene.getHeight());
        ctverec.setWidth(velikostMapy);
        ctverec.setLayoutY(0);
        ctverec.setLayoutX(0);
        ctverec.setOpacity(0.0);
        ctverec.setOnMouseClicked((MouseEvent event)->{
            textRada.setText(String.valueOf(getKordX(event.getX())));
            System.out.println("rada> " + getKordX(event.getX()) + "vyska >" + horizont[getKordX(event.getX())]);
            labelVyska.setText(String.valueOf(horizont[getKordX(event.getX())]));
        });



        root.getChildren().addAll( meritko, odrazka1, odrazka2, odrazka3, odrazka4, odrazka5,text1,text2,text3,text4,text5, rada, textRada, labelVyska, labelVyska1, ctverec  );
    }

    public void scenaVytvorMapu(Pane root){
        Rectangle nalepka = new Rectangle(root.getScene().getWidth(), root.getScene().getHeight());
        nalepka.setFill(Color.WHITE);
        nalepka.setLayoutX(0);
        nalepka.setLayoutY(0);

        Label yKoordinace = new Label();
        yKoordinace.setPrefSize(50, 150);
        yKoordinace.setLayoutX(velikostMapy + 70);
        yKoordinace.setLayoutY(100);
        Font currentFont = yKoordinace.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 10);
        yKoordinace.setFont(newFont);

        Label xKoordinace = new Label();
        xKoordinace.setPrefSize(50, 150);
        xKoordinace.setLayoutX(velikostMapy + 70);
        xKoordinace.setLayoutY(10);
        xKoordinace.setFont(newFont);

        Label labelVyska = new Label();
        labelVyska.prefHeight(50);
        labelVyska.setLayoutX(velikostMapy + 70);
        labelVyska.setLayoutY(266);
        labelVyska.setFont(newFont);

        Label textXKoordinace = new Label("Souřadnice X: ");
        textXKoordinace.setFont(newFont);
        textXKoordinace.setLayoutY(20);
        textXKoordinace.setLayoutX(velikostMapy + 20);

        Label textYKoordinace = new Label("Souřadnice Y: ");
        textYKoordinace.setFont(newFont);
        textYKoordinace.setLayoutY(113);
        textYKoordinace.setLayoutX(velikostMapy + 20);

        Label textVyska = new Label("Výška: ");
        textVyska.setFont(newFont);
        textVyska.setLayoutY(226);
        textVyska.setLayoutX(velikostMapy + 20);

        Pane rootHorizont = new Pane();
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX + 100, velikostHorizontuY );
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        stageHorizont.setScene(sceneHorizont);

        Button buttonGenerujHorizont = new Button("Pohled ze strany");
        buttonGenerujHorizont.setPrefSize(151, 113);
        buttonGenerujHorizont.setLayoutX(velikostMapy + 24.5);
        buttonGenerujHorizont.setLayoutY(velikostMapy - 18 - 113);
        buttonGenerujHorizont.setOnMouseClicked(e->{
            generujHorizont(getMAPA(),rootHorizont,sceneHorizont);
            stageHorizont.show();
        });


        Button buttonGenerujJinouMapu = new Button("generuj jinou mapu");
        buttonGenerujJinouMapu.setPrefSize(151, 113);
        buttonGenerujJinouMapu.setLayoutX(buttonGenerujHorizont.getLayoutX());
        buttonGenerujJinouMapu.setLayoutY(buttonGenerujHorizont.getLayoutY()-5-buttonGenerujJinouMapu.getPrefHeight());
        buttonGenerujJinouMapu.setOnMouseClicked(e->{
            scenaVytvorMapu(root);
            getPocet(0);
        });

        TextField integerTextField = new TextField();
        integerTextField.setAlignment(Pos.CENTER);
        integerTextField.setPrefSize(151,56);
        integerTextField.setLayoutX(buttonGenerujHorizont.getLayoutX());
        integerTextField.setLayoutY(buttonGenerujJinouMapu.getLayoutY() - 5 - buttonGenerujHorizont.getPrefHeight() - 5 - integerTextField.getPrefHeight());

        // Add key event filter to restrict input to integers within the specified range
        integerTextField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = integerTextField.getText() + event.getCharacter();
            if (!isInputValid(input)) {
                event.consume();
            }
        });


        Rectangle souradnicovyCtverec = new Rectangle(velikostMapy,velikostMapy);
        souradnicovyCtverec.setLayoutY(0);
        souradnicovyCtverec.setLayoutX(0);
        souradnicovyCtverec.setOpacity(0.0);


        Vyska vyska = new Vyska();

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];



        //tlacitko ktere prida body do pole (krome jinych)
        Button zvolitVysku = new Button("Zvolit Tento Bod");
        zvolitVysku.setPrefSize(buttonGenerujHorizont.getPrefWidth(),buttonGenerujHorizont.getPrefHeight());
        zvolitVysku.setLayoutY(buttonGenerujJinouMapu.getLayoutY() - 5 - zvolitVysku.getPrefHeight());
        zvolitVysku.setLayoutX(buttonGenerujHorizont.getLayoutX());
        zvolitVysku.setOnMouseClicked(e->{
            if (!Objects.equals(integerTextField.getText(), "")){
                root.getChildren().removeAll(souradnicovyCtverec);
                vyska.setVyska(Integer.parseInt(integerTextField.getText()));
                mapa[x][y] = new Bod(x*bodSize,  y* bodSize, vyska);
                poleVybranychBodu[pocet] = mapa[x][y];
                getPocet(1);
                root.getChildren().addAll(mapa[x][y]);
                System.out.println("zvolitVysku.setOnMouseClicked");
                setMapa(mapa,x,y,mapa,vyska, root);
                for (int i = 0;i<mapa.length;i++){
                    for (int j = 0; j < mapa.length; j++) {
                     root.getChildren().add(mapa[i][j]);
                    }
                }
            }
            else{
                System.out.println("Neni zadana Vyska" );
            }
            root.getChildren().add(souradnicovyCtverec);
        });

        souradnicovyCtverec.setOnMouseClicked((MouseEvent event)->{
            xKoordinace.setText(String.valueOf(getKordX(event.getX())));
            yKoordinace.setText(String.valueOf(getKordY(event.getY())));
            System.out.println(" x - " + x + "    " + " y - " + y);
            labelVyska.setText(String.valueOf(mapa[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska()));
            System.out.println(" x =" + getKordX(event.getX()) + " y = " + getKordY(event.getY()) + "vyska = " + mapa[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska());
        });

        root.getChildren().addAll(nalepka,souradnicovyCtverec, buttonGenerujJinouMapu, buttonGenerujHorizont, textVyska,textYKoordinace, textXKoordinace ,  labelVyska,
                xKoordinace, yKoordinace, integerTextField, zvolitVysku);

    }

    public int pocet;
    public void getPocet(int i){
        if (pocet<= poleVybranychBodu.length){
            pocet++;
        }
        if (i ==0){
            pocet = 0;
        }
    }

    Bod[][] MAPA;
    public Bod[][] setMapa(Bod [][] mapa1,int x, int y, Bod[][] mapa, Vyska vyska, Pane root ){
        mapa1 = vygenerujCustomMapu3(x,y,mapa,vyska, root);
        MAPA = mapa1;
        return mapa1;
    }

    public Bod[][] getMAPA(){
        if (MAPA == null){
            System.out.println("neni zadany zakladni bod");
        }
        return MAPA;
    }

    private boolean isInputValid(String input) {
        // Check if input is a valid integer within the specified range
        try {
            int value = Integer.parseInt(input);
            return value >= MIN && value <= MAX;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int x;
    private int y;

    public int getKordX(double dX){
        this.x = (int)dX/bodSize;
        return x;
    }
    public int getKordY(double dY){
        this.y = (int)dY/bodSize;
        return y;
    }

    public int vytvorVysku(int max, int min){
        Random random = new Random();
        return random.nextInt(min,max);
    }

    public void vygenerujCustomMapu1(int x, int y, Bod[][] mapa, Vyska vyska, Pane root) {
        int mapaLength;
        System.out.println("jsem v getPoleBodu");
        if (mapa.length - y > mapa.length - x && mapa.length - y > x && mapa.length - y > y) {
            mapaLength=mapa.length-y;
        }else if (x > mapa.length - x && x > y && x > mapa.length-y){
            mapaLength = x;
        }else if (y > mapa.length - x && y > x && y > mapa.length-y){
            mapaLength = y;
        }else{
            mapaLength = mapa.length - x;
        }

        int velikostPole = 2*mapaLength;
        Bod[][] vetsiMapa = new Bod[velikostPole+1][velikostPole+1];
        Bod zakladniBod = new Bod(velikostPole/2,velikostPole/2,vyska);
        vetsiMapa[velikostPole/2][velikostPole/2] = zakladniBod;
        System.out.println("inicializace poli    Velikost: " + velikostPole);
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;

        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + velikostPole/2);

//Vytvareni mapy
        for (int i = 1;i<=velikostPole/2;i++){

            for (int poc =1;poc<=4;poc++){

                //Horni hrana ctverce
                System.out.println(" i : " + i);
                if (poc == 1){

                    lokalniPocatekX = vetsiMapa.length/2-i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 0; j <= 2*i;j++){

                        kordX = lokalniPocatekX + j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println("j: " + j + " i: " + i);

                        if (i == 1){
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = doleVpravo + schodek;
                                min = doleVpravo - schodek;

                            }else if(j ==  1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                max = dole + schodek;
                                min = dole - schodek;
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                max = doleVlevo + schodek;
                                min = doleVlevo - schodek;
                            }

                        }else{
                            //Jestli je to posledni bod z leva
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = doleVpravo + schodek;
                                min = doleVpravo - schodek;

                                //Jestli je to predposledni z leva
                            }else if (j == 1){

                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();

                                max = (doleVpravo + dole)/2 + schodek;
                                min = (doleVpravo + dole)/2 - schodek;

                                //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole)/2 + schodek;
                                min = (doleVlevo + dole)/2 - schodek;

                                //Jestli je to posledni z prava
                            }else if (j == 2*i){

                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();

                                max = doleVlevo + schodek;
                                min = doleVlevo - schodek;
                            //Jestli je to nekde uprostred
                            } else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + doleVpravo)/3 + schodek;
                                min = (doleVlevo + dole + doleVpravo)/3 - schodek;
                            }
                        }

                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                //Hrana vpravo

                }else if (poc == 2){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX;
                        kordY = lokalniPocatekY + j;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println(" I = " + i + " j = " + j);
                        if (i == 1){
                            if (j == 1){
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = vlevo + schodek;
                                min = vlevo - schodek;

                            }else {
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                max = nahoreVlevo + schodek;
                                min = nahoreVlevo - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z hora
                            if (j == 1){
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2-schodek;

                                //Jestli je to predposledni z dola
                            }else if (j == 2*i-1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (nahoreVlevo + vlevo)/2 + schodek;
                                min = (nahoreVlevo + vlevo)/2 - schodek;

                                //Jestli je to posledni z dola
                            }else if (j == 2*i){

                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();

                                max = nahoreVlevo + schodek;
                                min = nahoreVlevo - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + nahoreVlevo + vlevo)/3 + schodek;
                                min = (doleVlevo + nahoreVlevo + vlevo)/3 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                //hrana Dole
                }else if (poc == 3){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2+i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX - j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                        //Jestli je to prvni cyklus
                        if (i == 1){
                            if (j == 1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = nahore + schodek;
                                min = nahore - schodek;

                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                max = nahoreVpravo + schodek;
                                min = nahoreVpravo - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z leva
                            if (j == 1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVlevo)/2 + schodek;
                                min = (nahore + nahoreVlevo)/2-schodek;
                            //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVpravo)/2 + schodek;
                                min = (nahore + nahoreVpravo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            }else if (j == 2*i){

                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = nahoreVpravo + schodek;
                                min = nahoreVpravo - schodek;
                                //Jestli je to nekde uprostred
                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + nahoreVlevo + nahore)/3 + schodek;
                                min = (nahoreVpravo + nahoreVlevo + nahore)/3 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                //hrana vpravo
                }else {
                    lokalniPocatekX = velikostPole/2-i;
                    lokalniPocatekY = velikostPole/2+i;
                    for (int j = 1; j < 2*i;j++){
                        kordX = lokalniPocatekX ;
                        kordY = lokalniPocatekY- j;
                        System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                        //jestli Je to ten prvni cyklus
                        if (i == 1){
                            vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                            max = vpravo + schodek;
                            min = vpravo-schodek;

                        }else{
                            //Jestli je to predposledni bod z dola
                            if (j == 1){
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (vpravo + nahoreVpravo)/2 + schodek;
                                min = (vpravo + nahoreVpravo)/2-schodek;
                            //Jestli je to predposledni z hora
                            }else if (j == 2*i){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (dole + doleVpravo)/2 + schodek;
                                min = (dole + doleVpravo)/2 - schodek;
                            //Jestli je to nekde uprostred
                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + vpravo + doleVpravo)/3 + schodek;
                                min = (nahoreVpravo + vpravo + doleVpravo)/3 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                }

            }
        }

        int zacatekX = vetsiMapa.length - x - vetsiMapa.length/2 ;
        int konecX = zacatekX + mapa.length;
        int zacatekY = vetsiMapa.length - y - vetsiMapa.length/2;
        int konecY = zacatekY + mapa.length;
        int t = -1;
        int k = -1;
        System.out.println("x= "+ x + " y= " + y);
        System.out.println("velikost Pole: " + velikostPole + " mapa.length: " + mapa.length);
        System.out.println("Zacatek x : " + zacatekX + " konecX: "+ konecX + " zacatekY: " + zacatekY + " konecY: " + konecY);

        for (int i = zacatekY; i < konecY-1; i++) {
            t++;
            for (int j = zacatekX;j<konecX-1;j++){
                k++;
                System.out.println(" t = " + t + " k = " + k);
                mapa[t][k] = vetsiMapa[i][j];
                mapa[t][k] = new Bod(t*bodSize,k*bodSize,vetsiMapa[i][j].getVyska().getvalueOfVyska());
                root.getChildren().add(mapa[t][k]);

            }
            k =-1;
        }


    }

    public Bod[][] vygenerujCustomMapu2(int x, int y, Bod[][] mapa, Vyska vyska, Pane root) {
        int mapaLength;
        System.out.println("jsem v getPoleBodu");
        if (mapa.length - y > mapa.length - x && mapa.length - y > x && mapa.length - y > y) {
            mapaLength=mapa.length-y;
        }else if (x > mapa.length - x && x > y && x > mapa.length-y){
            mapaLength = x;
        }else if (y > mapa.length - x && y > x && y > mapa.length-y){
            mapaLength = y;
        }else{
            mapaLength = mapa.length - x;
        }

        int velikostPole = 2*mapaLength;
        Bod[][] vetsiMapa = new Bod[velikostPole+1][velikostPole+1];
        Bod zakladniBod = new Bod(velikostPole/2,velikostPole/2,vyska);
        vetsiMapa[velikostPole/2][velikostPole/2] = zakladniBod;
        System.out.println("inicializace poli    Velikost: " + velikostPole);
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;

        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + velikostPole/2);

//Vytvareni mapy
        for (int i = 1;i<=velikostPole/2;i++){

            for (int poc =1;poc<=4;poc++){

                //Horni hrana ctverce
                System.out.println(" i : " + i);
                if (poc == 1){

                    lokalniPocatekX = vetsiMapa.length/2-i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 0; j <= 2*i;j++){

                        kordX = lokalniPocatekX + j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println("j: " + j + " i: " + i);

                        if (i == 1){
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                max = doleVpravo + schodek;
                                min = doleVpravo - schodek;

                            }else if(j ==  1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + dole)/2 + schodek;
                                min = (vlevo + dole)/2 - schodek;
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to posledni bod z leva
                            if (j == 0){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX+1][kordY+2].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX+2][kordX+1].getVyska().getvalueOfVyska();
                                max = (doleVpravo + dole + vlevo)/3 + schodek;
                                min = (doleVpravo + dole + vlevo)/3 - schodek;

                                //Jestli je to predposledni z leva
                            }else if (j == 1){

                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (doleVpravo + dole + vlevo)/3 + schodek;
                                min = (doleVpravo + dole + vlevo)/3 - schodek;

                                //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + vlevo)/3 + schodek;
                                min = (doleVlevo + dole + vlevo)/3 - schodek;

                                //Jestli je to posledni z prava
                            }else if (j == 2*i){

                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            } else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + doleVpravo + vlevo)/4 + schodek;
                                min = (doleVlevo + dole + doleVpravo + vlevo)/4 - schodek;
                            }
                        }

                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                    //Hrana vpravo

                }else if (poc == 2){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX;
                        kordY = lokalniPocatekY + j;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println(" I = " + i + " j = " + j);
                        if (i == 1){
                            if (j == 1){
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + nahore)/2 + schodek;
                                min = (vlevo + nahore)/2 - schodek;

                            }else {
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVlevo + nahore)/2 + schodek;
                                min = (nahoreVlevo + nahore)/2 - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z hora
                            if (j == 1){
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo + nahore)/3 + schodek;
                                min = (vlevo + doleVlevo + nahore)/3 - schodek;

                                //Jestli je to predposledni z dola
                            }else if (j == 2*i-1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo + vlevo+ nahore)/3 + schodek;
                                min = (nahoreVlevo + vlevo+ nahore)/3 - schodek;

                                //Jestli je to posledni z dola
                            }else if (j == 2*i){

                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo+ nahore)/2 + schodek;
                                min = (nahoreVlevo+ nahore)/2 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 + schodek;
                                min = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana Dole
                }else if (poc == 3){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2+i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX - j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                        //Jestli je to prvni cyklus
                        if (i == 1){
                            if (j == 1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + vpravo)/2 + schodek;
                                min = (nahore + vpravo)/2 - schodek;

                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (nahoreVpravo + vpravo)/2 + schodek;
                                min = (nahoreVpravo + vpravo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to predposledni bod z leva
                            if (j == 1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVlevo + vpravo )/3 + schodek;
                                min = (nahore + nahoreVlevo + vpravo )/3-schodek;
                                //Jestli je to predposledni z prava
                            }else if (j == 2*i-1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVpravo + vpravo )/3 + schodek;
                                min = (nahore + nahoreVpravo + vpravo )/3 - schodek;
                                //Jestli je posledni Bod z leva
                            }else if (j == 2*i){
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (vpravo + nahoreVpravo)/2 + schodek;
                                min = (vpravo + nahoreVpravo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            }else{
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 + schodek;
                                min = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana vpravo
                }else {
                    lokalniPocatekX = velikostPole/2-i;
                    lokalniPocatekY = velikostPole/2+i;
                    for (int j = 1; j < 2*i;j++){
                        kordX = lokalniPocatekX ;
                        kordY = lokalniPocatekY- j;
                        System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                        //jestli Je to ten prvni cyklus
                        if (i == 1){
                            dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                            vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                            nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                            max = (vpravo + dole + nahore)/3 + schodek;
                            min = (vpravo + dole + nahore)/3 - schodek;

                        }else{
                            //Jestli je to predposledni bod z dola
                            if (j == 1){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (vpravo + nahoreVpravo+ doleVpravo)/3 + schodek;
                                min = (vpravo + nahoreVpravo+ doleVpravo)/3 - schodek;

                                //Jestli je to predposledni z hora
                            }else if (j == 2*i){
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (dole + doleVpravo + nahore + nahoreVpravo)/4 + schodek;
                                min = (dole + doleVpravo + nahore + nahoreVpravo)/4 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + vpravo + doleVpravo + dole)/4 + schodek;
                                min = (nahoreVpravo + vpravo + doleVpravo + dole)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                }

            }
        }

        int zacatekX = vetsiMapa.length - x - vetsiMapa.length/2 ;
        int konecX = zacatekX + mapa.length;
        int zacatekY = vetsiMapa.length - y - vetsiMapa.length/2;
        int konecY = zacatekY + mapa.length;
        int t = -1;
        int k = -1;
        System.out.println("x= "+ x + " y= " + y);
        System.out.println("velikost Pole: " + velikostPole + " mapa.length: " + mapa.length);
        System.out.println("Zacatek x : " + zacatekX + " konecX: "+ konecX + " zacatekY: " + zacatekY + " konecY: " + konecY);

        for (int i = zacatekY; i < konecY; i++) {
            t++;
            for (int j = zacatekX;j<konecX;j++){
                k++;
                System.out.println(" t = " + t + " k = " + k);
                mapa[t][k] = vetsiMapa[i][j];
                mapa[t][k] = new Bod(t*bodSize,k*bodSize,vetsiMapa[i][j].getVyska().getvalueOfVyska());
            }
            k =-1;
        }

        return mapa;
    }

    public Bod[][] vygenerujCustomMapu3(int x, int y, Bod[][] mapa, Vyska vyska, Pane root) {
        int mapaLength;
        System.out.println("jsem v getPoleBodu");
        if (mapa.length - y > mapa.length - x && mapa.length - y > x && mapa.length - y > y) {
            mapaLength=mapa.length-y;
        }else if (x > mapa.length - x && x > y && x > mapa.length-y){
            mapaLength = x;
        }else if (y > mapa.length - x && y > x && y > mapa.length-y){
            mapaLength = y;
        }else{
            mapaLength = mapa.length - x;
        }

        int velikostPole = 2*mapaLength;
        Bod[][] vetsiMapa = new Bod[velikostPole+1][velikostPole+1];
        Bod zakladniBod = new Bod(velikostPole/2,velikostPole/2,vyska);
        vetsiMapa[velikostPole/2][velikostPole/2] = zakladniBod;
        System.out.println("inicializace poli    Velikost: " + velikostPole);
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;

        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + velikostPole/2);

//Vytvareni mapy
        for (int i = 1;i<=velikostPole/2;i++){

            for (int poc =1;poc<=4;poc++){

                //Horni hrana ctverce
                System.out.println(" i : " + i);
                if (poc == 1){

                    lokalniPocatekX = vetsiMapa.length/2-i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX + j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println("j: " + j + " i: " + i);

                        if (i == 1){
                            if (j == 1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                max = dole + schodek;
                                min = dole - schodek;

                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to posledni bod z leva
                            if (j == 1){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                dole = vetsiMapa[kordX+1][kordY+2].getVyska().getvalueOfVyska();

                                max = (doleVpravo + dole)/2 + schodek;
                                min = (doleVpravo + dole)/2 - schodek;

                                //Jestli je to predposledni z leva
                            }else if (j == 2*i-1){
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + vlevo)/3 + schodek;
                                min = (doleVlevo + dole + vlevo)/3 - schodek;

                                //Jestli je to posledni z prava
                            }else if (j == 2*i){

                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo)/2 + schodek;
                                min = (vlevo + doleVlevo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            } else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                max = (doleVlevo + dole + doleVpravo + vlevo)/4 + schodek;
                                min = (doleVlevo + dole + doleVpravo + vlevo)/4 - schodek;
                            }
                        }

                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                    //Hrana vpravo

                }else if (poc == 2){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2-i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX;
                        kordY = lokalniPocatekY + j;
                        System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                        System.out.println(" I = " + i + " j = " + j);
                        if (i == 1){
                            if (j == 1){
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + nahore+ nahoreVlevo)/3 + schodek;
                                min = (vlevo + nahore+ nahoreVlevo)/3 - schodek;

                            }else {
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVlevo + nahore)/2 + schodek;
                                min = (nahoreVlevo + nahore)/2 - schodek;
                            }

                        }else{
                            //Jestli je to druhy bod z hora
                            if (j == 1){
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                max = (vlevo + doleVlevo + nahore+ nahoreVlevo)/4 + schodek;
                                min = (vlevo + doleVlevo + nahore+ nahoreVlevo)/4 - schodek;

                                //Jestli je to druhy z dola
                            }else if (j == 2*i-1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo + vlevo+ nahore)/3 + schodek;
                                min = (nahoreVlevo + vlevo+ nahore)/3 - schodek;

                                //Jestli je to posledni z dola
                            }else if (j == 2*i){

                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (nahoreVlevo+ nahore)/2 + schodek;
                                min = (nahoreVlevo+ nahore)/2 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                doleVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                max = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 + schodek;
                                min = (doleVlevo + nahoreVlevo + vlevo + nahore)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana Dole
                }else if (poc == 3){
                    lokalniPocatekX = vetsiMapa.length/2+i;
                    lokalniPocatekY = vetsiMapa.length/2+i;

                    for (int j = 1; j <= 2*i;j++){

                        kordX = lokalniPocatekX - j;
                        kordY = lokalniPocatekY;
                        System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                        //Jestli je to prvni cyklus
                        if (i == 1){
                            if (j == 1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + vpravo + nahoreVpravo)/3 + schodek;
                                min = (nahore + vpravo + nahoreVpravo)/3 - schodek;

                            }else{
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (nahoreVpravo + vpravo)/2 + schodek;
                                min = (nahoreVpravo + vpravo)/2 - schodek;
                            }

                        }else{
                            //Jestli je to druhy bod z leva
                            if (j == 1){
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVlevo + vpravo + nahoreVpravo)/4 + schodek;
                                min = (nahore + nahoreVlevo + vpravo + nahoreVpravo)/4 - schodek;
                                //Jestli je to druhy z prava
                            }else if (j == 2*i-1){
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (nahore + nahoreVpravo + vpravo )/3 + schodek;
                                min = (nahore + nahoreVpravo + vpravo )/3 - schodek;
                                //Jestli je posledni Bod z leva
                            }else if (j == 2*i){
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();

                                max = (vpravo + nahoreVpravo)/2 + schodek;
                                min = (vpravo + nahoreVpravo)/2 - schodek;
                                //Jestli je to nekde uprostred
                            }else{
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                nahoreVlevo = vetsiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 + schodek;
                                min = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }

                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }
                    //hrana vlevo
                }else {
                    lokalniPocatekX = velikostPole/2-i;
                    lokalniPocatekY = velikostPole/2+i;
                    for (int j = 1; j <= 2*i;j++){
                        kordX = lokalniPocatekX ;
                        kordY = lokalniPocatekY- j;
                        System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                        //jestli Je to ten prvni cyklus
                        if (i == 1){
                            if (j == 1){

                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                max = (vpravo + dole + doleVpravo + nahoreVpravo)/4 + schodek;
                                min = (vpravo + dole + doleVpravo + nahoreVpravo)/4 - schodek;

                            }else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                max = (vpravo + dole + doleVpravo)/3 + schodek;
                                min = (vpravo + dole + doleVpravo)/3 - schodek;
                            }

                        }else{
                            //Jestli je to druhy bod z dola
                            if (j == 1){
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                max = (vpravo + nahoreVpravo+ doleVpravo)/3 + schodek;
                                min = (vpravo + nahoreVpravo+ doleVpravo)/3 - schodek;

                                //Jestli je to predposledni z hora
                            }else if (j == 2*i){

                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();

                                max = (dole + doleVpravo + vpravo)/3 + schodek;
                                min = (dole + doleVpravo + vpravo)/3 - schodek;

                                //Jestli je to nekde uprostred
                            }else{
                                dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();

                                max = (nahoreVpravo + vpravo + doleVpravo + dole)/4 + schodek;
                                min = (nahoreVpravo + vpravo + doleVpravo + dole)/4 - schodek;

                            }
                        }
                        if (min<MIN){
                            min = MIN;
                        }else if(max>MAX){
                            max = MAX;
                        }
                        vetsiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                    }

                }

            }
        }

        int zacatekX = vetsiMapa.length - x - vetsiMapa.length/2 ;
        int konecX = zacatekX + mapa.length;
        int zacatekY = vetsiMapa.length - y - vetsiMapa.length/2;
        int konecY = zacatekY + mapa.length;
        int t = -1;
        int k = -1;
        System.out.println("x= "+ x + " y= " + y);
        System.out.println("velikost Pole: " + velikostPole + " mapa.length: " + mapa.length);
        System.out.println("Zacatek x : " + zacatekX + " konecX: "+ konecX + " zacatekY: " + zacatekY + " konecY: " + konecY);

        for (int i = zacatekY; i < konecY; i++) {
            t++;
            for (int j = zacatekX;j<konecX;j++){
                k++;
                System.out.println(" t = " + t + " k = " + k);
                mapa[t][k] = vetsiMapa[i][j];
                mapa[t][k] = new Bod(t*bodSize,k*bodSize,vetsiMapa[i][j].getVyska().getvalueOfVyska());
            }
            k =-1;
        }

        return mapa;
    }

    public Bod[][] vygenerujCustomMapu4(Bod[] bodyNaMape){

        int vlevo;
        int nahoreVlevo;
        int nahoreVpravo;
        int nahore;
        int vpravo;
        int vpravoDole;
        int dole;
        int vlevoDole;
        int prumer;
        int max;
        int min;
        int vyska;

        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska zakladniVyska = new Vyska(MIN + schodek);

        //vytvori mapu
        for (int i = 0; i<mapa.length;i++){
            for (int j = 0;j<mapa.length;j++){
                //Jestli je to prvni bod
                if (i==0 && j==0){
                    Bod zakladniBod = new Bod(0, 0, zakladniVyska);
                    mapa[i][j] = zakladniBod;
                    prumer = zakladniVyska.getvalueOfVyska();
                }
                //Jestli je to prvni radek
                else if (i==0){
                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                    prumer = vlevo;
                }
                //jestli je to prvni bod jakehokoli radku
                else if (j==0){
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVpravo)/2;

                }
                //jestli je to posledni bod jakehokoli radku
                else if (j==mapa.length-1){
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo)/2;

                }
                //Jestli je to cokoli jineho
                else{
                    nahoreVlevo=mapa[i-1][j-1].getVyska().getvalueOfVyska();
                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                    prumer = (nahore + nahoreVlevo + nahoreVpravo)/3;
                }

                max = prumer + schodek;
                min = prumer - schodek;

                if (min<MIN){
                    min = MIN;
                }else if(max>MAX){
                    max = MAX;
                }
                vyska = vytvorVysku(max,min);
                Vyska v = new Vyska(vyska);
                Bod bod = new Bod(i*bodSize, j*bodSize, v);
                mapa[i][j]=bod;

            }
        }
        //Vytvori kolem vybranych body prazdne misto
        vytvorMapuSMezerama(mapa,bodyNaMape);


        for (int p = 0; p < bodyNaMape.length; p++) {
            for (int i = bodyNaMape[p].getCoordX()-10; i < bodyNaMape[p].getCoordX()+10; i++) {
                for (int j = bodyNaMape[p].getCoordY()-10; j < bodyNaMape[p].getCoordY()+10; j++) {
                    if (j ==0){

                    }
                }
            }
        }
        return null;
    }

    public Bod[][] vytvorMapuSMezerama(Bod[][] mapa, Bod[] bodyNaMape){

        for (int p = 0; p < bodyNaMape.length; p++) {
            for (int i = bodyNaMape[p].getCoordX()-10; i < bodyNaMape[p].getCoordX()+10; i++) {
                for (int j = bodyNaMape[p].getCoordY()-10; j < bodyNaMape[p].getCoordY()+10; j++) {
                    mapa[j][j] = null;
                }
            }
        }

        return mapa;
    }
}



