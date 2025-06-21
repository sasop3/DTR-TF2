module dtr {
    requires javafx.controls;
    requires javafx.fxml;

    opens dtr to javafx.fxml;
    exports dtr;
}
