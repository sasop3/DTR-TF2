package dtr;

import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class options extends Controller {

    @FXML
    protected ToggleButton LDMode;

    @FXML
    protected Pane optionsPane;

    protected Controller mainController;

    Properties prop = new Properties();

    protected Stage OptionStage = new Stage();

    public void showOptions() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/options.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            OptionStage.setScene(scene);
            OptionStage.getIcons().add(new Image("/optionsIcon.png"));
            OptionStage.setTitle("Options");
            OptionStage.setResizable(false);
            OptionStage.show();
            OptionsController Controller = loader.getController();
            Controller.startup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
