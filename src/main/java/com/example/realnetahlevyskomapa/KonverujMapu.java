package com.example.realnetahlevyskomapa;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.realnetahlevyskomapa.Vyskomapa.*;

public class KonverujMapu {
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

    String publicAdresa;

    public void konvertujMapu(Pane root, Scene scene, String adresa, Stage stage, Scene scenaPredchoziOkno){
        System.out.println("dobry");

        Rectangle r = new Rectangle(scene.getWidth(),scene.getHeight());
        r.setLayoutX(0);
        r.setLayoutY(0);
        r.setFill(Color.WHITE);
        root.getChildren().addAll(r);

        publicAdresa = adresa;


        if (adresa == null){
            Label l = new Label("NENAHRALI JSTE OBRAZEK");
            l.setLayoutX(size/3);
            l.setLayoutY(size/2);
            Font stary = l.getFont();
            Font novy = new Font(stary.getName(), stary.getSize() + 20);
            l.setFont(novy);
            root.getChildren().addAll(l);
        }else{
            javafx.scene.image.Image image = new Image(adresa);
            ImageView imageView = new ImageView(image);
            imageView.setLayoutX(0);
            imageView.setLayoutY(0);
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            root.getChildren().addAll(imageView);
        }


        Button buttonZpet = new Button("ZPET");
        buttonZpet.setPrefSize(188., size/7);
        buttonZpet.setLayoutX(size + 7.5590551181);
        buttonZpet.setLayoutY(20);
        buttonZpet.setOnMouseClicked(e->{
            stage.setScene(scenaPredchoziOkno);
        });

        Label yKoordinace = new Label();
        yKoordinace.setPrefSize(50, 150);
        yKoordinace.setLayoutX(velikostMapy + 70);
        yKoordinace.setLayoutY(100 + buttonZpet.getPrefHeight());
        Font currentFont = yKoordinace.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getSize() + 10);
        yKoordinace.setFont(newFont);

        Label xKoordinace = new Label();
        xKoordinace.setPrefSize(50, 150);
        xKoordinace.setLayoutX(velikostMapy + 70);
        xKoordinace.setLayoutY(10 +  buttonZpet.getPrefHeight());
        xKoordinace.setFont(newFont);

        Label labelVyska = new Label();
        labelVyska.prefHeight(50);
        labelVyska.setLayoutX(velikostMapy + 70);
        labelVyska.setLayoutY(266+ buttonZpet.getPrefHeight());
        labelVyska.setFont(newFont);

        Label textXKoordinace = new Label("Souřadnice X: ");
        textXKoordinace.setFont(newFont);
        textXKoordinace.setLayoutY(20+ buttonZpet.getPrefHeight());
        textXKoordinace.setLayoutX(velikostMapy + 20);

        Label textYKoordinace = new Label("Souřadnice Y: ");
        textYKoordinace.setFont(newFont);
        textYKoordinace.setLayoutY(113+ buttonZpet.getPrefHeight());
        textYKoordinace.setLayoutX(velikostMapy + 20);

        Label textVyska = new Label("Výška: ");
        textVyska.setFont(newFont);
        textVyska.setLayoutY(226+ buttonZpet.getPrefHeight());
        textVyska.setLayoutX(velikostMapy + 20);

        Pane rootHorizont = new Pane();
        Scene sceneHorizont = new Scene(rootHorizont, velikostHorizontuX + 100, velikostHorizontuY );
        Stage stageHorizont = new Stage();
        stageHorizont.setResizable(false);
        stageHorizont.setScene(sceneHorizont);

        Button buttonHorizont = new Button("Generuj Horizont");
        buttonHorizont.setPrefSize(188., size/7);
        buttonHorizont.setLayoutX(size + 7.5590551181);
        buttonHorizont.setLayoutY(size - 7.5590551181 - buttonHorizont.getPrefHeight());

        Vyska vyska1 = new Vyska();
        Rectangle souradnicovyCtverec = new Rectangle(size,size);
        souradnicovyCtverec.setLayoutX(0);
        souradnicovyCtverec.setLayoutY(0);
        souradnicovyCtverec.setFill(Color.BLACK);
        souradnicovyCtverec.setOpacity(0.0);
        souradnicovyCtverec.setOnMouseClicked((MouseEvent event)->{
            xKoordinace.setText(String.valueOf(getKordX(event.getX())));
            yKoordinace.setText(String.valueOf(getKordY(event.getY())));
            labelVyska.setText(String.valueOf(vyska1.vytvorVysku(getBarvaPixelu(event.getX(), event.getY()))));
        });
        Vyskomapa vyskomapa = new Vyskomapa();


