package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.model.ItemList;
import sample.model.View;
import java.io.IOException;

public class MesProduitsController {

    String pathMesProduits = "MesProduits.json";
    ItemList produits;

    @FXML
    private Button MesProduitsAjout;

    public void initMesProduits() {
        produits = ItemList.loadFromFile(pathMesProduits);
        MesProduitsAjout.setOnMouseClicked( event -> {
            try{
                addProduit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addProduit()throws Exception{
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Ajout));
        AjoutController controller_ajout = new AjoutController();
        loader.setController(controller_ajout);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Ajout));
            controller_ajout.initAjout(produits);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Produit_Ajout);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
