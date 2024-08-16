import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//Reading commands
		ArrayList<String> commands = readFile("command.txt");
		
		//Creating necessary objects by reading input files
		PropertyJsonReader propertyReader = new PropertyJsonReader();	
		ArrayList<Square> squares = new ArrayList<Square>(propertyReader.Read());
		ListJsonReader listReader = new ListJsonReader();
		ArrayList<Action> chanceActions = new ArrayList<Action>(listReader.ChanceRead());
		ArrayList<Action> communityChanceActions = new ArrayList<Action>(listReader.CommunityRead());
		
		
		//Adding the locations of 'chance' and 'community chest' into arrays
		ArrayList<Integer> chanceList = new ArrayList<Integer>();
		chanceList.add(8);
		chanceList.add(23);
		chanceList.add(37);
		
		ArrayList<Integer> communityChest = new ArrayList<Integer>();
		communityChest.add(3);
		communityChest.add(18);
		communityChest.add(34);
		
		//Creating 'banker' and 'player' objects
		User banker = new User(100000,"Banker");
		Player player1 = new Player(15000,"Player 1");
		Player player2 = new Player(15000,"Player 2");
		
		//Crating a string array to store the outputs
		ArrayList<String> output = new ArrayList<String>();
		

		//Looping through commands
		for(String command :commands) {
			
			//Initializing  2 Player objects
			Player currentPlayer = null;
			Player otherPlayer = null;
			
			//Displays the status if the command is 'show'
			if(command.equals("show()")) {
				output.add(Player.show(player1, player2, banker));
				continue;
			}
			
			//Assign the Player object to 'currentPlayer' and 'otherPlayer'
			else if(command.substring(0,8).equals("Player 1")){
				currentPlayer = player1;
				otherPlayer = player2;
			}
			else if(command.substring(0,8).equals("Player 2")) {
				currentPlayer = player2;
				otherPlayer = player1;
			}
			
			//Assign the rolled dice
			int dice = Integer.parseInt(command.substring(9));
			
			//Checking if current player is in prison or free parking. If true skips to the next iteration
			//If not true sets the location of the current player
			if(currentPlayer.inPrison()) {
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" in jail (count="+Math.abs(currentPlayer.prisonTurns-3)+")\n");
				continue;
			}
			else if(currentPlayer.inParking()) {
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" is in Free Parking \n");
				continue;
			}
			else {
				currentPlayer.setLocation(dice,currentPlayer,banker);
			}

				
			//Loops through all properties and looks if the player is located on a property
			for(Square property : squares) {
					
				if(currentPlayer.getLocation() == property.getID()) {
						
					//Checks the owner of the current property and takes the necessary actions
					if(property.getOwner().equals("Banker")) {
						Player.buyProperty(property, currentPlayer, banker);
						output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
								"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" bought "+property.getName()+"\n");
						
						if(property.getClass() == RailRoad.class) {
							currentPlayer.setRRamount();}
					}	
					else if (property.getOwner().equals(otherPlayer.getName())) {
						Player.payRent(currentPlayer, otherPlayer, property, dice);
						output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
								"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" paid rent for "+property.getName()+"\n");
						
					}	
					else {
						output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
								"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" has "+property.getName()+"\n");
						
						}
				}
			}
			
			//Checks if player is located on a chance square.
			//If yes takes the necessary action.
			//Takes different actions for the special case 'Advance to Leicester Square'
			if(chanceList.contains(currentPlayer.getLocation())) {
				
				if (ChanceList.sequence == 1 && (squares.get(14).getOwner()==otherPlayer.getName())) {
					ChanceList.ChanceAction(currentPlayer, otherPlayer, banker);
					Player.payRent(currentPlayer, otherPlayer, squares.get(14), dice);
					output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
							"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+chanceActions.get(ChanceList.sequence-1).toString()+
							" "+currentPlayer.getName()+" Paid rent for Leicester Square\n");
				}
				else if(ChanceList.sequence == 1 && (squares.get(14).getOwner()==banker.getName())) {
					ChanceList.ChanceAction(currentPlayer, otherPlayer, banker);
					Player.buyProperty(squares.get(14), currentPlayer, banker);
					output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
							"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+chanceActions.get(ChanceList.sequence-1).toString()+
							" "+currentPlayer.getName()+" bought Leicester Square\n");		
				}
				else if(ChanceList.sequence == 1 && (squares.get(14).getOwner()==banker.getName())) {
					ChanceList.ChanceAction(currentPlayer, otherPlayer, banker);
					output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" has Leicester Square\n");
				}	
				else{
					ChanceList.ChanceAction(currentPlayer, otherPlayer, banker);
					output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+chanceActions.get(ChanceList.sequence-1).toString()+"\n");
				}
			}
			
			//Checks if player is located on a community chest square
			//If yes takes the necessary actions
			else if(communityChest.contains(currentPlayer.getLocation())) {
				CommunityChanceList.CommunityAction(currentPlayer, otherPlayer, banker);
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+communityChanceActions.get(CommunityChanceList.sequence-1).toString()+"\n");
			}
			
			//Checks if player is located on a tax square
			//If yes takes the necessary actions
			else if(currentPlayer.getLocation()==5 || currentPlayer.getLocation()==39) {
				currentPlayer.setBalance(-100);
				banker.setBalance(+100);
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" paid Tax\n");
			}
			
			//Checks if player is located on jail square
			//If yes takes the necessary action
			else if(currentPlayer.getLocation()==11) {
				currentPlayer.setPrisonTurns();
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" went to jail\n");
			}
			
			//Checks if player is located on go to jail square
			//If yes takes the necessary action
			else if(currentPlayer.getLocation()==31) {
				currentPlayer.setPrisonTurns();
				currentPlayer.updateLocation(11);
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" went to jail\n");
			}
			
			//Checks if player is located on GO square
			//If yes takes the necessary action
			else if(currentPlayer.getLocation()==1) {
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" is in GO square\n");
			}
			
			//Checks if player is located on Free Parking square
			//If yes takes the necessary action
			else if(currentPlayer.getLocation()==21) {
				currentPlayer.setParkingTurns();
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" is in Free Parking\n");	
			}
			
			//Checks if player is bankrupt
			//If yes takes the necessary action
			if(currentPlayer.bankrupt) {
				output.remove(output.size()-1);
				output.add(currentPlayer.getName()+"\t"+dice+"\t"+currentPlayer.getLocation()+"\t"+player1.getBalanace()+
						"\t"+player2.getBalanace()+"\t"+currentPlayer.getName()+" goes bankrupt\n");
				break;
			}	
		}
		
		//Displays the game status at the end of the game
		output.add(Player.show(player1, player2, banker));
		
		//Writes the created output on a txt file
		writeFile("output.txt",output);
			
	}
		
	
	
	//This method is used to read a txt file
	//Takes the name of the file as argument
	//Returns the file content as String in an ArrayList
	public static ArrayList<String> readFile(String fileName ) throws IOException {
		
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
		
			ArrayList<String> data = new ArrayList<>();
			String line;
			while((line = br.readLine()) != null) {
				data.add(line);
			}
			br.close();
			return data;
	}
	
	//This method is used to create and write on a txt file
	//Takes the name and the content of the file as an ArrayList
	public static void writeFile(String fileName , ArrayList<String> content) throws IOException {
		
		File monitoring = new File(fileName);
		FileWriter output = new FileWriter(monitoring);
		
		for(String i : content) {
		output.write(i);
		}
		output.close();
	}

	
}
