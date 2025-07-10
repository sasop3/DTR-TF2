package dtr;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class OptionsController extends options {

    public static boolean dupedemooption = false;

    @FXML
    private CheckBox dupecheckbox;

    public void Savebutton() {

        try {
            dupedemooption = dupecheckbox.isSelected();
            System.out.println(dupedemooption); //debug
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
