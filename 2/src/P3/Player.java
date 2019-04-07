package P3;

public class Player {
	
	// the name of a player
	private String name;
	
	// the color of a player, 1:black, 2:white
	private int color;
	
	// constructor: to define a new player
	public Player(String name, int color){
		this.name = name;
		this.color = color;
	}
	
	// get the name
	public String getName() {
		return name;
	}
	
	// get the color
	public int getColor() {
		return color;
	}
	
	/**
	 * @return the color of the piece
	 */
	public String color() {
		String s = "";
		switch(color) {
		case 1:
			s += "black";
			break;
		case 2:
			s += "white";
			break;
		default:
			s += "no piece";
			break;
		}
		return s;
	}
	
}
