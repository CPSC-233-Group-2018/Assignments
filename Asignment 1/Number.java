import java.util.Random; //imports utilities
import java.util.Scanner;
import java.util.InputMismatchException;

public class Number {
	/**
	 * randomly generates a number and asks user to guess said number.
	 player wins once they guess the number correctly.
	 Team 3: Lecture 6: Kieran, Rulan, Seth, William
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in); //creates keyboard for input
		Random rand = new Random(); //generates random number between 1 and 20
		int num = rand.nextInt(20) + 1; //variable for generated number
		int playerGuess; //variable for player's guess
		boolean correct = false; //boolean expression used to end while loop once guess is correct
		System.out.println("I am thinking of a number between 1 and 20.");
		System.out.println("The machine number is: " + num); //Prints the machine guess for easy testing
		do {
		    System.out.println("\nWhat is your guess: ");
	    	try {
	    	    playerGuess = keyboard.nextInt();  //initiates variable for guesses
	      	    if (playerGuess > 20 || playerGuess < 1) { //if player's guess is out of range
	      	        System.out.println("Please enter a number between 1 and 20.");
	       	    } else if (playerGuess < num) { //if player guess is too low
	    	        System.out.println("Too low.");
	            } else if (playerGuess > num) { //if player guess is too high
	                System.out.println("Too high.");
	            } else { //correct guess
	                System.out.println("Congratulations! You are correct!");
	                keyboard.close(); //closes scanner
	                correct = true; //exits while loop
	        	}
	    	} catch (InputMismatchException e) { //if player does not enter an integer
	    	    System.out.println("Please enter an integer.");
	    	    keyboard.next(); //moves onto next keyboard input
	    	}
		} while (correct == false); //moves onto next iteration in while loop
	}
}
