package dtr;

import java.io.File;
import java.nio.file.Path;
import java.security.PublicKey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private Label DEMOPATHLABEL;
    @FXML
    private Label TF2DIRLABEL;

    private File DEMOFILE;
    private Path tf2Path;

    public boolean FindTF2DIR() {

        File temp;
        File TF2DIRLOCATION;

        for (File i : File.listRoots()) {
            if (new File(
                    i + "Program Files (x86)\\Steam\\steamapps\\common\\Team Fortress 2\\tf\\replay\\client\\replays")
                    .exists() &&
                    new File(i + "SteamLibrary\\steamapps\\common\\Team Fortress 2\\tf\\replay\\client\\replays")
                            .exists()) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("MULTIPLE TF2 DIRECTROIES DETECTED");
                error.setContentText(
                        "Multiple TF2 DIRECTORIES WERE DETECTED\n Please manually choose the location of your tf2 directory");
                error.showAndWait();
            }

            temp = new File(
                    i + "Program Files (x86)\\Steam\\steamapps\\common\\Team Fortress 2\\tf\\replay\\client\\replays");
            if (temp.exists()) {
                TF2DIRLOCATION = temp;
            } else {
                if (new File(i + "SteamLibrary\\steamapps\\common\\Team Fortress 2\\tf\\replay\\client\\replays")
                        .exists())
                    ;
            }

        }
        return false;// debug
    }

    public boolean isTF2DIRDETECTED() {
        boolean check = false;

        for (File i : File.listRoots()) {
            if (new File(
                    i + "Program Files (x86)\\Steam\\steamapps\\common\\Team Fortress 2\\tf\\replay\\client\\replays")
                    .exists())
                check = true;
            else {
                if (new File(i + "SteamLibrary\\steamapps\\common\\Team Fortress 2\\tf\\replay\\client\\replays")
                        .exists())
                    check = true;
            }
        }

        return check;
    }

    public void ConvertDemoFile(Path path) {

    }

    public void DemofileChooser(ActionEvent e) {
        FileChooser demofilechooser = new FileChooser();
        DEMOFILE = demofilechooser.showOpenDialog(null);

        if (DEMOFILE != null)
            DEMOPATHLABEL.setText(DEMOFILE.getAbsolutePath());
    }

    public void TF2DIRChooser(ActionEvent e) {
        FileChooser tf2Chooser = new FileChooser();
        File tmp = tf2Chooser.showOpenDialog(null);

        if (tmp != null) {
            tf2Path = tmp.toPath();
            TF2DIRLABEL.setText(tmp.getAbsolutePath());
        }
    }

    public void ConvertButtonHandler(ActionEvent e) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("MULTIPLE TF2 DIRECTROIES DETECTED");
        error.setContentText(
                "Multiple TF2 DIRECTORIES WERE DETECTED\n\n Please manually choose the location of the desire tf2 directory");
        error.setHeaderText(null);
        error.showAndWait();
    }

}
