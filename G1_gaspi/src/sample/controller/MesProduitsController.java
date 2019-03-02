package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.ItemList;
import sample.model.Produit;
import sample.model.View;

import java.io.IOException;

public class MesProduitsController {

    String pathMesProduits = "Produits.json";
    ItemList produits;
    ObservableList<Produit> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Produit> mytableTableView;
    @FXML
    private TableColumn<Produit, String> MesProduitsProduit;
    @FXML
    private TableColumn<Produit, String> MesProduitsType;
    @FXML
    private TableColumn<Produit, String> MesProduitsQuantite;
    @FXML
    private TableColumn<Produit, String> MesProduitsDate;
    @FXML
    private Button MesProduitsAjout;


    public void initMesProduits() {
        produits = ItemList.loadFromFile(pathMesProduits);
        MesProduitsAjout.setOnMouseClicked(event -> {
            try {
                addProduit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        MesProduitsProduit.setCellValueFactory(new PropertyValueFactory<>("MesProduitsProduit"));
        MesProduitsType.setCellValueFactory(new PropertyValueFactory<>("MesProduitsDate"));
        MesProduitsQuantite.setCellValueFactory(new PropertyValueFactory<>("MesProduitsQuantite"));
        MesProduitsDate.setCellValueFactory(new PropertyValueFactory<>("MesProduitsDate"));
        MesProduitsDate.setCellFactory(new Callback<TableColumn<Produit, String>, TableCell<Produit, String>>() {
            @Override
            public TableCell<Produit, String> call(
                    TableColumn<Produit, String> param) {
                TableCell<Produit, String> cell = new TableCell<>();
                Text text = new Text();
                text.setFill(Color.WHITE);
                cell.setGraphic(text);
                cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
                text.wrappingWidthProperty().bind(cell.widthProperty());
                text.textProperty().bind(cell.itemProperty());
                return cell;
            }
        });
    }

    public void addProduit() throws Exception {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Ajout));
        AjoutController controller_ajout = new AjoutController();
        loader.setController(controller_ajout);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Ajout));
            controller_ajout.initAjout(produits);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Produit_Ajout);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        /*try {
            for(String m : "sample/json/Produits.json") {
                JSONObject json = readJsonFromUrl(link);
                String prod = json.get("MesProduitsProduit").toString();
                String type = json.get("MesProduitsType").toString();
                int qtt = json.get("MesProduitsQuantite").toString();
                String date = json.get("MesProduitsDate").toString();
                list.add(new Produit(prod,type,qtt,date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mytableTableView.getItems().setAll(list);
    */}

}
