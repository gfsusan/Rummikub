package rummikub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Rummikub extends JFrame {
	private static Rummikub Rummikub;

	private JPanel back;
	private JPanel top, bottom;

	// top GUI
	private Board board;
	private JPanel topRight;
	private GameManagerPanel gmPanel;
	private JPanel space;

	JButton btnSort1;
	JButton btnSort2;

	// bottom GUI
	private Rack playerRackPanel;
	private JPanel sortPanel;

	final int RACK_SIZE = 14;
	private Deck deck; // CompleteTileSet

	private Rack aiRack;

	AI ai;
	Player player;

	// boolean addTile = false;

	public Rummikub(String title) throws IOException {
		super(title);

		// Deck 积己
		deck = new Deck();

		// 敲饭捞绢狼 Rack 积己
		playerRackPanel = new Rack();
		aiRack = new Rack();

		// Board and BoardChecker
		board = new Board();

		// player客 AI甫 积己
		player = new Player(playerRackPanel);
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
		// top.setPreferredSize(new Dimension(900, 600));

		bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		bottom.setBackground(Color.BLUE);
		// bottom.setPreferredSize(new Dimension(1200, 200));

		topRight = new JPanel();
		topRight.setLayout(new BorderLayout());
		topRight.setBackground(Color.BLACK);
		// topRight.setPreferredSize(new Dimension(300, 600));

		// manager
		gmPanel = new GameManagerPanel(board, player, ai);
		gmPanel.setBackground(Color.ORANGE);
		// TODO profile GUI

		space = new JPanel();
		space.setPreferredSize(new Dimension(0, 30));

		// TODO add the Profile GUI

		sortPanel = new JPanel();
		sortPanel.setLayout(new GridLayout(2, 1));
		btnSort1 = new JButton("Sort by Number");
		btnSort2 = new JButton("Sort by Color");

		// add Components
		this.add(back);

		back.add(top, BorderLayout.CENTER);
		back.add(bottom, BorderLayout.SOUTH);

		top.add(board, BorderLayout.CENTER);
		top.add(topRight, BorderLayout.EAST);
		top.add(space, BorderLayout.SOUTH);

		topRight.add(gmPanel, BorderLayout.CENTER);

		bottom.add(playerRackPanel, BorderLayout.CENTER);
		bottom.add(sortPanel, BorderLayout.EAST);

		sortPanel.add(btnSort1);
		sortPanel.add(btnSort2);

		ActionEventHandler handler = new ActionEventHandler();
		btnSort1.addActionListener(handler);
		btnSort2.addActionListener(handler);

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

	public static void gameOver(String message) {
		// TODO
		System.out.println("Game Over - " + message);

		System.exit(-1);
	}

	class ActionEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSort1) {
				player.getRack().sortByNumber();
			}
			if (e.getSource() == btnSort2) {
				player.getRack().sortByColor();
			}
		}

	}

}
