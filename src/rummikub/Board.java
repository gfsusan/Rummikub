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
	BoardListener boardHandler;
	BoardChecker checker;

	Image originalBoard, blankImage;
	Graphics boardGraphics;

	// number of TileSets
	private int nTileSets = 0;

	private static final int BOARD_WIDTH = 20;
	private static final int BOARD_HEIGHT = 10;

	int[][] currentTiles = new int[BOARD_HEIGHT][BOARD_WIDTH];
	int[][] previousTiles = new int[BOARD_HEIGHT][BOARD_WIDTH];

	String pathSep;

	public Board() {
		super();
		this.setLayout(new FlowLayout());
		setBackground(new Color(253, 236, 136));
		setPreferredSize(new Dimension(BOARD_WIDTH * 45, BOARD_HEIGHT * 60));
		setVisible(true);

		// boardHandler
		boardHandler = new BoardListener(this);
		addMouseMotionListener(boardHandler);
		addMouseListener(boardHandler);

		// boardChecker
		checker = new BoardChecker(BOARD_HEIGHT, BOARD_WIDTH);

		pathSep = System.getProperty("file.separator");
		if (pathSep.equals("\\")) {
			pathSep = "\\\\";
		}

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

	// returns an ArrayList of TileSet on the board
	public ArrayList<TileSet> getTileSets() {

		return null;

	}

	private void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles[i][j];

		if (tileId == -1)
			tileImage = blankImage;
		else
			tileImage = Deck.getTile(tileId).getImage();
	}

	public void paint(Graphics g) {
		if (originalBoard == null) {
			originalBoard = createImage(BOARD_WIDTH * 45, BOARD_HEIGHT * 60);
			boardGraphics = originalBoard.getGraphics();
			boardGraphics.setColor(new Color(250, 218, 94));
			boardGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				drawTile(i, j);
			}
		}
		g.drawImage(originalBoard, 0, 0, this);
	}

	public Board getBoard() {
		return this;
	}

}
