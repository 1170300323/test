package P3;

import java.util.Scanner;

public class MyChessAndGoGame{
	
	// chess or go
	private static Game a;
	
	// a Scanner for the game
	private static Scanner in = new Scanner(System.in);
	
	// to judge Player 1 or Player 2's turn
	private static int num = 1;
	
	// c is the String for Scanner, history is to express the history of game
	private static String c, history = "";
	
	// player 1 and player 2
	private static Player[] pl;
	
	/**
	 * the whole game is in function main
	 * @param args 
	 */
	public static void main(String[] args) {
		pl = new Player[3];
		System.out.println("we provide you two kinds of game, chess and go");
		System.out.println("I do not think you want to enter other kinds of game");
		System.out.println("please choose the type of game you want to play");
		System.out.println("enter chess to play chess, enter go to play go:");
		c = in.nextLine();
		a = new Game(c);
		System.out.println("please input the name of two players:");
		System.out.println("Player 1:(which will be black and action firstly)");
		c = in.nextLine();
		pl[1] = new Player(c, 1);
		System.out.println("Player 2:(which will be white and action secondly)");
		c = in.nextLine();
		pl[2] = new Player(c, 2);
		System.out.println("ok, now game start!");
		String end = "";
		help(a.getType());
		while(end != "end") {
			System.out.printf("Player %d, please choose your action(A/B/C/D/skip/end/help):\n", num);
			c = in.nextLine();
			int x, y;
			Position pos;
			Action act;
			if(a.getType() == 1) {
				switch(c) {
				case "A":
				case "a": 
					act = actionMyChess();
					System.out.println("please enter the new position:");
					x = in.nextInt();
					y = in.nextInt();
					pos = checkRange(x, y);
					pos = checkFreePiece(pos.getX(), pos.getY());
					a.getBoard().setIcon(act.getPiece().getPos(), pos);
					act.change(pos);
					history += "to (" + pos.getX() + ", " + pos.getY() + ")";
					break;
				case "B":
				case "b":
					act = actionMyChess();
					System.out.println("please enter the position of your opponent's piece");
					x = in.nextInt();
					y = in.nextInt();
					pos = checkRange(x, y);
					pos = checkOpponentPiece(pos.getX(), pos.getY());
					a.getBoard().setIconEat(act.getPiece().getPos(), pos);
					act.eat(pos);
					history += "eats the piece" + a.getBoard().concretePiece(pos).color() + " ";
					history += a.getBoard().concretePiece(pos).type() + " with a position (" + pos.getX() + ", " + pos.getY() + ") ";
					break;
				case "C":
				case "c":
					query();
					continue;
				case "d":
				case "D":
					System.out.println("the number of piece in the board is " + (count(8, 1) + count(8, 2)));
					continue;
				case "skip":
					history += "Player " + num + ":" + pl[num].getName() + "\nskips his/her turn";
					break;
				case "end":
					history += "Player " + num + ":" + pl[num].getName() + "\nends the game";
					end = "end";
					break;
				default:
					help(1);
					continue;
				}
			}
			else {
				switch(c) {
				case "a":
				case "A":
					System.out.println("please enter a free position of the board");
					System.out.println("please enter like 2 2, which means the coordinate is (2, 2)");
					System.out.println("the number you enter should be in the range of 0-18");
					x = in.nextInt();
					y = in.nextInt();
					pos = checkRange(x, y);
					pos = checkFreePiece(pos.getX(), pos.getY());
					act = new Action(a.getBoard(), new Piece(pos));
					if(num == 1)
						a.getBoard().getGo().getGp().setBlack(pos);
					else
						a.getBoard().getGo().getGp().setWhite(pos);
					act.setToTarget(pl[num]);
					history += "Player " + num + ":" + pl[num].getName();
					history += "\nputs a " + pl[num].color() + " piece at the position (" + pos.getX() + ", " + pos.getY() + ") ";
					break;
				case "b":
				case "B":
					if(count(19, (num == 1) ? 2 : 1) == 0) {
						System.out.println("sorry, there is no pieces of your opponent in the board,please try another operation.");
						continue;
					}
					System.out.println("please enter the position of your opponent's piece");
					System.out.println("please enter like 2 2, which means the coordinate is (2, 2)");
					System.out.println("the number you enter should be in the range of 0-18");
					x = in.nextInt();
					y = in.nextInt();
					pos = checkRange(x, y);
					pos = checkOpponentPiece(pos.getX(), pos.getY());
					act = new Action(a.getBoard(), new Piece(pos));
					if(num == 1)
						a.getBoard().getGo().getGp().removeWhite(pos);
					else
						a.getBoard().getGo().getGp().removeBlack(pos);
					act.remove();
					history += "Player " + num + ":" + pl[num].getName();
					history += "\nremoves a " + pl[(num == 1) ? 2 : 1].color() + " piece at the position (" + pos.getX() + ", " + pos.getY() + ") ";
					break;
				case "c":
				case "C":
					query();
					continue;
				case "d":
				case "D":
					System.out.println("the number of piece in the board is " + (count(19, 1) + count(19, 2)));
					continue;
				case "skip":
					history += "Player " + num + ":" + pl[num].getName() + "\nskips his/her turn";
					break;
				case "end":
					history += "Player " + num + ":" + pl[num].getName() + "\nends the game";
					end = "end";
					break;
				default:
					help(2);
					continue;
				}
			}
			if(num == 1)
				num = 2;
			else
				num = 1;
			history += "\n";
			if(c.equalsIgnoreCase("a") || c.equalsIgnoreCase("b"))
				in.nextLine();
		}
		System.out.println("history:\n" + history);
		System.exit(0);
	}
	/**
	 * print the menu for users
	 * @param 1 is chess, 2 is go
	 */
	public static void help(int a) {
		if(a == 1) {
			System.out.println("A: move a piece to a new position.(only operation A and B will change the player)");
			System.out.println("B: eat one of your opponent's pieces.(only operation A and B will change the player)");
		}
		else {
			System.out.println("A: set a free piece to a free position of the board(only operation A and B will change the player)");
			System.out.println("B: remove one of your opponent's pieces from the board(only operation A and B will change the player)");
		}
		System.out.println("C: query the occupancy of a position.");
		System.out.println("D: compute the number of piece in board.");
		System.out.println("skip: skip your turn");
		System.out.println("end: end the game");
		System.out.println("help: print this help menu");
	}
	
