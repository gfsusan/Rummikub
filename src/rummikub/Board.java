package rummikub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {
	BoardListener bl;

	Image blankImage;
	Image imgBoard;
	Graphics boardGraphics;

	// number of TileSets
	private int nTileSets = 0;

	public static final int BOARD_WIDTH = 20;
	public static final int BOARD_HEIGHT = 10;

	private int[][] currentTiles = new int[BOARD_HEIGHT][BOARD_WIDTH];
	private int[][] previousTiles = new int[BOARD_HEIGHT][BOARD_WIDTH];

	public Board() {
		super();
		this.setLayout(new FlowLayout());
		setBackground(new Color(253, 236, 136));
		setPreferredSize(new Dimension(BOARD_WIDTH * 45, BOARD_HEIGHT * 60));
		setVisible(true);

		// boardHandler
		bl = new BoardListener(this);
		addMouseMotionListener(bl);
		addMouseListener(bl);

	String 	pathSep = System.getProperty("file.separator");

		if(pathSep.equals("\\"))
		{
			pathSep = "\\\\";
		}
System.out.println(pathSep);
	blankImage = (new ImageIcon("pics" + pathSep + "blank.gif")).getImage();
		
		// initialize all the tiles as blank
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				currentTiles[i][j] = -1;
				previousTiles[i][j] = -1;
			}
		}
	}

	public int[][] getCurrentTiles() {
		return currentTiles;
	}

	private void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles[i][j];

		if (tileId == -1)
			//tileImage = Deck.getBlankTile().getImageIcon().getImage();
			tileImage=Deck.getBlankTile().getImage();
		else
			//tileImage = Deck.getTile(tileId).getImageIcon().getImage();
			tileImage=Deck.getTile(tileId).getImage();

		boardGraphics = imgBoard.getGraphics();
		boardGraphics.drawImage(tileImage, j * 45, i * 60, this);
	}

	public void paint(Graphics g) {

		if (imgBoard == null) {
			imgBoard = createImage(BOARD_WIDTH * 45, BOARD_HEIGHT * 60);
			boardGraphics = imgBoard.getGraphics();
			boardGraphics.setColor(new Color(250, 218, 94));
			boardGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				drawTile(i, j);
			}
		}
		g.drawImage(imgBoard, 0, 0, this);
	}

	public Board getBoard() {
		return this;
	}

}
