package sample.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
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
                    int changement = item.getQuantity()-newQuantity;
                    Item history = new Item(item.getName(), item.getTag(), changement, item.getExpiryDate());
                    item.setQuantity(newQuantity);
                    item.setName(MesProduitModifierNom.getText());
                    item.setExpiryDate(localDate);
                    if(changement >0 ) {
                        Gaspillage(history);
                    }
                } else {
                    Gaspillage(item);
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
}
