package project_RyanKing_40372847;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part3 {

	public static char[][] gridOfCharacters;

	public static int[] down = { 0, 1 };

	public static int[] rightUp = { 1, -1 };

	public static int[] rightDown = { 1, 1 };

	public static int countDown = 0;

	public static int countDiagonal = 0;

	public static int lineDown;

	public static int lineDiagonal;

	public static void main(String[] args) {

	}

	/**
	 * This method reads in the text from the file and splits it into lines, it then
	 * loops through each line of the array and puts them into an array of
	 * characters. It then uses this array of characters to call in the search
	 * method.
	 * 
	 * @param fileName
	 * @param wally
	 * @return
	 */

	public static int wheresWally1(String fileName, String wally) {

		try {

			// Reading the file.

			File file = new File(fileName);

			FileReader fr = new FileReader(file.getName());

			BufferedReader br = new BufferedReader(fr);

			String individualLine;

			String text = "";

			while ((individualLine = br.readLine()) != null) {

				text = text + individualLine + "\n";

			}

			text = text.toLowerCase();

			String[] lines = text.split("\\n");

			gridOfCharacters = new char[lines.length][];

			for (int i = 0; i < lines.length; i++) {

				char[] array = new char[lines[i].length()];

				for (int j = 0; j < lines[i].length(); j++) {

					array[j] = lines[i].charAt(j);

				}

				gridOfCharacters[i] = array;

			}

			for (int y = 0; y < gridOfCharacters.length; y++) {

				char[] row = gridOfCharacters[y];

				for (int x = 0; x < row.length; x++) {

					searchWally(x, y, wally, down);

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		System.out.println(lineDown);

		return countDown;

	}

	/**
	 * This is the search function that makes the two main methods work. It takes in
	 * a starting X and Y position as well as the direction to search in. Searching
	 * through each character to find the given word. Incrementing the counter every
	 * time it finds the word.
	 * 
	 * @param startX
	 * @param startY
	 * @param wally
	 * @param direction
	 */

	public static void searchWally(int startX, int startY, String wally, int[] direction) {

		if (startX < 0 || startY < 0) {

			return;

		}

		if (startY >= gridOfCharacters.length || startX >= gridOfCharacters[startY].length) {

			return;

		}

		if (gridOfCharacters[startY][startX] != wally.charAt(0)) {

			return;

		}

		int currentX = startX;

		int currentY = startY;

		boolean foundWally = true;

		for (int i = 1; i < wally.length(); i++) {

			// Moving to the next character in certain direction.

			currentX += direction[0];

			currentY += direction[1];

			if (currentX < 0 || currentY < 0) {

				return;

			}

			if (currentY >= gridOfCharacters.length || currentX >= gridOfCharacters[currentY].length) {

				return;

			}

			if (gridOfCharacters[currentY][currentX] != wally.charAt(i)) {

				foundWally = false;

				break;

			}

			/*
			 * These three if statements are here to determine which counter to increment
			 * based on the direction given. When Wally is found it makes the line of the
			 * first letter the new line, so that after the search function is finished the
			 * line will be the correct line to print out for the last occurence's first
			 * letter.
			 */

			if ((foundWally) && (direction == down)) {

				countDown++;

				lineDown = startY + 1;

				return;
			}

			if ((foundWally) && (direction == rightDown)) {

				countDiagonal++;

				lineDiagonal = startY + 1;

				return;

			}

			if ((foundWally) && (direction == rightUp)) {

				countDiagonal++;

				lineDiagonal = startY + 1;

				return;
			}

		}

	}

	/**
	 * This method reads in the text from the file and splits it into lines, it then
	 * loops through each line of the array and puts them into an array of
	 * characters. It then uses this array of characters to call in the search
	 * method.
	 * 
	 * @param fileName
	 * @param wally
	 * @return
	 */

	public static int wheresWally2(String fileName, String wally) {

		try {

			// Reading the file.

			File file = new File(fileName);

			FileReader fr = new FileReader(file.getName());

			BufferedReader br = new BufferedReader(fr);

			String individualLine;

			String text = "";

			while ((individualLine = br.readLine()) != null) {

				text = text + individualLine + "\n";

			}

			text = text.toLowerCase();

			String[] lines = text.split("\\n");

			gridOfCharacters = new char[lines.length][];

			for (int i = 0; i < lines.length; i++) {

				char[] array = new char[lines[i].length()];

				for (int j = 0; j < lines[i].length(); j++) {

					array[j] = lines[i].charAt(j);

				}

				gridOfCharacters[i] = array;

			}

			// As we are searching in two directions we have to call the search method
			// twice.

			for (int y = 0; y < gridOfCharacters.length; y++) {

				char[] row = gridOfCharacters[y];

				for (int x = 0; x < row.length; x++) {

					searchWally(x, y, wally, rightUp);

					searchWally(x, y, wally, rightDown);

				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		System.out.println(lineDiagonal);

		return countDiagonal;

	}

}
