package com.example.demo;

import back.invoice;

import back.vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class InvoiceScene
{
    String host = "jdbc:mysql://localhost:3306/carshop";
    String username = "root";
    String password = "password";
    Connection conn = DriverManager.getConnection(host, username, password);

    @FXML
    private Text UserID;
    @FXML
    private TextField searchFeild;
    @FXML
    private Button searchButton;
    @FXML
    private Button purchase;
    @FXML
    private Button removal;
    //Potential Buyer table
    @FXML
    private TableView pot_buyer_table;
    @FXML
    private TableColumn pot_OID;
    @FXML
    private TableColumn pot_buy_ssn;
    @FXML
    private TableColumn pot_buy_name;
    @FXML
    private TableColumn pot_MID;
    @FXML
    private TableColumn pot_pay;
    @FXML
    private TableColumn pot_SID;
    //Complete Order Table
    @FXML
    private  TableView com_buyer;
    @FXML
    private TableColumn com_OID;
    @FXML
    private TableColumn com_buy_ssn;
    @FXML
    private TableColumn com_buy_name;
    @FXML
    private TableColumn com_MID;
    @FXML
    private TableColumn com_pay;
    @FXML
    private TableColumn com_SID;

    public InvoiceScene() throws SQLException
    {

    }

    public ArrayList<invoice> storeData() throws SQLException {
           try {
               Connection conn = DriverManager.getConnection(host, username, password);
               System.out.println("Connected to MySQL database");
               String sql = "SELECT vehicle.v_id, invoice.buyer_ssn, buyer.first_name, buyer.last_name, invoice.order_id, seller.seller_id, vehicle.price FROM vehicle INNER JOIN invoice ON vehicle.v_id = invoice.v_id INNER JOIN buyer ON invoice.buyer_ssn = buyer.buyer_ssn INNER JOIN model ON vehicle.model_id = model.model_id INNER JOIN seller ON invoice.seller_id = seller.seller_id;";
               Statement statement = conn.createStatement();
               ResultSet resultSet = statement.executeQuery(sql);
               int i = 1;
               ArrayList<invoice> pot_buyers = new ArrayList<>();
               while(resultSet.next())
               {
                   int v_id = resultSet.getInt("v_id");
                   int buyer_ssn = resultSet.getInt("buyer_ssn");
                   String first_name = resultSet.getString("first_name");
                   String last_name = resultSet.getString("last_name");
                   int order_id = resultSet.getInt("order_id");
                   int seller_id = resultSet.getInt("seller_id");
                   int price = resultSet.getInt("price");
                   String name = first_name + " " + last_name;
                   pot_buyers.add(new invoice(order_id,buyer_ssn,name,v_id,seller_id,price));
               }
               resultSet.close();
               statement.close();
               conn.close();
               return pot_buyers;
           }
           catch (SQLException e)
           {
               e.printStackTrace();
               return null;
           }
    }

    public void FillData() throws SQLException {
        ArrayList<invoice> buyers = storeData();
        ObservableList<invoice> list = FXCollections.observableArrayList(buyers);
        pot_OID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        pot_buy_ssn.setCellValueFactory(new PropertyValueFactory<>("buyer_ssn"));
        pot_buy_name.setCellValueFactory(new PropertyValueFactory<>("buyer_name"));
        pot_MID.setCellValueFactory(new PropertyValueFactory<>("v_id"));
        pot_SID.setCellValueFactory(new PropertyValueFactory<>("seller_id"));
        pot_pay.setCellValueFactory(new PropertyValueFactory<>("price"));

        pot_buyer_table.setItems(list);

    }

    public void complete_purchase() throws SQLException
    {

    }


    public void switchCar(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listings.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }

    public void switchMotor(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listings.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }


    public void hoverInp(){
        purchase.setStyle("-fx-background-color: #153243; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
    public void hoverOutp(){
        purchase.setStyle("-fx-background-color: #284B63; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
    public void hoverInr(){
        removal.setStyle("-fx-background-color: #153243; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }
    public void hoverOutr(){
        removal.setStyle("-fx-background-color: #284B63; -fx-border-color: #153243; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 1px;");
    }

}
