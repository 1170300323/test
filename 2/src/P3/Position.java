package P3;

/**
 * for the chess game, x and y should be in the range of 0 to 7
 * for the go game, x and y should be in the range of 0 to 18
 */
public class Position {
	// x of the piece
	private int x;
	
	// y of the piece
	private int y;
	
	// constructor: to define a new position
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// get the x
	public int getX() {
		return x;
	}
	
	// get the y
	public int getY() {
		return y;
	}
	
}
