package dtr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

public class Controller {

    @FXML
    private Label DEMOPATHLABEL;
    @FXML
    private Label TF2DIRLABEL;

    private File DEMOFILE;
    private Path tf2Path;

    public boolean FindTF2DIR() {

        File temp;

        for (File i : File.listRoots()) {
            if (new File(
                    i + "Program Files (x86)/Steam/steamapps/common/Team Fortress 2/tf/replay/client/replays")
                    .exists() &&
                    new File(i + "SteamLibrary/steamapps/common/Team Fortress 2/tf/replay/client/replays")
                            .exists()) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("MULTIPLE TF2 DIRECTROIES DETECTED");
                error.setContentText(
                        "Multiple TF2 DIRECTORIES WERE DETECTED\n Please manually choose the location of your tf2 directory");
                error.showAndWait();
            }

            temp = new File(
                    i + "Program Files (x86)/Steam/steamapps/common/Team Fortress 2/tf/replay/client/replays");
            if (temp.exists()) {
                tf2Path = temp.toPath();
                return true;
            }

            temp = new File(i + "SteamLibrary/steamapps/common/Team Fortress 2/tf/replay/client/replays");
            if (temp.exists()) {
                tf2Path = temp.toPath();
                return true;
            }

        }
        return false;
    }

    public boolean isTF2DIRDETECTED() {
        boolean check = false;

        for (File i : File.listRoots()) {
            if (new File(
                    i + "Program Files (x86)/Steam/steamapps/common/Team Fortress 2/tf/replay/client/replays")
                    .exists())
                check = true;
            else {
                if (new File(i + "SteamLibrary/steamapps/common/Team Fortress 2/tf/replay/client/replays")
                        .exists())
                    check = true;
            }
        }

        return check;
    }

    public void DemofileChooser(ActionEvent e) {
        FileChooser demofilechooser = new FileChooser();
        DEMOFILE = demofilechooser.showOpenDialog(null);

        if (DEMOFILE != null)
            DEMOPATHLABEL.setText(DEMOFILE.getAbsolutePath());
    }

    public void TF2DIRChooser(ActionEvent e) {
        DirectoryChooser tf2Chooser = new DirectoryChooser();

        File tmp = tf2Chooser.showDialog(null);

        if (tmp != null) {
            tf2Path = tmp.toPath();
            TF2DIRLABEL.setText(tmp.getAbsolutePath());
        }

    }

    public void ConvertButtonHandler(ActionEvent e) {

        try {
            FileUtils.copyFileToDirectory(DEMOFILE, tf2Path.toFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void startup() {

        if (!isTF2DIRDETECTED()) {
            Alert error = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
            error.setTitle("TF2 DIRECTORY NOT DETECTED");
            error.setContentText(
                    "TF2 DIRECTORY WAS NOT DETECTED\n\n Please manually choose the location of your tf2 directory");
            error.setHeaderText(null);
            error.showAndWait();

        }

        FindTF2DIR();
        TF2DIRLABEL.setText(tf2Path + "");

    }

}
