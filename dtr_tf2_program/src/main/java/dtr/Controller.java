package dtr;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.commons.io.FileUtils;

public class Controller extends App {

    @FXML
    private Label DEMOPATHLABEL;
    @FXML
    private Label TF2DIRLABEL;

    @FXML
    protected Pane Mainpane;

    private File DEMOFILE;
    private Path tf2Path;

    public static void ShowError(String Title, String TextContent) {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle(Title);
        error.setHeaderText(null);
        Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
        Image img = new Image(Controller.class.getResourceAsStream("/errorIcon.png"));
        stage.getIcons().add(img);
        error.setContentText(TextContent);
        error.showAndWait();
    }

    public boolean FindTF2DIR() {

        File temp;

        for (File i : File.listRoots()) {
            if (new File(
                    i + "Program Files (x86)/Steam/steamapps/common/Team Fortress 2/tf/replay/client/replays")
                    .exists() &&
                    new File(i + "SteamLibrary/steamapps/common/Team Fortress 2/tf/replay/client/replays")
                            .exists()) {
                ShowError("MULTIPLE TF2 DIRECTROIES DETECTED", "Multiple TF2 DIRECTORIES WERE DETECTED\\n" + //
                        " Please manually choose the location of your tf2 directory");
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

    public void DemofileChooser(@SuppressWarnings("exports") ActionEvent e) {
        FileChooser demofilechooser = new FileChooser();
        demofilechooser.setTitle("Choose DemoFile");
        DEMOFILE = demofilechooser.showOpenDialog(null);

        if (DEMOFILE != null)
            DEMOPATHLABEL.setText(DEMOFILE.getAbsolutePath());
    }

    public void TF2DIRChooser(@SuppressWarnings("exports") ActionEvent e) {
        DirectoryChooser tf2Chooser = new DirectoryChooser();
        tf2Chooser.setTitle("Choose TF2 directory");
        File tmp = tf2Chooser.showDialog(null);

        if (tmp != null) {
            tf2Path = tmp.toPath();
            TF2DIRLABEL.setText(tmp.getAbsolutePath());
        }

    }

    public int getHighestReplayNumber() {
        int max = 0;
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".dmx") && name.startsWith("replay_");
            }

        };
        String[] files = tf2Path.toFile().list(filter);
        List<Integer> filteredfiles = new ArrayList<Integer>();

        for (String name : files) {
            name = name.replaceAll("replay_", "");
            name = name.replaceAll(".dmx", ""); // not too fond of this way
            filteredfiles.add(Integer.parseInt(name));
        }

        max = filteredfiles.get(0);
        for (int i = 0; i < files.length; i++) {
            if (max < filteredfiles.get(i)) {
                max = filteredfiles.get(i);
            }
        }

        return max + 1;
    }

    public static String readBytes(DataInputStream dis, int maxLength) throws IOException {
        byte[] buffer = new byte[maxLength];
        dis.readFully(buffer);
        int length = 0;
        while (length < maxLength && buffer[length] != 0) {
            length++;
        }
        return new String(buffer, 0, length, "UTF-8");
    }

    public static String extractMapName(File demoFile) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(demoFile))) {
            dis.skipBytes(8 + 4 + 4);
            dis.skipBytes(260);
            dis.skipBytes(260);

            return readBytes(dis, 260);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ConvertButtonHandler(@SuppressWarnings("exports") ActionEvent e) {

        if (DEMOFILE == null) {
            ShowError("Demofile not selected", "You have not Selected a demo file");
            return;
        }

        String dmxString = "\r\n" +
                "\"replay_" + getHighestReplayNumber() + "\"\r\n" +
                "{\r\n" +
                " \"handle\"  " + "\"" + getHighestReplayNumber() + "\"\r\n" +
                " \"map\"  \"" + extractMapName(DEMOFILE) + "\"\r\n" +
                " \"complete\"  \"1\"\r\n" +
                " \"title\"  \"TESTOFDTR\"\r\n" + // placeholder
                " \"recon_filename\" " + "\"" + DEMOFILE.getName() + "\"" + "\r\n" +
                "}";

        try {
            if (OptionsController.DupedDemoOption) {
                FileUtils.copyFileToDirectory(DEMOFILE, tf2Path.toFile());
            } else {
                FileUtils.moveFileToDirectory(DEMOFILE, tf2Path.toFile(), false);
            }

            File file = new File("replay_" + getHighestReplayNumber() + ".dmx");
            file.createNewFile();
            FileUtils.writeStringToFile(file, dmxString, StandardCharsets.UTF_8);
            FileUtils.moveToDirectory(file, tf2Path.toFile(), false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void Openoptions(@SuppressWarnings("exports") ActionEvent e) {

        try {
            new options().showOptions();
            // Mainpane.getStylesheets().add(getClass().getResource("/darkmode.css").toExternalForm());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void startup() {

            
        if (!isTF2DIRDETECTED()) {

            ShowError("TF2 DIRECTORY NOT DETECTED",
                    "TF2 DIRECTORY WAS NOT DETECTED\n\n Please manually choose the location of your tf2 directory");
        }

        FindTF2DIR();
        TF2DIRLABEL.setText(tf2Path + "");

    }

}
