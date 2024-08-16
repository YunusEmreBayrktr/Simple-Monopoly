
//This class extends Square class and used to create Land objects
//It can store, calculate, and return necessary attributes of the lands in the game
public class Land extends Square {
	
	private int cost;
	private int rent;
	private String owner = "Banker";
	
	public Land(int id,String name,int cost){
		super(id ,name);
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

	public int getRent() {
		
		if(cost<2000) {
			this.rent = cost*40/100;
			}
			else if(cost<3000) {
				this.rent = cost*30/100;
			}
			else if(cost<4000) {
				this.rent = cost*35/100;
			}
		return this.rent;
	}





}

