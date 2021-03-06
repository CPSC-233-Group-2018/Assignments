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
