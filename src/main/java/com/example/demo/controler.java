package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class controler {
    public void switchScrean(javafx.event.ActionEvent event, String fxml, double w, double h){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene newScene = new Scene(loader.load(), w, h);
        stage.setScene(newScene);
        stage.show();}
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
