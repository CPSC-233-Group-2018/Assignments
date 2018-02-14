/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 4, Feb.13,2018-
 *  	A customer class that interacts with the bankAccount class.
*/

public class Customer {
	//instance variables
	private String name;
	private int customerID;

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

	/** setName() takes in a string and sets it to name
		* @param input new customer name
		*/
	public void setName(String input) {
		name = input;
	}

	/** setID() takes in an int and sets it to customerID
		* @param input new customer id
		*/
	public void setID(int input) {
		customerID = input;
	}

	/** getName() returns the name
		* @return name
		*/
	public String getName() {
		return name;
	}

	/** getID() returns the customerID
		* @return customerID
		*/
	public int getID() {
		return customerID;
	}

	/** toString() formats the name and ID of customer in a read-able way.
		* It converts the customerID to a string and stores it in strID.
		* @return name and strID
		*/
	public String toString() {
		String strID = Integer.toString(customerID);
		return "Customer name: " + name + "." + "\nCustomer ID: " + strID + ".";
	}

}
