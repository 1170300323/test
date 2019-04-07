package P3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board {
	
	// the frame of GUI
	private JFrame jf;
	
	// panels of GUI
	private JPanel[][] jp = new JPanel[8][8];
	
	// labels of GUI
	private JLabel[][] jl = new JLabel[8][8];
	
	// icons of GUI
	private ImageIcon[][] icon = new ImageIcon[8][8];
	
	// paths of GUI
	private String[][] s = new String[8][8];
	
	// the GUI of go gmae
	private GoGUI go;
	
	// use the class piece to express the board.
	private Piece a[][];
	
	//constructor: to create a chess board or a go board.
	public Board(int size) {
		a = new Piece[size][size];
		if(size == 8) {
			chessGUI();
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					a[i][j] =  new Piece(new Position(i, j));
					if(i < 2)
						a[i][j].setColor(2);
					else if(i > 5)
						a[i][j].setColor(1);
					else
						a[i][j].setColor(0);
					if(i == 0 || i ==7) {
						switch(j) {
						case 0:
						case 7:a[i][j].setType(3);
								break;
						case 1:
						case 6:a[i][j].setType(4);
								break;
						case 2:
						case 5:a[i][j].setType(5);
								break;
						case 3:a[i][j].setType(1);
								break;
						default:a[i][j].setType(2);
								break;
						}
					}
					else if(i == 1 || i == 6)
						a[i][j].setType(6);
					else
						a[i][j].setType(0);
				}
			}
		}
		else {
			go = new GoGUI();
			for(int i = 0; i < 19; i++){
				for(int j = 0; j < 19; j++) {
					a[i][j] = new Piece(new Position(i, j));
				}
			}
		}
		
	}
	
	/**
	 * a mutator to get the concrete piece
	 * @param targeted position of the board
	 * @return the piece of that position
	 */
	public Piece concretePiece(Position pos) {
		return a[pos.getX()][pos.getY()];
	}
	
	/**
	 * to initialize the GUI of chess game
	 */
	public void chessGUI() {
		jf = new JFrame("chess");
		jf.setLayout(new GridLayout(8, 8));
		for(int i = 7; i >= 0; i--) {
			for(int j = 0; j < 8; j++) {
				s[i][j] = "src/P3/pic/";
				if(i == 1)
					s[i][j] += "pawn2.png";
				else if(i == 6)
					s[i][j] += "pawn1.png";
				else if(i == 0 || i == 7) {
					if(j == 0 || j == 7)
						s[i][j] += "rook";
					else if(j == 1 || j == 6)
						s[i][j] += "knight";
					else if(j == 2 || j == 5)
						s[i][j] += "bishop";
					else if(j == 3)
						s[i][j] += "king";
					else
						s[i][j] += "queen";
					if(i == 0)
						s[i][j] += "2.png";
					else
						s[i][j] += "1.png";
				}
				jp[i][j] = new JPanel();
				if(i % 2 == 0) {
					if(j % 2 == 0)
						jp[i][j].setBackground(Color.GRAY);
					else
						jp[i][j].setBackground(Color.LIGHT_GRAY);
				}
				else {
					if(j % 2 == 0)
						jp[i][j].setBackground(Color.LIGHT_GRAY);
					else
						jp[i][j].setBackground(Color.GRAY);
				}
				if(i == 0 || i == 1 || i == 6 || i == 7) {
					icon[i][j] = new ImageIcon(s[i][j]);
					jl[i][j] = new JLabel();
					icon[i][j].setImage(icon[i][j].getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
					jl[i][j].setIcon(icon[i][j]);
					jp[i][j].add(jl[i][j]);
				}
				jf.add(jp[i][j]);
			}
		}
		jf.setSize(500, 500);
		jf.setLocation(300, 300);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	
	/**
	 * set a piece in GUI of change
	 * @param pos1: old position
	 * @param pos2: new position
	 */
	public void setIcon(Position pos1, Position pos2) {
		int x1 = pos1.getX();
		int y1 = pos1.getY();
		int x2 = pos2.getX();
		int y2 = pos2.getY();
		s[x2][y2] = s[x1][y1];
		s[x1][y1] = "src/P3/pic/";
		icon[x2][y2] = new ImageIcon(s[x2][y2]);
		icon[x2][y2].setImage(icon[x2][y2].getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		jl[x2][y2] = new JLabel(icon[x2][y2]);
		jp[x1][y1].remove(jl[x1][y1]);
		jp[x1][y1].updateUI();
		jp[x2][y2].add(jl[x2][y2]);
		//jf.setVisible(true);
	}
	
	/**
	 * set a piece in GUI of eat
	 * @param pos1: old position
	 * @param pos2: new position
	 */
	public void setIconEat(Position pos1, Position pos2) {
		int x1 = pos1.getX();
		int y1 = pos1.getY();
		int x2 = pos2.getX();
		int y2 = pos2.getY();
		s[x2][y2] = s[x1][y1];
		s[x1][y1] = "src/P3/pic/";
		icon[x2][y2] = new ImageIcon(s[x2][y2]);
		icon[x2][y2].setImage(icon[x2][y2].getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		jp[x2][y2].remove(jl[x2][y2]);
		jp[x2][y2].updateUI();
		jl[x2][y2] = new JLabel(icon[x2][y2]);
		jp[x1][y1].remove(jl[x1][y1]);
		jp[x1][y1].updateUI();
		jp[x2][y2].add(jl[x2][y2]);
		//jf.setVisible(true);
	}
	
	public GoGUI getGo() {
		return go;
	}
}

/**
 * to initialize the game of go
 */
class GoGUI extends JFrame{
	
	// the panel of go game
	private GoPanel gp = new GoPanel();
	
	//constructor
	public GoGUI() {
		setVisible(true);
		setLayout(null);
		add(gp);
		gp.setBounds(70, 90, 440, 440);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		pack();
		setSize(600, 550);
	}
	
	/**
	 * @return GoPanel gp
	 */
	public GoPanel getGp() {
		return gp;
	}
	
}

/**
 * the panel of GUI go game
 */
class GoPanel extends JPanel{
	
	// the set of whiteGo
	private Set<WhiteGo> wgset = new HashSet<>();
	
	// the set of blackGo
	private Set<BlackGo> bgset = new HashSet<>();
	
	//constructor
	public GoPanel(){
		setSize(440, 440);
		setLayout(null);
	}
	
	// to paint a board of go
	@Override public void paint(Graphics g) {
		for (int i = 40; i <= 400; i += 20) {
			g.drawLine(40, i, 400, i);
		}
		for (int j = 40; j <= 400; j += 20) {
			g.drawLine(j, 40, j, 400);
		}
		g.fillOval(97, 97, 6, 6);
		g.fillOval(97, 337, 6, 6);
		g.fillOval(337, 97, 6, 6);
		g.fillOval(337, 337, 6, 6);
		g.fillOval(217, 217, 6, 6);
	}	
	
	// to put a white piece on the board
	public void setWhite(Position pos) {
		WhiteGo wg = new WhiteGo(this, pos);
		this.add(wg);
		wgset.add(wg);
		wg.setBounds(30 + pos.getX() * 20, 390 - pos.getY() * 20, 20, 20);
	}
	
	// to put a black piece on the board
	public void setBlack(Position pos) {
		BlackGo bg = new BlackGo(this, pos);
		this.add(bg);
		bgset.add(bg);
		bg.setBounds(30 + pos.getX() * 20, 390 - pos.getY() * 20, 20, 20);
	}
	
	// to remove a black piece of the board
	public void removeBlack(Position pos) {
		for(BlackGo bg : bgset) {
			if(bg.getPos().getX() == pos.getX() && bg.getPos().getY() == pos.getY()) {
				bg.remove();
				bgset.remove(bg);
				break;
			}
		}
	}
	
	// to remove a white piece of the board
	public void removeWhite(Position pos) {
		for(WhiteGo wg : wgset) {
			if(wg.getPos().getX() == pos.getX() && wg.getPos().getY() == pos.getY()) {
				wg.remove();
				wgset.remove(wg);
				break;
			}	
		}
	}
	
}

// the white piece of GUI go game
class WhiteGo extends Canvas{
	
	//the panel
	private GoPanel gp = null;
	
	// position of the go piece
	private Position pos;
	
	// constructor
	WhiteGo(GoPanel p, Position pos){
		setSize(20, 20);
		gp = p;
		this.pos = pos;
	}
	
	// to paint a white piece
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, 20, 20);
	}
	
	// remove the white piece from the board
	public void remove() {
		gp.remove(this);
	}
	
	// get the position of the white piece
	public Position getPos() {
		return pos;
	}
	
}

//the black piece of GUI go game
class BlackGo extends Canvas{
	
	//the panel
	private GoPanel gp = null;
	
	// position of the go piece
	private Position pos;
	
	//constructor
	BlackGo(GoPanel p, Position pos){
		setSize(20, 20);
		gp = p;
		this.pos = pos;
	}
	
	// to paint a black piece
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(0, 0, 20, 20);
	}
	
	// remove the black piece from the board
	public void remove() {
		gp.remove(this);
	}
	
	// get the position of the black piece
	public Position getPos() {
		return pos;
	}
	
}
