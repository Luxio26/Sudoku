package sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Grid
{
	public static final int CELLS_IN_A_ROW = 9;
	public static final int CELLS_IN_A_COL = 9;
	
	private Cell[][] grid = null;/*{
			{new Cell(5), new Cell(9), new Cell(4), new Cell(7), new Cell(6), new Cell(1), new Cell(8), new Cell(2), new Cell(3)},
			{new Cell(8), new Cell(7), new Cell(3), new Cell(2), new Cell(9), new Cell(5), new Cell(6), new Cell(1), new Cell(4)},
			{new Cell(2), new Cell(1), new Cell(6), new Cell(8), new Cell(4), new Cell(3), new Cell(9), new Cell(5), new Cell(7)},
			{new Cell(4), new Cell(3), new Cell(2), new Cell(5), new Cell(1), new Cell(6), new Cell(7), new Cell(9), new Cell(8)},
			{new Cell(6), new Cell(8), new Cell(1), new Cell(9), new Cell(7), new Cell(4), new Cell(2), new Cell(3), new Cell(5)},
			{new Cell(9), new Cell(5), new Cell(7), new Cell(3), new Cell(2), new Cell(8), new Cell(4), new Cell(6), new Cell(1)},
			{new Cell(1), new Cell(6), new Cell(8), new Cell(4), new Cell(5), new Cell(2), new Cell(3), new Cell(7), new Cell(9)},
			{new Cell(3), new Cell(2), new Cell(9), new Cell(1), new Cell(8), new Cell(7), new Cell(5), new Cell(4), new Cell(6)},
			{new Cell(7), new Cell(4), new Cell(5), new Cell(6), new Cell(3), new Cell(9), new Cell(1), new Cell(8), new Cell(2)}
		};*/
	
	public Grid()
	{
		this.grid = new Cell[CELLS_IN_A_ROW][CELLS_IN_A_COL];
		this.gridInitialization();
	}
	
	public Grid(File file)
	{
		this.grid = new Cell[CELLS_IN_A_ROW][CELLS_IN_A_COL];
		
		try
		{
			gridInitialization(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void gridInitialization()
	{
		for(int row = 0 ; row < CELLS_IN_A_ROW ; row++) {
			for(int col = 0 ; col < CELLS_IN_A_COL ; col++) {
				this.grid[row][col] = new Cell();
			}
		}
	}
	
	public void gridInitialization(File file) throws IOException
	{
		FileInputStream in;
		try
		{
			in = new FileInputStream(file);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File not found !");
			return;
		}
		int col = 0, row = 0;
		while(true)
		{
			int car = in.read();
			if(car == -1)
				break;
			else if(car == 13)
				row += 1;
			else if(car != 10)
			{
				int tmp = car-48;
				this.grid[row][col%9] = new Cell(tmp);
				col++;
			}
		}
		
		in.close();
	}
	
	private boolean rowIsValid(int rowIndex)
	{
		boolean[] numbersPresent = new boolean[CELLS_IN_A_ROW];
		// Initialisation du tableau
		for(int i = 0 ; i < CELLS_IN_A_ROW ; i++)
			numbersPresent[i] = false;
		
		for(int colIndex = 0 ; colIndex < CELLS_IN_A_COL ; colIndex++)
		{
			boolean tmp = numbersPresent[this.grid[rowIndex][colIndex].getValue()-1];
			if(tmp == true)
				return false;
			tmp = true;
		}
		return true;
	}
	
	private boolean columnIsValid(int colIndex)
	{
		boolean[] numbersPresent = new boolean[CELLS_IN_A_COL];
		// Initialisation du tableau
		for(int i = 0 ; i < CELLS_IN_A_COL ; i++)
			numbersPresent[i] = false;
		
		for(int rowIndex = 0 ; rowIndex < CELLS_IN_A_ROW ; rowIndex++)
		{
			boolean tmp = numbersPresent[this.grid[rowIndex][colIndex].getValue()-1];
			if(tmp == true)
				return false;
			tmp = true;
		}
		return true;
	}
	
	/*
	 * 0 <= squareRowIndex < 3
	 * 0 <= squareColIndex < 3
	 */
	private boolean areSquaresValid()
	{
		for(int squareRowIndex = 0 ; squareRowIndex < (int) CELLS_IN_A_ROW/3 ; squareRowIndex++)
		{
			for(int squareColIndex = 0 ; squareColIndex < (int) CELLS_IN_A_COL/3 ; squareColIndex++)
			{
				if(this.isSquareValid(squareRowIndex, squareColIndex) == false)
					return false;
			}
		}
		
		return true;
	}
	
	private boolean isSquareValid(int squareRowIndex, int squareColIndex)
	{
		boolean[] numbersPresent = new boolean[CELLS_IN_A_COL];
		// Initialisation du tableau
		for(int i = 0 ; i < CELLS_IN_A_COL ; i++)
			numbersPresent[i] = false;
		
		for(int row = 0 ; row < (int) CELLS_IN_A_ROW/3 ; row++)
		{
			for(int col = 0 ; col < (int) CELLS_IN_A_COL/3 ; col++)
			{
				int num = this.grid[row+squareRowIndex*3][col+squareColIndex*3].getValue();
				if(numbersPresent[num-1] == true)
					return false;
				numbersPresent[num-1] = true;
			}
		}
		return true;
	}

	public boolean isValid()
	{
		//TODO: Vérifier les lignes
		for(int rowIndex = 0 ; rowIndex < CELLS_IN_A_ROW ; rowIndex++)
		{
			if(this.rowIsValid(rowIndex) == false)
				return false;
		}
		
		//TODO: Vérifier les colonnes
		for(int colIndex = 0 ; colIndex < CELLS_IN_A_COL ; colIndex++)
		{
			if(this.columnIsValid(colIndex) == false)
				return false;
		}
		
		//TODO: Vérifier les carrés de 3 par 3
		if(this.areSquaresValid() == false)
			return false;
		
		return true;
	}
	
	public void showGrid()
	{
		for(int i = 0 ; i < CELLS_IN_A_ROW ; i++)
		{
			for(int j = 0 ; j < CELLS_IN_A_COL ; j++)
			{
				System.out.print(this.grid[i][j].toString()+" ");
			}
			System.out.print("\n");
		}
	}
	
	public void setGrid()
	{
		Scanner userInput;
		boolean again = false;
		do
		{
			userInput = new Scanner(System.in);
			System.out.println("Sur quelle ligne se situe le chiffre ?");
			int ligne = userInput.nextInt();
			if(ligne < 1 || ligne > 9)
			{
				userInput.close();
				return;
			}

			System.out.println("Sur quelle colonne se situe le chiffre ?");
			int colonne = userInput.nextInt();
			if(colonne < 1 || colonne > 9)
			{
				userInput.close();
				return;
			}

			System.out.println("De quel chiffre s'agit-il ?");
			char chiffre = (char) userInput.nextInt();
			
			this.grid[ligne-1][colonne-1].setValue(chiffre);
			
			System.out.println("Vous avez encore des chiffres à mettre ?\n1.Oui\t2.Non");
			int choix = userInput.nextInt();
			
			switch(choix)
			{
				case 1:
					again = true;
					break;
				default:
					again = false;
					break;
			}
		userInput.close();
		}while(again);
	}
}