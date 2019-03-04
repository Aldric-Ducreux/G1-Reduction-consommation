package sample.controller;


import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.Item;
import sample.model.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class MesProduitsController {
    static ObservableList<Item> list = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Chocapic Chocolat","Cereales",2,LocalDate.of(2000, Month.MAY, 20)),
            new Item("Soya Juice","Lait",10,LocalDate.of(2000, Month.MAY, 20))
    );
    @FXML
    private TableView<Item> mytableTableView;
    @FXML
    private TableColumn<Item, String> MesProduitsProduit;
    @FXML
    private TableColumn<Item, String> MesProduitsType;
    @FXML
    private TableColumn<Item, String> MesProduitsQuantite;
    @FXML
    private TableColumn<Item, String> MesProduitsDate;
    @FXML
    private TableColumn MesProduitsModification;
    @FXML
    private Button MesProduitsAjout;
    private static int rangeSelectedItem = -1;


    public void initMesProduits() throws Exception {
        MesProduitsAjout.setOnMouseClicked(event -> {
            try {
                addProduit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        MesProduitsProduit.setCellValueFactory(new PropertyValueFactory<>("name"));
        MesProduitsType.setCellValueFactory(new PropertyValueFactory<>("tag"));
        MesProduitsQuantite.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MesProduitsDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        mytableTableView.setItems(list);
        listenTo(mytableTableView);

        mytableTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Item itemz = mytableTableView.getSelectionModel().getSelectedItem();
                modifProduit(itemz);
            }
        });
        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory =
                new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Item, String> param) {
                        final TableCell<Item, String> cell = new TableCell<Item, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText(null);

                                } else {
                                    setText("Modification");;
                                }
                            }
                        };
                        return cell;
                    }
                };
        MesProduitsModification.setCellFactory(cellFactory);
    }

    public void addProduit() throws Exception {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Ajout));
        AjoutController controller_ajout = new AjoutController();
        loader.setController(controller_ajout);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Ajout));
            controller_ajout.initAjout(list);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Produit_Ajout);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifProduit(Item item) {
        //En cas de clic sur le bouton "Modif"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Modif));
        MesProduitsModificationController controller_modif = new MesProduitsModificationController();
        loader.setController(controller_modif);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Modif));
            controller_modif.initModif(item);
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Produit_Modif);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRangeSelectedItem() {
        return rangeSelectedItem;
    }

    private void listenTo(TableView listView) {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ChangeListener<Item>) (observable, oldValue, newValue) -> {
                    rangeSelectedItem = mytableTableView.getItems().indexOf(newValue);
                    //mytableTableView.
                });
    }

}
