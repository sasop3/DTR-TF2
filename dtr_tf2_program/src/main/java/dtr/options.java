package dtr;

import java.io.Closeable;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class options extends Controller implements Closeable {

    public Stage OptionStage = new Stage();
    public void showOptions() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("options.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            OptionStage.setScene(scene);
            OptionStage.setTitle("Options");
            OptionStage.setResizable(false);
            OptionStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void close() throws IOException {
        OptionStage.close();
    }

    

    

}
