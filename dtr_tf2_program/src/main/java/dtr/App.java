package dtr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("layoutthing.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Controller tf = new Controller();

        if (!tf.isTF2DIRDETECTED()) {
            Alert error = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
            error.setTitle("TF2 DIRECTORY NOT DETECTED");
            error.setContentText(
                    "TF2 DIRECTORY WAS NOT DETECTED\n\n Please manually choose the location of your tf2 directory");
            error.setHeaderText(null);
            error.showAndWait();

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}