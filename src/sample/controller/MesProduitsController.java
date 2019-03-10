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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.Item;
import sample.model.View;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.event.ActionEvent;

public class MesProduitsController {
    PseudoClass currentday = PseudoClass.getPseudoClass("currentday");
    PseudoClass past = PseudoClass.getPseudoClass("past");


    public static ObservableList<Item> produitsList = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2019, Month.MARCH, 29)),
            new Item("Chocapic Chocolat","Cereales",2,LocalDate.of(2020, Month.MAY, 20)),
            new Item("Soya Juice","Lait",10,LocalDate.of(2019, Month.MARCH, 28)),
            new Item("Cordon Bleu","Viande",2,LocalDate.of(2019, Month.MARCH, 05)),
            new Item("Cordon Bleu","Viande",1,LocalDate.of(2019, Month.MARCH, 11))
    );

    public static TableView<Item> tableTableView;

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
    private TableColumn<Item, Void> MesProduitsModification;
    @FXML
    private TableColumn<Item, Void> MesProduitsSuppression;
    @FXML
    private Button MesProduitsAjout;
    @FXML
    private TextField filterField;
    private LocalDate Date;

    public void initMesProduits() throws Exception {
        tableTableView = mytableTableView;
        tableTableView.setEditable(true);
        Date = LocalDate.now();
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

        addButtonToTable();
        addButtonToTableSuppr();


        String expiredProducts = String.join(", ", produitsList.stream().filter(item -> item.getExpiryDate().isBefore(LocalDate.now())).map(Item::getName).collect(Collectors.toList()));
        String nearExpiredProducts = String.join(", ", produitsList.stream().filter(item -> item.getExpiryDate().isEqual(LocalDate.now().plusDays(1))).map(Item::getName).collect(Collectors.toList()));

        String alerte = "";
        if (!expiredProducts.equals(""))
            alerte += "Ces produits ont expiré :\n" + expiredProducts + "\n";
        if (!nearExpiredProducts.equals(""))
            alerte += "Ces produits vont bientot expirer :\n" + nearExpiredProducts + "\n";
        if (!alerte.equals(""))
            AlerteController.alert(alerte);

//------------------------------Modification
        //name
        MesProduitsProduit.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
        MesProduitsProduit.setOnEditCommit((CellEditEvent<Item, String> event) -> {
            TablePosition<Item, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Item produit = event.getTableView().getItems().get(row);

            produit.setName(newFullName);
        });

        //type

        MesProduitsType.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
        MesProduitsType.setOnEditCommit((CellEditEvent<Item, String> event) -> {
            TablePosition<Item, String> pos = event.getTablePosition();

            String newFulltag = event.getNewValue();

            int row = pos.getRow();
            Item type = event.getTableView().getItems().get(row);

            type.setTag(newFulltag);
        });


//------------------------------------------------------------- COLOR
        mytableTableView.setRowFactory(tableView -> {
            TableRow<Item> row = new TableRow<>();
            row.itemProperty().addListener((obs, previousProduit, currentProduit) -> {
                if (currentProduit != null) {
                    row.pseudoClassStateChanged(currentday, currentProduit.getExpiryDate().equals(Date));
                    row.pseudoClassStateChanged(past, currentProduit.getExpiryDate().isBefore(Date));
                }
            });
            return row;
        });
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
            stage.setWidth(600);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Produit_Ajout);
            scene.getStylesheets().add(View.CSS_File);
            stage.getIcons().add(new Image("/sample/CSS/logo.png"));
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
            stage.setHeight(225);
            stage.setTitle(View.LABEL_Produit_Modif);
            scene.getStylesheets().add(View.CSS_File);
            stage.getIcons().add(new Image("/sample/CSS/logo.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void supprProduit(Item item){
        Gaspillage(item);
        MesProduitsController.produitsList.remove(item);
    }

    public void Gaspillage(Item item) {
        //En cas de clic sur le bouton "Modif"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Gaspillage));
        ModificationGaspillageController controller_gaspillage = new ModificationGaspillageController();
        loader.setController(controller_gaspillage);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Gaspillage));
            controller_gaspillage.initModificationGaspillage(item);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(400);
            stage.setHeight(225);
            stage.setTitle(View.XML_Label_Gaspillage);
            scene.getStylesheets().add(View.CSS_File);
            stage.getIcons().add(new Image("/sample/CSS/logo.png"));
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

                // Filter matches last name.
                if (produit.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else return produit.getTag().toLowerCase().contains(lowerCaseFilter);
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

    private void addButtonToTable() {
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            modifProduit(getTableView().getItems().get(getIndex()));
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        MesProduitsModification.setCellFactory(cellFactory);
    }
    private void addButtonToTableSuppr() {


        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btnsupp = new Button("Supprimer");

                    {
                        btnsupp.setOnAction((ActionEvent event) -> {
                            supprProduit(getTableView().getItems().get(getIndex()));
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnsupp);
                        }
                    }
                };
                return cell;
            }
        };

        MesProduitsSuppression.setCellFactory(cellFactory);
    }

}
