/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 5, Feb.19,2018-
 *  	A SavingsAccount class that is a subclass of BankAccount that creates
 *    and calculates interest based on the annual interest rate specified.
 */

public class SavingsAccount extends BankAccount {
  //Instance variable for annualInterestRate
  private double annualInterestRate = 0;

  /**
    * Default constructor that takes no arguments.
    */
  SavingsAccount() {
	  annualInterestRate = 0;
  }

  /**
    * Constructor that takes in a Customer object and a double and calls the superclass with the same parameters.
    * @param cust Customer object
    * @param balance initial amount of balance
    */
  SavingsAccount(Customer cust, double balance) {
	  super(cust, balance);
  }

  /**
    * Constructor that takes in two doubles and calls the superclass with one of the parameters.
    * @param initialBalance initial amount of balance
    * @param intitialInterest initial amount of interest
    */
  SavingsAccount(double initialBalance, double initialInterest) {
	  super(initialBalance);
    if (initialInterest > 0) {                //If the initialInterest is positive
      annualInterestRate = initialInterest;   //Set it to the annualInterestRate variable
    }
  }

  /**
    * setAnnualInterestRate() takes in a double and sets the annualInterestRate to the new input.
    * @param input new interest rate
    */
  public void setAnnualInterestRate(double input) {
    if (input > 0 && Double.isInfinite(input) == false) {                  //Check if the input is positive excluding infinity
      annualInterestRate = input;     //Set the annualInterestRate to the input
    }
    else {          //If input is not positive
      System.out.println("You can't have a negative interest rate!"); //Print error message
    }
  }

  /**
    * getAnnualInterestRate() returns the annualInterestRate.
    * @return annualInterestRate
    */
  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  /**
    * depositMonthlyInterest() calculates the monthly interest then deposits the amount into the account.
    */
  public void depositMonthlyInterest() {
    if (getBalance() > 0) {       //Checks if the balance is positive
      double monthInterest = annualInterestRate/100*getBalance()/12;
      deposit(monthInterest);     //Calls the deposit function with the monthInterest variable
    }
  }

}
