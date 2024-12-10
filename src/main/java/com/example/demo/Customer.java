package com.example.demo;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Customer {

    @FXML
    private Button New;
    @FXML
    private Button old;


    public void handleClick(ActionEvent actionEvent) {
        SellingPage.clickAnimation(New);
        controler controler = new controler();
        controler.switchScrean(actionEvent, "selling_page.fxml", 600, 400);
    }
    public void hoverIn(){
        New.setStyle("-fx-background-color: #153243; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
    public void hoverOut(){
        New.setStyle("-fx-background-color: #284B63; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }

    public void handleClick1(ActionEvent actionEvent) {
        SellingPage.clickAnimation(old);
        controler controler = new controler();
        controler.switchScrean(actionEvent, "oldCust.fxml", 600, 400);
    }

    public void hoverIn1(){
        old.setStyle("-fx-background-color: #153243; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
    public void hoverOut1(){
        old.setStyle("-fx-background-color: #284B63; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }

}
