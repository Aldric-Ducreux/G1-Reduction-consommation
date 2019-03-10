package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class ListeCourseController {
    public static ObservableList<Item> list = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Chocapic Chocolat","Cereales",2, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Soya Juice","Lait",10, LocalDate.of(2000, Month.MAY, 20))
    );

    public static TableView<Item> tableTableView;
    @FXML
    private TableView<Item> mytableTableView;
    @FXML
    private TableColumn<Item, String> ListeCourseProduits;
    @FXML
    private TableColumn<Item, String> ListeCourseQuantite;
    @FXML
    private TableColumn<Item, Void> ListeCourseModification;
    @FXML
    private TableColumn<Item, Void> ListeCourseSuppr;
    @FXML
    private Button MesCoursesAjout;
    @FXML
    private TextField filterField;

    public void initMesCourses() {
        tableTableView = mytableTableView;
        tableTableView.setEditable(true);
        MesCoursesAjout.setOnMouseClicked( event -> {
            try{
                addCourse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ListeCourseProduits.setCellValueFactory(new PropertyValueFactory<>("name"));
        ListeCourseQuantite.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //name
        ListeCourseProduits.setCellFactory(TextFieldTableCell.<Item> forTableColumn());
        ListeCourseProduits.setOnEditCommit((TableColumn.CellEditEvent<Item, String> event) -> {
            TablePosition<Item, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Item produit = event.getTableView().getItems().get(row);

            produit.setName(newFullName);
        });
        loadData();
        addButtonToTable();
        addButtonToTableSuppr();
        SearchBar();
    }

    public void addCourse() {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Course_Ajout));
        AjoutCourseController controller_ajoutCourse = new AjoutCourseController();
        loader.setController(controller_ajoutCourse);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Course_Ajout));
            controller_ajoutCourse.initAjoutCourse(list);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Course_Ajout);
            scene.getStylesheets().add(View.CSS_File);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifProduit(Item item) {
        //En cas de clic sur le bouton "Modif"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Course_Modif));
        ListeCourseModificationController controller_modif = new ListeCourseModificationController();
        loader.setController(controller_modif);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Course_Modif));
            controller_modif.initModifCourse(item);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(225);
            stage.setTitle(View.LABEL_Course_Modif);
            scene.getStylesheets().add(View.CSS_File);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void supprimerProduit(Item item) {
        ListeCourseController.list.remove(item);
        ListeCourseController.tableTableView.refresh();
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

        ListeCourseModification.setCellFactory(cellFactory);
    }
    private void addButtonToTableSuppr() {
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btnSuppr = new Button("Supprimer");

                    {
                        btnSuppr.setOnAction((ActionEvent event) -> {
                            supprimerProduit(getTableView().getItems().get(getIndex()));
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSuppr);
                        }
                    }
                };
                return cell;
            }
        };

        ListeCourseSuppr.setCellFactory(cellFactory);
    }
}
