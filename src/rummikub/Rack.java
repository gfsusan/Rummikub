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
	protected static final int INITIAL_DEAL = 14;
	protected static final int DRAW_NUM = 4;

	protected ArrayList<Integer> currentTiles = new ArrayList<Integer>();
	protected ArrayList<Integer> previousTiles = new ArrayList<Integer>();

	protected RackListener rl;
	protected Image imgRack;
	protected Image tileImage, blankImage;
	protected Graphics tilesGraphics;

	protected static final int WIDTH = 24;
	protected static final int HEIGHT = 2;

	protected int[][] currentTiles2D = new int[HEIGHT][WIDTH];
	protected int[][] previousTiles2D = new int[HEIGHT][WIDTH];

	public Rack() {
		super();
		setLayout(new FlowLayout());
		setBackground(Color.RED); // TODO why does this not work?
		setPreferredSize(new Dimension(WIDTH * 45, HEIGHT * 60));
		setVisible(true);

		// Deal
		for (int i = 0; i < INITIAL_DEAL; i++)
			currentTiles.add(Deck.takeTileFromDeck());

		// add Rack Listener and Mouse Listener
		rl = new RackListener(this);
		this.addMouseListener(rl);

		saveCurrentRack();
	}

	protected void drawTile() {
		// find an empty cell of rack
		
	}

	public void saveCurrentRack() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				previousTiles2D[i][j] = currentTiles2D[i][j];
			}
		}
	}

	public int getTileID(int y, int x) {
		return currentTiles2D[y][x];
	}

	public void reset() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				currentTiles2D[i][j] = previousTiles2D[i][j];
			}
		}
		repaint();
	}

	public void drawFourTiles() {
		for (int i = 0; i < DRAW_NUM; i++) {
			if (!isFull())
				currentTiles.add(Deck.takeTileFromDeck());
			else
				Rummikub.gameOver("Player rack is full!");
		}
		saveCurrentRack();
		repaint();
	}

	public void removeTile(int index) {
		currentTiles.remove(index);
	}

	// TODO public int getTileNumber(int tileIndex)

	public boolean hasRegistered() {
		return (currentTiles.size() != previousTiles.size());
	}

	public void sortByNumber() {
		for (int i = currentTiles.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (currentTiles.get(j) % 26 > currentTiles.get(j + 1) % 26) {
					currentTiles.add(j, currentTiles.get(j + 1));
					currentTiles.remove(j + 2);
				}
			}
		}
		repaint();
	}

	public void sortByColor() {
		Collections.sort(currentTiles);
		repaint();
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
			tilesGraphics.setColor(new Color(56, 87, 35));
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				drawTile(i, j);
			}
		}
		g.drawImage(imgRack, 0, 0, this);
		System.out.println("Rack - paint method called!");
	}

	public Rack getRack() {
		return this;
	}

	private int getNumberOfTiles() {
		int counter = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles2D[i][j] != -1)
					counter++;
			}
		}
		return counter;
	}

	protected boolean isFull() {
		return (getNumberOfTiles() >= 48);
	}

	public boolean isEmpty() {
		return (getNumberOfTiles() == 0);
	}

	public boolean isEmptyCell(int y, int x) {
		return currentTiles2D[y][x] == -1;
	}

	public int getTileAt(int y, int x) {
		return currentTiles2D[y][x];
	}

	public void removeTileAt(int y, int x) {

	}

}
