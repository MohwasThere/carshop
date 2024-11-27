package com.example.demo;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.*;

import static javafx.scene.shape.StrokeType.OUTSIDE;

public class Listings {
    @FXML
    private TextField searchFeild;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane layout;

    @FXML
    public void initialize(){
        String host = "jdbc:mysql://localhost:3306/carshop";
        String username = "root";
        String password = "password";
        try {
            Connection conn = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to MySQL database");
            String sql = "SELECT companies.name, model_name, yom, transmission, hp, nm, price FROM companies join (model join vehicle on model.model_id = vehicle.model_id) on companies.maker_id = model.maker_id";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int i = 1;
            while (resultSet.next()) {
                String company = resultSet.getString("name");
                String model_name = resultSet.getString("model_name");
                String yom = resultSet.getString("yom");
                String transmission = resultSet.getString("transmission");
                int hp = resultSet.getInt("hp");
                int nm = resultSet.getInt("nm");
                int price = resultSet.getInt("price");
                String model = model_name + " " + yom + " " + transmission;
                Card card = new Card(i++);
                layout.getChildren().add(card.create("porsche.jpg", company, model, hp, nm, price));

            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to MySQL database");
            e.printStackTrace();
        }
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
        this.card.setLayoutX(167);
        this.card.setLayoutY(185.0 + ((noi-1) * 110));
        this.card.setPrefHeight(100);
        this.card.setPrefWidth(600);
        this.card.setStyle("-fx-border-color: #B4B8AB; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-background-color: white; -fx-padding: 0 20 0 20;");
        this.card.getChildren().addAll(image, cardVBox, this.price);
        return this.card;
    }

}
