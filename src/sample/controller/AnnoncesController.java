package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private java.awt.Label nom_produit_ProduitAnnonces;
    @FXML
    private java.awt.Label date_limite_ProduitAnnonces;
    @FXML
    private java.awt.Label nom_magasin_ProduitAnnonces;
    @FXML
    private javafx.scene.control.Button bouton_ajouter_ProduitAnnonces;
    @FXML
    private javafx.scene.control.Button bouton_commenter_ProduitAnnonces;
    @FXML
    private javafx.scene.control.Button bouton_partager_ProduitAnnonces;
    @FXML
    private javafx.scene.control.Label ErrorChamp;

    @FXML
    private AnchorPane liste_annonce1;
    @FXML
    private java.awt.Label nom_produit_ProduitAnnonces1;
    @FXML
    private java.awt.Label date_limite_ProduitAnnonces1;
    @FXML
    private java.awt.Label nom_magasin_ProduitAnnonces1;
    @FXML
    private javafx.scene.control.Button bouton_ajouter_ProduitAnnonces1;
    @FXML
    private javafx.scene.control.Button bouton_commenter_ProduitAnnonces1;
    @FXML
    private javafx.scene.control.Button bouton_partager_ProduitAnnonces1;
    @FXML
    private javafx.scene.control.Label ErrorChamp1;

    public void initAnnonces() {

        bouton_ajouter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                addProduit(nom_magasin_ProduitAnnonces.getText(), nom_produit_ProduitAnnonces.getName(), date_limite_ProduitAnnonces.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });bouton_ajouter_ProduitAnnonces1.setOnMouseClicked (event -> {
            try{
                addProduit(nom_magasin_ProduitAnnonces1.getText(), nom_produit_ProduitAnnonces1.getName(), date_limite_ProduitAnnonces1.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_partager_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                partageAnnonce(nom_produit_ProduitAnnonces.getName());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_partager_ProduitAnnonces1.setOnMouseClicked (event -> {
            try{
                partageAnnonce(nom_produit_ProduitAnnonces1.getName());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_commenter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                commentaire();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        bouton_commenter_ProduitAnnonces1.setOnMouseClicked (event -> {
            try{
                commentaire();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void addProduit(String magasin, String produit, String date){
        if (produit.isEmpty()) {
            ErrorChamp.setVisible(true);
            ErrorChamp.setTextFill(Color.RED);
        } else {
            String nom = magasin +" "+ produit;
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ListeCourseController.list.add(new Item(nom, produit, 1, localDate));
            ListeCourseController.tableTableView.refresh();
            cancel(bouton_ajouter_ProduitAnnonces);
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commentaire() {
        System.out.println("commentaire");
    }

    public void cancel(javafx.scene.control.Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
    }
}


