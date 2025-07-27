package dtr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;

public class OptionsController extends options {

    @FXML
    private CheckBox DupeCheckBox;

    public static boolean DupedDemoOption = false;
    public static boolean LDModeOption = false;




    public void Savebutton() throws InterruptedException {

        DupedDemoOption = DupeCheckBox.isSelected();
        LDModeOption = LDModeToggleButton.isSelected();

        // This checks if config file exists
        try {
            if (!configFile.exists())
                configFile.createNewFile();
        } catch (IOException e) {
            ShowError("IO ERROR", e.getMessage());
        } catch (SecurityException e) {
            ShowError("Security ERROR",
                    "Config file couldn't be created or modified because security permissions were not granted");
        }
        // End

        prop.setProperty("DupeDemoOption", DupedDemoOption + "");
        prop.setProperty("DarkMode", LDModeOption + "");

        try (OutputStream output = new FileOutputStream(configFile)) {
            prop.store(output, null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
            mainController.UpdateDarkMode();
        
        SuccessSave.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.millis(1800));
        pause.setOnFinished(event -> SuccessSave.setVisible(false));
        pause.play();

    }

    public void DarkModeToggle() {
        if (LDModeToggleButton.isSelected()) {
            optionsPane.getStylesheets().add(getClass().getResource("/darkmode.css").toExternalForm());
            LDModeToggleButton.setText("Dark mode");
        }
    }

    public void LightModeToggle() {
        if (!LDModeToggleButton.isSelected()) {
            optionsPane.getStylesheets().clear();
            LDModeToggleButton.setText("Light mode");
        }
    }

    public void DLModeToggle() {
        try {
            if (!LDModeToggleButton.isSelected()) {
                LightModeToggle();
            } else {
                DarkModeToggle();
            }

        } catch (Exception e) {
            ShowError(e.getMessage(), e.getMessage());
        }
    }

    public void LoadOption(String propertyName, Object javafxElement) {
        boolean value = Boolean.parseBoolean(prop.getProperty(propertyName));
        if (javafxElement instanceof CheckBox) {
            ((CheckBox) javafxElement).setSelected(value);
        } else if (javafxElement instanceof ToggleButton) {
            ((ToggleButton) javafxElement).setSelected(value);
            DLModeToggle();
        } else {
            System.exit(1);
            throw new IllegalArgumentException("Unsupported JavaFX element for LoadOption"); // pain
        }
    }

    public void startup() {
        try (InputStream input = new FileInputStream(configFile)) {
            prop.load(input);
            LoadOption("DupeDemoOption", DupeCheckBox);

            LoadOption("DarkMode", LDModeToggleButton);

        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            ShowError("Options Startup Error", e.getMessage());
        }

        

    }

}
