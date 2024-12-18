package com.example.demo; // or your specified package name

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String host = "jdbc:mysql://localhost:3306/carshop";
        String username = "root";
        String password = "password";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/7ambola.fxml"));

        if (fxmlLoader.getLocation() == null) {
            System.out.println("FXML file not found!");
        }
        Scene LoginScene = new Scene(fxmlLoader.load(), 1080, 600);
        stage.setTitle("CarShop");
        stage.setScene(LoginScene);
        stage.setResizable(false);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
