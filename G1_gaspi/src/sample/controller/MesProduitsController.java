package sample.controller;


import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class MesProduitsController {
    static ObservableList<Item> produitsList = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Chocapic Chocolat","Cereales",2,LocalDate.of(2000, Month.MAY, 20)),
            new Item("Soya Juice","Lait",10,LocalDate.of(2000, Month.MAY, 20))
    );
    @FXML
    private TableView<Item> mytableTableView;
    @FXML
    private TableColumn<Item, String> MesProduitsProduit;
    @FXML
    private TableColumn<Item, String> MesProduitsType;
    @FXML
    private TableColumn<Item, String> MesProduitsQuantite;
    @FXML
    private TableColumn<Item, String> MesProduitsDate;
    @FXML
    private TableColumn<Item, String> MesProduitsModification;
    @FXML
    private Button MesProduitsAjout;
    @FXML
    private TextField filterField;

    public void initMesProduits() throws Exception {
        MesProduitsAjout.setOnMouseClicked(event -> {
            try {
                addProduit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MesProduitsProduit.setCellValueFactory(new PropertyValueFactory<>("name"));
        MesProduitsType.setCellValueFactory(new PropertyValueFactory<>("tag"));
        MesProduitsQuantite.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MesProduitsDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        mytableTableView.setItems(produitsList);

        mytableTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mytableTableView.setOnMouseClicked(event -> {
                    Item itemz = mytableTableView.getSelectionModel().getSelectedItem();
                    modifProduit(itemz);
                });
            }
        });
        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory =
                new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Item, String> param) {
                        final TableCell<Item, String> cell = new TableCell<Item, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText(null);

                                } else {
                                    setText("Modification");;
                                }
                            }
                        };
                        return cell;
                    }
                };
        MesProduitsModification.setCellFactory(cellFactory);
        SearchBar();
    }

    public void addProduit() throws Exception {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Ajout));
        AjoutController controller_ajout = new AjoutController();
        loader.setController(controller_ajout);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Ajout));
            controller_ajout.initAjout(produitsList);
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

    public void modifProduit(Item item) {
        //En cas de clic sur le bouton "Modif"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Modif));
        MesProduitsModificationController controller_modif = new MesProduitsModificationController();
        loader.setController(controller_modif);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Modif));
            controller_modif.initModif(item);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Produit_Modif);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SearchBar(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredData = new FilteredList<>(produitsList, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(produit -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (produit.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (produit.getTag().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Item> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(mytableTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        mytableTableView.setItems(sortedData);
    }
}
