public class Customer {

    private String customerName;
    private double customerLimit;
    private String customerEmail;

    public String getCustormerName() {
        return customerName;
    }

    public double getCustomerLimit() {
        return customerLimit;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Customer (String customerName, String customerEmail, double customerLimit){

        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerLimit =customerLimit;
    }

    public Customer (){
        this("Default", "Not specified", 9999);
    }

    public Customer (String customerName, String customerEmail){
        this(customerName, customerEmail, 0);
    }
}
