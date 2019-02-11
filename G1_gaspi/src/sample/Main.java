package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();

        //create controller
        Controller controller = new Controller();

        //attach controller
        loader.setController();

        //attach xml
        Parent root = loader.load(getClass().getResourceAsStream(View.XML_FILE));

        //attach css
        root.getStylesheets().add(View.CSS);

        //init controller
        controller.init();

        //display view
        primaryStage.setTitle(View.LABEL);
        primaryStage.setScene(new Scene(root,View.WIDTH, View.HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
