package P3;

public class Piece {
	
	// black or white means two players,0:no piece, 1:black, 2:white
	// use this rep to express statement
	private int color;
	
	// the position of the piece
	private Position pos;
	
	// only the chess game uses the rep type and it should be in 0-6
	// type 0:no piece, 1:king, 2:queen, 3:rook(³µ),  4:knight(Âí),  5:bishop(Ïó),  6:pawn(±ø)
	private int type;
	
	// constructor: to set a new piece
	public Piece(Position pos) {
		this.pos = pos;
		color = 0;
		type = 0;
	}
	
	// set the color
	public void setColor(int color) {
		this.color = color;
	}
	
	// get the color
	public int getColor() {
		return color;
	}
	
	// set the type
	public void setType(int type) {
		this.type = type;
	}
	
	// get the type
	public int getType() {
		return type;
	}
	
	// get the pos
	public Position getPos() {
		return pos;
	}
	
	/**
	 * @return concrete color of the piece
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
	
	/**
	 * @return concrete type of the piece
	 */
	public String type() {
		String s = "";
		switch(type) {
		case 1:
			s += "king";
			break;
		case 2:
			s += "queen";
			break;
		case 3:
			s += "rook";
			break;
		case 4:
			s += "knight";
			break;
		case 5:
			s += "bishop";
			break;
		case 6:
			s += "pawn";
			break;
		default:
			s += "no piece";
			break;
		}
		return s;
	}
}

