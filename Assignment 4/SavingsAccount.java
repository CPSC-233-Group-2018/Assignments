
public class SavingsAccount extends BankAccount {

  public double annualInterestRate;

  public SavingsAccount() {
	  annualInterestRate = 0;
  }

  public SavingsAccount(Customer cust, double balance) {
	  super(cust, balance);
  }

  public SavingsAccount(double initialBalance, double initialInterest) {
	  super(initialBalance);
	  annualInterestRate = initialInterest;
  }

  public void setBalance(double input) {
	  if (input > 0) {
		  super.balance = input;
	  }
	  else {
		  System.out.println("You can't have a negative balance!");
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

  public double getInterestRate() { //not sure what the difference is between this and the method below. both are used in the test though.
	  return annualInterestRate;
  }

  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  public double calculateMonthlyInterest() { //not done, calculates monthly interest from annual
	  return (annualInterestRate/12);
  }

  public void depositMonthlyInterest() { //also not done
    balance += annualInterestRate;
  }

}
