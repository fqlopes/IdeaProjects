public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();
//            BankAccount bob = new BankAccount(123,500,"FUCK YOU", "YOU DONT NEED TO KNOW");
//        Declaring a new object and making an instance of it.
//
//        BankAccount test = new BankAccount();
//
//        test.setAccountName("Felipe");
//        test.setAccountNumber(1);
//        test.setAccountEmail("felipeqlopes@gmail.com");
//        test.setAccountBalance(50);
//
//        test.addBalance(50);
//        System.out.println("\nNew balance is: " + test.getAccountBalance());
//        test.removeBalance(200);
//        System.out.println("New balance is: " + test.getAccountBalance());
//        test.printAccount();
//
        BankAccount Bob = new BankAccount ( 123, 500, "Bruh", "NULL");
        System.out.println(Bob.getAccountBalance());

    }
}
