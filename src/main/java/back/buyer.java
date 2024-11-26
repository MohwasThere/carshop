package back;

public class buyer
{
    private int buyer_ssn;
    private String first_name;
    private String last_name;

    public int getBuyer_ssn()
    {
        return this.buyer_ssn;
    }

    public String getName()
    {
        return (this.first_name + " " + this.last_name);
    }

    buyer(int ssn, String first_name, String last_name)
    {
        this.buyer_ssn = ssn;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