        Label dragNDrop = new Label("Zde dej png složku");
        dragNDrop.setStyle("-fx-background-color: lightgray;");
        dragNDrop.setAlignment(Pos.CENTER);
        dragNDrop.setPrefSize(buttonHorizont.getPrefWidth(), buttonHorizont.getPrefHeight());
        dragNDrop.setLayoutX(buttonHorizont.getLayoutX());
        dragNDrop.setLayoutY(buttonHorizont.getLayoutY() - dragNDrop.getPrefHeight() - 5);


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

        Button buttonKonvertujMapu = new Button("Zmen na zakladni mapu");
        buttonKonvertujMapu.setPrefSize(buttonHorizont.getPrefWidth(),buttonHorizont.getPrefHeight());
        buttonKonvertujMapu.setLayoutX(dragNDrop.getLayoutX());
        buttonKonvertujMapu.setLayoutY(dragNDrop.getLayoutY() - 5 - buttonKonvertujMapu.getPrefHeight());

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
                        vyskomapa.getAdresa(file.getAbsolutePath());
                        System.out.println("Dropped PNG file:" + vyskomapa.getAdresa(adresa));
                        konvertujMapu(root,scene,file.getAbsolutePath(),stage,scenaPredchoziOkno);
                        ready(true);
                        root.getChildren().add(buttonKonvertujMapu);
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
        buttonKonvertujMapu.setOnMouseClicked(e->{
            System.out.println("ahoj");
            Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];

            for (int i = 0; i<mapa.length;i++){
                for (int j = 0;j<mapa.length;j++){

                    mapa[i][j] = new Bod(i*bodSize, j*bodSize, vyska1.vytvorVysku(getBarvaPixelu(i*bodSize,j*bodSize)));
                    root.getChildren().addAll(mapa[i][j]);

                    System.out.println("nigger");
                }

            }

            System.out.println("hotovo");
        });




        root.getChildren().addAll(souradnicovyCtverec, buttonZpet, buttonHorizont,textVyska,textYKoordinace,textXKoordinace, labelVyska,xKoordinace ,yKoordinace, dragNDrop);
    }

    boolean pripraven = false;
    public boolean ready(boolean ready){
        pripraven = ready;
        return pripraven;
    }


    public Color barvaPixelu;

    public String getBarvaPixelu(double x, double y){
        if (publicAdresa!= null){
            Image image = new Image(publicAdresa);
            int sirka = (int) ((image.getWidth()/velikostMapy)*x);
            int vyska = (int)((image.getHeight()/velikostMapy)*y);
            PixelReader pixelReader = image.getPixelReader();
            System.out.println("--------------------------------------------------------------------");
            System.out.println(" x: " + x + "  y: " + y);

            barvaPixelu = pixelReader.getColor(sirka,vyska);

            String hexColor =
                    Integer.toHexString((int) (barvaPixelu.getRed() * 255)) +
                    Integer.toHexString((int) (barvaPixelu.getGreen() * 255)) +
                    Integer.toHexString((int) (barvaPixelu.getBlue() * 255));


            return hexColor;
        }else{
            return null;
        }

    }

    public void udelejMapu(Image image, Pane root){
        Bod[][] mapa = new Bod[velikostMapy/bodSize][velikostMapy/bodSize];
        Vyska vyska = new Vyska();
        int posledniVyska = 0;

        for (int i = 0;i<velikostMapy/bodSize;i++){
            for (int j = 0;j<velikostMapy/bodSize;j++){
                if (vyska.vytvorVysku(getBarvaPixelu(i*bodSize,j*bodSize))!=0){
                    mapa[i][j] = new Bod(i*bodSize,j*bodSize, vyska.vytvorVysku(getBarvaPixelu(i*bodSize,j*bodSize)));
                    posledniVyska = vyska.vytvorVysku(getBarvaPixelu(i*bodSize,j*bodSize));
                }else{
                    vyska.setVyska(posledniVyska);
                    mapa[i][j] = new Bod(i*bodSize,j*bodSize, vyska.getvalueOfVyska());
                }

            }
        }

    }

}
