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
public class UserPanel extends JPanel {

	RackListener rl;
	Image imgRack;
	Image tileImage, blankImage;
	Graphics tilesGraphics;

	public static final int RACK_WIDTH = 12;
	public static final int RACK_HEIGHT = 4;

	private int[][] currentTiles = new int[RACK_HEIGHT][RACK_WIDTH];
	private int[][] previousTiles = new int[RACK_HEIGHT][RACK_WIDTH];

	Rack playerRack;

	public UserPanel() {

	}

	public UserPanel(Rack playerRack) {
		super();
		setLayout(new FlowLayout());
		setBackground(Color.RED); // TODO why does this not work?
		setPreferredSize(new Dimension(playerRack.WIDTH * 45, playerRack.HEIGHT * 60));
		setVisible(true);

		// add Rack Listener and Mouse Listener
		rl = new RackListener(playerRack);
		this.addMouseListener(rl);

		// set playerRack
		this.playerRack = playerRack;

		for (int i = 0; i < RACK_HEIGHT * RACK_WIDTH; i++) {
			if (i < playerRack.getSize())
				currentTiles[i / RACK_WIDTH][i % RACK_WIDTH] = playerRack.getTileID(i);
			else
				currentTiles[i / RACK_WIDTH][i % RACK_WIDTH] = -1;
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
			imgRack = createImage(RACK_WIDTH*45,RACK_HEIGHT*60);
			tilesGraphics = imgRack.getGraphics();
			tilesGraphics.setColor(new Color(0, 156, 0));
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		for(int i=0;i<RACK_HEIGHT; i++) {
			for (int j=0;j<RACK_WIDTH;j++) {
				drawTile(i,j);
			}
		}
		g.drawImage(imgRack, 0, 0, this);
	}

	public UserPanel getPanel() {
		return this;
	}
}
