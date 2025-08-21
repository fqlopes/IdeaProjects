public class salariedEmployee extends Employee {

    double annualSalary;
    boolean isRetired;

    public salariedEmployee(String name, String birthDate, String hireDate, double annualSalary) {
        super(name, birthDate, hireDate);
        this.annualSalary = annualSalary;
    }


    @Override
    public double collectPay(){

        double paycheck = annualSalary  / 26;
        double adjustedPay = (isRetired) ? 0.9 * paycheck : paycheck;

        return (int) adjustedPay;
    }

    public void retire(){

            terminate("12/02/2025");
            isRetired = true;

    }
}
