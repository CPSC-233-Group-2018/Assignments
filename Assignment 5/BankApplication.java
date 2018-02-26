import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.text.*;

public class BankApplication extends Application {
  private Customer customer = new Customer("Charles Brown", 123456);
  private SavingsAccount savings = new SavingsAccount(customer, 150.00);

  @Override
  public void start(Stage primaryStage) throws Exception {
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    VBox vbox = new VBox();
    Label customerNameLabel = new Label("Customer name: " + customer.getName());
    Label customerIDLabel = new Label("Account ID: " + customer.getID());
    Label balanceLabel = new Label("Current balance: " + currency.format(savings.getBalance()));
    vbox.getChildren().add(customerNameLabel);
    vbox.getChildren().add(customerIDLabel);
    HBox hbox = new HBox();
    TextField depositTextField = new TextField("Amt to deposit");
    TextField withdrawTextField = new TextField("Amt to withdraw");
    hbox.getChildren().add(depositTextField);
    hbox.getChildren().add(withdrawTextField);
    vbox.getChildren().add(hbox);
    Button executeButton = new Button("Execute");
    vbox.getChildren().add(executeButton);
    vbox.getChildren().add(balanceLabel);
    executeButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          try {
            double depositAmt = Double.parseDouble(depositTextField.getText());
            if (depositAmt >= 0 && Double.isInfinite(depositAmt) == false) {
              savings.deposit(depositAmt);
            }
            depositTextField.setText("Amt to deposit");
            balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));
          } catch (NumberFormatException e) {
            depositTextField.setText("Amt to deposit");
            System.out.println("Error with number format. ");
            System.err.println("NumberFormatException: " + e.getMessage());
          } try {
            double withdrawAmt = Double.parseDouble(withdrawTextField.getText());
            if (withdrawAmt >= 0 && Double.isInfinite(withdrawAmt) == false) {
              savings.withdraw(withdrawAmt);
            }
            withdrawTextField.setText("Amt to withdraw");
            balanceLabel.setText("Current balance: " + currency.format(savings.getBalance()));
          } catch (NumberFormatException e) {
            withdrawTextField.setText("Amt to withdraw");
            System.out.println("Error with number format. ");
            System.err.println("NumberFormatException: " + e.getMessage());
          }
        }
      });

    Scene scene = new Scene(vbox, 400, 300);
    primaryStage.setTitle("Bank application");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args)
  {
     launch(args);
  }

}
