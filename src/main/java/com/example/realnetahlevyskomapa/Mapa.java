package com.example.realnetahlevyskomapa;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Clock;
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
        //vytvori ctverec ktery prekryje to co tam je od minule
        Rectangle nalepka = new Rectangle(root.getScene().getWidth(), root.getScene().getHeight());
        nalepka.setFill(Color.WHITE);
        nalepka.setLayoutX(0);
        nalepka.setLayoutY(0);


        //vytvori label ktery ukazuje koordinace Y
        Label yKoordinace = new Label();
        yKoordinace.setPrefSize(50, 150);
        yKoordinace.setLayoutX(velikostMapy + 70);
        yKoordinace.setLayoutY(100);
        Font currentFont = yKoordinace.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 10);
        yKoordinace.setFont(newFont);

        //vytvori label ktery ukazuje koordinace X
        Label xKoordinace = new Label();
        xKoordinace.setPrefSize(50, 150);
        xKoordinace.setLayoutX(velikostMapy + 70);
        xKoordinace.setLayoutY(10);
        xKoordinace.setFont(newFont);

        //vytvori label ktery ukazuje vysku zvoleneho bodu
        Label labelVyska = new Label();
        labelVyska.prefHeight(50);
        labelVyska.setLayoutX(velikostMapy + 70);
        labelVyska.setLayoutY(266);
        labelVyska.setFont(newFont);

        //Ten text na koordinace x
        Label textXKoordinace = new Label("Souřadnice X: ");
        textXKoordinace.setFont(newFont);
        textXKoordinace.setLayoutY(20);
        textXKoordinace.setLayoutX(velikostMapy + 20);

        //text na souradnice Y
        Label textYKoordinace = new Label("Souřadnice Y: ");
        textYKoordinace.setFont(newFont);
        textYKoordinace.setLayoutY(113);
        textYKoordinace.setLayoutX(velikostMapy + 20);

        //text na vysku
        Label textVyska = new Label("Výška: ");
        textVyska.setFont(newFont);
        textVyska.setLayoutY(226);
        textVyska.setLayoutX(velikostMapy + 20);

        //vytvari root stage a scene na horizont
        Pane rootHorizont = new Pane();
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX + 100, velikostHorizontuY );
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        stageHorizont.setScene(sceneHorizont);

        //tlacitko ktere kdyz zmacknu generuje horizont
        Button buttonGenerujHorizont = new Button("Pohled ze strany");
        buttonGenerujHorizont.setPrefSize(151, 113);
        buttonGenerujHorizont.setLayoutX(velikostMapy + 24.5);
        buttonGenerujHorizont.setLayoutY(velikostMapy - 18 - 113);
        buttonGenerujHorizont.setOnMouseClicked(e->{
            generujHorizont(getMAPA(),rootHorizont,sceneHorizont);
            stageHorizont.show();
        });

        //tlacitko ktere vytvori novou prazdnou slepou mapu
        Button buttonGenerujJinouMapu = new Button("generuj jinou mapu");
        buttonGenerujJinouMapu.setPrefSize(151, 113);
        buttonGenerujJinouMapu.setLayoutX(buttonGenerujHorizont.getLayoutX());
        buttonGenerujJinouMapu.setLayoutY(buttonGenerujHorizont.getLayoutY()-5-buttonGenerujJinouMapu.getPrefHeight());


        //misto kte uzivatel napise vysku
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

        //ctverec na ktery kdyz uzivatel klikne tak se mu zobrazi souradnice kde klikl
        Rectangle souradnicovyCtverec = new Rectangle(velikostMapy,velikostMapy);
        souradnicovyCtverec.setLayoutY(0);
        souradnicovyCtverec.setLayoutX(0);
        souradnicovyCtverec.setOpacity(0.0);


        Vyska vyska = new Vyska();
        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];



        //tlacitko ktere prida body do pole (krome jinych)
        Button buttonZvolitVysku = new Button("Zvolit Tento Bod");
        buttonZvolitVysku.setPrefSize(buttonGenerujHorizont.getPrefWidth(),buttonGenerujHorizont.getPrefHeight()/2);
        buttonZvolitVysku.setLayoutY(buttonGenerujJinouMapu.getLayoutY() - 5 - buttonZvolitVysku.getPrefHeight());
        buttonZvolitVysku.setLayoutX(buttonGenerujHorizont.getLayoutX());


        Button buttonGenerujCustomMapu = new Button("Generuj Mapu");
        buttonGenerujCustomMapu.setPrefSize(buttonZvolitVysku.getPrefWidth(),buttonZvolitVysku.getPrefHeight());
        buttonGenerujCustomMapu.setLayoutY(buttonZvolitVysku.getLayoutY() - 5 - buttonGenerujCustomMapu.getPrefHeight());
        buttonGenerujCustomMapu.setLayoutX(buttonZvolitVysku.getLayoutX());

        buttonGenerujCustomMapu.setOnMouseClicked(e->{
            root.getChildren().removeAll(souradnicovyCtverec);
            setMapa(mapa,x,y,vyska,root,4);

            root.getChildren().add(souradnicovyCtverec);

        });

        buttonZvolitVysku.setOnMouseClicked(e->{
            System.out.println("buttonZvolitVysku.setOnMouseClicked");
            if (!Objects.equals(integerTextField.getText(), "")){
                vyska.setVyska(Integer.parseInt(integerTextField.getText()));
                if (pocet< (poleVybranychBodu).length){
                    System.out.println("pocet: " + pocet + " x: " + x + " y: " + y);
                    poleVybranychBodu[pocet] = new Bod(x,  y, vyska);
                    System.out.println(poleVybranychBodu[pocet]);
                    getPocet(1);
                }
                if (pocet == 1){
                    root.getChildren().add(buttonGenerujCustomMapu);
                }

            }
            else{
                System.out.println("Neni zadana Vyska" );
            }

        });

        buttonGenerujJinouMapu.setOnMouseClicked(e->{
            scenaVytvorMapu(root);
            getPocet(0);
            root.getChildren().removeAll(buttonGenerujCustomMapu);
        });

        souradnicovyCtverec.setOnMouseClicked((MouseEvent event)->{
            xKoordinace.setText(String.valueOf(getKordX(event.getX())));
            yKoordinace.setText(String.valueOf(getKordY(event.getY())));
            System.out.println(" x : " + x + "    " + " y : " + y);
            if (mapa[getKordX(event.getX())][getKordY(event.getY())]!=null){
                labelVyska.setText(String.valueOf(mapa[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska()));
                System.out.println(" x =" + getKordX(event.getX()) + " y = " + getKordY(event.getY()) + "vyska = " + mapa[getKordX(event.getX())][getKordY(event.getY())].getVyska().getvalueOfVyska());
            }else{

                System.out.println(" x =" + getKordX(event.getX()) + " y = " + getKordY(event.getY()) + " vyska = null" );
            }


        });

        root.getChildren().addAll(nalepka,souradnicovyCtverec, buttonGenerujJinouMapu, buttonGenerujHorizont, textVyska,textYKoordinace, textXKoordinace ,  labelVyska,
                xKoordinace, yKoordinace, integerTextField, buttonZvolitVysku);

    }

    public Bod[][] vygenerujCustomMapu1(int x, int y, Bod[][] mapa, Vyska vyska, Pane root) {
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

            }
            k =-1;
        }

        return mapa;
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

        System.out.println("Zacinam prochazet metodu vygenerujCustomMapu4");
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
        Vyska vyska1 = new Vyska();

        System.out.println("Zacinam vytvaret prvni mapu");
        int nejvyssiVyska = Integer.MIN_VALUE;
        int nejnizsiVyska = Integer.MAX_VALUE;

        for (int i = 0; i < bodyNaMape.length; i++) {
            if (bodyNaMape[i]!=null){
                if (nejvyssiVyska<bodyNaMape[i].getVyska().getvalueOfVyska()){
                    nejvyssiVyska = bodyNaMape[i].getVyska().getvalueOfVyska();
                }
                if (nejnizsiVyska > bodyNaMape[i].getVyska().getvalueOfVyska()){
                    nejnizsiVyska = bodyNaMape[i].getVyska().getvalueOfVyska();
                }
            }


        }

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
                }else if(max>nejvyssiVyska + 10*schodek){
                    max = nejvyssiVyska + 10*schodek;
                }
                vyska = vytvorVysku(max,min);
                Vyska v = new Vyska(vyska);
                Bod bod = new Bod(i*bodSize, j*bodSize, v);
                mapa[i][j]=bod;

            }
        }

        //Vytvori kolem vybranych body prazdne misto
        mapa = vytvorMapuSMezerama(mapa,bodyNaMape);

        int prumerNahore = 0;
        int prumerDole = 0;
        boolean [] jeVyssiNahore = new boolean[bodyNaMape.length];
        boolean [] jeVyssiDole = new boolean[bodyNaMape.length];

        System.out.println("jdu na zjistovani prumeruu");
        //zjistovani prumeru
        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                for (int i = 0; i < 2; i++) {
                    for (int j = bodyNaMape[p].getCoordX() - 11; j < bodyNaMape[p].getCoordX() + 11; j++) {

                        if (i == 0) {
                            if (mapa[bodyNaMape[p].getCoordY()-11][j]!=null){
                                prumerNahore += mapa[bodyNaMape[p].getCoordY()-11][j].getVyska().getvalueOfVyska();
                            }

                        }else{

                            if (mapa[bodyNaMape[p].getCoordY()+11][j]!=null){
                                prumerDole += mapa[bodyNaMape[p].getCoordY()+11][j].getVyska().getvalueOfVyska();
                            }

                        }
                    }
                }

                jeVyssiDole[p] = prumerDole > bodyNaMape[p].getVyska().getvalueOfVyska();

                if (prumerNahore>bodyNaMape[p].getVyska().getvalueOfVyska()){
                    jeVyssiNahore[p] = true;
                }else{
                    jeVyssiNahore[p] = false;
                }
            }
        }

        System.out.println("jdu na vyplnovani mezer");
        mapa = vyplnMezery2(mapa,bodyNaMape , prumerDole, prumerNahore);




