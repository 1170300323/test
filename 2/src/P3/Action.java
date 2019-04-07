package P3;

public class Action {
	
	//the board of game
	private Board b;
	
	// the operating piece, and the color of the piece should be same as the color of player
	private Piece p;
	
	// constructor: to define a new action
	public Action(Board b, Piece p) {
			this.b = b;
			this.p = p;
	}
	
	/**
	 * change a piece for action
	 * @param a new piece
	 */
	public void setPiece(Piece p) {
		this.p = p;
	}
	/**
	 * @return a new piece but same as p
	 */
	public Piece getPiece() {
		Piece c = new Piece(p.getPos());
		c.setColor(p.getColor());
		c.setType(p.getType());
		return c;
	}
	
	/**
	 * only the go game can use this function.
	 * put the action piece to its position
	 * @param player a
	 */
	public void setToTarget(Player a) {
		p = b.concretePiece(p.getPos());
		p.setColor(a.getColor());
	}
	
	/**
	 * only the chess game can use this function.
	 * change the position of a piece
	 * @param targeted position
	 */
	public void change(Position pos) {
		p = b.concretePiece(p.getPos());
		Piece c = b.concretePiece(pos);
		c.setType(p.getType());
		c.setColor(p.getColor());
		p.setType(0);
		p.setColor(0);
	}
	
	/**
	 * only the go game can use this function.
	 * remove the piece(maybe not the Piece p) at targeted position.
	 */
	public void remove() {
		p = b.concretePiece(p.getPos());
		p.setColor(0);
	}
	
	/**
	 * only the chess game can use this function.
	 * Piece p will eat the piece at Position pos.
	 * so remove the piece at Position pos and put Piece p at Position pos. 
	 * @param the position of the piece be eaten.
	 */
	public void eat(Position pos) {
		p = b.concretePiece(p.getPos());
		Piece c = b.concretePiece(pos);
		c.setColor(p.getColor());
		c.setType(p.getType());
		p.setType(0);
		p.setColor(0);
	}
	
}
