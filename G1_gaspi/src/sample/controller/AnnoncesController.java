package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.model.Annonce;
import sample.model.Item;
import sample.model.View;

import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class AnnoncesController {
    PseudoClass tooMany = PseudoClass.getPseudoClass("tooMany");

    ObservableList<Annonce> list = FXCollections.observableArrayList(
            new Annonce(new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),"Carrefour Antibes"),
            new Annonce(new Item("Chocapic Chocolat","Cereales",2, LocalDate.of(2000, Month.MAY, 20)), "Carrefour Monaco"),
            new Annonce(new Item("Soya Juice","Lait",21, LocalDate.of(2000, Month.MAY, 20)),"Spar Nice")
    );
    @FXML
    private ListView<Annonce> ListV_Annonces;
    @FXML
    private TextField rechercher_Annonces;

    public void initAnnonce() {

        /*ListV_Annonces.setOrientation(Orientation.VERTICAL);

        loadData();
        SearchBar();*/

    }

    /*private void loadData() {ListV_Annonces.setItems(list);
    }

    public void SearchBar(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Annonce> filteredData = new FilteredList<>(list, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        rechercher_Annonces.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(produit -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (produit.getItem().getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (produit.getItem().getTag().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (produit.getMagasin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Annonce> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        //sortedData.comparatorProperty().bind(ListV_Annonces.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        ListV_Annonces.setItems(sortedData);
    }*/
}


