package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnnoncesPartageController {
    @FXML
    private TextField PartageAnnonceMail;
    @FXML
    private Button PartageAnnonceButton;
    @FXML
    private Label partageproduit;


    public void initAnnoncesPartage(String produit){
        partageproduit.setText(produit);
        PartageAnnonceButton.setOnMouseClicked( event -> {
            try{
                System.out.println("Annonce envoyé à" + PartageAnnonceMail.getText() );
                Stage stage = (Stage) PartageAnnonceButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
