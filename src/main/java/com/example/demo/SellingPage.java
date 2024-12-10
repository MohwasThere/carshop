package com.example.demo;

import back.Buyer;
import back.seller;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SellingPage {
    @FXML
    private TextField SSN;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private Button addInvoice;



    public void onClick(ActionEvent actionEvent) {
        clickAnimation(addInvoice);
        if(Buyer.checkInput(SSN.getText(), FirstName.getText(), LastName.getText())){
            SSN.setStyle("-fx-border-color: #153243");
            FirstName.setStyle("-fx-border-color: #153243");
            LastName.setStyle("-fx-border-color: #153243");
        } else {
            SSN.setStyle("-fx-border-color: red");
            FirstName.setStyle("-fx-border-color: red");
            LastName.setStyle("-fx-border-color: red");
            return;
        }
        String host = "jdbc:mysql://localhost:3306/carshop";
        String username = "root";
        String password = "password";
        Buyer.currentBuyer = new Buyer( SSN.getText(), FirstName.getText(), LastName.getText());
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            PreparedStatement sql = conn.prepareStatement("INSERT INTO buyer (buyer_ssn, first_name, last_name) VALUES (?, ?, ?)");
            sql.setString(1, Buyer.currentBuyer.getBuyer_ssn());
            sql.setString(2, Buyer.currentBuyer.getFirst_name());
            sql.setString(3, Buyer.currentBuyer.getLast_name());
            sql.executeUpdate();
            sql.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database");
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            PreparedStatement sql = conn.prepareStatement("INSERT INTO invoice (v_id, buyer_ssn, description, seller_id) VALUES (?, ?, ?, ?)");
            System.out.println(""+ Card.vehicl1.getv_id() + " " + Buyer.currentBuyer.getBuyer_ssn() + " " + "purchased a " + Card.vehicl1.getcompany() + " " + Card.vehicl1.getmodel_name() + " " + Card.vehicl1.getyom() + " " + seller.currentSeller.getSeller_id());
            sql.setString(1, "" + Card.vehicl1.getv_id());
            sql.setString(2, Buyer.currentBuyer.getBuyer_ssn());
            sql.setString(3, "purchased a " + Card.vehicl1.getcompany() + " " + Card.vehicl1.getmodel_name() + " " + Card.vehicl1.getyom());
            sql.setString(4, "" + seller.currentSeller.getSeller_id());
            sql.executeUpdate();
            sql.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database");
            e.printStackTrace();

        }


        controler controler = new controler();
        controler.switchScrean(actionEvent, "main7ambola.fxml", 1080, 600);
    }

    static void clickAnimation(Button addInvoice) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(addInvoice);
        scaleTransition.setByX(-0.05);
        scaleTransition.setByY(-0.05);
        scaleTransition.setCycleCount(2);
        scaleTransition.setDuration(javafx.util.Duration.seconds(0.15));
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    public void hoverIn(){
        addInvoice.setStyle("-fx-background-color: #153243; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }

    public void hoverOut(){
        addInvoice.setStyle("-fx-background-color: #284B63; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
}
