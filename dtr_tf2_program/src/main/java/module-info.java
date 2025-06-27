module dtr {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;

    opens dtr to javafx.fxml;
    exports dtr;
    
    
}
