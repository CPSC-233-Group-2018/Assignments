public class Test{
Customer c1 = new Customer("John Doe", 1001);
Customer c2 = new Customer("Jane Doe", 2002);
ChequingAccount b1 = new ChequingAccount(c1, 100.0, 10.0);
SavingsAccount b2 = new SavingsAccount(c2, 500.0);

Test(){
b1.monthEndUpdate();

b2.monthEndUpdate();

System.out.println(b1.getBalance() + "," + b2.getBalance());
}
}
