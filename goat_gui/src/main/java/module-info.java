module com.mycompany.goat_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    opens com.mycompany.goat_gui to javafx.fxml;
    exports com.mycompany.goat_gui;
}
