package rummikub;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class Rummikub extends JFrame {
	private static Rummikub Rummikub;
	private Board board;
	private UserPanel userPanel;
	private InfoPanel infoPanel;
	private JPanel top;

	final int RACK_SIZE = 14;
	private Deck deck; // CompleteTileSet
	private Rack playerRack;
	private Rack aiRack;

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

		// Deck 생성
		deck = new Deck();

		// 플레이어의 Rack 생성
		playerRack = new Rack();
		aiRack = new Rack();

		// player와 AI를 생성
		player = new Player(playerRack);
		ai = new AI(aiRack);

		board = new Board();
		userPanel = new UserPanel(playerRack);
		infoPanel = new InfoPanel(deck, playerRack);

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
