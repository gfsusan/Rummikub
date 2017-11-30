package rummikub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class UserPanel extends JPanel {
	RackListener rl;
	Image originalRack;
	Image tileImage, blankImage;
	Graphics tilesGraphics;
	String pathSep;

	Rack playerRack;

	public UserPanel() {

	}

	public UserPanel(Rack playerRack) {
		super();
		this.setPreferredSize(new Dimension(1000, 80));

		// add MouseListener
		rl = new RackListener(playerRack);
		this.addMouseListener(rl);

		// set playerRack
		this.playerRack = playerRack;

		pathSep = System.getProperty("file.separator");
		if (pathSep.equals("\\")) {
			pathSep = "\\\\";
		}
		blankImage = (new ImageIcon("pics" + pathSep + "blank.gif")).getImage();
	}

	public void drawTile(int i, int j) {
		if (originalRack == null) {
			originalRack = createImage(getWidth(), getHeight());
			tilesGraphics = originalRack.getGraphics();
			tilesGraphics.setColor(new Color(0, 156, 0));
			;
			tilesGraphics.fillRect(0, 0, getWidth(), getHeight());
		}

		if (i < Rack.HEIGHT && j < Rack.WIDTH) {
			int tileId = playerRack.getTileID(i, j);
			tileImage = Deck.getTile(tileId).getImage();
		}

		tilesGraphics = originalRack.getGraphics();
		tilesGraphics.drawImage(tileImage, i * 45, j * 60, this);
	}

	public void paint(Graphics g) {
		if (originalRack == null) {
			originalRack = createImage(getWidth(), getHeight());
			tilesGraphics = originalRack.getGraphics();
			tilesGraphics.setColor(new Color(0, 156, 0));
		}

		g.drawImage(originalRack, 0, 0, this);
	}

	public UserPanel getPanel() {
		return this;
	}
}
