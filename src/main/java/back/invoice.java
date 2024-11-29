package back;

public class invoice {
    private int v_id;
    private int buyer_ssn;
    private String desc;
    private int seller_id;
    private String buyer_name;
    private int order_id;
    private int price;

    public int getV_id()
    {
        return this.v_id;
    }

    public int getBuyer_ssn()
    {
        return this.buyer_ssn;
    }

    public String getDesc()
    {
        return this.desc;
    }

    public int getSeller_id()
    {
        return this.seller_id;
    }

    public String getBuyer_name()
    {   return this.buyer_name;   }

    public int getPrice()
    {   return this.price;  }

    public int getOrder_id()
    {
        return this.order_id;
    }

    public invoice(int order_id,int buyer_ssn,String buyer_name,int v_id,int seller_id,int price) {
        this.order_id = order_id;
        this.buyer_ssn = buyer_ssn;
        this.buyer_name = buyer_name;
        this.v_id = v_id;
        this.seller_id = seller_id;
        this.price = price;

    }
}
