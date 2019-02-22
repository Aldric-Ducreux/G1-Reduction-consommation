package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.security.util.Password;

import javax.xml.soap.Text;
import java.io.IOException;

public class ConnexionController {
    private Stage primaryStage;
    @FXML
    private Button Connexion_BT_Connexion;
    @FXML
    private Button Connexion_BT_Inscription;
    @FXML
    private TextField Connexion_TF_ID;
    @FXML
    private PasswordField Connexion_TF_MDP;
    @FXML
    private Label TextIncorrect;
    @FXML
    private VBox Fenetre;

    public void initConnexion(Stage primaryStage)  throws Exception {
        //Initialisation de la page de Connexionn
        this.primaryStage = primaryStage;
        primaryStage.setWidth(Fenetre.getPrefWidth());
        primaryStage.setHeight(Fenetre.getPrefHeight());
        //Replace la fenétre au centre de l'écran de l'utilisateur
        javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        TextIncorrect.setVisible(false);
        //Event en cas de clic sur le bouton de connexion
        Connexion_BT_Connexion.setOnMouseClicked( event ->{
            try {
                connect(Connexion_TF_ID.getText(),Connexion_TF_MDP.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
                });
        //Event en cas de clic sur le bouton d'Inscription
        Connexion_BT_Inscription.setOnMouseClicked(event -> {
            try {
                InscriptionStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void connect(String id, String mdp)  throws Exception {
        //Si clic sur le bouton de connection
        if (id.equals("admin") && mdp.equals("admin")) {
            //Si correct, on affiche la page du Menu
            try {
                MenuStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //Si incorrect, on affiche le texte d'erreur, et le met en rouge
            TextIncorrect.setTextFill(Color.RED);
            TextIncorrect.setVisible(true);
        }
    }

    public void InscriptionStage() throws  Exception{
        //Affichage de la page d'inscription
        String fxmlFile = "/view/Inscription.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        InscriptionController controller_inscription = new InscriptionController();
        loader.setController(controller_inscription);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Insc));
            controller_inscription.initInscription(primaryStage);
            Scene scene = primaryStage.getScene();
            primaryStage.getScene().setRoot(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MenuStage() throws Exception{
        //Affichage du menu
        String fxmlFile = "/view/Menu.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        MenuController controller_menu = new MenuController();
        loader.setController(controller_menu);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Menu));
            controller_menu.initMenu(primaryStage);
            Scene scene = primaryStage.getScene();
            primaryStage.getScene().setRoot(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
