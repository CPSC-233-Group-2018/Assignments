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
  }
  public static double get_balance() {
    /**
    Method that returns the balance of account
    */
    return balance;
  }

  public static void widthdraw(){
    /**
    Method for widthdrawing from the balance of the account
    */
  }

  public static void deposit(){
    /**
    Method for depositing to the balance of the account
    */
  }

}
