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

	private BoardChecker bc;
	
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

		// rack이 위치하는 userPanel 
		userPanel = new UserPanel(playerRack);
		
		// player와 AI를 생성
		player = new Player(playerRack);
		ai = new AI(aiRack);

		// Board and BoardChecker
		board = new Board();
		bc = new BoardChecker(board);
		
		// manager
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
