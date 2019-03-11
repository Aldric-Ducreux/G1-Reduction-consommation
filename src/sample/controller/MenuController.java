package sample.controller;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.model.User;
import sample.model.View;

import java.io.IOException;


public class MenuController {
    PseudoClass current = PseudoClass.getPseudoClass("current");
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


    public void initMenu(Stage primaryStage, User user){
        //Initialisation du Menu
        this.primaryStage = primaryStage;
        primaryStage.setWidth(Fenetre.getPrefWidth());
        primaryStage.setHeight(Fenetre.getPrefHeight()+20);
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
                MonCompteStage(primaryStage, user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void MesProduitsStage(){
        MenuNameBlue(MesProduits);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produits));
        MesProduitsController controller_produits = new MesProduitsController();
        loader.setController(controller_produits);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produits));
            controller_produits.initMesProduits();
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Produits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ListeDeCourseStage(){
        MenuNameBlue(ListeDeCourse);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Course));
        ListeCourseController controller_course = new ListeCourseController();
        loader.setController(controller_course);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Course));
            controller_course.initMesCourses();
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void HistoriqueStage(){
        MenuNameBlue(Historique);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Historique));
        HistoriqueController controller_historique = new HistoriqueController();
        loader.setController(controller_historique);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Historique));
            controller_historique.initHistorique();
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Historique);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void AnnoncesStage(){
        MenuNameBlue(Annonces);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Annonces));
        AnnoncesController controller_annonces = new AnnoncesController();
        loader.setController(controller_annonces);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Annonces));
            controller_annonces.initAnnonces(primaryStage, Content);
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Annonces);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void MesAmisStage(){
        MenuNameBlue(MesAmis);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Amis));
        MesAmisController controller_amis = new MesAmisController();
        loader.setController(controller_amis);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Amis));
            controller_amis.initMesAmis();
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Amis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void MonCompteStage(Stage primaryStage, User user) throws Exception{
        MenuNameBlue(MonCompte);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Compte));
        MonCompteController controller_compte = new MonCompteController();
        loader.setController(controller_compte);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Compte));
            controller_compte.initMonCompte(primaryStage, user);
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Compte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MenuNameBlue(Button boutton){
        //Change le nom de tout les menus en noir
        MesProduits.setTextFill(Color.BLACK);
        MesProduits.pseudoClassStateChanged(current,false);
        Historique.setTextFill(Color.BLACK);
        Historique.pseudoClassStateChanged(current,false);
        MesAmis.setTextFill(Color.BLACK);
        MesAmis.pseudoClassStateChanged(current,false);
        MonCompte.setTextFill(Color.BLACK);
        MonCompte.pseudoClassStateChanged(current,false);
        Annonces.setTextFill(Color.BLACK);
        Annonces.pseudoClassStateChanged(current,false);
        ListeDeCourse.setTextFill(Color.BLACK);
        ListeDeCourse.pseudoClassStateChanged(current,false);
        //Change le nom du menu actuellement en cours d'utilisation uniquement en bleu
        boutton.setTextFill(Color.WHITE);
        boutton.pseudoClassStateChanged(current,true);
    }

}
