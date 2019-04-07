package P3;

import java.util.Scanner;

public class Game {
	
	//type 1:chess, 2:go
	private int type;
	
	//the board of game
	private Board b;
	
	//the Scanner
	private Scanner in = new Scanner(System.in);
	
	//constructor: to make a choice of playing chess or go
	public Game(String a) {
		boolean flag = true;
		while(flag) {
			if(a.equalsIgnoreCase("chess")) {
				flag = false;
				System.out.println("Welcome to the chess game!");
				b = new Board(8);
				type = 1;
			}
			else if(a.equalsIgnoreCase("go")) {
				flag = false;
				System.out.println("Welcome to the go game!");
				b = new Board(19);
				type = 2;
			}
			else {
				System.out.println("Oh, no! You have entered an uncorrected game, please enter chess or go:");
				a = in.nextLine();
			}
		}
	}
	
	// get the type
	public int getType() {
		return type;
	}
	
	// get the board
	public Board getBoard() {
		return b;
	}
	
}
