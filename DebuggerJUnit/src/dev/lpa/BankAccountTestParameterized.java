package dev.lpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class BankAccountTestParameterized {

    private BankAccount account;
    private double amount;
    private boolean branch;
    private double expected;

    //Constructor for parametrized tests


    public BankAccountTestParameterized(double amount, boolean branch, double expected) {
        this.amount = amount;
        this.branch = branch;
        this.expected = expected;
    }

    @Before
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.CHECKING);
        System.out.println("Running a test");
    }

    //A Parametrized Test takes Parametrized.Parameters, which are values we want to test,
    //followed by their expected value
    //It creates instances of this class and test against each set of test data we declared.
    //We use the class constructor to set instance variables specified
    //We only need to ad instance variables for its fields that are present in the constructor
    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][]{
                {100.00, true, 1100.00},
                {200.00, true, 1200.00},
                {325.14, true, 1325.14},
                {489.33, true, 1489.33},
                {1000.00, true, 2000.00}
        });
    }

    @Test
    public void getBalance_deposit() {
        account.deposit(amount, branch);
        assertEquals(expected, account.getBalance(), 0.01);
    }
}



