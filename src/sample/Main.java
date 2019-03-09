package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.ConnexionController;
import sample.model.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        //create controller
        ConnexionController controller_connexion = new ConnexionController();
        //attach controller
        loader.setController(controller_connexion);
        //attach xml
        Parent root = loader.load(getClass().getResourceAsStream(View.XML_FILE_Connex));
        //attach css
        //root.getStylesheets().add(View.CSS);
        //init controller
        controller_connexion.initConnexion(primaryStage);
        //display view
        primaryStage.setTitle(View.LABEL_Connex);
        primaryStage.setScene(new Scene(root));
        Scene scene = primaryStage.getScene();
        scene.getStylesheets().add(View.CSS_File);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
