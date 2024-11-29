package com.example.demo;

import back.car;
import back.motorcycle;
import back.vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static java.beans.Beans.isInstanceOf;
import static javafx.scene.shape.StrokeType.OUTSIDE;

public class Listings {
    @FXML
    private VBox container;

    @FXML
    private AnchorPane layout;

    @FXML
    public void initialize(){
        ArrayList<vehicle> vehicles = storeData();
        displayData(vehicles);
    }

    @FXML
    private Slider maxprice;

    @FXML
    private Slider minnm;

    @FXML
    private Slider minhp;

    @FXML
    private TextField com;

    private void displayData(ArrayList<vehicle> vehicles){
        container.getChildren().clear();
        double noi = 1;
        for (vehicle vehicl : vehicles) {
            String model = vehicl.getmodel_name() + " " + vehicl.getyom() + " " + vehicl.gettransmission();
            Card card = new Card(noi);
            container.getChildren().add(card.create("Images/porsche.jpg", vehicl.getcompany(), model, vehicl.gethp(), vehicl.getnm(), vehicl.getprice()));
            noi++;
        }
    }

    private ArrayList<vehicle> storeData(){
        String host = "jdbc:mysql://localhost:3306/carshop";
        String username = "root";
        String password = "password";
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            String sql = "SELECT type, companies.name, model_name, yom, transmission, hp, nm, price FROM companies join (model join vehicle on model.model_id = vehicle.model_id) on companies.maker_id = model.maker_id";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int i = 1;
            ArrayList<vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String company = resultSet.getString("name");
                String model_name = resultSet.getString("model_name");
                String yom = resultSet.getString("yom");
                String transmission = resultSet.getString("transmission");
                int hp = resultSet.getInt("hp");
                int nm = resultSet.getInt("nm");
                int price = resultSet.getInt("price");
                if(type.equals("Car")){
                    vehicles.add(new car(company, model_name, yom, transmission, hp, nm, price));
                } else if (type.equals("Motorcycle")){
                    vehicles.add(new motorcycle(company, model_name, yom, transmission, hp, nm, price));
                }
            }
            resultSet.close();
            statement.close();
            conn.close();
            return vehicles;
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database");
            e.printStackTrace();
            return null;
        }
    }

    public void handlecar() {
        ArrayList<vehicle> vehicles = storeData();
        ArrayList<vehicle> cars = new ArrayList<>();
        for (vehicle vehicl : vehicles) {
            if(vehicl instanceof car){
                cars.add(vehicl);
            }
        }
        displayData(cars);
    }

    public void handlemotorcycle() {
        ArrayList<vehicle> vehicles = storeData();
        ArrayList<vehicle> motorcycles = new ArrayList<>();
        for (vehicle vehicl : vehicles) {
            if(vehicl instanceof motorcycle){
                motorcycles.add(vehicl);
            }
        }
        displayData(motorcycles);
    }

    public void handlefilters() {
        ArrayList<vehicle> vehicles = storeData();
        ArrayList<vehicle> filtered = new ArrayList<>();
        for (vehicle vehicl : vehicles) {
            if(vehicl.getprice() <= maxprice.getValue()){
                if(vehicl.getnm() >= minnm.getValue()){
                    if(vehicl.gethp() >= minhp.getValue()){
                        if(com.getText().isEmpty() || vehicl.getcompany().toLowerCase().equals(com.getText().toLowerCase())){
                            filtered.add(vehicl);
                        }
                    }
                }
            }
        }
        displayData(filtered);
    }


    public void switchInvoice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main7ambola.fxml"));

        // Get the current stage (window)
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Set the new scene and show it
        Scene MainMenu = new Scene(loader.load(), 1080, 600);
        stage.setScene(MainMenu);
        stage.show();
    }
}

class Card{
    private HBox card = new HBox();
    private ImageView image = new ImageView();
    private Text company;
    private Text model;
    private Text hp;
    private Text nm;
    private Text price;
    private VBox cardVBox = new VBox();
    private double noi;

    public Card(double noi){
        this.noi = noi;
    }


    public HBox create(String img, String company, String model, int hp, int nm, int price){
        image.setImage(new Image(img));
        image.setFitHeight(79);
        image.setFitWidth(98);
        image.setPickOnBounds(true);
        image.setPreserveRatio(true);
        cardVBox.setPrefHeight(98);
        cardVBox.setPrefWidth(404);
        cardVBox.setStyle("-fx-padding: 15");
        this.company = new Text(company);
        this.company.fontProperty().set(new Font("Arial Bold", 15));
        this.company.setStrokeType(OUTSIDE);
        this.company.setStrokeWidth(0);
        this.company.setWrappingWidth(369);
        this.model = new Text(model);
        this.model.setStrokeType(OUTSIDE);
        this.model.setStrokeWidth(0);
        this.model.setWrappingWidth(369);
        this.hp = new Text("HP: " + hp);
        this.hp.setStrokeType(OUTSIDE);
        this.hp.setStrokeWidth(0);
        this.hp.setWrappingWidth(369);
        this.nm = new Text("NM: " + nm);
        this.nm.setStrokeType(OUTSIDE);
        this.nm.setStrokeWidth(0);
        this.nm.setWrappingWidth(369);
        cardVBox.getChildren().addAll(this.company, this.model, this.hp, this.nm);
        this.price = new Text("" + price);
        this.card.setAlignment(Pos.CENTER_LEFT);
        this.card.effectProperty().set(new DropShadow(10, new Color(0.7058823704719543, 0.7215686440467834, 0.6705882549285889, 1)));
        this.card.setPrefHeight(100);
        this.card.setPrefWidth(600);
        this.card.setStyle("-fx-border-color: #B4B8AB; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-background-color: white; -fx-padding: 0 20 0 20;");
        this.card.getChildren().addAll(image, cardVBox, this.price);
        return this.card;
    }

}
