package back;

public class Buyer {
    private String buyer_ssn;
    private String first_name;
    private String last_name;
    public static Buyer currentBuyer;

    public Buyer( String buyer_ssn, String first_name, String last_name) {
        this.buyer_ssn = buyer_ssn;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public String getBuyer_ssn() {
        return buyer_ssn;
    }

    public void setBuyer_ssn(String buyer_ssn) {
        this.buyer_ssn = buyer_ssn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public static boolean checkInput(String ssn, String first_name, String last_name) {
        return ssn.length() == 9 && first_name.length() >= 3 && last_name.length() >= 3;
    }
}
