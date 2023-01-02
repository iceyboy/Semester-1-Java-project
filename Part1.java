package project_RyanKing_40372847;

public class Part1 {

	public static void main(String[] args) {

	}

	/**
	 * Comparing char to ASCII values for alphabetical characters. If char is within
	 * values for alphabetical characters the method returns true, if not it returns
	 * false.
	 * 
	 * @param ch
	 * @return
	 */

	public static boolean isLetter(char ch) {

		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {

			return true;

		} else {

			return false;

		}

	}

	/**
	 * Checking if the first character is equal to the second character. If it is
	 * the method returns true, if not it returns false.
	 * 
	 * @param ch
	 * @return
	 */

	public static boolean isSameLetterIgnoreCase(char ch1, char ch2) {

		/*
		 * Checking if any of the input characters are not letters, if they are not the
		 * method returns false.
		 */

		if ((isLetter(ch1) == false) || (isLetter(ch2) == false)) {

			return false;

		} else {

			// Converting characters to strings.

			String firstLetter = String.valueOf(ch1);

			String secondLetter = String.valueOf(ch2);

			if ((firstLetter.equalsIgnoreCase(secondLetter)) == true) {

				return true;

			} else {

				return false;

			}

		}

	}

	/**
	 * This is a method for checking if a string contains anything that isn't an
	 * alphabetic character. This method was created for use in the longestWord
	 * method.
	 * 
	 * @param s
	 * @return
	 */

	public static boolean containsNonLetter(String s) {

		boolean nonLetter = false;

		/*
		 * Iterating through the string and checking each character, passing the
		 * character into the previous isLetter method. If it is a letter then nonLetter
		 * is set to false, if it is not a letter nonLetter is set to true.
		 */

		for (int i = 0; i < s.length(); i++) {

			if (isLetter(s.charAt(i)) == true) {

				nonLetter = false;

			} else {

				nonLetter = true;

			}

			if (nonLetter == true) {

				break;

			}

		}

		return nonLetter;

	}

	/**
	 * Iterates through the array, checking if the word is longer than the previous
	 * longest word and doesn't contain any non alphabetical characters. If it is
	 * longer then it becomes the new longest word.
	 * 
	 * @param s
	 * @return
	 */

	public static String longestWord(String s) {

		/*
		 * Converting the string into an array of words, separated when a non
		 * alphabetical character is found (\\W+).
		 */

		String[] arrayOfWords = s.split("\\W+");

		String longestWord = "";

		for (int i = 0; i < arrayOfWords.length; i++) {

			if ((arrayOfWords[i].length() > longestWord.length() && (containsNonLetter(arrayOfWords[i]) == false))) {

				longestWord = arrayOfWords[i];

			}

		}

		return longestWord;

	}

	/**
	 * Iterates through string and checks if letter has been accounted for yet.
	 * .indexOf() returns the first instance of the character, it returns -1 if the
	 * character is not found. Thus it can be used to check if the letter is not in
	 * the string holding the letters.
	 * 
	 * @param s
	 * @return
	 */

	public static int countDifferentLetters(String s) {

		String string = s.toLowerCase();

		// Creates a string to hold each new letter in.

		String letters = "";

		for (int i = 0; i < string.length(); i++) {

			if ((letters.indexOf(string.charAt(i)) == -1) && (isLetter(string.charAt(i)) == true)) {

				letters = letters + string.charAt(i);

			}

		}

		return letters.length();

	}

	/**
	 * Increment the character count and then compare it to the final count. If the
	 * character count is bigger or equal to the final count it becomes the new
	 * final count.
	 * 
	 * @param s
	 * @return
	 */

	public static char mostCommonLetter(String s) {

		String string = s.toLowerCase();

		char mostCommon = ' ';

		int finalCount = 0;

		// Since the input is made of ASCII characters we can set the array size to 128.

		int[] charCount = new int[128];

		for (int i = string.length() - 1; i >= 0; i--) {

			char ch = string.charAt(i);

			if (++charCount[ch] >= finalCount) {

				finalCount = charCount[ch];

				mostCommon = ch;

			}

		}

		return mostCommon;

	}

	/**
	 * Iterate through array of words and check if it is equal to any of the other
	 * words.
	 * 
	 * @param s
	 * @return
	 */

	public static int countUniqueWords(String s) {

		String string = s.toLowerCase();

		// Creating an array of all individual words by splitting the words by non
		// alphabetical characters.

		String[] words = string.split("\\W+");

		// Setting the count to the total number of words.

		int countOfUniqueWords = words.length;

		for (int i = 0; i < words.length; i++) {

			for (int j = i + 1; j < words.length; j++) {

				if (words[i].equals(words[j])) {

					// When a non unique word is found it subtracts from the count and breaks out of
					// the loop.

					countOfUniqueWords--;

					break;

				}

			}

		}

		return countOfUniqueWords;

	}

}
