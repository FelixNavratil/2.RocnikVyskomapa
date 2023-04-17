package com.example.realnetahlevyskomapa;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;

import static com.example.realnetahlevyskomapa.Vyskomapa.bodSize;
import static com.example.realnetahlevyskomapa.Vyskomapa.schodek;
import static com.example.realnetahlevyskomapa.Vyskomapa.velikostMapy;
import static com.example.realnetahlevyskomapa.Vyskomapa.MAX;
import static com.example.realnetahlevyskomapa.Vyskomapa.MIN;


public class Mapa extends Vyskomapa{

     int x;
     int y;

    public int getKordX(double dX){
        x = (int)dX/bodSize;
        return x;
    }
    public int getKordY(double dY){
        y = (int)dY/bodSize;
        return y;
    }

    public int vytvorVysku(int max, int min){
        Random random = new Random();
        return random.nextInt(min,max);
    }



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

        double dilek = (double)(velikostHorizontuY)/(double)(m);
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

        Text text2 = new Text(String.valueOf(m/4));
        text2.setLayoutY(velikostHorizontuY - velikostHorizontuY/4 + 4);
        text2.setLayoutX(meritko.getStartX()-20 - 20);

        Text text3 = new Text(String.valueOf(m/2));
        text3.setLayoutY(velikostHorizontuY/2 + 4);
        text3.setLayoutX(meritko.getStartX()-20 - 20);

        Text text4 = new Text(String.valueOf(3*m/4));
        text4.setLayoutY(velikostHorizontuY/4 + 4);
        text4.setLayoutX(meritko.getStartX()-20 - 20);

        Text text5 = new Text(String.valueOf(m));
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

}
