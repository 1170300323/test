package P3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyGameTest {
	
	// the game to test
	private Game a;

	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
	
	// testing strategy
	// to test if input is correct
	// to test the color and type of initialized board
	// to test the color and type after take actions
	
	/**
	 * to test the chess game(including the action change and eat)
	 */
	@Test
	public void testChess() {
		a = new Game("chess");
		assertEquals(1, a.getType());
		a = new Game("ChEsS");
		assertEquals(1, a.getType());
		assertEquals("rook", a.getBoard().concretePiece(new Position(0, 0)).type());
		assertEquals("king", a.getBoard().concretePiece(new Position(0, 3)).type());
		assertEquals("pawn", a.getBoard().concretePiece(new Position(6, 7)).type());
		assertEquals("no piece", a.getBoard().concretePiece(new Position(4, 4)).type());
		assertEquals("white", a.getBoard().concretePiece(new Position(1, 5)).color());
		assertEquals("black", a.getBoard().concretePiece(new Position(7, 2)).color());
		assertEquals("no piece", a.getBoard().concretePiece(new Position(3, 3)).color());
		Action act = new Action(a.getBoard(), a.getBoard().concretePiece(new Position(0, 0)));
		act.change(new Position(3, 3));
		assertEquals("no piece", a.getBoard().concretePiece(new Position(0, 0)).type());
		assertEquals("rook", a.getBoard().concretePiece(new Position(3, 3)).type());
		act.setPiece(a.getBoard().concretePiece(new Position(0, 1)));
		act.eat(new Position(6, 7));
		assertEquals("knight", a.getBoard().concretePiece(new Position(6, 7)).type());
		assertEquals("white", a.getBoard().concretePiece(new Position(6, 7)).color());
	}
	
	/**
	 * to test the go game(including the action setToTarget and remove)
	 */
	@Test
	public void testGo() {
		Player p1 = new Player("A", 1);
		Player p2 = new Player("B", 2);
		a = new Game("go");
		assertEquals(2, a.getType());
		a = new Game("Go");
		assertEquals(2, a.getType());
		assertEquals("no piece", a.getBoard().concretePiece(new Position(18, 10)).color());
		Action act = new Action(a.getBoard(), a.getBoard().concretePiece(new Position(3, 3)));
		act.setToTarget(p1);
		assertEquals("black", a.getBoard().concretePiece(new Position(3, 3)).color());
		act.setPiece(a.getBoard().concretePiece(new Position(0, 1)));
		act.setToTarget(p2);
		assertEquals("white", a.getBoard().concretePiece(new Position(0, 1)).color());
		act.remove();
		assertEquals("no piece", a.getBoard().concretePiece(new Position(0, 1)).color());
	}
}
