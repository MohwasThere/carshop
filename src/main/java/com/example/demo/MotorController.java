package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MotorController {
    public void switchCar(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listings.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }

    public void switchInvoice(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main7ambola.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }
}
