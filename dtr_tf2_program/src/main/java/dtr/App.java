package dtr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {


    Pane Mainpane;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Image icon = new Image(getClass().getResourceAsStream("/Star_of_my_own.png"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("DTR");
        stage.show();
        Controller tf = loader.getController();
        tf.startup();
    }

    public static void main(String[] args) {
        launch(args);
    }

}