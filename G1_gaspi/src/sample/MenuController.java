package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
        MesProduitsStage();

        MesProduits.setOnMouseClicked( event -> {
            try{
                MesProduitsStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ListeDeCourse.setOnMouseClicked( event -> {
            try{
                ListeDeCourseStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Historique.setOnMouseClicked( event -> {
            try{
                HistoriqueStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Annonces.setOnMouseClicked( event -> {
            try{
                AnnoncesStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MesAmis.setOnMouseClicked( event -> {
            try{
                MesAmisStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MonCompte.setOnMouseClicked( event -> {
            try{
                MonCompteStage(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void MesProduitsStage() throws Exception{
        MenuNameBlue(MesProduits);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produits));
        MesProduitsController controller_produits = new MesProduitsController();
        loader.setController(controller_produits);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produits));
            controller_produits.initMesProduits();
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Produits);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ListeDeCourseStage() throws Exception{
        MenuNameBlue(ListeDeCourse);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Course));
        ListeCourseController controller_course = new ListeCourseController();
        loader.setController(controller_course);
        try {
            VBox newPane = loader.load(getClass().getResource(View.XML_FILE_Course));
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void HistoriqueStage() throws Exception{
        MenuNameBlue(Historique);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Historique));
        HistoriqueController controller_historique = new HistoriqueController();
        loader.setController(controller_historique);
        try {
            VBox newPane = loader.load(getClass().getResource(View.XML_FILE_Historique));
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Historique);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void AnnoncesStage() throws Exception{
        /*MenuNameBlue(Annonces);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Historique));
        HistoriqueController controller_historique = new HistoriqueController();
        loader.setController(controller_historique);
        try {
            VBox newPane = loader.load(getClass().getResource(View.XML_FILE_Historique));
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Annonces);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    private void MesAmisStage() throws Exception{
        MenuNameBlue(MesAmis);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Amis));
        MesAmisController controller_amis = new MesAmisController();
        loader.setController(controller_amis);
        try {
            VBox newPane = loader.load(getClass().getResource(View.XML_FILE_Amis));
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Amis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void MonCompteStage(Stage primaryStage) throws Exception{
        MenuNameBlue(MonCompte);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Compte));
        MonCompteController controller_compte = new MonCompteController();
        loader.setController(controller_compte);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Compte));
            controller_compte.initMonCompte(primaryStage);
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Compte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MenuNameBlue(Button boutton){
        //Change le nom de tout les menus en noir
        MesProduits.setTextFill(Color.BLACK);
        Historique.setTextFill(Color.BLACK);
        MesAmis.setTextFill(Color.BLACK);
        MonCompte.setTextFill(Color.BLACK);
        Annonces.setTextFill(Color.BLACK);
        ListeDeCourse.setTextFill(Color.BLACK);
        //Change le nom du menu actuellement en cours d'utilisation uniquement en bleu
        boutton.setTextFill(Color.BLUE);
    }
}
