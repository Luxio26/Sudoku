package sudoku;

import java.io.File;

public class Main
{
	public static void main(String[] args)
	{
		
		Grid aGrille = new Grid(new File("grid.txt"));
		
		//aGrille.setGrid(saisieUtilisateur);
		aGrille.showGrid();
		System.out.println(aGrille.isValid());
	}
}