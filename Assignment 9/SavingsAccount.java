/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 8, Mar.20,2018-
 *  	A SavingsAccount class that is a subclass of BankAccount that creates
 *    and calculates interest based on the annual interest rate specified. Now with
 *	  annualInterestRate as static.
 */

public class SavingsAccount extends BankAccount {
  //Static instance variable for annualInterestRate
  private static double annualInterestRate = 0;

  /**
   * Default constructor that takes no arguments.
   */
  SavingsAccount() {
  }

  /**
   * Constructor that takes in a Customer object and a double and calls the superclass with the same parameters.
   * @param cust Customer object
   * @param balance initial amount of balance
   */
  SavingsAccount(Customer cust, double balance) {
	  super(cust, balance);
  }

  //No longer require the old constructor that also initialized the non-static annual rate
  //------

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
   * @param input	new interest rate
   */
  public static void setAnnualInterestRate(double input) {
    if (input > 0 && Double.isInfinite(input) == false) {                  //Check if the input is positive excluding infinity
      annualInterestRate = input;     //Set the annualInterestRate to the input
    }
    else {          //If input is not positive
      System.out.println("You can't have a negative interest rate!"); //Print error message
    }
  }

  /**
   * getAnnualInterestRate() returns the annualInterestRate.
   * @return annualInterestRate	current annual interest rate
   */
  public static double getAnnualInterestRate() {
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

  /**
   * getMonthlyFeesAndInterest() checks if the balance is below 1000, then divide the interest rate by 12 and subtract 5.
   * Or return balance multiplied by interest rate divided by 1200.
   * This method overrides the abstract method in BankAccount.
   * @return feesAndInterest	the calculated fees and interest for the month
   */
  @Override
  protected double getMonthlyFeesAndInterest() {
    if (super.getBalance() < 1000) {
      return super.getBalance()*annualInterestRate/1200-5;
    }
    return super.getBalance()*annualInterestRate/1200;
  }
}
