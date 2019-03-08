package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Item;

public class ListeCourseModificationController {
    @FXML
    private Spinner MesProduitModifierQuantite;
    @FXML
    private DatePicker MesProduitModifierDate;
    @FXML
    private Button MesProduitModifierButton;
    @FXML
    private TextField MesProduitModifierNom;

    public void initModifCourse(Item item){

    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
        MesProduitsController.tableTableView.refresh();
    }
}
