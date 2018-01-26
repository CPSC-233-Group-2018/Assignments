//TEMPORARY PREVIEW ONLY, file name does not match also

/**
 * Seth Campbell, Jan 26, 2018
 * -team assignment 2-
 *  	A BankAccount class that allows accounts to be made and withdraws and deposits
 *  	to be made. Also includes an overdraft feature.
 */
public class BankAccount {
	//class instance variables
	double balance = 0;
	double overdraftAmount= 100;
	
	void deposit(double input) {
		/**
		 * deposits inputed amount of money in account.
		 */
		if (input > 0) {
			balance += input; 
		} else System.out.println("You can't deposit a negative value!");
	}
	
	void withdraw(double input) {
		/**
		 * withdraws the inputed amount if allowed based on balance and overdraft.
		 */
		if ((balance - input) > -1*(overdraftAmount)) {
			balance -= input;
			System.out.println("withdrew "+input);
		} else {
			System.out.println("You don't have enough funds/overdraft to make this transaction");
		}	
	}
	
	double getBalance() {       
		/**
		 * returns the current balance of the account.
		 */
		return balance;
	}
	
	void setOverdraftAmount(double input) {
		/**
		 * Sets the overdraft amount if the current balance is adequate.  
		 */
		if (balance > -(input)) {
			overdraftAmount = input;
		} else System.out.println("Your balance is too low for that overdraft amount."); 
	}
	
} //end of class
