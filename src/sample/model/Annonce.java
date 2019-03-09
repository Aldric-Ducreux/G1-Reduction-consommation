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
    @FXML
    private AnchorPane liste_annonce;
    @FXML
    private Label nom_produit_ProduitAnnonces;
    @FXML
    private Label date_limite_ProduitAnnonces;
    @FXML
    private Label nom_magasin_ProduitAnnonces;
    @FXML
    private Button bouton_ajouter_ProduitAnnonces;
    @FXML
    private Button bouton_commenter_ProduitAnnonces;
    @FXML
    private Button bouton_partager_ProduitAnnonces;

    public Annonce(Item item, String magasin){
        this.magasin = magasin;
        this.item = item;
        initAnnonce();
    }

    public void initAnnonce(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_annonces));
        loader.setController(this);
        try {
            VBox newPane = loader.load(getClass().getResource(View.XML_FILE_Produit_annonces));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*nom_magasin_ProduitAnnonces.setText(magasin);
        nom_produit_ProduitAnnonces.setText(item.getName());
        date_limite_ProduitAnnonces.setText(String.valueOf(item.getExpiryDate()));
        bouton_ajouter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                ajouter();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_commenter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                ajouter();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_partager_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                ajouter();
            } catch (Exception e){
                e.printStackTrace();
            }
        });*/
    }


    public void ajouter(){ }

    public Item getItem() {
        return item;
    }

    public String getMagasin() {
        return magasin;
    }
}
