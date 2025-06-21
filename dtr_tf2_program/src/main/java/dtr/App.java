package dtr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
 
@Override
public void start(Stage stage) throws Exception {
    
    Parent root = FXMLLoader.load(getClass().getResource("layoutthing.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();



    
}

    public static void main(String[] args) {
        launch(args);
    }

}