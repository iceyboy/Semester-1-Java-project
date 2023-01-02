package project_RyanKing_40372847;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Part2 {

	public static void main(String[] args) {

	}

	/**
	 * Converts the string into an array of characters, then sorts through each
	 * character seeing where it lies within the ASCII chart, applying the shift to
	 * the character.
	 * 
	 * @param s
	 * @param shift
	 * @return
	 */

	public static String caesarEncrypt(String s, int shift) {

		char[] stringAsCharArray = s.toCharArray();

		String resultString = "";

		for (int i = 0; i < stringAsCharArray.length; i++) {

			if (stringAsCharArray[i] < 91 && stringAsCharArray[i] > 64) {

				int withShift = stringAsCharArray[i] + shift;

				if (withShift < 91) {

					resultString = resultString + (char) withShift;

				}

				else {

					// Making sure it loops over z.

					int loop = withShift - 26;

					resultString = resultString + (char) loop;

				}

			} else if (stringAsCharArray[i] < 123 && stringAsCharArray[i] > 96) {

				int withShift = stringAsCharArray[i] + shift;

				if (withShift < 123) {

					resultString += (char) withShift;

				}

				else {

					// Making sure it loops over z.

					int loop = withShift - 26;

					resultString = resultString += (char) loop;

				}

			}

			else {

				// If the character is not a letter then no shift is applied.

				resultString = resultString + stringAsCharArray[i];
			}

		}

		return resultString;
	}

	/**
	 * Converts the string into an array of characters, then sorts through each
	 * character seeing where it lies within the ASCII chart, taking away the shift
	 * from the character.
	 * 
	 * @param s
	 * @param shift
	 * @return
	 */

	public static String caesarDecrypt(String s, int shift) {

		char[] stringAsCharArray = s.toCharArray();

		String resultString = "";

		for (int i = 0; i < stringAsCharArray.length; i++) {

			if (stringAsCharArray[i] < 91 && stringAsCharArray[i] > 64) {

				int withShift = stringAsCharArray[i] - shift;

				if (withShift > 64) {

					resultString = resultString + (char) withShift;

				}

				else {

					// Making sure it goes back from a to z.

					int loop = withShift + 26;

					resultString = resultString += (char) loop;

				}

			} else if (stringAsCharArray[i] < 123 && stringAsCharArray[i] > 96) {

				int withShift = stringAsCharArray[i] - shift;

				if (withShift > 96) {

					resultString += (char) withShift;

				}

				else {

					// Making sure it goes back from a to z.

					int loop = withShift + 26;

					resultString = resultString += (char) loop;

				}

			}

			else {

				resultString = resultString + stringAsCharArray[i];
			}

		}

		return resultString;

	}

	/**
	 * Split words up using REGEX, then looping through each word and testing if it
	 * matches to the given word. If it does the count increments.
	 * 
	 * @param fileName
	 * @param s
	 * @return
	 */

	public static int countWordOccurrences(String fileName, String s) {

		String sLowerCase = s.toLowerCase();

		int count = 0;

		try {

			// Reading the file.

			File file = new File(fileName);

			FileReader fr = new FileReader(file.getName());

			BufferedReader br = new BufferedReader(fr);

			String individualLine;

			String text = "";

			while ((individualLine = br.readLine()) != null) {

				text = text + individualLine + " ";

			}

			String stringLowerCase = text.toLowerCase();

			// Splitting the text up into each different word.

			String[] words = stringLowerCase.split("\\W+");

			for (int i = 0; i < words.length; i++) {

				if (words[i].equals(sLowerCase)) {

					count++;

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		return count;

	}

	/**
	 * Searches through text file, when it finds given word it replaces it with the
	 * new word using the .replaceAll function.
	 * 
	 * @param oldFileName
	 * @param newFileName
	 * @param oldWord
	 * @param newWord
	 */

	public static void replaceWordOccurrences(String oldFileName, String newFileName, String oldWord, String newWord) {

		try {

			// Reading the file.

			File file = new File(oldFileName);

			FileReader fr = new FileReader(file.getName());

			BufferedReader br = new BufferedReader(fr);

			String individualLine;

			String text = "";

			while ((individualLine = br.readLine()) != null) {

				text = text + individualLine + " ";

			}

			// Creating capitalized versions of each word.

			String oldWordCap = oldWord.substring(0, 1).toUpperCase() + oldWord.substring(1);

			String newWordCap = newWord.substring(0, 1).toUpperCase() + newWord.substring(1);

			// Replacing old occurrences with the new word. \\b ensures only the
			// alphabetical letters are changed, leaving any special characters.

			String replaced1 = text.replaceAll("\\b" + oldWord + "\\b", newWord);

			String replaced2 = replaced1.replaceAll("\\b" + oldWordCap + "\\b", newWordCap);

			// Creating new text file.

			File newFile = new File(newFileName);

			if (!newFile.exists()) {

				newFile.createNewFile();

			}

			FileWriter fw = new FileWriter(newFile, false);

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(replaced2);

			bw.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
