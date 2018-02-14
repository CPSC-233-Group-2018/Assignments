/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 4, Feb.13,2018-
 *  	A SavingsAccount class that is a subclass of BankAccount that creates
 *    and calculates interest based on the annual interest rate specified.
 */

public class SavingsAccount extends BankAccount {
  //Instance variable for annualInterestRate
  private double annualInterestRate = 0;

  /**
    * Default constructor that takes no arguments
    */
  SavingsAccount() {
	  annualInterestRate = 0;
  }

  /**
    * Constructor that takes in a Customer object and a double and calls the superclass with the same parameters
    * @param cust Customer object
    * @param balance initial amount of balance
    */
  SavingsAccount(Customer cust, double balance) {
	  super(cust, balance);
  }

  /**
    * Constructor that takes in two doubles and calls the superclass 
    */
  SavingsAccount(double initialBalance, double initialInterest) {
	  super(initialBalance);
    if (initialInterest > 0) {
      annualInterestRate = initialInterest;
    }
  }

  public void setAnnualInterestRate(double input) {
    if (input > 0) {
      annualInterestRate = input;
    }
    else {
      System.out.println("You can't have a negative interest rate!");
    }
  }

  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  public void depositMonthlyInterest() {
    if (getBalance() > 0) {
      double monthInterest = annualInterestRate/100*getBalance()/12;
      deposit(monthInterest);
    }
  }

}
