package back;

public class motorcycle implements vehicle{
    String company;
    String model_name;
    String yom;
    String transmission;
    int hp ;
    int nm ;
    int price ;
    public motorcycle(String company, String model_name, String yom, String transmission, int hp, int nm, int price) {
        this.company = company;
        this.model_name = model_name;
        this.yom = yom;
        this.transmission = transmission;
        this.hp = hp;
        this.nm = nm;
        this.price = price;
    }

    @Override
    public int gethp() {
        return this.hp;
    }

    @Override
    public int getnm() {
        return this.nm;
    }

    @Override
    public int getprice() {
        return this.price;
    }

    @Override
    public String getcompany() {
        return this.company;
    }

    @Override
    public String getmodel_name() {
        return model_name;
    }

    @Override
    public String gettransmission() {
        return this.transmission;
    }

    @Override
    public String getyom() {
        return this.yom.substring(0, 4);
    }
}
