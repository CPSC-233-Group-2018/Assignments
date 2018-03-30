
public class Vowels {
	
	public static int numOfVowels(String str) {
		int vowels = 0;
		str = str.toLowerCase();
		if (str.length() == 0) { //base case
			return 0;
		}
		else {
			if (str.charAt(0) == 'a' || str.charAt(0) == 'e' || str.charAt(0) == 'i' 
				|| str.charAt(0) == 'o' || str.charAt(0) == 'u') {
				vowels++; //if index is a vowel, increase count
			}
			return vowels + numOfVowels(str.substring(1)); //calls recursive function of shorter string with first index cut off
		}
	}
	
}