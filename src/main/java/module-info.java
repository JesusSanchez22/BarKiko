module com.example.barkiko {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.barkiko to javafx.fxml;
    exports com.example.barkiko;
}