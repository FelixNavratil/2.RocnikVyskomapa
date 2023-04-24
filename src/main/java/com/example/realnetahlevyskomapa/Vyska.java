package com.example.realnetahlevyskomapa;


import javafx.scene.paint.Color;

public class Vyska {

    private  int vyska;
    public Vyska(){

    }

    public Vyska(int vyska) {
        this.vyska = vyska;
    }

    public int setVyska(int v){
        this.vyska = v;
        return v;
    }

    @Override
    public String toString() {
        return "Vyska{" +
                "vyska=" + vyska +
                '}';
    }
    /*
    nahoreVlevo = vetsiMapa[kordX-1][kordY+1].getVyska().getvalueOfVyska();
    nahore = vetsiMapa[kordX][kordY-1].getVyska().getvalueOfVyska();
    nahoreVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
    vlevo = vetsiMapa[kordX-1][kordY].getVyska().getvalueOfVyska();
    vpravo = vetsiMapa[kordX+1][kordY].getVyska().getvalueOfVyska();
    doleVlevo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
    dole = vetsiMapa[kordX][kordY+1].getVyska().getvalueOfVyska();
    doleVpravo = vetsiMapa[kordX+1][kordY+1].getVyska().getvalueOfVyska();
     */

    public int getvalueOfVyska(){
        return vyska;
    }

    public Color getBarva() {

        if (vyska<=0){
            return Color.WHITE;
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
    public Color getBarva2(){

        if ( vyska < 2){
            return Color.rgb(0x40, 0xd0, 0xff);
        }else if ( vyska < 70){
            return Color.rgb(0x40, 0xff, 0xa7);
        }else if ( vyska < 216){
            return  Color.rgb(0x40, 0xff, 0x60);
        }else if ( vyska < 418){
            return Color.rgb(0x59, 0xff, 0x40);
        }else if ( vyska < 669){
            return  Color.rgb(0x8c, 0xff, 0x40);
        }else if ( vyska < 964){
            return  Color.rgb(0xb9, 0xff, 0x40);

        }else if ( vyska < 1300){
            return  Color.rgb(0xe4, 0xff, 0x40);

        }else if ( vyska < 1674){
            return  Color.rgb(0xff, 0xf3, 0x40);

        }else if ( vyska < 2084){
            return  Color.rgb(0xff, 0xce, 0x40);

        }else if ( vyska < 2529){
            return  Color.rgb(0xff, 0xaa, 0x40);

        }else if ( vyska < 3006){
            return  Color.rgb(0xff, 0x88, 0x40);

        }else if ( vyska < 3515){
            return  Color.rgb(0xff, 0x67, 0x40);

        }else if ( vyska < 4055){
            return  Color.rgb(0xff, 0x47, 0x40);

        }else if ( vyska < 4625){
            return  Color.rgb(0xff, 0x56, 0x56);

        }else if ( vyska < 5223){
            return  Color.rgb(0xff, 0x74, 0x74);

        }else if ( vyska < 5850){
            return  Color.rgb(0xff, 0x92, 0x92);
        }else if ( vyska < 6504){
            return  Color.rgb(0xff, 0x92, 0x92);
        }else if ( vyska < 7185){
            return  Color.rgb(0xff, 0xc9, 0xc9);
        }else if ( vyska < 7892){
            return  Color.rgb(0xff, 0xe4, 0xe4);
        }else if ( vyska < 8625){
            return  Color.rgb(0xff, 0xfe, 0xfe);
        }
        else{
            return Color.WHITESMOKE;
        }
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

    /**
     *Chat gpt
     */
    public int najdiNejmensiRozdil(int barva, int barva2, int barva70, int barva200, int barva400, int barva600, int barva900, int barva1300, int barva1600,
                                   int barva2000, int barva2500, int barva3000, int barva3500, int barva4000, int barva4500, int barva5200, int barva5800,
                                   int barva6500, int barva7100, int barva7700, int barva8000){

        int[] hodnoty = {  barva2,  barva70,    barva200,   barva400,   barva600,   barva900,   barva1300,  barva1600,
         barva2000,    barva2500,  barva3000,  barva3500,  barva4000,  barva4500,  barva5200,  barva5800,
         barva6500,    barva7100,  barva7700,  barva8000};

        int minDifference = Integer.MAX_VALUE; // Set to maximum possible value initially
        int minIndex = -1;

        for (int i = 0; i < hodnoty.length; i++) {
            int difference = Math.abs(barva - hodnoty[i]);
            if (difference < minDifference) {
                minDifference = difference;
                minIndex = i;
            }
        }
        return hodnoty[minIndex];
    }

    public int vytvorVysku(String hexCode){
        int barva = Integer.parseInt(hexCode,16);

        int barva2 =  Integer.parseInt("40d0ff",16);
        int barva70 = Integer.parseInt("40ffa7",16);
        int barva200 = Integer.parseInt("40ff60",16);
        int barva400 = Integer.parseInt("59ff40",16);
        int barva600 = Integer.parseInt("8cff40",16);
        int barva900 = Integer.parseInt("b9ff40",16);
        int barva1300 = Integer.parseInt("e4ff40",16);
        int barva1600 = Integer.parseInt("fff340",16);
        int barva2000 = Integer.parseInt("ffce40",16);
        int barva2500 = Integer.parseInt("ffaa40",16);
        int barva3000 = Integer.parseInt("ff8840",16);
        int barva3500 = Integer.parseInt("ff6740",16);
        int barva4000 = Integer.parseInt("ff4740",16);
        int barva4500 = Integer.parseInt("ff5656",16);
        int barva5200 = Integer.parseInt("ff7474",16);
        int barva5800 = Integer.parseInt("ff9292",16);
        int barva6500 = Integer.parseInt("ffadad",16);
        int barva7100 = Integer.parseInt("ffc9c9",16);
        int barva7700 = Integer.parseInt("ffe4e4",16);
        int barva8000 = Integer.parseInt("fffefe",16);

        int vyslednaBarva = najdiNejmensiRozdil(barva,barva2,barva70,barva200,barva400,barva600,barva900,barva1300,barva1600,barva2000,barva2500,barva3000,barva3500,
                barva4000,barva4500,barva5200,barva5800,barva6500,barva7100,barva7700,barva8000);


        if (vyslednaBarva == barva2){
            return 2;
        }else if (vyslednaBarva == barva70){
            return 70;
        }else if (vyslednaBarva == barva200){
            return 216;
        }else if (vyslednaBarva == barva400){
            return 418;
        }else if (vyslednaBarva == barva600){
            return 669;
        }else if (vyslednaBarva == barva900){
            return 964;
        }else if (vyslednaBarva == barva1300){
            return 1300;
        }else if (vyslednaBarva == barva1600){
            return 1674;
        }else if (vyslednaBarva == barva2000){
            return 2084;
        }else if (vyslednaBarva == barva2500){
            return 2529;
        }else if (vyslednaBarva == barva3000){
            return 3006;
        }else if (vyslednaBarva == barva3500){
            return 3515;
        }else if (vyslednaBarva == barva4000){
            return 4055;
        }else if (vyslednaBarva == barva4500){
            return 4625;
        }else if (vyslednaBarva == barva5200){
            return 5223;
        }else if (vyslednaBarva == barva5800){
            return 5850;
        }else if (vyslednaBarva == barva6500){
            return 6504;
        }else if (vyslednaBarva == barva7100){
            return 7185;
        }else if (vyslednaBarva == barva7700){
            return 7892;
        }else{
            return 8625;
        }
    }


}