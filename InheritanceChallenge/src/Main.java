public class Main {
    public static void main(String[] args) {

        Employee tim = new Employee("Tim", "11/11/1992", "08/12/2004");

        System.out.println(tim);
        System.out.println("Age: " + tim.getAge());
        System.out.println("Pay: " + tim.collectPay());

        salariedEmployee joe = new salariedEmployee("Joe", "03/04/1954", "03/01/2000", 35000);
        System.out.println(joe);
        System.out.println("Joe's paycheck = $ "+ joe.collectPay());

        joe.retire();
         System.out.println("Joe's pension paycheck = $ " + joe.collectPay());


         HourlyEmployee mary = new HourlyEmployee("Mary", "05/05/1970", "03/03/2021", 15);
         System.out.println(mary);
         System.out.println("Mary's paycheck = $ " + mary.collectPay());
         System.out.println("Mary's Holiday Pay = $ " + mary.getDoublePay());


         System.out.print (joe.toString().formatted() + "\n");
    }
}
