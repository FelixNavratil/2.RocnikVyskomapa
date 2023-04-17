package com.example.realnetahlevyskomapa;


import javafx.scene.paint.Color;

public class Vyska {

    private  int vyska;
    //public Color Barva;

    public Vyska(int vyska) {
        this.vyska = vyska;
    }

    public int getvalueOfVyska(){
        return vyska;
    }

    public Color getBarva() {

        if (vyska<0){
            return Color.TEAL;
        }else if (vyska<100){
            return Color.MINTCREAM;
        }else if (vyska<200){
            return Color.MISTYROSE;
        }else if (vyska<300){
            return Color.WHEAT;
        }else if (vyska<400){
            return Color.BLANCHEDALMOND;
        }else if (vyska<500){
            return Color.BISQUE;
        }else if (vyska<600){
            return Color.POWDERBLUE;
        }else if (vyska<700){
            return Color.AQUAMARINE;
        }else if (vyska<800){
            return Color.LIGHTGREEN;
        }else if (vyska<900){
            return Color.PALEGREEN;
        }else if (vyska<1000){
            return Color.LAWNGREEN;
        }else if (vyska<1250){
            return Color.CHARTREUSE;
        }else if (vyska<1500){
            return Color.YELLOWGREEN;
        }else if (vyska<1750){
            return Color.OLIVEDRAB;
        }else if (vyska<2000){
            return Color.OLIVE;
        }else if (vyska<2250){
            return Color.PERU;
        }else if (vyska<2500){
            return Color.CORAL;
        }else if (vyska<2750){
            return Color.DARKSALMON;
        }else if (vyska<3000){
            return Color.PERU;
        }else if (vyska<3250){
            return Color.RED;
        }else if (vyska<3500){
            return Color.CRIMSON;
        }else if (vyska<3750){
            return Color.SIENNA;
        }else if (vyska<4000){
            return Color.SADDLEBROWN;
        }else if (vyska<4250){
            return Color.FIREBRICK;
        }else if (vyska<4500){
            return Color.DARKRED;
        }else if (vyska<4750){
            return Color.MAROON;
        }else if (vyska<5000){
            return Color.GRAY;
        }else if (vyska<5250){
            return Color.DARKGRAY;
        }else if (vyska<5500){
            return Color.DIMGRAY;
        }else if (vyska<5750){
            return Color.CADETBLUE;
        }else if (vyska<6000){
            return Color.DEEPSKYBLUE;
        }else if (vyska<6250){
            return Color.DODGERBLUE;
        }else if (vyska<6500){
            return Color.ROYALBLUE;
        }else if (vyska<6750){
            return Color.BLUE;
        }else if (vyska<7000){
            return Color.MEDIUMBLUE;
        }else if (vyska<7250){
            return Color.DARKBLUE;
        }else if (vyska<7500){
            return Color.DARKSLATEBLUE;
        }else if (vyska<7750){
            return Color.MIDNIGHTBLUE;
        }else if (vyska<8000){
            return Color.DARKORCHID;
        }else if (vyska<8250){
            return Color.DARKVIOLET;
        }else if (vyska<8500){
            return Color.MEDIUMPURPLE;
        }else if (vyska<8750){
            return Color.BLUEVIOLET;
        }else if (vyska<9000){
            return Color.PURPLE;
        }else if (vyska<9250){
            return Color.DARKMAGENTA;
        }else if (vyska<9500){
            return Color.INDIGO;
        }else{
            return Color.BLACK;
        }



/*
        if (vyska<0)
        {
            return Color.WHITE;
        }
        else if (vyska<200)
        {
            return Color.LIGHTYELLOW;
        }
        else if (vyska<300)
        {
            return Color.LIGHTGOLDENRODYELLOW;
        }
        else if (vyska<400)
        {
            return Color.YELLOW;
        }
        else if (vyska<500)
        {
            return Color.YELLOWGREEN;
        }
        else if (vyska<600)
        {
            return Color.GREENYELLOW;
        }
        else if (vyska<700)
        {
            return Color.LIGHTGREEN;
        }
        else if (vyska<800)
        {
            return Color.LIMEGREEN;
        }
        else if (vyska<900)
        {
            return Color.GREEN;
        }
        else if (vyska<1000)
        {
            return Color.DARKGREEN;
        }
        else if (vyska<1500)
        {
            return Color.DARKOLIVEGREEN;
        }
        else if (vyska<2000)
        {
            return Color.BLUE;
        }
        else if (vyska<2500)
        {
            return Color.ALICEBLUE;
        }
        else if (vyska<3000)
        {
            return Color.CADETBLUE;
        }
        else if (vyska<3500)
        {
            return Color.DEEPSKYBLUE;
        }
        else if (vyska<4000)
        {
            return Color.DARKBLUE;
        }
        else if (vyska<4500)
        {
            return Color.BLUEVIOLET;
        }
        else if (vyska<5000)
        {
            return Color.VIOLET;
        }
        else if (vyska<5500)
        {
            return Color.DARKVIOLET;
        }
        else if (vyska<6000)
        {
            return Color.LIGHTPINK;
        }
        else if (vyska<6500)
        {
            return Color.PINK;
        }
        else if (vyska<7000)
        {
            return Color.DEEPPINK;
        }
        else if (vyska<7500)
        {
            return Color.ORANGERED;
        }
        else if (vyska<8000)
        {
            return Color.INDIANRED;
        }
        else if (vyska<8500)
        {
            return Color.RED;
        }
        else if (vyska<9000)
        {
            return Color.DARKRED;
        }
        else if (vyska<10000)
        {
            return Color.SANDYBROWN;
        }
        else if (vyska<20000)
        {
            return Color.ROSYBROWN;
        }
        else if (vyska<30000)
        {
            return Color.SADDLEBROWN;
        }
        else if (vyska<40000)
        {
            return Color.BROWN;
        }
        else if (vyska<50000)
        {
            return Color.DARKGRAY;
        }
        else
        {
            return Color.BLACK;
        }
*/

    }

