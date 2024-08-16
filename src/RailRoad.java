
//This class extends Square class and used to create RailRoad objects
//It can store, calculate, and return necessary attributes of the railroads in the game
public class RailRoad extends Square {
	
	private int cost;
	private String owner = "Banker";
	
	public RailRoad(int id,String name,int cost){
		super(id,name);
		this.cost = cost;	
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public String getOwner() {
		return this.owner;
	}
	
	@Override
	public void setOwner(String owner) {
		this.owner = owner;	
	}

	@Override
	public int getRent() {
		return 0;
	}

}
