
public class SavingsAccount extends BankAccount {

  private double annualInterestRate = 0;

  public SavingsAccount() {
	  annualInterestRate = 0;
  }

  public SavingsAccount(Customer cust, double balance) {
	  super(cust, balance);
  }

  public SavingsAccount(double initialBalance, double initialInterest) {
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
