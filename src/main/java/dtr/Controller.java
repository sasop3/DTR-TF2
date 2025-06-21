package dtr;

import javafx.event.ActionEvent;

public class Controller {
    
    int i = 0;


    public void startingbutton(ActionEvent e)
    {
        i++;
        System.out.println("Start " + i);
    }
}
