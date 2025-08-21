public class BankAccount { //this is a blueprint to what a Bank account has

    //Declaring thing a bank account might have: an account number, it's balance, customer name, it's e-mail, etc
    private int accountNumber;
    private double accountBalance;
    private String accountName;
    private String accountEmail;

    //Now, we code tools to get and set these variables. Namely, Getters and Setters, and they are public

    //GETTERS
    //OBS:As these methods are the only way the user can see data, we must return the exact type of data
    //that the method requires

    public int getAccountNumber(){
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    //SETTERS

    //OBS: They are void, meaning they don't return anything, but instead take a variable of the
    // same type and make changes on it.These changes are never shown to the user,
    // That's why they remain can remain void.

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }
//
//    public double removeBalance (double money){
//
//
//        if (this.accountBalance - money >= 0){
//           this.accountBalance -= money;
//        } else {
//            System.out.print("You tried to withdraw $"+ money + " but ");
//            System.out.print(" you don't have enough money! This transaction wasn't made, moving on...");
//        }
//            return this.accountBalance;
//

//    }
//    public double addBalance (double money){
//        return this.accountBalance += money;
//    }

    public void printAccount() {
        System.out.println("Account Information:");
        System.out.println("Owner: " + accountName);
        System.out.println("ID: " + accountNumber);
        System.out.println("E-mail: " + accountEmail);
        System.out.println("Balance : $" + accountBalance);
    }

    public BankAccount() {
        //we can call other constructors by chaining. we use this() + the necessary arguments
        this(0, 0, "Default", "Default");
        System.out.println("Empty constructor created,but who called us?");
    }

    public BankAccount(int accountNumber, double accountBalance, String accountName, String accountEmail) {
        System.out.println("Arguments called with no declaration on main");
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountName = accountName;
        this.accountEmail = accountEmail;


    }
}
