module com.example.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;


    opens com.example.javafxexample to javafx.fxml;
    exports com.example.javafxexample;
}