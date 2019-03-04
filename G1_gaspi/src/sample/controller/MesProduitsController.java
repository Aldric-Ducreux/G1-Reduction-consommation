package sample.controller;


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
import sample.model.ItemList;
import sample.model.View;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

public class MesProduitsController {
    String pathMesProduits = "Produits.json";
    ItemList produits;
    ObservableList<Item> list = FXCollections.observableArrayList(
            new Item("Jambon Laoste","Jambon",5, LocalDate.of(2000, Month.MAY, 20)),
            new Item("Chocapic Chocolat","Cereales",2,LocalDate.of(2000, Month.MAY, 22)),
            new Item("Soya Juice","Lait",10,LocalDate.of(2000, Month.MAY, 25))
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


    public void initMesProduits() {
        //produits = ItemList.loadFromFile(pathMesProduits);
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

        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory =
                new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Item, String> param) {
                        final TableCell<Item, String> cell = new TableCell<Item, String>() {

                            final Button btn = new Button("Just Do It");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Item person = getTableView().getItems().get(getIndex());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
    }

    public void addProduit() throws Exception {
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Produit_Ajout));
        AjoutController controller_ajout = new AjoutController();
        loader.setController(controller_ajout);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Produit_Ajout));
            controller_ajout.initAjout(produits);
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

}
