package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.model.View;
import java.io.IOException;

public class ListeCourseController {
    @FXML
    private Button MesCoursesAjout;

    public void initMesCourses() {
        MesCoursesAjout.setOnMouseClicked( event -> {
            try{
                addCourse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addCourse()throws Exception{
        //En cas de clic sur le bouton "Ajout"
        FXMLLoader loader = new FXMLLoader(getClass().getResource(View.XML_FILE_Course_Ajout));
        AjoutCourseController controller_ajoutCourse = new AjoutCourseController();
        loader.setController(controller_ajoutCourse);
        try {
            Parent page = loader.load(getClass().getResourceAsStream(View.XML_FILE_Course_Ajout));
            controller_ajoutCourse.initAjoutCourse();
            Scene scene = new Scene(page);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setWidth(550);
            stage.setHeight(250);
            stage.setTitle(View.LABEL_Course_Ajout);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