    public Color getBarvaGPT(){
        if (vyska<0){
            return Color.TEAL;
        }else if (vyska<100){
            return Color.LIGHTGREEN;
        }else if (vyska<200){
            return Color.LAWNGREEN;
        }else if (vyska<300){
            return Color.CHARTREUSE;
        }else if (vyska<400){
            return Color.LIME;
        }else if (vyska<500){
            return Color.LIMEGREEN;
        }else if (vyska<600){
            return Color.MEDIUMSEAGREEN;
        }else if (vyska<700){
            return Color.SEAGREEN;
        }else if (vyska<800){
            return Color.DARKGREEN;
        }else if (vyska<900){
            return Color.GREEN;
        }else if (vyska<1000){
            return Color.FORESTGREEN;
        }else if (vyska<1250){
            return Color.DARKOLIVEGREEN;
        }else if (vyska<1500){
            return Color.OLIVEDRAB;
        }else if (vyska<1750){
            return Color.YELLOWGREEN;
        }else if (vyska<2000){
            return Color.BISQUE;
        }else if (vyska<2250){
            return Color.BLANCHEDALMOND;
        }else if (vyska<2500){
            return Color.BURLYWOOD;
        }else if (vyska<2750){
            return Color.KHAKI;
        }else if (vyska<3000){
            return Color.GOLD;
        }else if (vyska<3250){
            return Color.GOLDENROD;
        }else if (vyska<3500){
            return Color.DARKGOLDENROD;
        }else if (vyska<3750){
            return Color.ORANGE;
        }else if (vyska<4000){
            return Color.ORANGERED;
        }else if (vyska<4250){
            return Color.TOMATO;
        }else if (vyska<4500){
            return Color.CORAL;
        }else if (vyska<4750){
            return Color.SALMON;
        }else if (vyska<5000){
            return Color.SALMON;
        }else if (vyska<5250){
            return Color.LIGHTSALMON;
        }else if (vyska<5500){
            return Color.RED;
        }else if (vyska<5750){
            return Color.INDIANRED;
        }else if (vyska<6000){
            return Color.DARKSALMON;
        }else if (vyska<6250){
            return Color.PERU;
        }else if (vyska<6500){
            return Color.SADDLEBROWN;
        }else if (vyska<6750){
            return Color.SIENNA;
        }else if (vyska<7000){
            return Color.BROWN;
        }else if (vyska<7250){
            return Color.MAROON;
        }else if (vyska<7500){
            return Color.DARKRED;
        }else if (vyska<7750){
            return Color.FIREBRICK;
        }else if (vyska<8000){
            return Color.ROSYBROWN;
        }else if (vyska<8250){
            return Color.SIENNA;
        }else if (vyska<8500){
            return Color.CHOCOLATE;
        }else if (vyska<8750){
            return Color.ROSYBROWN;
        }else if (vyska<9000){
            return Color.GRAY;
        }else if (vyska<9250){
            return Color.LIGHTGRAY;
        }else if (vyska<9500){
            return Color.WHITESMOKE;
        }else{
            return Color.GHOSTWHITE;
        }
    }

