import java.util.Scanner;
import java.util.Random;

public class Guessing{
  /** Produces a randomly generated number and has user guess the number with
  feedback provided on whether their guess is too high, too low, or correct*/
  public static void main(String[] args){
    boolean is_Right = false;
    Random answer = new Random();
    int machineChoice = answer.nextInt(20)+1;
    System.out.println(machineChoice);
    while (is_Right == false){ //until the guess is right it will continue allowing you to guess
    Scanner keyboard = new Scanner(System.in);
    int personChoice = keyboard.nextInt();

  	 if (personChoice < machineChoice){
    	  System.out.println("Too Low");
  	}
    if (personChoice > machineChoice){
       System.out.println("Too High");
   }
   if (personChoice == machineChoice){
      System.out.println("That's the number I was thinking of! Well done.");
      is_Right = true;
  }
  }
  }
}
