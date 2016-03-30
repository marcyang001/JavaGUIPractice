package client;
public class Seat{
	private int aColumn;
	private int name;
	private int row;

	public Seat(int column, int name){
		this.aColumn = column;
		this.name = name;
		
	}
	public Seat( int row, int column, int name){
		this.aColumn = column;
		this.name = name;
		this.row = row;
		
	}
	
	public int getColumn(){
		return this.aColumn;
	}

	public int getName() {
		return this.name;	
	}
	
	public int getRow() {
		return this.row;
	}
}