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

		for (int i = 0; i < HEIGHT; i++)
			for (int j = 0; j < WIDTH; j++)
				currentTiles2D[i][j] = -1;

		// Deal
		for (int i = 0; i < INITIAL_DEAL; i++) {
			int index = firstBlankIndex();
			currentTiles2D[index / WIDTH][index % WIDTH] = Deck.takeTileFromDeck();
		}

		// add Rack Listener and Mouse Listener
		rl = new RackListener(this);
		this.addMouseListener(rl);

		saveCurrentRack();
	}

	///////////////////////////////////
	// check - return boolean
	///////////////////////////////////

	protected boolean isFull() {
		return (getCurrentSize() >= HEIGHT * WIDTH);
	}

	public boolean isEmpty() {
		return (getCurrentSize() == 0);
	}

	public boolean isEmptyCell(int y, int x) {
		return currentTiles2D[y][x] == -1;
	}

	public boolean hasRegistered() {
		return (getCurrentSize() != getPreviousSize());
	}

	//////////////////////////////
	// get
	//////////////////////////////

	public Rack getRack() {
		return this;
	}

	private int getCurrentSize() {
		int counter = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles2D[i][j] != -1)
					counter++;
			}
		}
		return counter;
	}

	private int getPreviousSize() {
		int counter = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (previousTiles2D[i][j] != -1)
					counter++;
			}
		}
		return counter;
	}

	public int getTileAt(int y, int x) {
		return currentTiles2D[y][x];
	}

	private ArrayList<Integer> make2Dto1D() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles2D[i][j] != -1)
					arrayList.add(currentTiles2D[i][j]);
			}
		}
		return arrayList;
	}

	private void make1Dto2D(ArrayList<Integer> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			currentTiles2D[i / WIDTH][i % WIDTH] = arrayList.get(i);
		}
	}

	// TODO public int getTileNumber(int tileIndex)

	/////////////////////////////
	// remove
	////////////////////////////

	public void removeTileAt(int y, int x) {
		currentTiles2D[y][x] = -1;
	}

	/////////////////////////////////////////
	// save board or add to board
	/////////////////////////////////////////

	public void saveCurrentRack() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				previousTiles2D[i][j] = currentTiles2D[i][j];
			}
		}
	}

	public void reset() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				currentTiles2D[i][j] = previousTiles2D[i][j];
			}
		}
		repaint();
	}

	// method that finds the first blank tile in currentTiles2D
	private int firstBlankIndex() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles2D[i][j] == -1)
					return (i * WIDTH + j);
			}
		}
		return -1;

	}

	public void drawFourTiles() {
		for (int i = 0; i < DRAW_NUM; i++) {
			int index = firstBlankIndex();
			if (index != -1)
				currentTiles2D[index / WIDTH][index % WIDTH] = Deck.takeTileFromDeck();
			else
				Rummikub.gameOver("Rack is full!");
		}
		saveCurrentRack();
		repaint();
	}

	//////////////////////////////
	// sort
	//////////////////////////////

	public void sortByNumber() {
		ArrayList<Integer> temp = make2Dto1D();
		for (int i = temp.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (temp.get(j) % 26 > temp.get(j + 1) % 26) {
					temp.add(j, temp.get(j + 1));
					temp.remove(j + 2);
				}
			}
		}
		make1Dto2D(temp);
		repaint();
	}

	public void sortByColor() {
		ArrayList<Integer> temp = make2Dto1D();
		Collections.sort(temp);
		make1Dto2D(temp);
		repaint();
	}

	//////////////////////////////////////////////////
	// GUI Graphics
	/////////////////////////////////////////////////

	public void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles2D[i][j];

		if (tileId == -1)
			tileImage = Deck.getBlankTile().getImage();
		else
			tileImage = Deck.getTile(tileId).getImage();

		System.out.println(tileId);

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

}
