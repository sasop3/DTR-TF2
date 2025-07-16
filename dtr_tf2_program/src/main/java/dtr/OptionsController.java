package dtr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class OptionsController extends options {

    @FXML
    private CheckBox DupeCheckBox;

    public static boolean DupedDemoOption = false;
    File configFile = new File("dtr_tf2_program/src/main/java/config.cfg");

    public void Savebutton() {

        DupedDemoOption = DupeCheckBox.isSelected();

        try {
            if (!configFile.exists())
                configFile.createNewFile();
        } catch (IOException e) {
            ShowError("IO ERROR", e.getMessage());
        } catch (SecurityException e) {
            ShowError("Security ERROR",
                    "Config file couldn't be created or modified because security permissions were not granted");
        }
        prop.setProperty("DupeDemoOption", DupedDemoOption + "");

        try (OutputStream output = new FileOutputStream(configFile)) {
            prop.store(output, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DLModeToggle() {
        try {

            if (LDMode.isSelected()) {
                optionsPane.getStylesheets().add(getClass().getResource("/darkmode.css").toExternalForm());
                LDMode.setText("Dark mode");
                mainController.setLightMode();
                // setDarkMode(); debug
            } else {
                optionsPane.getStylesheets().clear();
                LDMode.setText("Light mode");
                mainController.setDarkMode();
                // setLightMode(); debug
            }

        } catch (Exception e) {
            ShowError(e.getMessage(), e.getMessage());
        }
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void startup() {
        try (InputStream input = new FileInputStream(configFile)) {
            prop.load(input);
            if (Boolean.parseBoolean(prop.getProperty("DupeDemoOption"))) {
                DupeCheckBox.setSelected(true);
            }

        } catch (Exception e) {
            ShowError("Load Config error", e.getMessage());
        }

    }

}
