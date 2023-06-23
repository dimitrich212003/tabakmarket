module com.example.tabakjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.tabakjavafx to javafx.fxml;
    exports com.example.tabakjavafx;
}