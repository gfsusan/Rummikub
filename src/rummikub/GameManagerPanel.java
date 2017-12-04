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

	JButton btnEndTurn;
	JButton btnResetBoard;

	JLabel profile;
	JLabel space;
	JPanel buttonPanel;

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
				if (true) { // TODO 게임이 끝났는지 판별 - 만약 computer 쪽에서 끝나면???
					if (board.check()) {
						if (player.getRack().isEmpty())
							Rummikub.gameWin("rack is empty!");
						if (!player.hasRegistered()) {
							player.drawFourTiles();

							// TODO turn 끝남처리 , current 을 previous으로 저장하는거 외에 할거있나?
						}
						player.getRack().saveCurrentRack();
						board.saveCurrentTiles();
						ai.takeTurn();

					} else {
						// error 메시지 출력 - 플레이어가 board 수정하고 다시 버튼클릭해야함
						JOptionPane.showMessageDialog(null, "Your turn has not ended yet. ", "Rummikub",
								JOptionPane.ERROR_MESSAGE);
					}
				} else // if game over,...
					Rummikub.gameOver("Game Over"); // TODO game end
			} else if (e.getSource() == btnResetBoard) {
				player.getRack().reset();
				board.reset();
			}

		}
	}
}
