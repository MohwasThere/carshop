package back;

public class companies {
    private int maker_id;
    private String maker_name;
    private String coo;

    public int getMaker_id() {
        return this.maker_id;
    }

    public String getMaker_name() {
        return this.maker_name;
    }

    public String getCoo()
    {
        return this.coo;
    }

    companies(int maker_id, String maker_name, String coo) {
        this.maker_id = maker_id;
        this.maker_name = maker_name;
        this.coo = coo;
    }
}
