package dtr;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private Label DEMOPATHLABEL;



    public void DemofileChooser(ActionEvent e)
    {
        FileChooser demofilechooser = new FileChooser();
        File file = demofilechooser.showOpenDialog(null);
        

        if(file != null)
            DEMOPATHLABEL.setText(file.getAbsolutePath());



    }
}
