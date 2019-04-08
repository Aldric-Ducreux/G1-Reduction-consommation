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

public class AjoutController {
    @FXML
    private Spinner MesProduitAjoutQuantite;
    @FXML
    private DatePicker MesProduitAjoutDate;
    @FXML
    private Button MesProduitAjoutButton;
    @FXML
    private TextField MesProduitAjoutNom;
    @FXML
    private Label ErrorChamp;

    public void initAjout(ObservableList<Item> produits) throws Exception {
        ErrorChamp.setVisible(false);
        MesProduitAjoutQuantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));
        EventHandler<KeyEvent> enterKeyEventHandler;
        enterKeyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String OldValue = MesProduitAjoutQuantite.getEditor().textProperty().get();
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        Integer.parseInt(MesProduitAjoutQuantite.getEditor().textProperty().get());
                    } catch (NumberFormatException e) {
                        MesProduitAjoutQuantite.getEditor().textProperty().set(OldValue);
                    }
                }
            }
        };
        MesProduitAjoutQuantite.getEditor().addEventHandler(KeyEvent.KEY_PRESSED, enterKeyEventHandler);
        MesProduitAjoutButton.setOnMouseClicked( event -> {
            try{
                addProduit(produits, MesProduitAjoutNom.getText(), MesProduitAjoutQuantite.getEditor().textProperty().get(), MesProduitAjoutDate.getEditor().getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void addProduit(ObservableList<Item> produits, String produit, String nombre, String date ){
        if (produit.isEmpty() || nombre.isEmpty() || date.isEmpty() || nombre.matches(".*[a-z].*") || nombre.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*") ||
                !(isValidDate(date))|| nombre.equals(0)){
            ErrorChamp.setVisible(true);
            ErrorChamp.setTextFill(Color.RED);
        } else {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (produits.stream().anyMatch(item -> item.getName().equals(produit) && item.getExpiryDate().equals(localDate))) {
                Item i = produits.stream().filter(item -> item.getName().equals(produit) && item.getExpiryDate().equals(localDate)).findFirst().get();
                i.setQuantity(i.getQuantity() + Integer.parseInt(nombre));

                int n = produits.stream().filter(item -> item.getName().equals(produit) && item.getExpiryDate().equals(localDate)).findAny().get().getQuantity();
                if (n >= 5) AlerteController.alert("Vous avez déjà " + n + " " + produit + ", attention à ne pas gaspiller !");
            }else {
                produits.add(new Item(produit, produit, Integer.parseInt(nombre), localDate));
            }
            cancel(MesProduitAjoutButton);
        }

    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
