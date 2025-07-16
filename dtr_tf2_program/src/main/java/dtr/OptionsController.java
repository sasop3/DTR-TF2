package dtr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OptionsController extends options {

    public static boolean DupedDemoOption = false;

    public void Savebutton() {

        DupedDemoOption = DupeCheckBox.isSelected();
        File configFile = new File("dtr_tf2_program/src/main/java/config.cfg");

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
                // setDarkMode();                
            } else {
                optionsPane.getStylesheets().clear();
                // setLightMode();
            }

        } catch (Exception e) {
            ShowError(e.getMessage(), e.getMessage());
        }
    }

}
