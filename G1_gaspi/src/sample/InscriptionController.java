package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.xml.soap.Text;
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
    private TextField Inscription_TF_MDP;
    @FXML
    private TextField Inscription_TF_MDPV;
    @FXML
    private TextField Inscription_TF_Mail;
    @FXML
    private CheckBox Inscription_CB_Conditions;
    public void valid(char operation) {

    }
    public void initInscription(Stage primaryStage)throws  Exception{
        this.primaryStage = primaryStage;
        Inscription_BT_Inscription.setOnMouseClicked( event -> {
            try{
                inscription(Inscription_TF_ID.getText(),Inscription_TF_MDP.getText(), Inscription_TF_MDPV.getText(), Inscription_TF_Mail.getText(), Inscription_CB_Conditions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Inscription_BT_Conditions.setOnMouseClicked( event -> {
            try{
                conditions();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Inscription_BT_Retour.setOnMouseClicked(event -> {
            try {
                retour();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void inscription (String id, String mdp, String mdpv, String mail, CheckBox condition) throws  Exception{
        if (id.isEmpty() || mdp.isEmpty() || mdpv.isEmpty() || mail.isEmpty() || !(condition.isSelected())){
            System.out.println("Champ vide ou conditions non acceptées");
        } else {
            System.out.println("Compte ajouté");
            ConnexionStage();
        }
    }

    public void retour()throws  Exception {
        ConnexionStage();
    }

    public void conditions()throws Exception{
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
        String fxmlFile = "/view/Connexion.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        ConnexionController controller_connexion = new ConnexionController();
        loader.setController(controller_connexion);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Connex));
            controller_connexion.initConnexion(primaryStage);
            Scene scene = primaryStage.getScene();
            primaryStage.getScene().setRoot(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
