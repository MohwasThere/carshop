package com.example.demo;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label CarShop;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    public HelloController() throws SQLException {
    }

    public void initialize() {
        // Populate the ChoiceBox when the controller initializes
        System.out.println("initialize method called!");

    }

    String host = "jdbc:mysql://localhost:3306/carshop";
    String username = "root";
    String password = "password";
    Connection conn = DriverManager.getConnection(host, username, password);

    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private Rectangle rect;
    @FXML
    private Button Login;

    public boolean checkuser() throws SQLException {
        Connection connection = DriverManager.getConnection(host, username, password);

        // 2. Prepare the SQL query
        String sql = "SELECT COUNT(*) FROM seller WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getText());

        // 3. Execute the query
        ResultSet resultSet = statement.executeQuery();

        // 4. Process the result
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            connection.close();
            if (count == 0){
                user.setStyle("-fx-background-color: #eee; -fx-border-style: solid; -fx-border-color: red; -fx-border-width: 2px; -fx-padding: 10px 0 10px 15px; -fx-border-radius: 10; -fx-min-width: 200px; -fx-background-radius: 10px;");
                pass.setStyle("-fx-background-color: #eee; -fx-border-style: solid; -fx-border-color: red; -fx-border-width: 2px; -fx-padding: 10px 0 10px 15px; -fx-border-radius: 10; -fx-min-width: 200px; -fx-background-radius: 10px;");
            }
            return count > 0;
        }
        resultSet.close();
        statement.close();
        connection.close();

        return false;
    }

    public boolean checkpass() throws SQLException {
        Connection connection = DriverManager.getConnection(host, username, password);

        // 2. Prepare the SQL query
        String sql = "SELECT COUNT(*) FROM seller WHERE password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, pass.getText());

        // 3. Execute the query
        ResultSet resultSet = statement.executeQuery();

        // 4. Process the result
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            connection.close();
            if (count == 0){
                user.setStyle("-fx-background-color: #eee; -fx-border-style: solid; -fx-border-color: red; -fx-border-width: 2px; -fx-padding: 10px 0 10px 15px; -fx-border-radius: 10; -fx-min-width: 200px; -fx-background-radius: 10px;");
                pass.setStyle("-fx-background-color: #eee; -fx-border-style: solid; -fx-border-color: red; -fx-border-width: 2px; -fx-padding: 10px 0 10px 15px; -fx-border-radius: 10; -fx-min-width: 200px; -fx-background-radius: 10px;");
            }
            return count > 0;
        }
        resultSet.close();
        statement.close();
        connection.close();

        return false;
    }


    public void handleLogin(javafx.event.ActionEvent event) throws IOException, SQLException {
        // Load the second scene (Scene2.fxml)
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(Login);
        scaleTransition.setByX(-0.05);
        scaleTransition.setByY(-0.05);
        scaleTransition.setCycleCount(2);
        scaleTransition.setDuration(javafx.util.Duration.seconds(0.15));
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
        if(checkuser() && checkpass()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main7ambola.fxml"));

            // Get the current stage (window)
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene and show it
            Scene MainMenu = new Scene(loader.load(), 1080, 600);
            stage.setScene(MainMenu);
            stage.show();
        }
        else
            System.out.println("SAD SHIT");
    }

    public void hoverIn(){
        Login.setStyle("-fx-background-color: #153243; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
    public void hoverOut(){
        Login.setStyle("-fx-background-color: #284B63; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
}
