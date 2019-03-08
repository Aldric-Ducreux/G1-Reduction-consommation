package sample.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Item;

import java.awt.*;
import java.beans.EventHandler;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;

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

    public void initModif(Item item){
        MesProduitModifierNom.setText(item.getName());
        //MesProduitModifierQuantite.getValueFactory().setValue(item.getQuantity()); normalement c'est ca mais j'ai un nullpointer
        MesProduitModifierDate.setValue(item.getExpiryDate());
        MesProduitSupprimerButton.setOnMouseClicked(event ->  {
            HistoriqueController.list.add(item);
            MesProduitsController.produitsList.remove(item);
            cancel(MesProduitSupprimerButton);
        });
        MesProduitModifierButton.setOnMouseClicked(event ->  {
            int newQuantity = Integer.parseInt(MesProduitModifierQuantite.getEditor().textProperty().get());
            if (newQuantity > 0) {
                Item history = new Item(item.getName(), item.getTag(), item.getQuantity()-newQuantity, item.getExpiryDate());
                item.setQuantity(newQuantity);
                HistoriqueController.list.add(history);
            }
            else {
                HistoriqueController.list.add(item);
                MesProduitsController.produitsList.remove(item);
            }
            cancel(MesProduitModifierButton);
        });
    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
        MesProduitsController.tableTableView.refresh();
    }
}
