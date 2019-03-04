package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AjoutCourseController {
    @FXML
    private TextField AjoutCourseNom;
    @FXML
    private Spinner AjoutCourseQuantite;
    @FXML
    private Button AjoutCourseBoutton;

    public void initAjoutCourse(ObservableList<Item> produits) {
        AjoutCourseQuantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100,
                Integer.parseInt("1")));
        EventHandler<KeyEvent> enterKeyEventHandler;
        enterKeyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String OldValue = AjoutCourseQuantite.getEditor().textProperty().get();
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        Integer.parseInt(AjoutCourseQuantite.getEditor().textProperty().get());
                    } catch (NumberFormatException e) {
                        AjoutCourseQuantite.getEditor().textProperty().set(OldValue);
                    }
                }
            }
        };
        AjoutCourseQuantite.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        AjoutCourseBoutton.setOnMouseClicked( event -> {
            try{
                addProduit(produits, AjoutCourseNom.getText(), AjoutCourseQuantite.getEditor().textProperty().get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void addProduit(ObservableList<Item> produits, String produit, String nombre){
        LocalDate localDate = LocalDate.now();
        produits.add(new Item(produit, produit, Integer.parseInt(nombre), localDate));
        cancel(AjoutCourseBoutton);
    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
    }
}
