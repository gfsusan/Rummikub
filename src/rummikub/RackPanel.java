package rummikub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Rack class에 있던 display함수 가져오기
 */
public class RackPanel extends JPanel {

	RackListener rl;
	Image imgRack;
	Image tileImage, blankImage;
	Graphics tilesGraphics;

	public static final int WIDTH = 24;
	public static final int HEIGHT = 2;

	private int[][] currentTiles = new int[HEIGHT][WIDTH];
	private int[][] previousTiles = new int[HEIGHT][WIDTH];

	Rack playerRack;

	public RackPanel() {

	}

	public RackPanel(Rack playerRack) {
		super();
		setLayout(new FlowLayout());
		setBackground(Color.RED); // TODO why does this not work?
		setPreferredSize(new Dimension(WIDTH * 45, HEIGHT * 60));
		setVisible(true);

		// add Rack Listener and Mouse Listener
		rl = new RackListener(playerRack);
		this.addMouseListener(rl);

		// set playerRack
		this.playerRack = playerRack;

		for (int i = 0; i < HEIGHT * WIDTH; i++) {
			if (i < playerRack.getSize())
				currentTiles[i / WIDTH][i % WIDTH] = playerRack.getTileID(i);
			else
				currentTiles[i / WIDTH][i % WIDTH] = -1;
		}
	}

	public void drawTile(int i, int j) {
		Image tileImage;
		int tileId = currentTiles[i][j];

		if (tileId == -1)
			tileImage = Deck.getBlankTile().getImage();
		else
			tileImage = Deck.getTile(tileId).getImage();

		tilesGraphics = imgRack.getGraphics();
		tilesGraphics.drawImage(tileImage, j * 45, i * 60, this);
	}

	public void paint(Graphics g) {
		if (imgRack == null) {
			imgRack = createImage(WIDTH*45,HEIGHT*60);
			tilesGraphics = imgRack.getGraphics();
			tilesGraphics.setColor(new Color(0, 156, 0));
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for(int i=0;i<HEIGHT; i++) {
			for (int j=0;j<WIDTH;j++) {
				drawTile(i,j);
			}
		}
		g.drawImage(imgRack, 0, 0, this);
	}

	public RackPanel getPanel() {
		return this;
	}
}
