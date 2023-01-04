```cs
using System;
using System.IO;
using System.Collections.Generic;

class Program
{
	private static readonly string FileName = "bigwally.txt";
	private static readonly string Wally = "wally";
	private static readonly int[][] Directions = new int[][]
	{
		new int[] {0, 1}, // Up
		new int[] {1, 1}, // Top Right
		new int[] {1, 0}, // Right
		new int[] {1, -1}, // Bottom Right
		new int[] {0, -1}, // Down (ha ha)
		new int[] {-1, -1}, // Bottom Left
		new int[] {-1, 0}, // Left
		new int[] {-1, 1}, // Top Left
	};

	private static List<List<char>> Grid;
	private static int WallyCount = 0; // Result

	private static void SearchWally(int startX, int startY)
	{
		if (startX < 0 || startY < 0) return;
		if (startY >= Grid.Count || startX >= Grid[startY].Count) return;

		// Doesn't start with w
		if (Grid[startY][startX] != Wally[0]) return;
		Console.WriteLine($"Found W at ({startX}, {startY})");

		foreach (int[] direction in Directions)
		{
			int currentX = startX;
			int currentY = startY;
			
			bool foundWally = true;
			for (int i = 1; i < Wally.Length; i++)
			{
				// Traverse in that direction
				currentX += direction[0];
				currentY += direction[1];

				if (currentX < 0 || currentY < 0) return;
				if (currentY >= Grid.Count || currentX >= Grid[currentY].Count) return;

				Console.WriteLine($"Looking for {Wally[i]} at ({currentX}, {currentY})");
				if (Grid[currentY][currentX] != Wally[i])
				{
					foundWally = false;
					break;
				}
			}

			if (foundWally)
			{
				WallyCount++;
			}
		}
	}

	static void Main(string[] args)
	{
		Grid = new List<List<char>>();
		string content = File.ReadAllText(FileName);
		
		foreach (string line in content.Split("\n"))
		{
			List<char> row = new List<char>(line.Length);
			for (int x = 0; x < line.Length; x++)
			{
				char c = line[x];
				row.Add(c);
			}

			Grid.Add(row);
		}

		for (int y = 0; y < Grid.Count; y++)
		{
			List<char> row = Grid[y];
			for (int x = 0; x < row.Count; x++)
			{
				SearchWally(x, y);
			}
		}

		Console.WriteLine($"Wally was found {WallyCount} time(s)");
	}
}
```