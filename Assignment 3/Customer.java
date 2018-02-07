/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 3, Feb.6,2018-
 *  	A customer class that interacts with the bankAccount class.
*/

public class Customer {
	//instance variables
	public String name;
	public int customerID;


	Customer() {
		/** Default constructor */
    name = "";
    customerID = 0;
	}


	Customer(Customer c) {
		/** Copy constructor */
    name = c.name;
    customerID = c.customerID;
	}


	Customer(String cusName, int cusID) {
		/** Constructor taking name and ID */
		name = cusName;
		customerID = cusID;
	}


	public void setName(String input) {
		/** sets name of customer
		* @param name
		*/
		name = input;
	}


	public void setID(int input) {
		/** sets ID of customer
		* @param ID number
		*/
		customerID = input;
	}


	public String getName() {
		/** retrieves name of customer
		* @return name of customer
		*/
		return name;
	}


	public int getID() {
		/** retrieves ID of customer
		* @return ID of customer
		*/
		return customerID;
	}


	public String toString() {
		/** Formats the name and ID of customer in a read-able way.
		* @return formatted string of name and ID
		*/
		String strID = Integer.toString(customerID);
		return "Customer name: " + name + "." + "\nCustomer ID: " + strID + ".";
	}

}
