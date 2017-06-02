package sudoku;

public class Square
{
	private Cell cells[][] = null;
	
	public Square()
	{
		this.cells = new Cell[3][3];
	}
	
	public void squareInitialization()
	{
		for(int i = 0 ; i < 3 ; i++)
		{
			for(int j = 0 ; j < 3 ; j++)
			{
				this.cells[i][j] = new Cell();
			}
		}
	}

	public Cell[][] getCells()
	{
		return this.cells;
	}
}