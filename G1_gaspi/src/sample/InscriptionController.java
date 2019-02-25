package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;


public class InscriptionController {
    private Stage primaryStage;
    @FXML
    private Button Inscription_BT_Inscription;
    @FXML
    private Button Inscription_BT_Retour;
    @FXML
    private Button Inscription_BT_Conditions;
    @FXML
    private TextField Inscription_TF_ID;
    @FXML
    private PasswordField Inscription_TF_MDP;
    @FXML
    private PasswordField Inscription_TF_MDPV;
    @FXML
    private TextField Inscription_TF_Mail;
    @FXML
    private CheckBox Inscription_CB_Conditions;
    @FXML
    private Label TextIncorrect;
    @FXML
    private VBox Fenetre;

    public void initInscription(Stage primaryStage)throws  Exception{
        //Initialisation de la fenètre d'Inscription
        this.primaryStage = primaryStage;
        //On change la taille de la fenétre pour qu'elle corresponde à la taille attendue
        primaryStage.setWidth(Fenetre.getPrefWidth());
        primaryStage.setHeight(Fenetre.getPrefHeight());
        //Replace la fenétre au centre de l'écran de l'utilisateur
        javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        //On cache le message d'erreur
        TextIncorrect.setVisible(false);
        //Event si clic sur le bouton "Inscription"
        Inscription_BT_Inscription.setOnMouseClicked( event -> {
            try{
                inscription(Inscription_TF_ID.getText(),Inscription_TF_MDP.getText(), Inscription_TF_MDPV.getText(), Inscription_TF_Mail.getText(), Inscription_CB_Conditions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //Event si clic sur le bouton "Lire" des conditions
        Inscription_BT_Conditions.setOnMouseClicked( event -> {
            try{
                conditions();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //Event si clic sur le bouton "Retour"
        Inscription_BT_Retour.setOnMouseClicked(event -> {
            try {
                ConnexionStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void inscription (String id, String mdp, String mdpv, String mail, CheckBox condition) throws  Exception{
        //En cas de clic sur le bouton inscription
        if (id.isEmpty() || mdp.isEmpty() || mdpv.isEmpty() || mail.isEmpty() || !(condition.isSelected())){
            TextIncorrect.setTextFill(Color.RED);
            TextIncorrect.setVisible(true);
        } else {
            ConnexionStage();
        }
    }

    public void conditions()throws Exception{
        //En cas de clic sur le bouton "Lire"
        String fxmlFile = "/view/Conditions.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        ConditionsController controller_conditions = new ConditionsController();
        loader.setController(controller_conditions);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Cond));
            controller_conditions.initCondition();
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
