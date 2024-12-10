package com.example.demo;

import back.Buyer;
import back.seller;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class OldCust {
    @FXML
    public Button addInvoice;
    @FXML
    private TextField SSN;


    public void onClick(ActionEvent actionEvent) {
        clickAnimation(addInvoice);
        String host = "jdbc:mysql://localhost:3306/carshop";
        String username = "root";
        String password = "password";
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("SELECT * FROM buyer WHERE buyer_ssn = " + SSN.getText());

            if (rs.next()) {
                Buyer.currentBuyer = new Buyer(rs.getString("buyer_ssn"), rs.getString("first_name"), rs.getString("last_name"));
            } else {
                SSN.setStyle("-fx-border-color: red");
            }
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
