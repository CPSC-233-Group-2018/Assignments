
public class Customer {
	
	public String name; //instance variables
	public int customerID;
	
	public void setName(String input) { //method for name
		name = input;
	}
	
	public void setID(int input) { //method for ID
		customerID = input;
	}
	
	public String toString() { //converts ID to string, not entirely sure about the return?
		String strID = Integer.toString(customerID);
		return strID;
	}
	
	public Customer() { //constructor with no arguments 
		
	}
	
	Customer(String cusName, int cusID) { //constructor that takes name and ID
		this.name = cusName;
		this.customerID = cusID;
	}
	
	void information() { //prints out info from constructor
		System.out.println("Customer name: " + name + "." + "\nCustomer ID: " + customerID + ".");
	}

}
