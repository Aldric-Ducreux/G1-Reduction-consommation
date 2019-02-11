package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.xml.soap.Text;

public class Controller {
    @FXML
    private TextField val1; //Champ de la page xml, dois prendre le noms du xml
    @FXML
    private Button button;
    private Text res;
    public void valid(char operation) {
        int value1=Integer.parseInt(val1.getText());
        Model test = new Model(value1);
        res.setText(String.valueOf(calc.compute(operation)));
    }
    public void init(){

    }

}
