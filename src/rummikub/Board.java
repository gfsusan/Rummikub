package rummikub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {
	private BoardListener bl;

	private Image imgBoard;
	private Graphics boardGraphics;

	public static final int WIDTH = 22;
	public static final int HEIGHT = 8;

	private static int[][] currentTiles = new int[HEIGHT][WIDTH];
	private static int[][] previousTiles = new int[HEIGHT][WIDTH];

	private static ArrayList<TileSet> setList;

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
			}
		}
		saveCurrentTiles();
	}

	/////////////////////////////////
	// get
	/////////////////////////////////

	public int[][] getCurrentTiles() {
		return currentTiles;
	}

	public int getTileAt(int y, int x) {
		System.out.println("getTileAt value: " + currentTiles[y][x]);
		return currentTiles[y][x];
	}

	public static int getBlankTilesIndex(int size) {

		boolean inGroup;
		int a = 0, b = 0;
		int count = 0;
		for (int i = 0; i < HEIGHT; i++) {
			inGroup = false;
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles[i][j] != -1) {
					if (inGroup) {
						inGroup = false;
					}
				} else {
					if (inGroup) {
						// add to group
						count++;
						if (count >= size) {
							System.out.println("2");
							return a * WIDTH + b;
						}
					} else {
						inGroup = true;
						a = i;
						b = j;
						count = 0;
						// initialize new group
						count++;
						if (count >= size) {
							System.out.println("3");
							return a * WIDTH + b;
						}
					}
				}
			}
		}
		return -1; // no space for 'size'

	}

	private int getCurrentTilesSum() {
		int total = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				int temp = Deck.getTile(currentTiles[i][j]).getTileNum();
				if (temp != -1 && temp < 104)
					total += temp;
			}
		}
		return total;
	}

	private int getPreviousTilesSum() {
		int total = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				int temp = Deck.getTile(previousTiles[i][j]).getTileNum();
				if (temp != -1 && temp < 104)
					total += temp;
			}
		}
		return total;
	}

	public static ArrayList<TileSet> getSet() {
		updateTileSets();
		return setList;
	}

	public Board getBoard() {
		return this;
	}

	////////////////////////////////
	// check(boolean)
	///////////////////////////////

	public boolean isEmptyCell(int y, int x) {
		System.out.println("This is an empty cell (" + y + ", " + x + ")");
		return (currentTiles[y][x] == -1);
	}

	//////////////////////////////////////
	// remove
	//////////////////////////////////////

	public void removeTileAt(int y, int x) {
		currentTiles[y][x] = -1;
		repaint();
	}

	////////////////////////////////////
	// add
	///////////////////////////////////

	public void addTileAt(int y, int x) {
		currentTiles[y][x] = Rummikub.getWhichTile();
		repaint();
	}

	public static void addTileSet(TileSet set) {
		// set의 size() 양옆으로 빈칸이 존재해야함 "+2" 그리고 여유공간 "+2"
		int index = getBlankTilesIndex(set.getSize() + 2 + 2);
		if (index == -1) {
			System.out.println("Canot add TileSet! - ");
			set.print();
		} else
			index += 3; // "+1" 한칸 띄어야 하니깐

		for (int i = 0; i < set.getSize(); i++)
			currentTiles[((index + i) / WIDTH)][(index + i) % WIDTH] = set.getTileSet().get(i);
	}

	///////////////////////////
	// save board
	///////////////////////////

	public void saveCurrentTiles() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				previousTiles[i][j] = currentTiles[i][j];
			}
		}
	}

	public void reset() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				currentTiles[i][j] = previousTiles[i][j];
			}
		}

		System.out.println("Board - paint method called!");
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++)
				System.out.print(currentTiles[i][j] + " ");
			System.out.print("\n");
		}

		// TODO reset 할 때 왜 오른쪽 위에 tile이 생기지???
		repaint();
	}

	////////////////////////////////////////
	// Board Check
	////////////////////////////////////////
	private static void updateTileSets() {
		boolean inGroup = false;
		ArrayList<Integer> tempset = new ArrayList<Integer>();
		setList = new ArrayList<TileSet>();

		for (int i = 0; i < HEIGHT; i++) {
			inGroup = false;
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles[i][j] == -1) {
					if (inGroup) {
						inGroup = false;
						setList.add(new TileSet(tempset));// numGroups++;
					}
				} else {
					if (inGroup) {
						// add to group
						tempset.add(currentTiles[i][j]);
					} else {
						inGroup = true;
						tempset = new ArrayList<Integer>();
						// initialize new group
						tempset.add(currentTiles[i][j]);
					}
				}
			}
		}

	}

	// check if Board has validSets
	public boolean check(Player player) {
		updateTileSets();
		for (int i = 0; i < setList.size(); i++) {
			if (!player.hasFirstReg()) {
				// check if first registration set sum is 30+
				if ((this.getCurrentTilesSum() - this.getPreviousTilesSum()) >= 30)
					player.setFirstReg(true);
			}
			if (!setList.get(i).isValidSet()) {
				player.setFirstReg(false);
				return false;
			}

		}
		// 여기까지 왔으면 모든게 valid set이고, firstReg 충족
		// first register가 되었거나,
		return player.hasFirstReg() || setList.isEmpty() || this.getCurrentTilesSum() == this.getPreviousTilesSum();

	}

	//////////////////////////////////////
	// GUI graphics
	//////////////////////////////////////

	// drawTile at index (i,j)
	private void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles[i][j];

		if (tileId == -1)
			tileImage = Deck.getBlankTile().getImage();
		else
			tileImage = Deck.getTile(tileId).getImage();

		boardGraphics = imgBoard.getGraphics();
		boardGraphics.drawImage(tileImage, j * 45, i * 60, this);
	}

	public void paint(Graphics g) {

		if (imgBoard == null) {
			imgBoard = createImage(WIDTH * 45, HEIGHT * 60);
			String imagePath = "./pic\\board.png";
			// System.out.println(imagePath);
			try {
				imgBoard = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				drawTile(i, j);
			}
		}

		g.drawImage(imgBoard, 0, 0, this);
		imgBoard = null;
		// System.out.println("Board - paint method called!");

	}
}
