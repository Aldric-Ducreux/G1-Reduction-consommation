package sample.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class Annonce {
    private Item item;
    private String magasin;


    public Annonce(Item item, String magasin){
        this.magasin = magasin;
        this.item = item;
    }
    public void ajouter(){ }

    public Item getItem() {
        return item;
    }

    public String getMagasin() {
        return magasin;
    }
}
