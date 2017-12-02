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

	Image imgBoard;
	Graphics boardGraphics;

	// number of TileSets
	private int nTileSets = 0;

	public static final int WIDTH = 20;
	public static final int HEIGHT = 8;

	private int[][] currentTiles = new int[HEIGHT][WIDTH];
	private int[][] previousTiles = new int[HEIGHT][WIDTH];

	public Board() {
		super();
		this.setLayout(new FlowLayout());
		setBackground(new Color(253, 236, 136));
		setPreferredSize(new Dimension(WIDTH * 45, HEIGHT * 60));
		setVisible(true);

		// add boardListener
		bl = new BoardListener(this);
		addMouseMotionListener(bl);
		addMouseListener(bl);

		// initialize all the tiles as blank
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				currentTiles[i][j] = -1;
				previousTiles[i][j] = -1;
			}
		}
	}

	public int[][] getCurrentTiles() {
		return currentTiles;
	}

	// drawTile at index (i,j)
	private void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles[i][j];

		if (tileId == -1)
			tileImage=Deck.getBlankTile().getImage();
		else
			tileImage=Deck.getTile(tileId).getImage();

		boardGraphics = imgBoard.getGraphics();
		boardGraphics.drawImage(tileImage, j * 45, i * 60, this);
	}

	public void paint(Graphics g) {

		if (imgBoard == null) {
			imgBoard = createImage(WIDTH * 45, HEIGHT * 60);
			boardGraphics = imgBoard.getGraphics();
			boardGraphics.setColor(new Color(250, 218, 94));
			boardGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				drawTile(i, j);
			}
		}
		g.drawImage(imgBoard, 0, 0, this);
	}

	public Board getBoard() {
		return this;
	}

}
