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

    public void connect(String id, String mdp){
        System.out.println(Connexion_TF_ID.getText());
        System.out.println(Connexion_TF_MDP.getText());
        if (Connexion_TF_ID.getText().equals("admin") && Connexion_TF_MDP.getText().equals("admin")){
            System.out.println("GG");
        } else { System.out.println("RIP");}
    }

    public void insc () throws  Exception{
        String fxmlFile = "/view/Inscription.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Insc));
            Scene scene = primaryStage.getScene();
            primaryStage.getScene().setRoot(page);
            primaryStage.setWidth(500);
            primaryStage.setHeight(600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initConnexion(Stage primaryStage)  throws Exception {
        this.primaryStage = primaryStage;
        Connexion_BT_Connexion.setOnMouseClicked( event -> connect(Connexion_TF_ID.toString(),Connexion_TF_MDP.toString()));
        Connexion_BT_Inscription.setOnMouseClicked(event -> {
            try {
                insc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
