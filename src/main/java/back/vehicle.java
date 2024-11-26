package back;

public class vehicle
{
    private int v_id;
    private int model_id;
    private int yom;
    private int price;
    private String type;

    public int getV_id(){
        return this.v_id;
    }
    public String getType(){return this.type;}
    public int getModel_id(){
        return this.model_id;
    }
    public int getYom(){
        return this.yom;
    }
    public int getPrice(){ return this.price; }


    vehicle(int v_id, int model_id, int yom, int price, String type) {
        this.v_id = v_id;
        this.model_id = model_id;
        this.yom = yom;
        this.price = price;
        this.type = type;
    }


}

