package rummikub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.*;

public class Rummikub extends JFrame {
	private static Rummikub Rummikub;

	private JPanel back;
	private JPanel top, bottom;

	// top GUI
	private Board board;
	private JPanel topRight;
	private GameMangerPanel gmPanel;

	// bottom GUI
	private UserPanel userPanel;
	private JPanel sortPanel;

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

		// Board and BoardChecker
		board = new Board();
		bc = new BoardChecker(board);

		// player와 AI를 생성
		player = new Player(playerRack);
		ai = new AI(aiRack);

		///////////////////////////////////////////////////////
		// GUI
		///////////////////////////////////////////////////////

		// setSize(1000, 710);
		setResizable(false);

		back = new JPanel();
		back.setLayout(new BorderLayout());

		top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setBackground(Color.CYAN);
		top.setPreferredSize(new Dimension(900, 600));

		bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.setBackground(Color.BLUE);
		bottom.setPreferredSize(new Dimension(1200, 200));

		topRight = new JPanel();
		topRight.setLayout(new BorderLayout());
		topRight.setBackground(Color.BLACK);
		topRight.setPreferredSize(new Dimension(300, 600));

		// manager
		gmPanel = new GameMangerPanel(playerRack, aiRack);
		gmPanel.setBackground(Color.ORANGE);
		// TODO profile GUI

		userPanel = new UserPanel(playerRack);
		userPanel.setBackground(Color.RED);
		sortPanel = new JPanel();
		sortPanel.setLayout(new GridLayout(2, 1));
		JButton btnSort1 = new JButton("Sort by Number");
		JButton btnSort2 = new JButton("Sort by Color");

		// add Components
		this.add(back);

		back.add(top, BorderLayout.CENTER);
		back.add(bottom, BorderLayout.SOUTH);

		top.add(board, BorderLayout.CENTER);
		top.add(topRight, BorderLayout.EAST);

		// TODO 삭제할 확률 높음 ㅋㅋ 임시
		topRight.add(new JLabel("임시"), BorderLayout.NORTH);
		topRight.add(gmPanel, BorderLayout.CENTER); // TODO or CENTER?
		// TODO add the Profile GUI

		bottom.add(userPanel, BorderLayout.CENTER);
		bottom.add(sortPanel, BorderLayout.EAST);

		sortPanel.add(btnSort1);
		sortPanel.add(btnSort2);

		// rack이 위치하는 userPanel
		userPanel = new UserPanel(playerRack);

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
