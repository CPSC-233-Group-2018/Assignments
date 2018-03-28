/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan
 * -team assignment 10, Mar.27,2018-
 *  	A BankApplication class that creates a GUI for a user to withdraw and deposit
 *    and shows the balance to the user. Now updated with exception handling, error
 *    messages and file saving/loading.
 */

//Import javafx libraries
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.text.*;     //Imports text library ffor currency format

public class BankApplication extends Application {
  //Create and initialize instance objects Customer and SavgingsAccount
  private Customer customer = new Customer("Charles Brown", 123456);
  private SavingsAccount savings = new SavingsAccount(customer, 150.00);
  //private SavingsAccount savings = new SavingsAccount();
  private ChequingAccount chequing = new ChequingAccount(customer, 2000.00, 10);
  //private ChequingAccount chequing = new ChequingAccount();
  private Boolean isSavings = false;

  /**
    * start() shows the GUI and its components
    * @param primaryStage stage for GUI
    */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Scanner input = new Scanner(System.in);


    File acc = new File("Account.txt");
    if (acc.exists()) {
      BufferedReader inputFile = null;
    try {
      inputFile = new BufferedReader(new FileReader(acc));
    }
    catch (Exception e){
      System.out.println("Exception in line 42");
    }
        String line = inputFile.readLine();
        String[] accInfo = new String[6];     //new array to store information
        //0: Name, 1: ID, 2: Balance, 3: Interest Rate, 4: Overdraft Amount, 5: Overdraft Fee
        int count = 0;
         while (((line = inputFile.readLine()) != null) && (count <accInfo.length)){
             accInfo[count] = line;
             //System.out.println("Iteration Number:" + count + Arrays.toString(accInfo));
             count+=1;
         }
         //System.out.println(Arrays.toString(accInfo));
         inputFile.close();
      try{
        Customer cust = new Customer(accInfo[0], Integer.valueOf(accInfo[1]));  //new customer with name and id
        customer = new Customer(cust);
        if (accInfo[3].equals("0.0")) {    //checks to see if interest rate is empty
          chequing = new ChequingAccount(cust, Double.parseDouble(accInfo[2]), Double.parseDouble(accInfo[5]));
          chequing.setOverdraftAmount(Double.parseDouble(accInfo[4]));
        } else {
          savings = new SavingsAccount(cust, Double.parseDouble(accInfo[2]));
          savings.setAnnualInterestRate(Double.parseDouble(accInfo[3]));
          isSavings = true;
        }

      }catch (Exception e) {
            System.out.println("Error in lines 58-65");
            System.err.println("Exception: " + e.getMessage());
      }
    }else { //create a savings or chequing account

      System.out.println("Create a savings account (S) or chequing account (C): ");
      String choice = input.nextLine().toUpperCase();
      System.out.println("Enter name: ");
      String name = input.nextLine();
      Random random = new Random();
      int id = random.nextInt(9999 - 1000 + 1) + 1000;
      Customer createC = new Customer(name, id);
      customer = new Customer(createC);
      if (choice.equals("S")) { //creating a savings account
        System.out.println("Enter initial balance: ");
        input = new Scanner(System.in);
        double balS = input.nextDouble();
        System.out.println("Enter annual interest rate: ");
        input = new Scanner(System.in);
        double rate = input.nextDouble();
        savings = new SavingsAccount(createC, balS);
        savings.setAnnualInterestRate(rate);
        isSavings = true;
      } else if (choice.equals("C")) { //creating a chequing account
        System.out.println("Enter initial balance: ");
        input = new Scanner(System.in);
        double balC = input.nextDouble();
        System.out.println("Enter overdraft amount: ");
        input = new Scanner(System.in);
        double overAmt = input.nextDouble();
        System.out.println("Enter overdraft fee: ");
        input = new Scanner(System.in);
        double overFee = input.nextDouble();
        chequing = new ChequingAccount(createC, balC, overFee);
        chequing.setOverdraftAmount(overAmt);
      }

    }
    NumberFormat currency = NumberFormat.getCurrencyInstance();       //Currency formatter used to format doubles
    VBox vbox = new VBox();                                           //Create new empty vertical box panel
    Label customerNameLabel = new Label("Customer name: " + customer.getName());                  //Create new label for customer name
    Label customerIDLabel = new Label("Account ID: " + customer.getID());                         //Create new label for customer id
    Label balanceLabel;
    Label errorMessages = new Label("\n");
    Label customerTypeLabel = new Label("");                  //blank to start off
    // Label fileNotificationLabel = new Label("");
    // TextField typeField = new TextField("Type of Account: (S) or (C)");
    // TextField nameField = new TextField("Name");              //name field for creating an account
    // TextField inputBalanceField = new TextField("balance");   //balance fields
    // TextField overDraftAndInterestField = new TextField("Overdraft or Interest"); //will function for either overdraft or interest
    // TextField overDraftFeeField = new TextField("overdraft fee (if needed)");

