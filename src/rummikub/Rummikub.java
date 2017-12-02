package rummikub;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;

public class Rummikub extends JFrame {
	private static Rummikub Rummikub;

	private UserPanel userPanel;
	private InfoPanel infoPanel;
	
	private JPanel back;
	private JPanel top, bottom;

	private Board board;
	private JPanel topRight;
	
	
	private BoardChecker bc;

	final int RACK_SIZE = 14;
	private Deck deck; // CompleteTileSet
	private Rack playerRack;
	private Rack aiRack;

	AI ai;
	Player player;

	// boolean addTile = false;

	public Rummikub(String title) throws IOException {
		super(title);

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

	public static void main(String[] args) throws IOException {
		Rummikub game = new Rummikub("Rummikub");
	}

}
