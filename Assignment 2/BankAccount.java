public class BankAccount{
  /**
   * A class containing attributes about a bank account, as well as having
   methods for getting the balance, widthrawing and depositing to the balance
   Team 3: Lecture 6: Kieran, Rulan, Seth, William
   */

  public static double balance = 100.00;

  public static void main(String[] args){
    /**
    This function creates all the initial attributes needed for the class
    */
    System.out.println("The current balance is: " + get_balance());//Test code to print current balance
    widthdraw(10.12);
    System.out.println("\nThe current balance is: " + get_balance() + "\nAnd it should be 89.88");//Test code to print current balance
    deposit(10.12);
    System.out.println("\nThe current balance is: " + get_balance() + "\nAnd it should be 100.0");//Test code to print current balance

  }
  public static double get_balance() {
    /**
    Method that returns the balance of account
    */
    return balance;
  }

  public static void widthdraw(double amount){
    /**
    Method for widthdrawing from the balance of the account,takes a double as an argument
    */
    balance -= amount;
  }

  public static void deposit(double amount){
    /**
    Method for depositing to the balance of the account,takes a double as an argument
    */
    balance += amount;
  }

}
