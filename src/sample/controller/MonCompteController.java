package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.User;
import sample.model.View;

import java.io.IOException;
import java.util.Optional;

public class MonCompteController {
    private Stage primaryStage;
    @FXML
    private Label Error;
    @FXML
    private Label Success;
    @FXML
    private Label identifiant;
    @FXML
    private PasswordField OldMDP;
    @FXML
    private PasswordField NewMDP;
    @FXML
    private PasswordField NewMDPV;
    @FXML
    private Button BT_ModifierMDP;
    @FXML
    private Button BT_GetInfo;
    @FXML
    private Button BT_SupprAccount;
    @FXML
    private Button LogOut;

    public void initMonCompte(Stage primaryStage, User user)throws  Exception{
        this.primaryStage = primaryStage;
        Error.setVisible(false);
        Error.setTextFill(Color.RED);
        Success.setVisible(false);
        Success.setTextFill(Color.GREEN);
        identifiant.setText(user.getPseudo());
        BT_ModifierMDP.setOnMouseClicked( event -> {
            try{
                modifyMDP(OldMDP.getText(),NewMDP.getText(), NewMDPV.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        BT_GetInfo.setOnMouseClicked( event -> {
            try{
                getInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        BT_SupprAccount.setOnMouseClicked( event -> {
            try{
                supprAccount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        LogOut.setOnMouseClicked( event -> {
            try{
                ConnexionStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    private void modifyMDP(String old, String new1, String new2) throws  Exception{
        if (old.equals("admin") && !(new1.isEmpty()) && new1.equals(new2)){
            Success.setVisible(true);
            Error.setVisible(false);
        } else {
            Error.setVisible(true);
            Success.setVisible(false);
        }
    }

    public void getInfo() {
        System.out.println("Ok");
    }

    private void supprAccount() throws  Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de compte");
        alert.setContentText("Voulez vous vraiment supprimer votre compte ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                ConnexionStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.close();
        }
    }

    public void ConnexionStage()throws  Exception{
        //Changement de la page pour afficher la page de connexion
        String fxmlFile = View.XML_FILE_Connex;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        ConnexionController controller_connexion = new ConnexionController();
        loader.setController(controller_connexion);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(fxmlFile));
            controller_connexion.initConnexion(primaryStage);
            Scene scene = primaryStage.getScene();
            primaryStage.getScene().setRoot(page);
            scene.getStylesheets().add(View.CSS_File);
            primaryStage.setTitle(View.LABEL_Connex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
