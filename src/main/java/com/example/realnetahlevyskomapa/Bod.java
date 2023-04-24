package com.example.realnetahlevyskomapa;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import static com.example.realnetahlevyskomapa.Vyskomapa.bodSize;


public class Bod extends Rectangle {

    private Vyska vyska1;
    private int vyska;
    private int coordX;
    private int coordY;

    public Bod(int coordX, int coordY, int vyska) {
        Vyska vyska2 = new Vyska(vyska);
        vyska1 = vyska2;
        this.vyska = vyska;
        setWidth(bodSize);
        setHeight(bodSize);
        if (vyska==0){
            setFill(Color.WHITE);
        }else{
            if (vyska1!=null){
                vyska1.setVyska(vyska);
                setFill(vyska1.getBarva2());
            }
        }
        setLayoutX(coordX);
        setLayoutY(coordY);
    }

    public Bod(int coordX, int coordY, Vyska vyska1) {
        this.vyska1 = vyska1;
        setWidth(bodSize);
        setHeight(bodSize);
        setFill(vyska1.getBarva2());
        setLayoutX(coordX);
        setLayoutY(coordY);

    }
    public Vyska setVyska(int vyska){
        vyska1.setVyska(vyska);
        return vyska1;
    }



    public Vyska getVyska(){
        return vyska1;
    }

    public Color getBarva(){
        if (vyska1!=null){
            return vyska1.getBarva();
        }else{
            return Color.WHITE;
        }

    }

    public int getCoordX(){
        return coordX;
    }

    public int getCoordY(){
        return coordY;
    }

    @Override
    public String toString() {
        return "Bod{" +
                "vyska1=" + vyska1 +
                ", vyska=" + vyska +
                ", coordX=" + coordX +
                ", coordY=" + coordY +
                '}';
    }
}
