package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import javax.xml.soap.Node;
import javax.xml.soap.Text;
import java.io.IOException;
import java.util.Collection;

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
    @FXML
    private AnchorPane Content;


    public void initMenu(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setWidth(Fenetre.getPrefWidth());
        primaryStage.setHeight(Fenetre.getPrefHeight());
        MenuNameBlue(MesProduits);
        fillAnchorContent();
    }

    public void fillAnchorContent() throws Exception{
        //Affichage du contenu dans l'AnchorPane
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produits));
        MesProduitsController controller_produits = new MesProduitsController();
        loader.setController(controller_produits);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produits));
            Content.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MenuNameBlue(Button boutton){
        boutton.setTextFill(Color.BLUE);
    }
}
