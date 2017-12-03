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
	Board board;
	Player player;
	AI ai;

	// rack->board 넘겨줄 index
	private static int messenger;

	JButton btnEndTurn;
	JButton btnResetBoard;

	JLabel profile;
	JLabel space;
	JPanel buttonPanel;

	private static boolean isAdding;

	public GameManagerPanel(Board board, Player player, AI ai) {
		this.board = board;
		this.player = player;
		this.ai = ai;

		// TODO
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 100));

		btnEndTurn = new JButton("End Turn");
		btnResetBoard = new JButton("Reset Board");

		ActionEventHandler handler = new ActionEventHandler();
		btnEndTurn.addActionListener(handler);
		btnResetBoard.addActionListener(handler);
		profile = new JLabel("Profile goes here.");
		space = new JLabel("space");
		space.setBackground(Color.RED);
		// space.setPreferredSize(new Dimension(0,300));

		buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(btnEndTurn);
		buttonPanel.add(btnResetBoard);

		add(profile, BorderLayout.NORTH);
		add(space, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		isAdding = false;

	}

	public static void setAdding(boolean flag) {
		isAdding = flag;
	}

	public static boolean isAdding() {
		return isAdding;
	}

	public void setMessenger(int id) {
		this.messenger = id;

	}

	public static int getMessenger() {

		return messenger;
	}

	
	private class ActionEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnEndTurn) {
				if (true) { // TODO 게임이 끝났는지 판별 - 만약 computer 쪽에서 끝나면???
					// TODO 플레이어의 턴이 끝났는지 판별 가 아니라 Board가 valid한지 판별아님 ?
					if (!player.hasRegistered())
						player.drawFourTiles();
						// TODO turn 끝남처리 , current 을 previous으로 저장하는거 외에 할거있나?
					else
						JOptionPane.showMessageDialog(null, "Your turn has not ended yet. ", "Rummikub", JOptionPane.ERROR_MESSAGE);
				} else // if Deck is empty ,...
					Rummikub.gameOver("Game Over"); // TODO game end
			} else if (e.getSource() == btnResetBoard) {
				player.getRack().reset();
				board.reset();
			}

		}
	}
}
