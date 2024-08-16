
//This class is used store and take necessary actions of the chance cards
public class ChanceList extends Action{
	
	//Storing the action of the cards and the current sequence of the cards as static
	String action;
	public static int sequence = 0;
	
	
	public ChanceList(String action) {
		this.action = action;
	}
	
//This method takes current player, other player and the banker as an argument
//Takes the necessary action according to the sequence of the cards and increases the sequence by 1
//If a player doesn't have enough money for the action then sets the players bankrupt attribute as true
	public static void ChanceAction(Player currentPlayer ,Player otherPlayer ,User banker) {

		if(sequence == 0) {
			currentPlayer.setBalance(+200);
			banker.setBalance(-200);
			currentPlayer.updateLocation(1);
		}
		else if(sequence == 1) {
			currentPlayer.updateLocation(27);
		}
		else if(sequence == 2) {
			currentPlayer.setLocation(-3,currentPlayer, banker);
		}
		else if(sequence == 3) {
			currentPlayer.setBalance(-15);
			banker.setBalance(+15);
			if (currentPlayer.getBalanace()<0) {
				currentPlayer.bankrupt = true;
				currentPlayer.setBalance(+15);
				banker.setBalance(-15);
			}
		}
		else if(sequence == 4) {
			currentPlayer.setBalance(+150);
			banker.setBalance(-150);
		}
		else if(sequence == 5) {
			currentPlayer.setBalance(+100);
			banker.setBalance(-100);	
		}
	
	sequence +=1;
	sequence = sequence%6;
	
	}


	@Override
	public String toString() {
		return " "+action;
	}
}
