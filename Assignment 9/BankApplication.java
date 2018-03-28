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
  private ChequingAccount chequing = new ChequingAccount(customer, 2000.00, 10);
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
    //     //0: Name, 1: ID, 2: Balance, 3: Interest Rate, 4: Overdraft Amount, 5: Overdraft Fee
        int count = 0;
         while (((line = inputFile.readLine()) != null) && (count <accInfo.length)){
             accInfo[count] = line;
             System.out.println("Iteration Number:" + count + Arrays.toString(accInfo));
             count+=1;

         }
         System.out.println(Arrays.toString(accInfo));
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
    }else {


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
    if (isSavings == true){
      balanceLabel= new Label("Current balance: " + currency.format(savings.getBalance()));  //Create new label for current balance
    }
    else{ balanceLabel= new Label("Current balance: " + currency.format(chequing.getBalance()));  //Create new label for current balance
  }

    //Add customer name and id labels to vertical box
    vbox.getChildren().add(customerNameLabel);
    vbox.getChildren().add(customerIDLabel);

    //adds the error message box to the verticalbox
    vbox.getChildren().add(errorMessages);

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
            if (depositAmt <0){
            errorMessages.setText("\nInvalid deposit please try again with a number greater than 0");
          }
            if (depositAmt >= 0 && Double.isInfinite(depositAmt) == false && (isSavings == false)) {    //Check if the double parsed is positive and is not infinity
              chequing.deposit(depositAmt);          //Call the deposit method from the chequing object with depositAmt
            }
            if (depositAmt >= 0 && Double.isInfinite(depositAmt) == false && (isSavings == true)) {    //Check if the double parsed is positive and is not infinity
              savings.deposit(depositAmt);          //Call the deposit method from the chequing object with depositAmt
            }
            depositTextField.setText("Amt to deposit");         //Reset the deposit text field with initial text
            if (isSavings == true){
                balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));  //Update the balance
            }
            if (isSavings == false){
            balanceLabel.setText("Current balance: " + currency.format(chequing.getBalance()));  //Update the balance
          }
          } catch (NumberFormatException e) {       //Catch a NumberFormatException
            depositTextField.setText("Amt to deposit");         //Reset the deposit text field with initial text
            System.out.println("Error with number format. ");   //Print to user that number format is incorrect
            System.err.println("NumberFormatException: " + e.getMessage());     //Print error message
          } try {           //Try to parse a double from the text obtained from the withdraw text field
            double withdrawAmt = Double.parseDouble(withdrawTextField.getText());
            if (withdrawAmt <0){
            errorMessages.setText("\nInvalid withdrawal please try again with a number greater than 0");
          }
            if (withdrawAmt >= 0 && Double.isInfinite(withdrawAmt) == false && (isSavings == false)) {  //Check if the double parsed is positive and is not infinity
              chequing.withdraw(withdrawAmt);        //Call the withdraw method from the chequing object with withdrawAmt
            }
            if (withdrawAmt >= 0 && Double.isInfinite(withdrawAmt) == false && (isSavings == true)) {  //Check if the double parsed is positive and is not infinity
              savings.withdraw(withdrawAmt);        //Call the withdraw method from the chequing object with withdrawAmt
            }
            withdrawTextField.setText("Amt to withdraw");       //Reset the withdraw text field with initial text
            if (isSavings==false){
            balanceLabel.setText("Current balance: " + currency.format(chequing.getBalance()));  //Update the balance
          }
          if (isSavings==true){
          balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));  //Update the balance
        }
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
