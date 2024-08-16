
//This class is used store and take necessary actions of the community chance cards
public class CommunityChanceList extends Action{
	
	//Storing the action of the cards and the current sequence of the cards as static
	String action;
	public static int sequence = 0;


	public CommunityChanceList(String action) {
		this.action = action;
	}

//This method takes current player, other player and the banker as an argument
//Takes the necessary action according to the sequence of the cards and increases the sequence by 1
//If a player doesn't have enough money for the action then sets the players bankrupt attribute as true
	public static void CommunityAction(Player currentPlayer ,Player otherPlayer ,User banker) {
		
	
		if(sequence == 0) {
			currentPlayer.setBalance(+200);
			banker.setBalance(-200);
			currentPlayer.updateLocation(1);		
		}
		else if(sequence == 1) {
			currentPlayer.setBalance(+75);
			banker.setBalance(-75);
		}
		else if(sequence == 2) {
			currentPlayer.setBalance(-50);
			banker.setBalance(+50);
			if (currentPlayer.getBalanace()<0) {
				currentPlayer.bankrupt = true;
				currentPlayer.setBalance(+50);
				banker.setBalance(-50);
			}
		}
		else if(sequence == 3) {
			currentPlayer.setBalance(+10);
			otherPlayer.setBalance(-10);
			if(otherPlayer.getBalanace()<0) {
				otherPlayer.bankrupt = true;
				currentPlayer.setBalance(-10);
				otherPlayer.setBalance(+10);
			}
		}
		else if(sequence == 4) {
			currentPlayer.setBalance(+50);
			otherPlayer.setBalance(-50);
			if(otherPlayer.getBalanace()<0) {
				otherPlayer.bankrupt = true;
				currentPlayer.setBalance(-50);
				otherPlayer.setBalance(+50);
			}
		}
		else if(sequence == 5) {
			currentPlayer.setBalance(+20);
			banker.setBalance(-20);
		}
		else if(sequence == 6) {
			currentPlayer.setBalance(+100);
			banker.setBalance(-100);
		}
		else if(sequence == 7) {
			currentPlayer.setBalance(-100);
			banker.setBalance(+100);
			if (currentPlayer.getBalanace()<0) {
				currentPlayer.bankrupt = true;
				currentPlayer.setBalance(+100);
				banker.setBalance(-100);
			}
		}
		else if(sequence == 8) {
			currentPlayer.setBalance(-50);
			banker.setBalance(+50);
			if (currentPlayer.getBalanace()<0) {
				currentPlayer.bankrupt = true;
				currentPlayer.setBalance(+50);
				banker.setBalance(-50);
			}
		}
		else if(sequence == 9) {
			currentPlayer.setBalance(+100);
			banker.setBalance(-100);
		}
		else if(sequence == 10) {
			currentPlayer.setBalance(+50);
			banker.setBalance(-50);
		}
	sequence += 1;
	sequence = sequence&11;
		
	}

	@Override
	public String toString() {
		return " "+action;
	}

}
