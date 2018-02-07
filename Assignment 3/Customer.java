
public class Customer {

	public String name; //instance variables
	public int customerID;

	Customer() {		//default constructor
    name = "";
    customerID = 0;
  }

	Customer(Customer c) {      //copy constructor
    name = c.name;
    customerID = c.customerID;
  }

	Customer(String cusName, int cusID) { //constructor that takes name and ID
		name = cusName;
		customerID = cusID;
	}

	public void setName(String input) { //method for name
		name = input;
	}

	public void setID(int input) { //method for ID
		customerID = input;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return customerID;
	}

	public String toString() { //converts Customer object into a string that can be printed.
		String strID = Integer.toString(customerID);
		return "Customer name: " + name + "." + "\nCustomer ID: " + strID + ".";
	}

}
