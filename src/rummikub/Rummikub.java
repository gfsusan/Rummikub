package rummikub;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class Rummikub extends JFrame {
	static Rummikub Rummikub;
	Board board;
	UserPanel userPanel;
	InfoPanel infoPanel;
	JPanel top;

	final int RACK_SIZE = 14;
	Deck deck; // CompleteTileSet
	Rack pool;
	Rack playerRack;
	Rack aiRack;

	AI ai;
	Player player;

	// boolean addTile = false;

	// TODO pathSep 지울거면 삭제
	String pathSep;

	public Rummikub(String title) {
		super(title);

		// TODO pathSep 지울거면 삭제
		pathSep = System.getProperty("file.separator");
		if (pathSep.equals("\\"))
			pathSep = "\\\\";

		deck = new Deck();

		pool = new Rack();
		playerRack = new Rack(pool, RACK_SIZE);
		aiRack = new Rack(pool, RACK_SIZE);
		// TODO 얘 왜함 ? ㅋㅋㅋㅋ
		playerRack.setNewRack();

		ai = new AI(aiRack);
		player = new Player(playerRack);

		board = new Board();
		userPanel = new UserPanel(playerRack);
		infoPanel = new InfoPanel(pool, playerRack);

		setSize(1000, 710);
		setResizable(false);
		
		top = new JPanel();
		top.setLayout(new BorderLayout());
		top.add(board, BorderLayout.WEST);
		top.add(infoPanel, BorderLayout.EAST);
		top.setPreferredSize(new Dimension(1000, 625));

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(userPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		// TODO
		// infoPanel.setGameFrame(this);
		// GAME_FRAME = this;

		// showPlayersTiles(playerRack);

	}

	public static void main(String[] args) {
		Rummikub game = new Rummikub("Rummikub");
	}

}
