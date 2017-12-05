package rummikub;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpacePanel extends JPanel {

	public void paint(Graphics g) {
		Image imgBoard = null;
		if (imgBoard == null) {
			String imagePath = "./pic\\space.png";

			try {
				imgBoard = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		g.drawImage(imgBoard, 0, 0, this);

	}

}
