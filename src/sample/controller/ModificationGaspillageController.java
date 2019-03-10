package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import sample.model.Item;

public class ModificationGaspillageController {
    @FXML
    private Spinner GaspillageQuantite;
    @FXML
    private Button GaspillageButton;

    public void initModificationGaspillage(Item item){
        int max = item.getQuantity();
        GaspillageQuantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max, 0));
        GaspillageButton.setOnMouseClicked(event -> {
            item.setQuantity(Integer.parseInt(GaspillageQuantite.getEditor().textProperty().get()));
            HistoriqueController.list.add(item);
            Stage stage = (Stage) GaspillageButton.getScene().getWindow();
            stage.close();
            MesProduitsController.tableTableView.refresh();
        });
    }
}
