package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class MesProduitsController {
    @FXML
    private Button Ajout;

    public void initMesProduits() {
        Ajout.setOnMouseClicked( event -> {
            try{
                addProduit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public void addProduit()throws Exception{
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Ajout));
        AjoutController controller_ajout = new AjoutController();
        loader.setController(controller_ajout);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Ajout));
            controller_ajout.initAjout();
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
