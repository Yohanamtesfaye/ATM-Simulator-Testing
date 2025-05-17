module com.abeto.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    // requires org.controlsfx.controls;
    // requires org.kordamp.bootstrapfx.core;

    opens com.abeto.javafx to javafx.fxml, org.mockito, org.testfx.junit5, javafx.graphics;

    exports com.abeto.javafx;
}