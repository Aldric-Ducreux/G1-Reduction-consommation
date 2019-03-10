package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnnoncesCommentaires {
    @FXML
    private ListView ListViewCommentaire;
    @FXML
    private Label nom_produit_ProduitAnnonces;
    @FXML
    private Label date_limite_ProduitAnnonces;
    @FXML
    private Label nom_magasin_ProduitAnnonces;
    @FXML
    private Button bouton_ajouter_ProduitAnnonces;
    @FXML
    private Button bouton_partager_ProduitAnnonces;
    @FXML
    private TextField CommentaireText;
    @FXML
    private Button CommentButton;

    private static ObservableList<String> ListCommentaire = FXCollections.observableArrayList(
            new String("Wow, c'est vraiment un bon produit, pas déçu")
    );

    public void initAnnoncesCommentaires(ObservableList<Item> produits, String produit, String date, String magasin){
        nom_produit_ProduitAnnonces.setText(produit);
        date_limite_ProduitAnnonces.setText(date);
        nom_magasin_ProduitAnnonces.setText(magasin);
        bouton_ajouter_ProduitAnnonces.setOnMouseClicked (event -> {
            try{
                addProduit(produits, nom_magasin_ProduitAnnonces.getText(), nom_produit_ProduitAnnonces.getText(), date_limite_ProduitAnnonces.getText());
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

        CommentButton.setOnMouseClicked (event -> {
            try{
                commentaire(CommentaireText.getText());
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        ListViewCommentaire.setItems(ListCommentaire);

    }
    public void addProduit(ObservableList<Item> produits, String magasin, String produit, String date){
        String nom = magasin + " - " + produit;
        if (produits.stream().anyMatch(item -> item.getName().equals(nom))) {
            Item i = produits.stream().filter(item -> item.getName().equals(nom)).findFirst().get();
            i.setQuantity(i.getQuantity() + 1);
        }else {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ListeCourseController.list.add(new Item(nom, produit, 1, localDate));
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
    public void commentaire(String commentaire) {
        ListCommentaire.add(commentaire);
        ListViewCommentaire.refresh();
        MesAmisController.ListCommentaire.add("Vous (" + nom_produit_ProduitAnnonces.getText() + ") : " + commentaire);

    }

}
