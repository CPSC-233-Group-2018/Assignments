/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 3, Feb.6,2018-
 *  	A BankAccount class that allows accounts to be made and withdraws and deposits
 *  	to be made. Also includes an overdraft feature and works with the Customer class.
 */

public class BankAccount {
	//class instance variables
	private double balance = 0;
	private double overdraftAmount= 100;
	private Customer customer = new Customer();


	BankAccount() {
    /** Default Constructor */
    balance = 0.00;
    overdraftAmount = 100.00;
	}

	BankAccount(double initialBalance) {
		balance = initialBalance;
	}

	BankAccount(Customer c, double bal){
    /** Contructor with customer object and balance parameters */
    customer = c;
    balance = bal;
	}

	public void deposit(double input) {
		/**
		 * deposits inputed amount of money in account.
		 * @param money to desposit
		 */
		if (input > 0) { //only positive values allowed
			balance += input;
		} else System.out.println("You can't deposit a negative value!");
	}

	public void withdraw(double input) {
		/**
		 * withdraws the inputed amount if allowed based on balance and overdraft.
		 * @param withdrawal amount
		 */
		if ((balance - input) > -1*(overdraftAmount) && (input > 0)) { //i.e checks if resulting balance is within the overdraft and if input is non-negative
			balance -= input;
			System.out.println("withdrew " + input);
		} else {
			System.out.println("You don't have enough funds/overdraft to make this transaction or withdraw a negative amount.");
		}
	}

	public double getBalance() {
		/**
		 * returns the current balance of the account.
		 * @return balance total
		 */
		return balance;
	}

	public void setOverdraftAmount(double input) {
		/**
		 * Sets the overdraft amount if the current balance is adequate.
		 * @param new overdraft amount
		 */
		 if ((0-input) <= balance) {
       overdraftAmount = input;
     }
	}

	public Customer getCustomer(){
    return (customer);
  }

} //end of class
