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

import java.time.LocalDate;

public class AjoutHistoriqueController {
    @FXML
    private TextField AjoutCourseNom;
    @FXML
    private Spinner AjoutCourseQuantite;
    @FXML
    private Button AjoutCourseBoutton;
    @FXML
    private Label ErrorChamp;

    public void initAjoutHistorique(ObservableList<Item> produits, Item item) {
        ErrorChamp.setVisible(false);
        AjoutCourseNom.setText(item.getName());
        AjoutCourseQuantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));
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
        if (!(AjoutCourseNom.getText().isEmpty())) {
            LocalDate localDate = LocalDate.now();
            produits.add(new Item(produit, produit, Integer.parseInt(nombre), localDate));
            int n = produits.stream().filter(item -> item.getName().equals(produit)).findAny().get().getQuantity();
            if (n >= 2)
                AlerteController.alert("Vous avez déjà " + n + " " + produit + ", attention à ne pas gaspiller !");
            cancel(AjoutCourseBoutton);
        } else {
            ErrorChamp.setVisible(true);
            ErrorChamp.setTextFill(Color.RED);
        }
    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
    }
}