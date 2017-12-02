package rummikub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;

public class GameMangerPanel extends JPanel {
	Rummikub game;

	JButton btnEndTurn;
	JButton btnResetBoard;

	JLabel profile;

	JLabel space;

	JPanel buttonPanel;

	public GameMangerPanel(Rack playerRack, Rack aiRack) {
		// TODO
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 100));

		btnEndTurn = new JButton("End Turn");
		btnResetBoard = new JButton("Reset Board");

		
		profile = new JLabel("Profile goes here.");
		space = new JLabel("space");
		space.setBackground(Color.RED);
		//space.setPreferredSize(new Dimension(0,300));
		

		buttonPanel = new JPanel(new GridLayout(2, 1));
		buttonPanel.add(btnEndTurn);
		buttonPanel.add(btnResetBoard);
		
		add(profile,BorderLayout.NORTH);
		add(space, BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
	}

}
