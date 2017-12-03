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

	// rack->board �Ѱ��� index
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
				if (true) { // TODO ������ �������� �Ǻ� - ���� computer �ʿ��� ������???
					// TODO �÷��̾��� ���� �������� �Ǻ� �� �ƴ϶� Board�� valid���� �Ǻ��ƴ� ?
					if (!player.hasRegistered())
						player.drawFourTiles();
						// TODO turn ����ó�� , current �� previous���� �����ϴ°� �ܿ� �Ұ��ֳ�?
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
