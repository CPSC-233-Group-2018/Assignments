public class BankAccount{
  /**
   * A class containing attributes about a bank account, as well as having
   methods for getting the balance, widthrawing and depositing to the balance
   Team 3: Tutorial 6: Kieran, Rulan, Seth, William
   */

  public double balance = 0.00;
  public double overdraftAmount = 100.00;
  /**
  Constructors for object class
  */

  public static void main(String[] args){
    /**
    This function calls and invokes all the methods
    */

    //Start of test code
    System.out.println("The current balance is: " + get_balance());//Test code to print current balance
    withdraw(500);
    System.out.println("\nThe current balance is: " + get_balance() + "\nAnd it should be 89.88");//Test code to print current balance
    deposit(10.12);
    System.out.println("\nThe current balance is: " + get_balance() + "\nAnd it should be 100.0");//Test code to print current balance

    System.out.println("\nThe current overdraftAmount is: " + overdraftAmount);
    setOverdraftAmount(200.00);
    System.out.println("\nThe current overdraftAmount is: " + overdraftAmount);
    //End of test code

  }
  public double getBalance() {
    /**
    Method that returns the balance of account
    */
    return balance;
  }

  public void withdraw(double amount){
    /**
    Method for withdrawing from the balance of the account,takes a double as an argument
    */
    double maxWithdraw = 0.00 - overdraftAmount;
    if (balance > 0 && (balance - amount >= maxWithdraw)) {
      balance -= amount;
    }
  }

  /*
  public void deposit(double amount) {

    do{
      double overdraftDifference = (0.00-overdraftAmount);
      double new_balance = balance - amount;
      boolean bad_credit = false;
      System.out.println("\nThe overdraftDifference is: " + overdraftDifference);

    if (new_balance < overdraftDifference){
      bad_credit = true;
      System.out.println("Your too poor try again");
      amount = 10;
      }
    } while (bad_credit == true);
  }
  */
  public void deposit(double amount){
    /**
    Method for depositing to the balance of the account,takes a double as an argument
    */
    if (amount > 0) {
      balance += amount;
    }
  }

  public void setOverdraftAmount(double newAmount){
    /**
    Method for setting the overdraft amount,takes a double as an argument
    */
    overdraftAmount = newAmount;
  }

} //End of class
