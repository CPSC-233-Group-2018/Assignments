/**
 * Tutorial 6 Team 3:
 * Seth Campbell, Kieran Woods, Rulan Lu, William Chan Jan 26, 2018
 * -team assignment 5, Feb.26,2018-
 *  	A BankApplication class that creates a GUI for a user to withdraw and deposit
 *    and shows the balance to the user.
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

  /**
    * start() shows the GUI and its components
    * @param primaryStage stage for GUI
    */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Scanner input = new Scanner(System.in);
    File acc = new File("Account.txt");
    if (acc.exists()) {
      try {
        BufferedReader inputFile = new BufferedReader(new FileReader(acc));
        String line = inputFile.readLine();
        String[] accInfo = new String[6];     //new array to store information
        //0: Name, 1: ID, 2: Balance, 3: Interest Rate, 4: Overdraft Amount, 5: Overdraft Fee

        while ((line = inputFile.readLine()) != null) {
          for (int i = 0; i < accInfo.length; i++) {
            accInfo[i] = line;
          }
        }
        inputFile.close();
        Customer cust = new Customer(accInfo[0], Integer.parseInt(accInfo[1]));  //new customer with name and id
        customer = new Customer(cust);
        if (accInfo[3].equals("")) {    //checks to see if interest rate is empty
          ChequingAccount cAcc = new ChequingAccount(cust, Double.parseDouble(accInfo[2]), Double.parseDouble(accInfo[5]));
          cAcc.setOverdraftAmount(Double.parseDouble(accInfo[4]));
        } else {
          savings = new SavingsAccount(cust, Double.parseDouble(accInfo[2]));
          savings.setAnnualInterestRate(Double.parseDouble(accInfo[3]));
        }

      } catch (FileNotFoundException e) {
              System.out.println("File could not be found. ");
              System.err.println("FileNotFoundException: " + e.getMessage());
      } catch (IOException e) {
              System.out.println("Problem with input/output. ");
              System.err.println("IOException: " + e.getMessage());
      }
    } else {
      System.out.println("Create a savings account (S) or chequing account (C): ");
      String choice = input.nextLine().toUpperCase();
      System.out.println("Enter name: ");
      String name = input.nextLine();
      Random random = new Random();
      int id = random.nextInt(9999 - 1000 + 1) + 1000;
      Customer createC = new Customer(name, id);
      customer = new Customer(createC);
      if (choice.equals("S")) {
        System.out.println("Enter initial balance: ");
        input = new Scanner(System.in);
        double balS = input.nextDouble();
        System.out.println("Enter annual interest rate: ");
        input = new Scanner(System.in);
        double rate = input.nextDouble();
        savings = new SavingsAccount(createC, balS);
        savings.setAnnualInterestRate(rate);
      } else if (choice.equals("C")) {
        System.out.println("Enter initial balance: ");
        input = new Scanner(System.in);
        double balC = input.nextDouble();
        System.out.println("Enter overdraft amount: ");
        input = new Scanner(System.in);
        double overAmt = input.nextDouble();
        System.out.println("Enter overdraft fee: ");
        input = new Scanner(System.in);
        double overFee = input.nextDouble();
        ChequingAccount c = new ChequingAccount(createC, balC, overFee);
        c.setOverdraftAmount(overAmt);
      }

    }
    NumberFormat currency = NumberFormat.getCurrencyInstance();       //Currency formatter used to format doubles
    VBox vbox = new VBox();                                           //Create new empty vertical box panel
    Label customerNameLabel = new Label("Customer name: " + customer.getName());                  //Create new label for customer name
    Label customerIDLabel = new Label("Account ID: " + customer.getID());                         //Create new label for customer id
    Label balanceLabel = new Label("Current balance: " + currency.format(savings.getBalance()));  //Create new label for current balance

    //Add customer name and id labels to vertical box
    vbox.getChildren().add(customerNameLabel);
    vbox.getChildren().add(customerIDLabel);

    HBox hbox = new HBox();                                         //Create new empty horizontal box panel
    TextField depositTextField = new TextField("Amt to deposit");   //Create a text field showing initial text to user
    TextField withdrawTextField = new TextField("Amt to withdraw"); //Craete a text field showing initial text to user

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
          try {             //Try to parse a double from the text obtained from the deposit text field
            double depositAmt = Double.parseDouble(depositTextField.getText());
            if (depositAmt >= 0 && Double.isInfinite(depositAmt) == false) {    //Check if the double parsed is positive and is not infinity
              savings.deposit(depositAmt);          //Call the deposit method from the savings object with depositAmt
            }
            depositTextField.setText("Amt to deposit");         //Reset the deposit text field with initial text
            balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));  //Update the balance
          } catch (NumberFormatException e) {       //Catch a NumberFormatException
            depositTextField.setText("Amt to deposit");         //Reset the deposit text field with initial text
            System.out.println("Error with number format. ");   //Print to user that number format is incorrect
            System.err.println("NumberFormatException: " + e.getMessage());     //Print error message
          } try {           //Try to parse a double from the text obtained from the withdraw text field
            double withdrawAmt = Double.parseDouble(withdrawTextField.getText());
            if (withdrawAmt >= 0 && Double.isInfinite(withdrawAmt) == false) {  //Check if the double parsed is positive and is not infinity
              savings.withdraw(withdrawAmt);        //Call the withdraw method from the savings object with withdrawAmt
            }
            withdrawTextField.setText("Amt to withdraw");       //Reset the withdraw text field with initial text
            balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));  //Update the balance
          } catch (NumberFormatException e) {       //Catch a NumberFormatException
            withdrawTextField.setText("Amt to withdraw");       //Reset the withdraw text field with initial text
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
    primaryStage.setOnCloseRequest((WindowEvent e) -> {
      BufferedWriter writer = new BufferedWriter(new FileWriter("Account.txt"), true);
      writer.write(customer.getName());
      writer.write(customer.getID());
      writer.write(customer.getBalance());
      writer.write(savings.getAnnualInterestRate());
      writer.write(c.getOverdraftAmount());
      writer.write(c.getOverdraftFee());
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
