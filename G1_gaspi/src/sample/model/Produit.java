package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produit {
    private SimpleStringProperty MesProduitsProduit;
    private SimpleStringProperty MesProduitsType;
    private SimpleIntegerProperty MesProduitsQuantite;
    private SimpleStringProperty MesProduitsDate;

    public Produit(String MesProduitsProduit, String MesProduitsType, int MesProduitsQuantite, String MesProduitsDate) {
        this.MesProduitsProduit = new SimpleStringProperty(MesProduitsProduit);
        this.MesProduitsType = new SimpleStringProperty(MesProduitsType);
        this.MesProduitsQuantite = new SimpleIntegerProperty(MesProduitsQuantite);
        this.MesProduitsDate = new SimpleStringProperty(MesProduitsDate);
    }

    public String getProd(){
        return MesProduitsProduit.get();
    }
    public String getType(){
        return MesProduitsType.get();
    }
    public int getQtt(){
        return MesProduitsQuantite.get();
    }
    public String getDate(){
        return MesProduitsDate.get();
    }

}
