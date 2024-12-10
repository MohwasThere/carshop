package back;

public class seller {
    private int seller_id;
    private String first_name;
    private String last_name;
    private int salary;
    public static seller currentSeller;

    public int getSeller_id() {
        return seller_id;
    }
    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getSalary() {
        return this.salary;
    }

    public seller(int seller_id, String first_name, String last_name, int salary) {
        this.seller_id = seller_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
    }

        }
