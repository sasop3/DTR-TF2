package dtr;

import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class options extends Controller {

    @FXML
    protected CheckBox DupeCheckBox;

    Properties prop = new Properties();

    public Stage OptionStage = new Stage();

    public void showOptions() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/options.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            OptionStage.setScene(scene);
            OptionStage.setTitle("Options");
            OptionStage.setResizable(false);
            OptionStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
