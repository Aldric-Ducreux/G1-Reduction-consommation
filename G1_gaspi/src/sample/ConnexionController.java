package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextField Connexion_TF_MDP;

    public void initConnexion(Stage primaryStage)  throws Exception {
        this.primaryStage = primaryStage;
        Connexion_BT_Connexion.setOnMouseClicked( event ->{
            try {
                connect(Connexion_TF_ID.getText(),Connexion_TF_MDP.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
                });
        Connexion_BT_Inscription.setOnMouseClicked(event -> {
            try {
                InscriptionStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void connect(String id, String mdp)  throws Exception {
        if (id.equals("admin") && mdp.equals("admin")){
            System.out.println("Compte valide");
            try {
                MenuStage();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Compte invalide");}
    }

    public void InscriptionStage() throws  Exception{
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
        String fxmlFile = "/view/Menu.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        MenuController controller_menu = new MenuController();
        loader.setController(controller_menu);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Menu));
            controller_menu.initMenu(primaryStage);
            Scene scene = primaryStage.getScene();
            primaryStage.getScene().setRoot(page);
            primaryStage.setHeight(primaryStage.getHeight());
            primaryStage.setWidth(primaryStage.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
