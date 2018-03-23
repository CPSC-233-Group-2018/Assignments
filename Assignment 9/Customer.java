/**
 	* Tutorial 6 Team 3:
 	* Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 	* -team assignment 8, Mar.20,2018-
 	*  	An immutable customer class that interacts with the BankAccount class.
	*/

public final class Customer {
	//private instance variables, final values, cannot be changed
	private final String name;
	private final int customerID;

	/**
 	 * Default constructor for the class
	 */
	Customer() {
    name = "";
    customerID = 0;
	}

	/**
	 * Copy constructor that takes in a Customer object and sets the name and id to that customer.
	 * @param c Customer object
	 */
	Customer(Customer c) {
    name = c.name;
    customerID = c.customerID;
	}

	/**
	 * Constructor that takes in a string and integer and sets them as the name and customerID
	 * @param cusName customer's name
	 * @param cusID customer's ID
	 */
	Customer(String cusName, int cusID) {
		name = cusName;
		customerID = cusID;
	}

	/** getName() returns the name
	 * @return name the current name of the customer
	 */
	public String getName() {
		String copyName = name;
		return copyName;
	}

	/** getID() returns the customerID
	 * @return customerID current ID of the customer
	 */
	public int getID() {
		return customerID;
	}

	/** toString() formats the name and ID of customer in a read-able way.
	 * It converts the customerID to a string and stores it in strID.
	 * @return name and strID A nicely formated string with the name and ID of customer
	 */
	public String toString() {
		String strID = Integer.toString(customerID);
		return "Customer name: " + name + "." + "\nCustomer ID: " + strID + ".";
	}

}
