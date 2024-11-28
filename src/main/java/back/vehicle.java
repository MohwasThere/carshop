package back;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public interface vehicle
{
    String company = "";
    String model_name = "";
    String yom = "";
    String transmission = "";
    int hp = 0;
    int nm = 0;
    int price = 0;

    String getcompany();
    String getmodel_name();
    String getyom();
    String gettransmission();
    int gethp();
    int getnm();
    int getprice();
}