    if (isSavings == true) {    //creating an account label based on its type
      customerTypeLabel.setText("Account type: " + "Savings");
    } else {
      customerTypeLabel.setText("Account type: " + "Chequing");
    }

    if (isSavings == true){     //creating the balance label based on account's balance
      balanceLabel= new Label("Current balance: " + currency.format(savings.getBalance()));  //Create new label for current balance
      System.out.println("balance of savings for testing: "+savings.getBalance());
    } else {
      balanceLabel= new Label("Current balance: " + currency.format(chequing.getBalance()));  //Create new label for current balance
    }

    // if (acc.exists()) {       //if an account file exists, display a message accordingly
    //   fileNotificationLabel.setText("Account File found!\n");
    // } else {
    //   fileNotificationLabel.setText("Account File NOT found! Please setup an account!\n");
    // }

    //Add customer name, id labels and type and textfields to vertical box
    // vbox.getChildren().add(fileNotificationLabel);
    // vbox.getChildren().add(typeField);
    // vbox.getChildren().add(nameField);
    // vbox.getChildren().add(inputBalanceField);
    // vbox.getChildren().add(overDraftAndInterestField);
    // vbox.getChildren().add(overDraftFeeField);
    vbox.getChildren().add(customerNameLabel);
    vbox.getChildren().add(customerIDLabel);
    vbox.getChildren().add(customerTypeLabel);

    //adds the error message box to the verticalbox
    vbox.getChildren().add(errorMessages);

    HBox hbox = new HBox();                                         //Create new empty horizontal box panel
    TextField depositTextField = new TextField();
    depositTextField.setPromptText("Amt to deposit");   //Create a text field showing initial text to user
    TextField withdrawTextField = new TextField();       //Craete a text field showing initial text to user
    withdrawTextField.setPromptText("Amt to withdraw");

    //Add both text fields to the horizontal box
    hbox.getChildren().add(depositTextField);
    hbox.getChildren().add(withdrawTextField);

    //Add the horizontal box to the veritcal box
    vbox.getChildren().add(hbox);

    Button executeButton = new Button("Execute");   //Create a button with execute as text

    //Add the button and balance label to the vertical box
    vbox.getChildren().add(executeButton);
    vbox.getChildren().add(balanceLabel);

