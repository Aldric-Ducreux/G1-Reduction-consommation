package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.User;

public class AjoutAmisController {

    @FXML
    private TextField AjoutAmisPseudo;

    @FXML
    private Button AjoutAmisBoutton;
    @FXML
    private Label ErrorChamp;

    public void initAjoutAmis(ObservableList<User> amis) {
        ErrorChamp.setVisible(false);
        AjoutAmisBoutton.setOnMouseClicked( event -> {
            try{
                addAmi(amis, AjoutAmisPseudo.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void addAmi(ObservableList<User> amis, String ami){
        if (AjoutAmisPseudo.getText().isEmpty() || amis.stream().anyMatch(copain -> copain.getPseudo().equals(ami))){
            ErrorChamp.setVisible(true);
            ErrorChamp.setTextFill(Color.RED);
        } else {
            amis.add(new User(ami, "", ""));
            cancel(AjoutAmisBoutton);
        }
    }

    public void cancel(Button BT){
        Stage stage = (Stage) BT.getScene().getWindow();
        stage.close();
    }
}