	/**
	 * check the range of position is legal or not
	 * @param x
	 * @param y
	 * @return the legal position
	 */
	public static Position checkRange(int x, int y) {
		if(a.getType() == 1) {
			while(x >= 8 || y >= 8 || x < 0 || y < 0) {
				System.out.println("Oh, no! You input a number out of range. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
			}
		}
		else {
			while(x >= 19 || y >= 19 || x < 0 || y < 0) {
				System.out.println("Oh, no! You input a number out of range. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
			}
		}
		return new Position(x, y);
	}
	
	/**
	 * check the piece is yours or not
	 * @param x
	 * @param y
	 * @return the legal position
	 */
	public static Position checkMyPiece(int x, int y) {
		Position pos = new Position(x, y);
		boolean flag = true;
		while(flag) {
			flag = false;
			if(a.getBoard().concretePiece(pos).getColor() == 0) {
				System.out.println("Oh, no! There is no piece in this position. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
				flag = true;
			}
			else if(a.getBoard().concretePiece(pos).getColor() != num) {
				System.out.println("Oh, no! There is your opponent's piece. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
				flag = true;
			}
			if(flag)
				pos = new Position(x, y);
		}
		return pos;
	}
	
	/**
	 * check the piece is your opponent's or not
	 * @param x
	 * @param y
	 * @return the legal position
	 */
	public static Position checkOpponentPiece(int x, int y) {
		Position pos = new Position(x, y);
		boolean flag = true;
		while(flag) {
			flag = false;
			if(a.getBoard().concretePiece(pos).getColor() == 0) {
				System.out.println("Oh, no! There is no piece in this position. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
				flag = true;
			}
			else if(a.getBoard().concretePiece(pos).getColor() == num) {
				System.out.println("Oh, no! There is your piece. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
				flag = true;
			}
			if(flag)
				pos = new Position(x, y);
		}
		return pos;
	}
	
	/**
	 * check the piece is free or not
	 * @param x
	 * @param y
	 * @return the legal position
	 */
	public static Position checkFreePiece(int x, int y) {
		Position pos = new Position(x, y);
		boolean flag = true;
		while(flag) {
			flag = false;
			if(a.getBoard().concretePiece(pos).getColor() != 0) {
				System.out.println("Oh, no! There is a piece in this position. Please input x and y again");
				x = in.nextInt();
				y = in.nextInt();
				flag = true;
			}
			if(flag)
				pos = new Position(x, y);
		}
		return pos;
	}
	
	/**
	 * to query the piece of the targeted position
	 */
	public static void query() {
		System.out.println("please enter the position of a piece:");
		int x = in.nextInt();
		int y = in.nextInt();
		Position pos = checkRange(x, y);
		int t = a.getBoard().concretePiece(pos).getColor();
		if(a.getType() == 1) {
			if(t == 1)
				System.out.print("Player 1's black ");
			else if(t == 2)
				System.out.print("Player 2's white ");
			System.out.println(a.getBoard().concretePiece(pos).type());
		}
		else {
			if(t == 1)
				System.out.println("Player 1's black piece");
			else if(t == 2)
				System.out.println("Player 2's white piece");
			else
				System.out.println("no piece");
		}
		in.nextLine();
	}
	
	/**
	 * to count pieces with a kind of color
	 * @param t
	 * @param color
	 * @return counting numbers
	 */
	public static int count(int t, int color) {
		int cnt = 0;
		for(int i = 0; i < t; i++) {
			for(int j = 0; j < t; j++) {
				Position pos = new Position(i, j);
				if(a.getBoard().concretePiece(pos).getColor() == color)
					cnt++;
			}
		}
		return cnt;
	}
	
	/**
	 * to operate your piece in chess game
	 * @return the action with your piece
	 */
	public static Action actionMyChess() {
		System.out.println("please enter the position of your piece:");
		System.out.println("please enter like 2 2, which means the coordinate is (2, 2)");
		System.out.println("the number you enter should be in the range of 0-7");
		int x = in.nextInt();
		int y = in.nextInt();
		Position pos = checkRange(x, y);
		pos = checkMyPiece(pos.getX(), pos.getY());
		Piece p = new Piece(pos);
		p.setColor(num);
		if(c.equalsIgnoreCase("a")) {
			history += "Player " + num + ":" + pl[num].getName();
			history += "\nchange: the piece " + a.getBoard().concretePiece(pos).color() + " ";
			history += a.getBoard().concretePiece(pos).type() + " changes from (" + pos.getX() + ", " + pos.getY() + ") ";
		}
		else {
			history += "Player " + num + ":" + pl[num].getName();
			history += "\neat: the piece" + a.getBoard().concretePiece(pos).color() + " ";
			history += a.getBoard().concretePiece(pos).type() + " with a position (" + pos.getX() + ", " + pos.getY() + ") ";
		}
		return new Action(a.getBoard(), p);
	}
	
}