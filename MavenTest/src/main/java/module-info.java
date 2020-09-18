module com.mycompany.maventest {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.maventest to javafx.fxml;
    exports com.mycompany.maventest;
}
