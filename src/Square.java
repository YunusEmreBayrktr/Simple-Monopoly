
//This abstract class is used to create Square objects which are the properties in the game

public abstract class Square {

	private int ID;
	private String name;
	
	public Square(int ID ,String name) {
		this.ID = ID;
		this.name = name;
	}
	
	public int getID(){
		return ID;	
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract String getOwner();
	public abstract void setOwner(String string);
	public abstract int getCost();
	public abstract int getRent();

}
