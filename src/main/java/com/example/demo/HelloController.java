package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.awt.*;

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

    public void check()
    {

    }

    public void round()
    {
    }



}