    public Color getBarva2(){

        if (vyska<0){
            return Color.TEAL;
        }else if (vyska<100){
            return Color.MINTCREAM;
        }else if (vyska<200){
            return Color.MISTYROSE;
        }else if (vyska<300){
            return Color.WHEAT;
        }else if (vyska<400){
            return Color.BLANCHEDALMOND;
        }else if (vyska<500){
            return Color.BISQUE;
        }else if (vyska<600){
            return Color.POWDERBLUE;
        }else if (vyska<700){
            return Color.AQUAMARINE;
        }else if (vyska<800){
            return Color.LIGHTGREEN;
        }else if (vyska<900){
            return Color.PALEGREEN;
        }else if (vyska<1000){
            return Color.LAWNGREEN;
        }else if (vyska<1250){
            return Color.CHARTREUSE;
        }else if (vyska<1500){
            return Color.YELLOWGREEN;
        }else if (vyska<1750){
            return Color.OLIVEDRAB;
        }else if (vyska<2000){
            return Color.OLIVE;
        }else if (vyska<2250){
            return Color.PERU;
        }else if (vyska<2500){
            return Color.CORAL;
        }else if (vyska<2750){
            return Color.DARKSALMON;
        }else if (vyska<3000){
            return Color.PERU;
        }else if (vyska<3250){
            return Color.RED;
        }else if (vyska<3500){
            return Color.CRIMSON;
        }else if (vyska<3750){
            return Color.SIENNA;
        }else if (vyska<4000){
            return Color.SADDLEBROWN;
        }else if (vyska<4250){
            return Color.FIREBRICK;
        }else if (vyska<4500){
            return Color.DARKRED;
        }else if (vyska<4750){
            return Color.MAROON;
        }else if (vyska<5000){
            return Color.GRAY;
        }else if (vyska<5250){
            return Color.DARKGRAY;
        }else if (vyska<5500){
            return Color.DIMGRAY;
        }else if (vyska<5750){
            return Color.CADETBLUE;
        }else if (vyska<6000){
            return Color.DEEPSKYBLUE;
        }else if (vyska<6250){
            return Color.DODGERBLUE;
        }else if (vyska<6500){
            return Color.ROYALBLUE;
        }else if (vyska<6750){
            return Color.BLUE;
        }else if (vyska<7000){
            return Color.MEDIUMBLUE;
        }else if (vyska<7250){
            return Color.DARKBLUE;
        }else if (vyska<7500){
            return Color.DARKSLATEBLUE;
        }else if (vyska<7750){
            return Color.MIDNIGHTBLUE;
        }else if (vyska<8000){
            return Color.DARKORCHID;
        }else if (vyska<8250){
            return Color.DARKVIOLET;
        }else if (vyska<8500){
            return Color.MEDIUMPURPLE;
        }else if (vyska<8750){
            return Color.BLUEVIOLET;
        }else if (vyska<9000){
            return Color.PURPLE;
        }else if (vyska<9250){
            return Color.DARKMAGENTA;
        }else if (vyska<9500){
            return Color.INDIGO;
        }else if (vyska<9750){
            return Color.WHITE;
        }else{
            return Color.BLACK;
        }
    }
}