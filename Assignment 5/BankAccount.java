/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 5, Feb.19,2018-
 *  	A BankAccount class that allows accounts to be created and withdraws and deposits
 *  	to be made. Also includes an overdraft feature.
 */

public class BankAccount {
	//class instance variables
	private double balance = 0;
	private double overdraftAmount= 100;
	private Customer customer = new Customer();

	/**
		* Default constructor that takes no arguments.
		*/
	BankAccount() {
    balance = 0.00;
    overdraftAmount = 100.00;
	}

	/**
		* Constructor that takes in an initial balance.
		* @param initialBalance initial amount of balance
		*/
	BankAccount(double initialBalance) {
		balance = initialBalance;
	}

  /** Contructor that takes in a Customer object and a balance.
		* @param c Customer object
		* @param bal initial amount of balance
		*/
	BankAccount(Customer c, double bal){
    customer = c;
    balance = bal;
	}

	/**
	 * deposit() takes in a double and adds the input to balance.
	 * Balance does not change if the input is a negative amount.
	 * @param input amount to deposit
	 */
	public void deposit(double input) {
		if (input >= 0 && Double.isInfinite(input) == false) { //only positive values allowed excluding infinity
			balance += input;
			System.out.println("deposited " + input);
		} else System.out.println("You can't deposit a negative value!");
	}

	/**
	 * withdraw() takes in a double and subtracts it from the balance.
	 * It will only change when the input is positive and if the input does not exceed the overdraft limit.
	 * @param input amount to subtract
	 */
	public void withdraw(double input) {
		if ((balance - input) >= -1*(overdraftAmount) && (input >= 0)) { //i.e checks if resulting balance is within the overdraft and if input is non-negative
			balance -= input;
			System.out.println("withdrew " + input);
		} else {
			System.out.println("You don't have enough funds/overdraft to make this transaction or withdraw a negative amount.");
		}
	}

	/**
	 * getBalance() returns the balance.
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * setOverdraftAmount() takes in a double and changes the current overdraftAmount to the input.
	 * It only changes if the new amount will be lower than the current balance.
	 * @param input new overdraft amount
	 */
	public void setOverdraftAmount(double input) {
		 if ((0-input) <= balance) {
       overdraftAmount = input;
     }
	}

	/**
		* getCustomer() returns the customer.
		* @return customer new object
		*/
	public Customer getCustomer(){
    return (new Customer(customer));
  }

	/**
		* setCustomer() takes in a customer object and sets the instance variable to that customer.
		* @param c customer object to set
		*/
	public void setCustomer(Customer c) {
		customer = new Customer(c);
	}

}
//end of class
