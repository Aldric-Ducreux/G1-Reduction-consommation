package sample.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import sample.model.Item;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    }
}
