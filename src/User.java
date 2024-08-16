
//This class is used to create User objects of the game and for polymorphism
//It stores name and balance of the users
public class User {
	
	private String name;
	private int balance;
	
	public User(int balance, String name) {
		this.balance = balance;
		this.name = name;
	}

	public int getBalanace() {
		return balance;
	}

	public void setBalance(int money) {
		this.balance += money;
	}
	
	public String getName() {
		return name;
	}

}
