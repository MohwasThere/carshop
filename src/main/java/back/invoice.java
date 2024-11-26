package back;

public class invoice {
    private int v_id;
    private int buyer_ssn;
    private String desc;
    private int seller_id;

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

    invoice(int v_id, int buyer_ssn, String desc, int seller_id) {
        this.v_id = v_id;
        this.buyer_ssn = buyer_ssn;
        this.desc = desc;
        this.seller_id = seller_id;
    }
}
