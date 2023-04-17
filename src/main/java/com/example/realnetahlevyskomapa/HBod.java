package com.example.realnetahlevyskomapa;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static com.example.realnetahlevyskomapa.Vyskomapa.*;

public class HBod extends Line {
    private Vyska vyska;

    public HBod(int x, double dilek, Vyska vyska){
        this.vyska = vyska;
        setStrokeWidth(bodSize);
        setStartX(x*bodSize);
        setEndX(x*bodSize);
        setStartY(velikostHorizontuY);
        setEndY(velikostHorizontuY - vyska.getvalueOfVyska()*dilek);
        setStroke(vyska.getBarvaGPT());

    }

    public int getVyska(){
        return vyska.getvalueOfVyska();
    }



}
