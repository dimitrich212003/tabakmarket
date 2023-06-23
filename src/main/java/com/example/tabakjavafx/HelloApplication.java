package com.example.tabakjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();

            HelloController controller = fxmlLoader.getController();
            DataAccessor dataAccessor = DataAccessor.getDataAccessor();
            controller.setDataAccessor(dataAccessor);

            Scene scene = new Scene(root, 1041, 509);
            stage.setTitle("Tabak shop");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

