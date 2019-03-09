package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
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

public class HistoriqueController {
    PseudoClass tooMany = PseudoClass.getPseudoClass("tooMany");

    public static ObservableList<Item> list = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Chocapic Chocolat","Cereales",2, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Soya Juice","Lait",21, LocalDate.of(2000, Month.MAY, 20))
    );
    @FXML
    private TableView<Item> mytableTableView;
    @FXML
    private TableColumn<Item, String> HistoriqueProduits;
    @FXML
    private TableColumn<Item, String> HistoriqueType;
    @FXML
    private TableColumn<Item, String> HistoriqueQuantiteGaspi;
    @FXML
    private TableColumn<Item, String> HistoriqueDernierAchat;
    @FXML
    private TableColumn<Item, String> HistoriqueAjouter;
    @FXML
    private TextField filterField;

    public void initHistorique() {

        HistoriqueProduits.setCellValueFactory(new PropertyValueFactory<>("name"));
        HistoriqueType.setCellValueFactory(new PropertyValueFactory<>("tag"));
        HistoriqueQuantiteGaspi.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        HistoriqueDernierAchat.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        mytableTableView.setRowFactory(tableView -> {
            TableRow<Item> row = new TableRow<>();
            row.itemProperty().addListener((obs, previousProduit, currentProduit) -> {
                if (currentProduit != null) {
                    row.pseudoClassStateChanged(tooMany, currentProduit.getQuantity() > 20);
                }
            });
            return row;
        });

        loadData();

        mytableTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mytableTableView.setOnMouseClicked(event -> {
                    Item items = mytableTableView.getSelectionModel().getSelectedItem();
                    addProduitHistorique(items);
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
                                    setText("Ajout");;
                                }
                            }
                        };
                        return cell;
                    }
                };
        HistoriqueAjouter.setCellFactory(cellFactory);
        SearchBar();

    }

    public void addProduitHistorique(Item items){
        //En cas de clic sur le bouton "Ajout"
        ObservableList<Item> liste = ListeCourseController.list;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Historique_Ajout));
        AjoutHistoriqueController controller_ajoutHistorique = new AjoutHistoriqueController();
        loader.setController(controller_ajoutHistorique);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Historique_Ajout));
            controller_ajoutHistorique.initAjoutHistorique(liste, items);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Historique_Ajout);
            scene.getStylesheets().add(View.CSS_File);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        mytableTableView.setItems(list);
    }


    public void SearchBar(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Item> filteredData = new FilteredList<>(list, p -> true);

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

