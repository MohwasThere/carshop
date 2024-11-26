package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label CarShop;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    public void initialize() {
        // Populate the ChoiceBox when the controller initializes
        System.out.println("initialize method called!");

    }

    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private Rectangle rect;
    @FXML
    static private Button login;

    public void check()
    {

    }


    public void handleLogin(javafx.event.ActionEvent event) throws IOException
    {
        // Load the second scene (Scene2.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main7ambola.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene  MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }
}
