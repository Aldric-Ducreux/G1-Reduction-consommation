package sample.controller;

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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.model.Item;
import sample.model.User;
import sample.model.View;

import java.io.IOException;

public class MesAmisController {

    ObservableList<User> ListUser = FXCollections.observableArrayList(
            new User("Bertrand","","")
    );
    public static ObservableList<String> ListCommentaire = FXCollections.observableArrayList(
            new String("Bertrand (Haricot Géant Vert) : Wow, c'est vraiment un bon produit, pas déçu")
    );

    @FXML
    private TableView<User> myTableAmis;
    @FXML
    private ListView AmisListCommentaire;
    @FXML
    private TableColumn<User, String> MesAmis;
    @FXML
    private Button MesAmisAjout;
    @FXML
    private TextField filterField;

    public void initMesAmis() {
        MesAmisAjout.setOnMouseClicked( event -> {
            try{
                addAmi();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MesAmis.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        loadData();
        SearchBar();
    }

    public void addAmi() {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Amis_Ajout));
        AjoutAmisController controller_ajoutAmis = new AjoutAmisController();
        loader.setController(controller_ajoutAmis);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Amis_Ajout));
            controller_ajoutAmis.initAjoutAmis(ListUser);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Amis_Ajout);
            scene.getStylesheets().add(View.CSS_File);
            stage.getIcons().add(new Image("/sample/CSS/logo.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        myTableAmis.setItems(ListUser);
        AmisListCommentaire.setItems(ListCommentaire);
    }
    public void SearchBar(){
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(ListUser, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ami -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                // Filter matches first name.
                return ami.getPseudo().toLowerCase().contains(lowerCaseFilter);
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(myTableAmis.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        myTableAmis.setItems(sortedData);
    }
}
