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
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;
import javax.xml.soap.Node;
import javax.xml.soap.Text;
import java.awt.geom.Rectangle2D;
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
        //Initialisation du Menu
        this.primaryStage = primaryStage;
        primaryStage.setWidth(Fenetre.getPrefWidth());
        primaryStage.setHeight(Fenetre.getPrefHeight());
        //Replace la fenétre au centre de l'écran de l'utilisateur
        javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

        MenuNameBlue(MesProduits);
        fillAnchorContent();
    }

    public void fillAnchorContent() throws Exception{
        //Affichage du contenu dans l'AnchorPane
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produits));
        MesProduitsController controller_produits = new MesProduitsController();
        loader.setController(controller_produits);
        try {
            //On affiche dans l'Anchor pane "Content" le contenu
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produits));
            Content.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MenuNameBlue(Button boutton){
        //Change le nom du menu actuellement en cours d'utilisation en bleu
        boutton.setTextFill(Color.BLUE);
    }
}
