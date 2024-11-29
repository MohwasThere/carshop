package back;

public class model
{
    private int maker_id;
    private String model_name;
    private int hp;
    private int nm;
    private String transmission;

    public int getMaker_id() {
        return maker_id;
    }

    public String getModel_name() {
        return model_name;
    }
    public int getHp() {
        return hp;
    }
    public int getNm() {
        return nm;
    }
    public String getTransmission() {
        return transmission;
    }

    public model(int maker_id, String model_name, int hp, int nm, String transmission)
    {
        this.maker_id = maker_id;
        this.model_name = model_name;
        this.hp = hp;
        this.nm = nm;
        this.transmission = transmission;
    }
}
