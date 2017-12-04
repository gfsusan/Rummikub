package rummikub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameManagerPanel extends JPanel {
	private Board board;
	private Player player;
	private AI ai;

	private JButton btnEndTurn;
	private JButton btnResetBoard;

	private JLabel profile;
	private JLabel space;
	private JPanel buttonPanel;

	private int turn;

	public GameManagerPanel(Board board, Player player, AI ai) {
		this.board = board;
		this.player = player;
		this.ai = ai;

		// initialize # of turn
		turn = 0;

		// TODO
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 100));

		btnEndTurn = new JButton("End Turn");
		btnResetBoard = new JButton("Reset Board");

		ActionEventHandler handler = new ActionEventHandler();
		btnEndTurn.addActionListener(handler);
		ImageIcon imgEndTurn = new ImageIcon("./pic\\endTurn.png");
		btnEndTurn.setIcon(imgEndTurn);
		btnResetBoard.addActionListener(handler);
		ImageIcon imgResetBoard = new ImageIcon("./pic\\resetBoard.png");
		btnResetBoard.setIcon(imgResetBoard);

		profile = new JLabel("Profile goes here.");
		space = new JLabel("space");
		space.setBackground(Color.RED);

		buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(btnEndTurn);
		buttonPanel.add(btnResetBoard);

		add(profile, BorderLayout.NORTH);
		add(space, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	private class ActionEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnEndTurn) {
				// check if board is valid
				if (board.check()) {
					// if player's rack is empty, player wins
					if (player.getRack().isEmpty())
						Rummikub.gameWin("rack is empty!");
					// else, if player has not registered, draw four tiles from deck
					else if (!player.hasRegistered()) {
						player.drawFourTiles();
					}

					// initialize rack and board
					player.getRack().saveCurrentRack();
					board.saveCurrentTiles();

					// ai's turn
					ai.takeTurn();

					// check # of turns
					if (turn++ > 15)
						Rummikub.gameOver("Game Over"); // TODO game end
				} else {
					// error 메시지 출력 - 플레이어가 board 수정하고 다시 버튼클릭해야함
					JOptionPane.showMessageDialog(null, "Your turn has not ended yet. ", "Rummikub",
							JOptionPane.ERROR_MESSAGE);
				}
			// reset board
			} else if (e.getSource() == btnResetBoard) {
				player.getRack().reset();
				board.reset();
			}

		}
	}
}
