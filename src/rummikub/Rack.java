package rummikub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Rack class에 있던 display함수 가져오기
 */
public class Rack extends JPanel {
	private static final int INITIAL_DEAL = 14;
	private static final int DRAW_NUM = 4;

	private ArrayList<Integer> currentTiles = new ArrayList<Integer>();
	private ArrayList<Integer> previousTiles = new ArrayList<Integer>();

	RackListener rl;
	Image imgRack;
	Image tileImage, blankImage;
	Graphics tilesGraphics;

	public static final int WIDTH = 24;
	public static final int HEIGHT = 2;

	private int[][] currentTiles2D = new int[HEIGHT][WIDTH];
	private int[][] previousTiles2D = new int[HEIGHT][WIDTH];

	public Rack() {
		super();
		setLayout(new FlowLayout());
		setBackground(Color.RED); // TODO why does this not work?
		setPreferredSize(new Dimension(WIDTH * 45, HEIGHT * 60));
		setVisible(true);

		// add Rack Listener and Mouse Listener
		rl = new RackListener(this);
		this.addMouseListener(rl);

		for (int i = 0; i < HEIGHT * WIDTH; i++) {
			if (i < this.getCurrentRackSize())
				currentTiles2D[i / WIDTH][i % WIDTH] = getTileID(i);
			else
				currentTiles2D[i / WIDTH][i % WIDTH] = -1;
		}
		for (int i = 0; i < INITIAL_DEAL; i++)
			currentTiles.add(Deck.takeTileFromDeck());

		saveCurrentRack();
	}

	public void saveCurrentRack() {
		previousTiles = new ArrayList<Integer>(currentTiles);
	}

	public int getTileID(int index) {
		return currentTiles.get(index);
	}

	public void reset() {
		currentTiles = new ArrayList<Integer>(previousTiles);

	}

	public void drawFourTiles() {
		for (int i = 0; i < DRAW_NUM; i++) {
			currentTiles.add(Deck.takeTileFromDeck());
		}
	}

	public void removeTile(int index) {
		currentTiles.remove(index);
	}

	// TODO public int getTileNumber(int tileIndex)

	public int getCurrentRackSize() {
		return currentTiles.size();
	}

	public boolean hasRegistered() {
		return (currentTiles.size() != previousTiles.size());
	}

	public void sortByNumber() {
		// TODO ;
		for (int i = currentTiles.size(); i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (currentTiles.get(j) % 26 > currentTiles.get(j + 1) % 26) {
					currentTiles.add(j, currentTiles.get(j + 1));
					currentTiles.remove(j + 2);
				}
			}
		}
	}

	public void sortByColor() {
		Collections.sort(currentTiles);
	}

	public void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles2D[i][j];

		if (tileId == -1)
			tileImage = Deck.getBlankTile().getImage();
		else
			tileImage = Deck.getTile(tileId).getImage();

		tilesGraphics = imgRack.getGraphics();
		tilesGraphics.drawImage(tileImage, j * 45, i * 60, this);
	}

	public void paint(Graphics g) {
		if (imgRack == null) {
			imgRack = createImage(WIDTH * 45, HEIGHT * 60);
			tilesGraphics = imgRack.getGraphics();
			tilesGraphics.setColor(new Color(0, 156, 0));
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				drawTile(i, j);
			}
		}
		g.drawImage(imgRack, 0, 0, this);
	}

	public Rack getRack() {
		return this;
	}
}
