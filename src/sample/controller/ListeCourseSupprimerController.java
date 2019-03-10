package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static sample.controller.AjoutController.isValidDate;

public class ListeCourseSupprimerController {
    @FXML
    private Spinner SupprimerQuantité;
    @FXML
    private DatePicker SupprimerDate;
    @FXML
    private Button SupprimerAchat;
    @FXML
    private Button SupprimerSuppr;
    @FXML
    private TextField SupprimerNom;
    @FXML
    private Label ErrorChamp;


    public void initAchatSuppr(Item item){
        ErrorChamp.setVisible(false);
        SupprimerNom.setText(item.getName());
        SupprimerQuantité.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, item.getQuantity()));

        SupprimerAchat.setOnMouseClicked(event ->  {
            try{
                addProduit(MesProduitsController.produitsList, item, SupprimerNom.getText(), SupprimerQuantité.getEditor().textProperty().get(), SupprimerDate.getEditor().getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        SupprimerSuppr.setOnMouseClicked(event ->  {
            try{
                ListeCourseController.list.remove(item);
                cancel(SupprimerSuppr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void addProduit(ObservableList<Item> produits,Item itemm, String produit, String nombre, String date) {
        if (produit.isEmpty() || nombre.isEmpty() || date.isEmpty() || nombre.matches(".*[a-z].*") || nombre.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*") || !(isValidDate(date))|| nombre.equals(0)){
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
            ListeCourseController.list.remove(itemm);
            cancel(SupprimerAchat);
        }

    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
        MesProduitsController.tableTableView.refresh();
        ListeCourseController.tableTableView.refresh();
    }
}
