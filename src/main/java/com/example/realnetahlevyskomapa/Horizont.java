package com.example.realnetahlevyskomapa;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static com.example.realnetahlevyskomapa.Vyskomapa.bodSize;
import static com.example.realnetahlevyskomapa.Vyskomapa.velikostHorizontuY;

public class Horizont extends Rectangle {
    /**vytvor si novou scenu na horizont i na ovladaci panelk kde budu zadavat hlavni parametry**/

    private int x;
    private Vyska vyska1;


    public Horizont(int x, Vyska vyska1) {

        this.x = x;
        this.vyska1 = vyska1;
        setWidth(bodSize);
        setX(x*bodSize);
        setY(velikostHorizontuY);
        setFill(vyska1.getBarva());

    }

    public Color getBarva(){
        return vyska1.getBarva();
    }

}
