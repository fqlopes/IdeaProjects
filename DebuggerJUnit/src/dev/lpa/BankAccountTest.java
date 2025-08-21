package dev.lpa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount account;
    private static int count;

    @Before
    public void beforeClass() {
        System.out.println("This executes before any test cases. Count = " + count++);
    }

    @Before
    public void setup(){
        account = new BankAccount("Tim", "Buchalka", 1000, BankAccount.CHECKING);
        System.out.println("Running this @Before anything else");

    }

    @Test
    public void deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200.00, balance, 0);
    }

    @Test
    public void withdraw_branch() {
        double balance = account.withdraw(600.00, true);
        assertEquals(400.00, balance, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch(){
        double balance = account.withdraw(600.00, false);
        assertEquals(400.00, balance, 0);
    }

    @Test
    public void getBalance_deposit() {
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @Test
    public void getBalance_withdraw() {

        account.withdraw(200.00, true);
        assertEquals(800.00, account.getBalance(), 0);
    }

    @Test
    public void isChecking_true (){

        assertEquals(true, account.isChecking());
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("This executes fater any test cases. Count = " + count++);
    }

    @After
    public void teardown(){
        System.out.println("Count = " +count++);
    }
    //Making a dummy method for testing. Needs to be anotated
//    @org.junit.Test
//    public void dummyTest(){
//        assertEquals(20,21);
//    }

}