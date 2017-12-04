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
			//imgBoard = createImage(WIDTH * 45, HEIGHT * 60);
			/*
			 * boardGraphics = imgBoard.getGraphics(); boardGraphics.setColor(new Color(250,
			 * 218, 94)); boardGraphics.fillRect(0, 0, getWidth(), getHeight());
			 */
			String pathSep = System.getProperty("file.separator");
			if (pathSep.equals("\\")) {
				pathSep = "\\\\";
			}

			String imagePath = "./" + "pic" + pathSep + "space" + ".png";
			System.out.println(imagePath);
			try {
				imgBoard = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		g.drawImage(imgBoard, 0, 0, this);

	}

	// TODO add the Profile GUI
}
