/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 3, Feb.6,2018-
 *  	A customer class that interacts with the bankAccount class. 
*/
public class Customer {

	public String name; //instance variables
	public int customerID;

	/** Default constructor */
	Customer() {		
    name = "";
    customerID = 0;
	}
	
	/** Copy constructor */
	Customer(Customer c) {     
    name = c.name;
    customerID = c.customerID;
	}
	
	/** Constructor taking name and ID */
	Customer(String cusName, int cusID) { 
		name = cusName;
		customerID = cusID;
	}

	/** sets name of customer
	* @param name 
	*/
	public void setName(String input) { 
		name = input;
	}

	/** sets ID of customer
	* @param ID number 
	*/
	public void setID(int input) { 
		customerID = input;
	}

	/** retrieves name of customer
	* @return name of customer
	*/
	public String getName() {
		return name;
	}

	/** retrieves ID of customer
	* @return ID of customer
	*/
	public int getID() {
		return customerID;
	}

	/** Formats the name and ID of customer in a read-able way.
	* @return formatted string of name and ID
	*/
	public String toString() { //converts Customer object into a string that can be printed.
		String strID = Integer.toString(customerID);
		return "Customer name: " + name + "." + "\nCustomer ID: " + strID + ".";
	}

}
