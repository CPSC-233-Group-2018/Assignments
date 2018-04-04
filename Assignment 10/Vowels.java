/**
	* Tutorial 6 Team 3:
  * Seth Campbell, Kieran Woods, Rulan Lu, William Chan
  * -team assignment 10, Apr2,2018-
	*			A method that recursively finds the number of vowels in a given string. Explainations
	*			of the recuersive components is provided.
	*/
public class Vowels {

	/**
		* Finds the number of vowels in an inputted string using recursion.
		*	@param str	String to search
		*	@return 		An int of the number of vowels found in total
		*/
	public static int numOfVowels(String str) {
		int vowels = 0;
		str = str.toLowerCase();


		//Terminating Conditon:
		//	The base case or terminating condition will always be reached because the
		//	recursive call is always truncating the string by one, so it will always decreasing
		//	till it reaches 0 length, at which it terminates.
		if (str.length() == 0) { //base case
			return 0;
		}
		else {
			if (str.charAt(0) == 'a' || str.charAt(0) == 'e' || str.charAt(0) == 'i'
				|| str.charAt(0) == 'o' || str.charAt(0) == 'u') {
				vowels++; //if index is a vowel, increase count
			}
			//Recursive Call:
			//	Calling the method again, but with a shorter string means it is approaching the 0
			//	length termination condition. The method is essentially processing one letter at a
			//	time from left to right till nothing is left.
			return vowels + numOfVowels(str.substring(1)); //calls recursive function of shorter string with first index cut off

			//Using the Recursive Call:
			//	The recursive call is using its own method to solve smaller parts of the problem.
			//	Each recursive call to a subsection computes a correct vowel count. Then, through
			//	the addition operator in the return statement, we are able to sum all these results
			//	and return the final total.
		}
	}

} //end of class
