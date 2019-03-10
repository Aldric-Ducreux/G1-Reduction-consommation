package sample.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Annonce;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnnoncesController {
    @FXML
    private TableView<Annonce> myTableAnnonces;
    @FXML
    private TableColumn<Annonce, Annonce> MesAnnonces;
    @FXML
    private TextField rechercher_Annonces;

    @FXML
    private AnchorPane liste_annonce;
    @FXML
    private Label nom_produit_ProduitAnnonces;
    @FXML
    private Label date_limite_ProduitAnnonces;
    @FXML
    private Label nom_magasin_ProduitAnnonces;
    @FXML
    private Button bouton_ajouter_ProduitAnnonces;
    @FXML
    private Button bouton_commenter_ProduitAnnonces;
    @FXML
    private Button bouton_partager_ProduitAnnonces;
    @FXML
    private Label ErrorChamp;

    @FXML
    private AnchorPane liste_annonce1;
    @FXML
    private Label nom_produit_ProduitAnnonces1;
    @FXML
    private Label date_limite_ProduitAnnonces1;
    @FXML
    private Label nom_magasin_ProduitAnnonces1;
    @FXML
    private Button bouton_ajouter_ProduitAnnonces1;
    @FXML
    private Button bouton_commenter_ProduitAnnonces1;
    @FXML
    private Button bouton_partager_ProduitAnnonces1;
    @FXML
    private Label ErrorChamp1;

    private ObservableList<Item> produits = ListeCourseController.list;

    public void initAnnonces(Stage primaryStage, AnchorPane Content) {
        ErrorChamp.setVisible(false);
        ErrorChamp1.setVisible(false);


        bouton_ajouter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                addProduit(produits, nom_magasin_ProduitAnnonces.getText(), nom_produit_ProduitAnnonces.getText(), date_limite_ProduitAnnonces.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });bouton_ajouter_ProduitAnnonces1.setOnMouseClicked (event -> {
            try{
                addProduit1(produits, nom_magasin_ProduitAnnonces1.getText(), nom_produit_ProduitAnnonces1.getText(), date_limite_ProduitAnnonces1.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_partager_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                partageAnnonce(nom_produit_ProduitAnnonces.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_partager_ProduitAnnonces1.setOnMouseClicked (event -> {
            try{
                partageAnnonce(nom_produit_ProduitAnnonces1.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_commenter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                commentaire(produits, primaryStage, Content, nom_magasin_ProduitAnnonces.getText(), nom_produit_ProduitAnnonces.getText(), date_limite_ProduitAnnonces.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_commenter_ProduitAnnonces1.setOnMouseClicked (event -> {
            try{
                commentaire(produits, primaryStage, Content,  nom_produit_ProduitAnnonces.getText(), date_limite_ProduitAnnonces.getText(), nom_magasin_ProduitAnnonces.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addProduit(ObservableList<Item> produits, String magasin, String produit, String date){
        String nom = magasin + " - " + produit;
        if (produits.stream().anyMatch(item -> item.getName().equals(nom))) {
            Item i = produits.stream().filter(item -> item.getName().equals(nom)).findFirst().get();
            i.setQuantity(i.getQuantity() + 1);
        }else {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ListeCourseController.list.add(new Item(nom, produit, 1, localDate));
            ErrorChamp.setVisible(true);
            ErrorChamp.setTextFill(Color.GREEN);
        }
    }
    public void addProduit1(ObservableList<Item> produits, String magasin, String produit, String date){
        String nom = magasin + " - " + produit;
        if (produits.stream().anyMatch(item -> item.getName().equals(nom))) {
            Item i = produits.stream().filter(item -> item.getName().equals(nom)).findFirst().get();
            i.setQuantity(i.getQuantity() + 1);
        }else {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ListeCourseController.list.add(new Item(nom, produit, 1, localDate));
            ErrorChamp.setVisible(true);
            ErrorChamp.setTextFill(Color.GREEN);
        }
    }


    public void partageAnnonce(String produit) {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Annonces_Partage));
        AnnoncesPartageController controller_partageAnnonce = new AnnoncesPartageController();
        loader.setController(controller_partageAnnonce);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Annonces_Partage));
            controller_partageAnnonce.initAnnoncesPartage(produit);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Annonces_Partage);
            scene.getStylesheets().add(View.CSS_File);
            stage.getIcons().add(new Image("/sample/CSS/logo.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commentaire(ObservableList<Item> produits, Stage primaryStage, AnchorPane Content, String produit, String date, String magasin) {
        //En cas de clic sur le bouton "commentaire"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Annonces_Commentaire));
        AnnoncesCommentaires controller_CommentaireAnnonce = new AnnoncesCommentaires();
        loader.setController(controller_CommentaireAnnonce);
        try {
            VBox newPane = loader.load(getClass().getResourceAsStream(View.XML_FILE_Annonces_Commentaire));
            controller_CommentaireAnnonce.initAnnoncesCommentaires(produits, produit, date, magasin);
            Content.getChildren().setAll(newPane);
            primaryStage.setTitle(View.LABEL_Annonces_Commentaire);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