    //Create an EventHandler when user pushes button
    executeButton.setOnAction(new EventHandler<ActionEvent>() {
        /**
          * handle() takes in an ActionEvent and checks the text in each text field and deposits
          * or withdraws the amount
          * @param event ActionEvent
          */
        @Override
        public void handle(ActionEvent event) {
          errorMessages.setText("");   //resetting the error message field

          //doing checks with the deposit field first
          try {    //Try to parse a double from the text obtained from the deposit text field

            double depositAmt = Double.parseDouble(depositTextField.getText());
            if (depositAmt <0){
              errorMessages.setText("~Invalid deposit please try again with a number greater than 0");
            }
            if (depositAmt >= 0 && Double.isInfinite(depositAmt) == false && (isSavings == false)) {    //Check if the double parsed is positive and is not infinity
              chequing.deposit(depositAmt);          //Call the deposit method from the chequing object with depositAmt
            }
            if (depositAmt >= 0 && Double.isInfinite(depositAmt) == false && (isSavings == true)) {    //Check if the double parsed is positive and is not infinity
              savings.deposit(depositAmt);          //Call the deposit method from the chequing object with depositAmt
            }
            depositTextField.clear();      //Reset the deposit text field with initial text
            if (isSavings == true){
              balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));  //Update the balance
            }
            if (isSavings == false){
              balanceLabel.setText("Current balance: " + currency.format(chequing.getBalance()));  //Update the balance
            }
          } catch (NumberFormatException e) {       //Catch a NumberFormatException
            errorMessages.setText("~Not a valid input! Must be numbers.");
            depositTextField.clear();         //Reset the deposit text field with initial text
            System.out.println("Error with number format. ");   //Print to user that number format is incorrect
            System.err.println("NumberFormatException: " + e.getMessage());     //Print error message
          }

          //working on the withdrawal field now
          try {     //Try to parse a double from the text obtained from the withdraw text field
            double withdrawAmt = Double.parseDouble(withdrawTextField.getText());
            if (withdrawAmt <0){
            errorMessages.setText("~Invalid withdrawal please try again with a number greater than 0");
            }
            if (withdrawAmt >= 0 && Double.isInfinite(withdrawAmt) == false && (isSavings == false)) {  //Check if the double parsed is positive and is not infinity
              if ((chequing.getBalance() - withdrawAmt) < -1*chequing.getOverdraftAmount()) {  //check if withdrawing too much
                errorMessages.setText("~You don't have enough to withdraw that amount!");
              } else {
                chequing.withdraw(withdrawAmt);        //Call the withdraw method from the chequing object with withdrawAmt
              }
              System.out.println("balance:"+chequing.getBalance());
            }

            //checking for valid inputs if it's a savings account for withdraws
            if (withdrawAmt >= 0 && Double.isInfinite(withdrawAmt) == false && (isSavings == true)) {  //Check if the double parsed is positive and is not infinity
              if ((savings.getBalance() - withdrawAmt) < 0) {  //check if withdrawing too much
                errorMessages.setText("~You don't have enough to withdraw that amount!");
              } else {
                savings.withdraw(withdrawAmt);        //Call the withdraw method from the savings object with withdrawAmt
              }
              System.out.println("balance:"+savings.getBalance());
            }

            //after execution of withdraw and deposit, reset the GUI's display
            withdrawTextField.clear();   //Reset the withdraw text field with initial text

            if (isSavings==false){
              balanceLabel.setText("Current balance: " + currency.format(chequing.getBalance()));  //Update the balance
            }
            if (isSavings==true){
              balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));  //Update the balance
            }

          } catch (NumberFormatException e) {       //Catch a NumberFormatException
            errorMessages.setText("~Not a valid input! Must be numbers.");
            withdrawTextField.clear();       //Reset the withdraw text field with initial text
            System.out.println("Error with number format. ");   //Print to user that number format is incorrect
            System.err.println("NumberFormatException: " + e.getMessage());     //Print error message
          }
        }
      });

    Scene scene = new Scene(vbox, 400, 300);        //Create a new scene with vertical box panel that is 400x300 in size
    primaryStage.setTitle("Bank application");      //Set the title of the stage
    primaryStage.setScene(scene);                   //Set the stage to the scene
    primaryStage.show();                            //Show the stage

    //0: Name, 1: ID, 2: Balance, 3: Interest Rate, 4: Overdraft Amount, 5: Overdraft Fee
    primaryStage.setOnCloseRequest(a -> {
      System.out.println("Saving ...");
      BufferedWriter theWriter = null;
      try{
       theWriter= new BufferedWriter(new FileWriter("Account.txt"));
    } catch(Exception e){
      System.out.println("Exception thrown in line 175");
    }


    try{
     theWriter.write("\n" + (customer.getName()));
  } catch(Exception e){
    System.out.println("Exception thrown in line 182");
  }

try{
 theWriter.write("\n"+Integer.toString(customer.getID()));
} catch(Exception e){
System.out.println("Exception thrown in line 188");
}

if (isSavings==false){
try{
 theWriter.write("\n"+Double.toString(chequing.getBalance()));
} catch(Exception e){
System.out.println("Exception thrown in line 194");
}
}

if (isSavings==true){
try{
 theWriter.write("\n"+Double.toString(savings.getBalance()));
} catch(Exception e){
System.out.println("Exception thrown in line 194");
}
}

if (isSavings==true){
try{
 theWriter.write("\n"+Double.toString(savings.getAnnualInterestRate()));
} catch(Exception e){
System.out.println("Exception thrown in line 200");
}
}

if (isSavings==false){
try{
 theWriter.write("\n"+Double.toString(chequing.getOverdraftAmount()));
} catch(Exception e){
System.out.println("Exception thrown in line 206");
}
}

if (isSavings==false){
try{
 theWriter.write("\n"+Double.toString(chequing.getOverdraftFee()));
} catch(Exception e){
System.out.println("Exception thrown in line 212");
}
}

try{
       theWriter.close();
    } catch(Exception e){
      System.out.println("Exception thrown in line 218");
    }

    });
  }

  /**
    * main() launches the javafx GUI with args
    * @param args
    */
  public static void main(String[] args)
  {
    launch(args);
  }
}
