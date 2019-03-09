package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.model.Item;

public class ListeCourseModificationController {
    @FXML
    private Spinner MesProduitModifierQuantite;
    @FXML
    private Button MesProduitModifierButton;
    @FXML
    private Button MesProduitSupprimerButton;
    @FXML
    private TextField MesProduitModifierNom;
    @FXML
    private Label ErrorChamp;

    public void initModifCourse(Item item){
        ErrorChamp.setVisible(false);
        MesProduitModifierNom.setText(item.getName());
        MesProduitModifierQuantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, item.getQuantity()));
        MesProduitSupprimerButton.setOnMouseClicked(event ->  {
            ListeCourseController.list.remove(item);
            cancel(MesProduitSupprimerButton);
        });
        MesProduitModifierButton.setOnMouseClicked(event ->  {
            int newQuantity = Integer.parseInt(MesProduitModifierQuantite.getEditor().textProperty().get());

            item.setQuantity(newQuantity);
            item.setName(MesProduitModifierNom.getText());
            cancel(MesProduitModifierButton);
        });
    }


    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
        ListeCourseController.tableTableView.refresh();
    }
}
