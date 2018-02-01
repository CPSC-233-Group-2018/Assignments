
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

	public String toString() { //converts ID to string, not entirely sure about the return?
		String strID = Integer.toString(customerID);
		return strID;
	}

	void information() { //prints out info from constructor
		System.out.println("Customer name: " + name + "." + "\nCustomer ID: " + customerID + ".");
	}

}
