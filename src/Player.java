import java.util.ArrayList;

/*This class is used to create Player objects of the game and extends 'User'
 *It stores: Location, Owned Properties, Owned Railroads,Turns to wait in prison, 
 * Turns to wait in free parking and the bankruptcy of the player.
 */
public class Player extends User {

	private int location = 1;
	private ArrayList<String> properties = new ArrayList<String>();
	private int RRamount = 0;
	public int prisonTurns = 0;
	public int parkingTurns = 0;
	public boolean bankrupt = false;
	
	public Player(int balance,String name) {
		super(balance,name);
	}

	public int getLocation() {
		return location;
	}
	
	//This method sets the new location of the player according to the rolled dice
	//If the player crosses the go square gives him 200$
	public void setLocation(int dice,Player player,User banker) {
		this.location += dice;
		
		if(this.location > 40 || this.location<0) {
			this.location = this.location%40;
			player.setBalance(+200);
			banker.setBalance(-200);
		}
	}

	public String getProperties() {
		
		return String.join(",", this.properties);
	}

	public void addProperties(String property) {
		this.properties.add(property);
	}
	
	public void setRRamount() {
		this.RRamount += 1;	
	}
	
	public int getRRamount() {
		return RRamount;
	}
	
	//This method displays the current status of the game 
	//Returns the first output if Player 1 is winning and second output if Player 2 is winning
	public static String show(Player player1, Player player2, User banker) {
		
		if(player1.getBalanace()>player2.getBalanace()) {
			
			return 	"-----------------------------------------------------------------------\n"+
					"Player 1\t"+player1.getBalanace()+"\thave: "+player1.getProperties()+"\n"+
					"Player 2\t"+player2.getBalanace()+"\thave: "+player2.getProperties()+"\n"+
					"Banker\t"+banker.getBalanace()+"\n"+
					"Winner Player 1\n"+
					"-----------------------------------------------------------------------\n";
		}
		else  {
			return 	"-----------------------------------------------------------------------\n"+
					"Player 1\t"+player1.getBalanace()+"\thave: "+player1.getProperties()+"\n"+
					"Player 2\t"+player2.getBalanace()+"\thave: "+player2.getProperties()+"\n"+
					"Banker\t"+banker.getBalanace()+"\n"+
					"Winner Player 2\n"+
					"-----------------------------------------------------------------------\n";
		}	
	}
	
	//Sets the turns to wait in the prison
	public void setPrisonTurns() {
		this.prisonTurns = 3;
	}
	//Checks if the player is in prison and decreases turns to wait in the prison by 1
	public boolean inPrison() {
		
		if(this.prisonTurns>0) {
			this.prisonTurns -= 1;
			return true;
		}
		else {
			return false;
		}
	}
	
	//Sets the turns to wait in free parking
	public void setParkingTurns() {
		this.parkingTurns = 1;
	}
	//Checks if the player is in free parking and decreases turns to wait in free parking by 1
	public boolean inParking() {
		if(this.parkingTurns == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	//Updates the location of the player
	public void updateLocation(int location) {
		this.location = location;
	}
	//Buys the property for the player 
	//Sets the bankruptcy of the player as true if the player can't afford to buy the property
	public static void buyProperty(Square property, Player currentPlayer, User banker) {
		
		if (currentPlayer.getBalanace()<property.getCost()) {
			currentPlayer.bankrupt = true;
		}
		else {
			property.setOwner(currentPlayer.getName());
			currentPlayer.setBalance(-property.getCost());
			banker.setBalance(property.getCost());
			currentPlayer.addProperties(property.getName());
		}
	}
	
	//Checks the class of the property and pays the rent to other player
	//Sets the bankruptcy of the player as true if the player can't pay the rent
	public static void payRent(Player currentPlayer, Player otherPlayer, Square property,int dice) {
	
		
		if(property.getClass() == RailRoad.class) {
			currentPlayer.setBalance(-25*otherPlayer.getRRamount());
			otherPlayer.setBalance(+25*currentPlayer.getRRamount());	
			if(currentPlayer.getBalanace()<0) {
				currentPlayer.setBalance(+25*otherPlayer.getRRamount());
				otherPlayer.setBalance(-25*currentPlayer.getRRamount());
				currentPlayer.bankrupt = true;
			}
		}
		
		else if(property.getClass() == Company.class) {
			currentPlayer.setBalance(-4*dice);
			otherPlayer.setBalance(+4*dice);	
			if(currentPlayer.getBalanace()<0) {
				currentPlayer.setBalance(+4*dice);
				otherPlayer.setBalance(-4*dice);
				currentPlayer.bankrupt = true;
			}
		}
		
		else {
			currentPlayer.setBalance(-property.getRent());
			otherPlayer.setBalance(+property.getRent());	
			if(currentPlayer.getBalanace()<0) {
				currentPlayer.setBalance(+property.getRent());
				otherPlayer.setBalance(-property.getRent());
				currentPlayer.bankrupt = true;
			}
		}	
	}
}
