package sample.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static sample.controller.AjoutController.isValidDate;

public class MesProduitsModificationController {
    @FXML
    private Spinner MesProduitModifierQuantite;
    @FXML
    private DatePicker MesProduitModifierDate;
    @FXML
    private Button MesProduitModifierButton;
    @FXML
    private TextField MesProduitModifierNom;
    @FXML
    private Button MesProduitSupprimerButton;
    @FXML
    private Label ErrorChamp;

    public void initModif(Item item){
        ErrorChamp.setVisible(false);
        MesProduitModifierNom.setText(item.getName());
        MesProduitModifierQuantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, item.getQuantity()));
        MesProduitModifierDate.setValue(item.getExpiryDate());
        MesProduitModifierButton.setOnMouseClicked(event ->  {
            if (MesProduitModifierNom.getText().isEmpty() || MesProduitModifierQuantite.getEditor().textProperty().get().isEmpty() || MesProduitModifierDate.getEditor().getText().isEmpty() || MesProduitModifierQuantite.getEditor().textProperty().get().matches(".*[a-z].*")|| MesProduitModifierQuantite.getEditor().textProperty().get().matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*") || !(isValidDate(MesProduitModifierDate.getEditor().getText()))){
                ErrorChamp.setVisible(true);
                ErrorChamp.setTextFill(Color.RED);
            } else {
                int newQuantity = Integer.parseInt(MesProduitModifierQuantite.getEditor().textProperty().get());
                if (newQuantity > 0) {
                    LocalDate localDate = LocalDate.parse(MesProduitModifierDate.getEditor().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    Item history = new Item(item.getName(), item.getTag(), item.getQuantity() - newQuantity, item.getExpiryDate());
                    item.setQuantity(newQuantity);
                    item.setName(MesProduitModifierNom.getText());
                    item.setExpiryDate(localDate);
                    HistoriqueController.list.add(history);
                } else {
                    HistoriqueController.list.add(item);
                    MesProduitsController.produitsList.remove(item);
                }
                cancel(MesProduitModifierButton);
            }
        });
    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
        MesProduitsController.tableTableView.refresh();
    }
}
