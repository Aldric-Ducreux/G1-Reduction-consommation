package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.soap.Text;
import java.io.IOException;

public class MenuController {
    private Stage primaryStage;
    @FXML
    private Button MesProduits;
    @FXML
    private Button ListeDeCourse;
    @FXML
    private Button Historique;
    @FXML
    private Button Annonces;
    @FXML
    private Button MesAmis;
    @FXML
    private Button MonCompte;
    @FXML
    private VBox Fenetre;


    public void initMenu(Stage primaryStage)  throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setWidth(Fenetre.getPrefWidth());
        primaryStage.setHeight(Fenetre.getPrefHeight());
    }

}
