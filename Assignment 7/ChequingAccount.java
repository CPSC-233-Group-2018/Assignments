/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 7, Mar.12,2018-
 *  	A BankAccount class that allows accounts to be created and withdraws and deposits
 *  	to be made. Also includes an overdraft feature.
 */

 public class ChequingAccount extends BankAccount {
  private double overdraftFee;
  private double overdraftAmount = 100;

  /**
    * Constructor that takes in a double for the overfraft fee.
    * @param transactionFee overdraft fee
    */
  ChequingAccount(double transactionFee) {
    overdraftFee = transactionFee;
  }

  /**
    * Constructor that takes in a customer, intial balance and overdraft fee
    * @param accountHolder customer object
    * @param startBalance intial balance
    * @param transactionFee overdraft fee
    */
  ChequingAccount(Customer accountHolder, double startBalance, double transactionFee){
    super(accountHolder, startBalance);     //Call the super (BankAccount) with accountHolder and startBalance parameters
    overdraftFee = transactionFee;          //Set overdraftFee to transactionFee
  }

  /**
    * getOverdraftFee() returns the overdraftFee as a double.
    * @return overdraftFee
    */
  public double getOverdraftFee() {
    return overdraftFee;
  }

  /**
    * setOverdraftFee() takes in a double and sets it to overdraftFee.
    * @param fee
    */
  public void setOverdraftFee(double fee) {
    overdraftFee = fee;
  }

  /**
    * getOverdraftAmount() returns the overdraftAmount as a double.
    * @return overdraftAmount
    */
  public double getOverdraftAmount() {
    return overdraftAmount;
  }

  /**
    * setOverdraftAmount() takes in a double and sets it to overdraftAmount.
    * @param amt
    */
  public void setOverdraftAmount(double amt) {
    overdraftAmount = amt;
  }

  /**
    * withdraw() takes in a double and checks if balance is within overdraft amount,
    * then sets the new balance to the account.
    * @param amt
    */
  public void withdraw(double amt) {
    //Checks if the resulting balance is greater than overdraft and if the amount is positive
    if ((super.getBalance() - amt) >= (0-overdraftAmount) && (amt >= 0)) {
      double newBalance = super.getBalance();     //Initialize temporary balance and get the current balance
      newBalance -= amt;                          //Subtract the amount from the temporary balance
      if (newBalance < 0) {                       //Check if the balance is negative
        newBalance -= overdraftFee;               //Subtract the overdraftFee from the balance
      }
      super.setBalance(newBalance);               //Call the setBalance method from superclass with newBalance as parameter

    }
  }

  /**
    * getMonthlyFeesAndInterest() checks if the balance is positive, return 0. Or return the 20% of the current balance.
    * This method overrides the abstract method in BankAccount.
    */
  @Override
  protected double getMonthlyFeesAndInterest() {
      if (super.getBalance() >= 0) {
        return 0;
      }
      return super.getBalance()*0.2;
  }
}
