package dtr;

import java.io.FileOutputStream;

import java.io.OutputStream;



public class OptionsController extends options {

    public static boolean DupedDemoOption = false;

    public void Savebutton() {

        DupedDemoOption = DupeCheckBox.isSelected();
        
        prop.setProperty("DupeDemoOption", DupedDemoOption + "");
        
        try(OutputStream output = new FileOutputStream("src/main/java/config.cfg")) {
            prop.store(output, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finished");
    }

}
