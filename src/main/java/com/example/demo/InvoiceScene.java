package com.example.demo;

import back.invoice;
import back.vehicle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.io.FileWriter;

class PotThread extends Thread {
    private ObservableList<invoice> list;

    @Override
    public void run() {
        try {
            String host = "jdbc:mysql://localhost:3306/carshop";
            String username = "root";
            String password = "password";
            Connection conn = DriverManager.getConnection(host, username, password);

            String sql = "SELECT invoice.buyer_ssn, buyer.first_name, buyer.last_name, invoice.order_id, seller.seller_id, invoice.price " +
                    "FROM vehicle " +
                    "INNER JOIN invoice ON vehicle.v_id = invoice.v_id " +
                    "INNER JOIN buyer ON invoice.buyer_ssn = buyer.buyer_ssn " +
                    "INNER JOIN model ON vehicle.model_id = model.model_id " +
                    "INNER JOIN seller ON invoice.seller_id = seller.seller_id;";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<invoice> pot_buyers = new ArrayList<>();
            while (resultSet.next()) {
                int buyer_ssn = resultSet.getInt("buyer_ssn");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int order_id = resultSet.getInt("order_id");
                int seller_id = resultSet.getInt("seller_id");
                int price = resultSet.getInt("price");
                String name = first_name + " " + last_name;
                pot_buyers.add(new invoice(order_id, buyer_ssn, name, seller_id, price));
            }
            resultSet.close();
            statement.close();
            conn.close();

            // Set the result
            list = FXCollections.observableArrayList(pot_buyers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<invoice> getList() {
        return list;
    }
}

class ComThread extends Thread {
    private ObservableList<invoice> list;


    @Override
    public void run() {
        try{
            String host = "jdbc:mysql://localhost:3306/carshop";
            String username = "root";
            String password = "password";
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            String sql = "Select * from Full_purchase";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int i = 1;
            ArrayList<back.invoice> Full_buyers = new ArrayList<>();
            while(resultSet.next())
            {
                int buyer_ssn = resultSet.getInt("buyer_ssn");
                String name = resultSet.getString("name");
                int order_id = resultSet.getInt("order_id");
                int seller_id = resultSet.getInt("seller_id");
                int price = resultSet.getInt("price");
                Full_buyers.add(new back.invoice(order_id,buyer_ssn,name,seller_id,price));
            }
            resultSet.close();
            statement.close();
            conn.close();

            list = FXCollections.observableArrayList(Full_buyers);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ObservableList<invoice> getList() {
        return list;
    }
}

public class InvoiceScene
{
    String host = "jdbc:mysql://localhost:3306/carshop";
    String username = "root";
    String password = "password";

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
    private TableColumn pot_pay;
    @FXML
    private TableColumn pot_SID;
    //Complete Order Table
    @FXML
    private  TableView com_buyer_table;
    @FXML
    private TableColumn com_OID;
    @FXML
    private TableColumn com_buy_ssn;
    @FXML
    private TableColumn com_buy_name;
    @FXML
    private TableColumn com_pay;
    @FXML
    private TableColumn com_SID;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    public void initialize()
    {
        pot_OID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        pot_buy_ssn.setCellValueFactory(new PropertyValueFactory<>("buyer_ssn"));
        pot_buy_name.setCellValueFactory(new PropertyValueFactory<>("buyer_name"));

        pot_SID.setCellValueFactory(new PropertyValueFactory<>("seller_id"));
        pot_pay.setCellValueFactory(new PropertyValueFactory<>("price"));

        com_OID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        com_buy_ssn.setCellValueFactory(new PropertyValueFactory<>("buyer_ssn"));
        com_buy_name.setCellValueFactory(new PropertyValueFactory<>("buyer_name"));

        com_SID.setCellValueFactory(new PropertyValueFactory<>("seller_id"));
        com_pay.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public InvoiceScene() throws SQLException
    {

    }

    public void FillData() {
        PotThread thread = new PotThread();
        thread.start();

        // Use a separate thread to wait for the data and update the UI
        new Thread(() -> {
            try {
                thread.join(); // Wait for the thread to finish
                ObservableList<invoice> list = thread.getList();

                // Update the UI on the JavaFX Application Thread
                Platform.runLater(() -> pot_buyer_table.setItems(list));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        ComThread thread1 = new ComThread();
        thread1.start();

        new Thread(() -> {
            try {
                thread1.join(); // Wait for the thread to finish
                ObservableList<invoice> list = thread1.getList();

                // Update the UI on the JavaFX Application Thread
                Platform.runLater(() -> com_buyer_table.setItems(list));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public ArrayList<invoice> storeDataFP() throws SQLException{
        try{
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            String sql = "Select * from Full_purchase";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int i = 1;
            ArrayList<back.invoice> Full_buyers = new ArrayList<>();
            while(resultSet.next())
            {
                int buyer_ssn = resultSet.getInt("buyer_ssn");
                String name = resultSet.getString("name");
                int order_id = resultSet.getInt("order_id");
                int seller_id = resultSet.getInt("seller_id");
                int price = resultSet.getInt("price");
                Full_buyers.add(new back.invoice(order_id,buyer_ssn,name,seller_id,price));
            }
            resultSet.close();
            statement.close();
            conn.close();
            return Full_buyers;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void complete_purchase() throws SQLException
    {
        invoice com_buyer = (invoice) pot_buyer_table.getSelectionModel().getSelectedItem();


        if (com_buyer != null)
        {
            // Add selected item to the second TableView
            com_buyer_table.getItems().add(com_buyer);
            Connection connv = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            String sqlv = "SELECT type, company.name, model_name, yom, hp, nm, vehicle.v_id FROM company join (model join (vehicle join invoice on vehicle.v_id = invoice.v_id) on model.model_id = vehicle.model_id) on company.maker_id = model.maker_id WHERE order_id = "+com_buyer.getOrder_id()+";";
            Statement statement = connv.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlv);
            int i = 1;
            ArrayList<vehicle> vehicles = new ArrayList<>();
            resultSet.next();

            String type = resultSet.getString("type");
            String company = resultSet.getString("name");
            String model_name = resultSet.getString("model_name");
            String yom = resultSet.getString("yom");
            int hp = resultSet.getInt("hp");
            int nm = resultSet.getInt("nm");
            int v_id = resultSet.getInt("v_id");

            String Path = "/Users/mohamedsaba/IdeaProjects/demo/src/main/resources/Invoices/"+com_buyer.getOrder_id()+".txt";
            try{
                File file = new File(Path);


                if (file.createNewFile())
                {
                    System.out.println("File created: " + file.getName());
                }

                FileWriter writer = new FileWriter(file);
                String invoice = """
                    ---------------------------------------------------------
                                          Vehicle Purchase Invoice
                    ---------------------------------------------------------
                    Seller:       Best Auto Dealership
                                  123 Auto Lane
                                  Auto City, Auto State, 45678
                                  Phone: (123) 456-7890
            
                    Buyer:        """+"  "+com_buyer.getBuyer_name()+"\n"+
                        """
                        456 Buyer Street
                        Buyer Town, Buyer State, 12345
                        Phone: (987) 654-3210
  
          ---------------------------------------------------------
          Vehicle Details:
          ---------------------------------------------------------
          Type:           """+"  "+type+"\n"+"""
                    Make & Model:  """+"  "+company +"  "+model_name +"  "+yom+"\n"+"""
                    V_ID:           """+"  "+v_id+"\n"+"""
                    HP:              """+"  "+hp+"\n"+"""
                    NM:              """+"  "+nm+"\n"+"""
                    
                    ---------------------------------------------------------
                    
                    Total Amount Due:           $"""+com_buyer.getPrice()+"\n"+"""
            
                    ---------------------------------------------------------
                    Payment Method: Cash
                    ---------------------------------------------------------
            
                    Signature:
                    Seller: _______________________     Date: ______________
                    Buyer:  _______________________     Date: ______________
            
                    ---------------------------------------------------------
                    Thank you for your purchase!
                    ---------------------------------------------------------
                    """;
                writer.write(invoice);
                writer.close();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            // Connect to the database
            try (Connection conn = DriverManager.getConnection(host, username, password)) {
                System.out.println("Connected to MySQL database");

                // Start transaction
                conn.setAutoCommit(false);

                // Insert into Full_purchase table
                String insertSQL = "INSERT INTO Full_purchase(order_id, buyer_ssn, name, seller_id, price) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                    insertStmt.setInt(1, com_buyer.getOrder_id());
                    insertStmt.setInt(2, com_buyer.getBuyer_ssn());
                    insertStmt.setString(3, com_buyer.getBuyer_name());
                    insertStmt.setInt(4, com_buyer.getSeller_id());
                    insertStmt.setInt(5, com_buyer.getPrice());
                    insertStmt.executeUpdate();

                }


                String deleteInvoiceSQL = "DELETE FROM invoice WHERE  order_id = " + com_buyer.getOrder_id()+ ";";
                try (PreparedStatement deleteInvoiceStmt = conn.prepareStatement(deleteInvoiceSQL)) {
//                    deleteInvoiceStmt.setInt(1, com_buyer.getOrder_id());
                    deleteInvoiceStmt.executeUpdate();
                }
                String deleteVehicleSQL = "DELETE FROM vehicle WHERE v_id = (SELECT v_id FROM invoice WHERE order_id = ?);";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteVehicleSQL)) {
                    pstmt.setInt(1, com_buyer.getOrder_id()); // Replace orderId with your specific value
                    int rowsAffected = pstmt.executeUpdate();
                    System.out.println("Rows deleted from vehicle table: " + rowsAffected);
                } catch (SQLException e) {
                    e.printStackTrace();
                }



                // Commit transaction
                conn.commit();

                //Remove the item from the first TableView
                pot_buyer_table.getItems().remove(com_buyer);
            }
            catch (SQLException ex)
            {
                System.err.println("SQL Error: " + ex.getMessage());
                throw ex;
            }
        }
    }



    public void removal() throws SQLException
    {
        invoice com_buyer = (invoice) pot_buyer_table.getSelectionModel().getSelectedItem();

        try (Connection conn = DriverManager.getConnection(host, username, password))
        {
            System.out.println("Connected to MySQL database");
            conn.setAutoCommit(false);
            String deleteInvoiceSQL = "DELETE FROM invoice WHERE  order_id = " + com_buyer.getOrder_id() + ";";
            try (PreparedStatement deleteInvoiceStmt = conn.prepareStatement(deleteInvoiceSQL)) {
//                    deleteInvoiceStmt.setInt(1, com_buyer.getOrder_id());
                deleteInvoiceStmt.executeUpdate();
            } catch (SQLException ex) {
                System.err.println("SQL Error: " + ex.getMessage());
                throw ex;
            }
            conn.commit();

        }
        FillData();
    }


    public void switchCar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listings.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }

    public void switchMotor(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listings.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }

    public void applydiscount(ActionEvent event) throws IOException, SQLException {
        invoice dis_buyer = (invoice) pot_buyer_table.getSelectionModel().getSelectedItem();

        double discount = 0;
        if ("5%".equals(choiceBox.getValue())) {
            discount = 0.05;
        } else if ("10%".equals(choiceBox.getValue())) {
            discount = 0.10;
        }

        Connection conn = DriverManager.getConnection(host,username,password);
        System.out.println("Connected to MySQL database");
        String sql = "UPDATE invoice SET price = price * (1 - ?) WHERE order_id = ?;";
        try (PreparedStatement applydiscount = conn.prepareStatement(sql)) {
//                    deleteInvoiceStmt.setInt(1, com_buyer.getOrder_id());
            applydiscount.setDouble(1, discount); // First placeholder is for the discount rate
            applydiscount.setInt(2, dis_buyer.getOrder_id());
            applydiscount.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            throw ex;
        }
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
