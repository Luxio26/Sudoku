package sudoku;

public class Cell
{
	public static final int DEFAULT_VALUE = 0;
	
	private int value;
	
	public Cell()
	{
		this.value = DEFAULT_VALUE;
	}
	
	public Cell(int aValue)
	{
		this.setValue(aValue);
	}

	public int getValue()
	{
		return this.value;
	}

	public void setValue(int value)
	{
		if(value >= 0 && value <= 9)
			this.value = value;
		else
			return;
	}
	
	@Override
	public String toString()
	{
		return ""+this.value;
	}
}