package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.View;

import java.io.IOException;

public class AlerteController {

    @FXML
    private Label Label;
    @FXML
    private Button Boutton;

    public static void alert(String alerte) {
        AlerteController alerteController = new AlerteController();

        FXMLLoader loader = new FXMLLoader(AlerteController.class.getResource(View.XML_FILE_Alerte));
        loader.setController(alerteController);
        try {
            Parent page = loader.load(AlerteController.class.getResourceAsStream(View.XML_FILE_Alerte));
            alerteController.initAlerte(alerte);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Alerte);
            scene.getStylesheets().add(View.CSS_File);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initAlerte(String message) {
        Label.setText(message);
        Boutton.setOnMouseClicked(event -> {
            Stage stage = (Stage) Boutton.getScene().getWindow();
            stage.close();
        });
    }

}