/*
        for (int p = 0; p < bodyNaMape.length; p++) {
            //Jestli momentalni polozka v poli neni null
            if (bodyNaMape[p]!=null){

                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {

                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {
                        //Jestli to neni vybrany bod
                        if (i!= bodyNaMape[p].getCoordY() && j != bodyNaMape[p].getCoordX()){

                            //jestli to neni posledni radek
                            if (i!=bodyNaMape[p].getCoordX()+9){

                                //jestli je to prvni sloupec
                                if (j ==bodyNaMape[p].getCoordY()-10){
                                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                    prumer = (vlevo + nahore + nahoreVlevo + nahoreVpravo)/4;

                                    //jestli to je posledni sloupec
                                }else if (j == bodyNaMape[p].getCoordY()+9){
                                    vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + nahore + nahoreVlevo + nahoreVpravo)/4;

                                    //jestli to je uprostred
                                }else{
                                    System.out.println(" i-1 = " + (i-1));
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    System.out.println("i-1 " +(i-1) + " j+1 " + (j+1));
                                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo)/3;
                                }

                            }else{
                                //jestli je to prvni sloupec
                                if (j == bodyNaMape[p].getCoordY()-10){
                                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][i+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    vlevoDole = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                                    vpravoDole = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo + dole + vlevoDole + vpravoDole + vlevo)/7;

                                    //jestli je to posledni sloupec
                                }else if (j == bodyNaMape[p].getCoordY()+9){
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][i+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    vlevoDole = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                                    vpravoDole = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo + dole + vlevoDole + vpravoDole + vpravo)/7;

                                    //jestli to je uprostred
                                }else{
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][i+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    vlevoDole = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                                    vpravoDole = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo + dole + vlevoDole + vpravoDole)/6;
                                }
                            }

                            //jestli se pohybuju nad vybranym bodem
                            if (i<bodyNaMape[p].getCoordY()){
                                //jestli je to nahore vyssi nez je muj bod
                                if (jeVyssiNahore[p]){
                                    //postara se o to aby vyska jen klesala
                                    min = prumer - schodek;
                                    max = prumer;
                                }else{
                                    //postara se o to aby vyska jen stoupala
                                    min = prumer;
                                    max = prumer + schodek;
                                }
                                vyska1.setVyska(vytvorVysku(max,min));
                                mapa[i][j] = new Bod(i*bodSize,j*bodSize, vyska1);
                            }else{
                                //zjisti jestli je to dole vyssi nez muj bod
                                if (jeVyssiDole[p]){
                                    //postara se o to aby vyska jen klesala
                                    min = prumer - schodek;
                                    max = prumer;
                                }else{
                                    //postara se o to aby vyska jen stoupala
                                    min = prumer;
                                    max = prumer + schodek;
                                }
                                vyska1.setVyska(vytvorVysku(max,min));
                                mapa[i][j] = new Bod(i*bodSize,j*bodSize, vyska1);
                            }

                        }//konec podminky kontrolujici jestli to neni vybrany bod
                    }
                }

            }
        }
*/
        System.out.println("vracim jiz zmenenou mapu");
        return mapa;
    }


    public Bod[][] vyplnMezery2(Bod[][] mapa, Bod[] bodyNaMape, int prumerDole, int prumerNahore){
        int nahoreVlevo;
        int nahoreVpravo;
        int nahore;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int doleVpravo;
        int dole;
        int prumer;
        int prumerRady =prumerNahore;
        int[] schodekNadBodem = new int[bodyNaMape.length];
        int[] schodekPodBodem = new int[bodyNaMape.length];
        Vyska vyska = new Vyska();

        for (int i = 0; i < bodyNaMape.length; i++) {
            if (bodyNaMape[i]!=null){
                schodekNadBodem[i] = (prumerNahore + bodyNaMape[i].getVyska().getvalueOfVyska())/10;
                schodekPodBodem[i] = (prumerDole + bodyNaMape[i].getVyska().getvalueOfVyska())/10;
            }
        }

        //prochazi pole vybranych bodu
        for (int p = 0; p < bodyNaMape.length; p++) {

            if (bodyNaMape[p] !=null){

                //prochazi pole po ose y
                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {

                    //prochazi pole po ose x
                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {

                        //jestli neprochazi vybrany bod
                        if ((i==bodyNaMape[p].getCoordY()) && (j==bodyNaMape[p].getCoordX())){
                            mapa[i][j] = bodyNaMape[p];
                        }else{

                            //je to posledni bod z leva
                            if (j==bodyNaMape[p].getCoordX()+10-1){

                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                prumer = (nahore + nahoreVpravo + nahoreVlevo + vpravo + doleVpravo)/5;

                            //jestli je to posledni radek
                            }else if (i == bodyNaMape[p].getCoordY()+10 -1){
                                //posledni bod z leva
                                if (j==bodyNaMape[p].getCoordX()+10-1){

                                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                    vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    doleVlevo = mapa[i+1][j].getVyska().getvalueOfVyska();
                                    doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVpravo + nahoreVlevo + vlevo + doleVlevo + vpravo +  dole + doleVpravo)/8;

                                //jestli je to nekde uprostred
                                }else{
                                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                    doleVlevo = mapa[i+1][j].getVyska().getvalueOfVyska();
                                    doleVpravo = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i+1][j].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVpravo + nahoreVlevo + vlevo + doleVlevo + dole + doleVpravo)/7;
                                }
                            //jstli je to nekde uprostred
                            }else {

                                nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                doleVlevo = mapa[i-1][j].getVyska().getvalueOfVyska();
                                nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                prumer = (nahore + nahoreVpravo + nahoreVlevo + vlevo + doleVlevo)/5;

                            }

/*
                            //jestli je to prvni radek a jeho prumerna vyska je mensi nez vyska vybraneho bodu
                            if (i == bodyNaMape[p].getCoordY()-10 && prumerNahore<bodyNaMape[p].getVyska().getvalueOfVyska()){
                                vyska.setVyska(vytvorVysku(prumer+schodek,prumer - schodek/2));

                                //Jestli je to prvni radek a jeho prumerna vyska je vetsi nez vyska vybraneho bodu
                            }else if (i == bodyNaMape[p].getCoordY()-10 && prumerNahore>bodyNaMape[p].getVyska().getvalueOfVyska()){
                                vyska.setVyska(vytvorVysku(prumer + schodek/2,prumer-schodek));

                                //jestli se pohybuju pod vybranym bodem a prumerna vyska dole nizsi nez prumerna vyska radku
                            }else if (i>bodyNaMape[p].getCoordY() && prumerRady>prumerDole){
                                vyska.setVyska(vytvorVysku(prumer + schodek/2,prumer-schodek));

                                //Jestli se nachazim pod vybranym bodem a prumerna vyska rady je nizsi nez prumerna vyska radku dole
                            }else if (i>bodyNaMape[p].getCoordY() && prumerRady<prumerDole){
                                vyska.setVyska(vytvorVysku(prumer+schodek,prumer - schodek/2));

                                //jestli se nachazim nad vybranym bodem a prumerna vyska rady je vyssi nez vyska vybraneho bodu
                            }else if (i<bodyNaMape[p].getCoordY() && prumerRady>bodyNaMape[p].getVyska().getvalueOfVyska()){
                                vyska.setVyska(vytvorVysku(prumer + schodek/2,prumer-schodek));

                                //nachazim-li se nad vybranym bodem a jeho vyska je vyssi nez prume rady
                            }else if (i<bodyNaMape[p].getCoordY() && prumerRady<bodyNaMape[p].getVyska().getvalueOfVyska()){
                                vyska.setVyska(vytvorVysku(prumer+schodek,prumer - schodek/2));
                            }
                            */
                            if (i== bodyNaMape[p].getCoordY()-10){

                                if (prumerNahore>bodyNaMape[p].getVyska().getvalueOfVyska()){
                                    vyska.setVyska(prumer-schodekNadBodem[p]);
                                }else{
                                    vyska.setVyska(prumer+schodekNadBodem[p]);
                                }

                            }else if (i< bodyNaMape[p].getCoordY()){

                                if (prumerRady>bodyNaMape[p].getVyska().getvalueOfVyska()){
                                    vyska.setVyska(prumer-schodekNadBodem[p]);
                                }else{
                                    vyska.setVyska(prumer+schodekNadBodem[p]);
                                }

                            }else if (i ==  bodyNaMape[p].getCoordY()){

                                vyska.setVyska(vytvorVysku(prumer+schodek,prumer-schodek));

                            }else{

                                if (prumerDole>bodyNaMape[p].getVyska().getvalueOfVyska()){
                                    vyska.setVyska(prumer+schodekNadBodem[p]);
                                }else{
                                    vyska.setVyska(prumer-schodekNadBodem[p]);
                                }

                            }

                            prumerRady+=vyska.getvalueOfVyska();
                            mapa[i][j] = new Bod(j*bodSize,i*bodSize, vyska);
                            System.out.println("prave jsem pridal tento bod" + mapa[i][j] + "\n");

                        }
                    }
                    prumerRady /=20;
                }
            }

        }
        return mapa;
    }

    public Bod[][] vyplnMezery1(Bod[][] mapa, Bod[] bodyNaMape, boolean[] jeVyssiNahore, boolean[] jeVyssiDole){
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


        Vyska vyska = new Vyska();
        /*
        for (int p = 0; p < bodyNaMape.length; p++) {
            //Jestli momentalni polozka v poli neni null
            if (bodyNaMape[p]!=null){

                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {

                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {
                        //Jestli to neni vybrany bod
                        if (i!= bodyNaMape[p].getCoordY() && j != bodyNaMape[p].getCoordX()){

                            //jestli pohybuju nad Vybranym bodem

                            //jestli se pohybuju na stejne radce kde je bod

                            //Jestli jsem tam kde je vybrany bod

                            //Jestli se pohybuju pod bodem


                                //jestli je to prvni sloupec
                                if (j ==bodyNaMape[p].getCoordY()-10){
                                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                    prumer = (vlevo + nahore + nahoreVlevo + nahoreVpravo)/4;

                                    //jestli to je posledni sloupec
                                }else if (j == bodyNaMape[p].getCoordY()+9){
                                    vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + nahore + nahoreVlevo + nahoreVpravo)/4;

                                    //jestli to je uprostred
                                }else{
                                    System.out.println(" i-1 = " + (i-1));
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    System.out.println("i-1 " +(i-1) + " j+1 " + (j+1));
                                    nahoreVpravo = mapa[i-1][j+1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo)/3;
                                }


                                //jestli je to prvni sloupec
                                if (j == bodyNaMape[p].getCoordY()-10){
                                    vlevo = mapa[i][j-1].getVyska().getvalueOfVyska();
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][i+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    vlevoDole = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                                    vpravoDole = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo + dole + vlevoDole + vpravoDole + vlevo)/7;

                                    //jestli je to posledni sloupec
                                }else if (j == bodyNaMape[p].getCoordY()+9){
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][i+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    vlevoDole = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                                    vpravoDole = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    vpravo = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo + dole + vlevoDole + vpravoDole + vpravo)/7;

                                    //jestli to je uprostred
                                }else{
                                    nahore = mapa[i-1][j].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mapa[i-1][j-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mapa[i-1][i+1].getVyska().getvalueOfVyska();
                                    dole = mapa[i][j+1].getVyska().getvalueOfVyska();
                                    vlevoDole = mapa[i+1][j-1].getVyska().getvalueOfVyska();
                                    vpravoDole = mapa[i+1][j+1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + nahoreVpravo + dole + vlevoDole + vpravoDole)/6;
                                }


                            //jestli se pohybuju nad vybranym bodem
                            if (i<bodyNaMape[p].getCoordY()){
                                //jestli je to nahore vyssi nez je muj bod
                                if (jeVyssiNahore[p]){
                                    //postara se o to aby vyska jen klesala
                                    min = prumer - schodek;
                                    max = prumer;
                                }else{
                                    //postara se o to aby vyska jen stoupala
                                    min = prumer;
                                    max = prumer + schodek;
                                }
                                vyska1.setVyska(vytvorVysku(max,min));
                                mapa[i][j] = new Bod(i*bodSize,j*bodSize, vyska1);
                            }else{
                                //zjisti jestli je to dole vyssi nez muj bod
                                if (jeVyssiDole[p]){
                                    //postara se o to aby vyska jen klesala
                                    min = prumer - schodek;
                                    max = prumer;
                                }else{
                                    //postara se o to aby vyska jen stoupala
                                    min = prumer;
                                    max = prumer + schodek;
                                }
                                vyska1.setVyska(vytvorVysku(max,min));
                                mapa[i][j] = new Bod(i*bodSize,j*bodSize, vyska1);
                            }

                        }//konec podminky kontrolujici jestli to neni vybrany bod
                    }
                }

            }
        }
        */
        for (int i = 0; i < bodyNaMape.length; i++) {

            if (bodyNaMape[i]!=null){
                System.out.println(i + ". bod" + bodyNaMape[i]);
                System.out.println("bodyNaMape[i].getCoordY()-10= " +( bodyNaMape[i].getCoordY()-10));
                System.out.println("bodyNaMape[i].getCoordY() " +( bodyNaMape[i].getCoordY()));
                System.out.println("bodyNaMape[i].getCoordX()-10 " +( bodyNaMape[i].getCoordX()-10));
                System.out.println("bodyNaMape[i].getCoordX()+10 " +( bodyNaMape[i].getCoordX()+10));
                for (int y = bodyNaMape[i].getCoordY()-10; y<bodyNaMape[i].getCoordY();y++){

                    for (int x = bodyNaMape[i].getCoordX()-10; x < bodyNaMape[i].getCoordX()+10; x++) {
                        System.out.println("----------------------------------------");
                        System.out.println(" x = " + x + " y = " + y);
                        nahore = mapa[y-1][x].getVyska().getvalueOfVyska();
                        nahoreVlevo = mapa[y-1][x-1].getVyska().getvalueOfVyska();
                        nahoreVpravo = mapa[y-1][x+1].getVyska().getvalueOfVyska();
                        if (x == bodyNaMape[i].getCoordX()+9){
                            System.out.println("y = " + y + " x-1= " + (x-1) + "ten bod s errorem: " + mapa[y][x-1]);
                            vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            vpravo = mapa[y][x+1].getVyska().getvalueOfVyska();
                            prumer = (vlevo+ vpravo + nahoreVpravo + nahoreVlevo + nahore)/5;
                        }else{
                            //vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            prumer = ( nahoreVpravo + nahoreVlevo + nahore)/3;
                        }

                        if (jeVyssiNahore[i]){
                            vyska.setVyska(vytvorVysku(prumer,prumer-schodek));
                        }else{
                            vyska.setVyska(vytvorVysku(prumer+schodek,prumer));
                        }
                        mapa[y][x] = new Bod(x*bodSize,y*bodSize,vyska);
                        System.out.println("bod je zapsan|" + mapa[x][y]);
                    }
                }

                for (int y =  bodyNaMape[i].getCoordY()+10; y > bodyNaMape[i].getCoordY(); y--) {

                    for (int x = bodyNaMape[i].getCoordX()-10; x < bodyNaMape[i].getCoordX()+10; x++) {

                        if (x == bodyNaMape[i].getCoordX()+9){
                            dole = mapa[y+1][x].getVyska().getvalueOfVyska();
                            vlevoDole = mapa[y+1][x-1].getVyska().getvalueOfVyska();
                            vpravoDole = mapa[y+1][x+1].getVyska().getvalueOfVyska();
                            vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            vpravo = mapa[y][x+1].getVyska().getvalueOfVyska();
                            prumer = (vpravo + vlevo + vlevoDole + vpravoDole + dole)/5;
                        }else{
                            dole = mapa[y+1][x].getVyska().getvalueOfVyska();
                            vlevoDole = mapa[y+1][x-1].getVyska().getvalueOfVyska();
                            vpravoDole = mapa[y+1][x+1].getVyska().getvalueOfVyska();
                            vlevo = mapa[y][x-1].getVyska().getvalueOfVyska();
                            prumer = ( vlevo + vlevoDole + vpravoDole + dole)/4;
                        }

                        if (jeVyssiDole[i]){
                            vyska.setVyska(vytvorVysku(prumer,prumer-schodek));
                        }else{
                            vyska.setVyska(vytvorVysku(prumer+schodek,prumer));
                        }
                        mapa[y][x] = new Bod(x*bodSize,y*bodSize,vyska);
                    }

                }

            }
        }

        return mapa;
    }

    public Bod[][] vyplnMezery3(Bod[][] mapa, Bod[] bodyNaMape, int prumerDole, int prumerNahore){

        Bod[][] mensiMapa = new Bod[19][19];
        int max;
        int min;
        int lokalniPocatekX;
        int lokalniPocatekY;
        int kordX;
        int kordY;
        int prumer;
        int nahoreVlevo;
        int nahore;
        int nahoreVpravo;
        int vlevo;
        int vpravo;
        int doleVlevo;
        int dole;
        int doleVpravo;

        System.out.println("jdu na cyklus");
        System.out.println("vleikostPole/2: " + mensiMapa.length/2);

//Vytvareni mapy
        for (int p = 0;p< bodyNaMape.length;p++){


            for (int i = 1;i<=mensiMapa.length/2;i++){

                for (int poc =1;poc<=4;poc++){

                    //Horni hrana ctverce
                    System.out.println(" i : " + i);
                    if (poc == 1){

                        //zjisti z jakeho bodu se zacnu pohybovat
                        lokalniPocatekX = mensiMapa.length/2-i;
                        lokalniPocatekY = mensiMapa.length/2-i;

                        //j urcuje jak daleko jsem od lokalniho pocatku
                        for (int j = 1; j <= 2*i;j++){

                            //kord proto abych to mel oznaecene jasne
                            kordX = lokalniPocatekX + j;
                            kordY = lokalniPocatekY;
                            System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                            System.out.println("j: " + j + " i: " + i);

                            //jestli jsem vzdalen jen jeden bod od pocatecniho bodu
                            if (i == 1){

                                //jestli je to prvni bod
                                if (j == 1){
                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    prumer = dole;

                                //jestli je to nejakej dalsi bod
                                }else{
                                    doleVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (vlevo + doleVlevo)/2;

                                }
                            //jestli to neni prvni kolo kolem pocatecniho bodu
                            }else{
                                //Jestli je to posledni bod z leva
                                if (j == 1){
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    dole = mensiMapa[kordX+1][kordY+2].getVyska().getvalueOfVyska();
                                    prumer = (doleVpravo + dole)/2;

                                    //Jestli je to predposledni z leva
                                }else if (j == 2*i-1){
                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    doleVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();

                                    prumer = (doleVlevo + dole + vlevo)/3;


                                    //Jestli je to posledni z prava
                                }else if (j == 2*i){

                                    doleVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (vlevo + doleVlevo)/2;

                                    //Jestli je to nekde uprostred
                                } else{
                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    doleVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (doleVlevo + dole + doleVpravo + vlevo)/4;

                                }
                            }

                            mensiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                        }

                        //Hrana vpravo
                    }else if (poc == 2){

                        lokalniPocatekX = mensiMapa.length/2+i;
                        lokalniPocatekY = mensiMapa.length/2-i;

                        for (int j = 1; j <= 2*i;j++){

                            kordX = lokalniPocatekX;
                            kordY = lokalniPocatekY + j;
                            System.out.println("poc: "+poc+  " kordX: " + kordX + "   kordY: " + kordY);
                            System.out.println(" I = " + i + " j = " + j);
                            if (i == 1){
                                if (j == 1){
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (vlevo + nahore+ nahoreVlevo)/3;


                                }else {
                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (nahoreVlevo + nahore)/2;

                                }

                            }else{
                                //Jestli je to druhy bod z hora
                                if (j == 1){
                                    doleVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    prumer =  (vlevo + doleVlevo + nahore+ nahoreVlevo)/4 ;


                                    //Jestli je to druhy z dola
                                }else if (j == 2*i-1){
                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (nahoreVlevo + vlevo+ nahore)/3;


                                    //Jestli je to posledni z dola
                                }else if (j == 2*i){

                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (nahoreVlevo+ nahore)/2;

                                    //Jestli je to nekde uprostred
                                }else{
                                    doleVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mensiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
                                    vlevo = mensiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (doleVlevo + nahoreVlevo + vlevo + nahore)/4;


                                }
                            }

                            mensiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                        }
                        //hrana Dole
                    }else if (poc == 3){
                        lokalniPocatekX = mensiMapa.length/2+i;
                        lokalniPocatekY = mensiMapa.length/2+i;

                        for (int j = 1; j <= 2*i;j++){

                            kordX = lokalniPocatekX - j;
                            kordY = lokalniPocatekY;
                            System.out.println("poc: "+poc+  "kordX: " + kordX + "   kordY: " + kordY);
                            //Jestli je to prvni cyklus
                            if (i == 1){
                                if (j == 1){
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + vpravo + nahoreVpravo)/3;


                                }else{
                                    nahoreVpravo = mensiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (nahoreVpravo + vpravo)/2;

                                }

                            }else{
                                //Jestli je to druhy bod z leva
                                if (j == 1){
                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVlevo + vpravo + nahoreVpravo)/4;

                                    //Jestli je to druhy z prava
                                }else if (j == 2*i-1){
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (nahore + nahoreVpravo + vpravo )/3;

                                    //Jestli je posledni Bod z leva
                                }else if (j == 2*i){
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + nahoreVpravo)/2;

                                    //Jestli je to nekde uprostred
                                }else{
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY-1].getVyska().getvalueOfVyska();
                                    nahoreVlevo = mensiMapa[kordX-1][kordY-1].getVyska().getvalueOfVyska();
                                    nahore = mensiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
                                    prumer = (nahoreVpravo + nahoreVlevo + nahore + vpravo )/4;


                                }
                            }


                            mensiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                        }
                        //hrana vlevo
                    }else {
                        lokalniPocatekX = mensiMapa.length/2-i;
                        lokalniPocatekY = mensiMapa.length/2+i;
                        for (int j = 1; j <= 2*i;j++){
                            kordX = lokalniPocatekX ;
                            kordY = lokalniPocatekY- j;
                            System.out.println("kordX: " + kordX + "   kordY: " + kordY);
                            //jestli Je to ten prvni cyklus
                            if (i == 1){
                                if (j == 1){

                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + dole + doleVpravo + nahoreVpravo)/4;


                                }else{
                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + dole + doleVpravo)/3;

                                }

                            }else{
                                //Jestli je to druhy bod z dola
                                if (j == 1){
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (vpravo + nahoreVpravo+ doleVpravo)/3;


                                    //Jestli je to predposledni z hora
                                }else if (j == 2*i){

                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    prumer = (dole + doleVpravo + vpravo)/3;


                                    //Jestli je to nekde uprostred
                                }else{
                                    dole = mensiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
                                    nahoreVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    vpravo = mensiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
                                    doleVpravo = mensiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
                                    prumer = (nahoreVpravo + vpravo + doleVpravo + dole)/4;

                                }
                            }
                            mensiMapa[kordX][kordY] = new Bod(kordX,kordY,vytvorVysku(max,min));
                        }
                    }
                }
            }
        }

        //musis udelat to aby si pripsal body z mensi mapy na normalni mapu
        return mensiMapa;
    }

    public Bod[][] vytvorMapuSMezerama(Bod[][] mapa, Bod[] bodyNaMape){

        System.out.println("tvorim mezery");

        for (int p = 0; p < bodyNaMape.length; p++) {
            if (bodyNaMape[p]!=null){
                for (int i = bodyNaMape[p].getCoordY()-10; i < bodyNaMape[p].getCoordY()+10; i++) {
                    for (int j = bodyNaMape[p].getCoordX()-10; j < bodyNaMape[p].getCoordX()+10; j++) {
                        mapa[i][j] = null;
                    }
                }
            }
        }
        System.out.println("hotovo mezery");
        return mapa;
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
    public void setMapa(Bod [][] mapaKterouChciZmenit,int x, int y, Vyska vyska, Pane root, int verze){
        if (verze == 1){
            MAPA = vygenerujCustomMapu1(x,y,mapaKterouChciZmenit,vyska, root);
        }else if(verze == 2){
            MAPA = vygenerujCustomMapu2(x,y,mapaKterouChciZmenit,vyska, root);
        } else if (verze == 3) {
            MAPA = vygenerujCustomMapu3(x,y,mapaKterouChciZmenit,vyska, root);
        }else if (verze == 4){
            MAPA = vygenerujCustomMapu4(poleVybranychBodu);
        }

            for (int i = 0; i< mapaKterouChciZmenit.length;i++){
                for (int j = 0;j< mapaKterouChciZmenit.length;j++){
                    if (!root.getChildren().contains(MAPA[i][j])){
                        root.getChildren().add(MAPA[i][j]);
                    }else{

                        System.out.println(MAPA[i][j]);
                    }
                }
            }


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


}




