package sample.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class ListeCourseController {
    ObservableList<Item> list = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Chocapic Chocolat","Cereales",2, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Soya Juice","Lait",10, LocalDate.of(2000, Month.MAY, 20))
    );
    @FXML
    private TableView<Item> mytableTableView;
    @FXML
    private TableColumn<Item, String> ListeCourseProduits;
    @FXML
    private TableColumn<Item, String> ListeCourseQuantite;
    @FXML
    private TableColumn<Item, String> ListeCourseModification;
    @FXML
    private TableColumn<Item, String> ListeCourseSuppr;
    @FXML
    private Button MesCoursesAjout;


    public void initMesCourses() {
        MesCoursesAjout.setOnMouseClicked( event -> {
            try{
                addCourse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ListeCourseProduits.setCellValueFactory(new PropertyValueFactory<>("name"));
        ListeCourseQuantite.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadData();
    }

    public void addCourse()throws Exception{
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Course_Ajout));
        AjoutCourseController controller_ajoutCourse = new AjoutCourseController();
        loader.setController(controller_ajoutCourse);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Course_Ajout));
            controller_ajoutCourse.initAjoutCourse();
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Course_Ajout);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        mytableTableView.setItems(list);
    }
}
