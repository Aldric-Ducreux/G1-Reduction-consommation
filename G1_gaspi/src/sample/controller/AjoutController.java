package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Window;

import java.awt.*;

public class AjoutController {
    @FXML
    private TextField TF_Produit;
    @FXML
    private TextField TF_Nombre;
    @FXML
    private Button BT_Ajouter;
    @FXML
    private Button BT_Annuler;

    public void initAjout() throws Exception {
        RestrictNumbersOnly(TF_Nombre);
        BT_Ajouter.setOnMouseClicked( event -> {
            try{
                addProduit(TF_Produit.getText(), TF_Nombre.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        BT_Annuler.setOnMouseClicked( event -> {
            try{
                cancel(BT_Annuler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addProduit(String produit,String nombre){
        //Ajout au JSON
        cancel(BT_Ajouter);
    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
    }

    public void RestrictNumbersOnly(TextField tf){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("|[-\\+]?|[-\\+]?\\d+\\.?|[-\\+]?\\d+\\.?\\d+")){
                    tf.setText(oldValue);
                }
            }
        });
    }
}
