package rummikub;

import java.util.ArrayList;

public class Rack {
	// default size
	public static final int HEIGHT = 4;
	public static final int WIDTH = 12;
	private static final int INITIAL_DEAL = 14;

	// holds the tileID
	private int[][] myTiles = new int[HEIGHT][WIDTH];
	private int[][] previousTiles = new int[HEIGHT][WIDTH];

	private static Deck deck;

	UserPanel userPanel;

	// remains ����
	public Rack() {

	}

	// player, ai�� rack ���� - remains�� �޴´�
	public Rack(Deck deck, int rackSize) {
		// ��� ī�带 blank tile�� ����
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++)
				myTiles[i][j] = -1;
		}

		// 
		for (int i = 0; i < INITIAL_DEAL; i++) {
			int tileIndex = deck.takeTileFromDeck();
			myTiles[i / WIDTH][i % WIDTH] = tileIndex;
		}
		
		// TODO �����ڿ��� �� �ʿ� ��??? �� ���� �� �ϸ� �ȵǳ�???? 
		setNewRack();
	}

	public void setNewRack() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				previousTiles[i][j] = myTiles[i][j];
			}
		}
	}

	public int getTileID(int i, int j) {
		return myTiles[i][j];
	}
}
