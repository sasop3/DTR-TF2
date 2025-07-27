module dtr {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;
    requires javafx.graphics;

    opens dtr to javafx.fxml;
    exports dtr;
    
    
}
