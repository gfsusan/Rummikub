package rummikub;

import java.util.ArrayList;

public class Rack {
	// default size
	public static final int HEIGHT = 4;
	public static final int WIDTH = 12;

	// holds the tileID
	private int[][] myTiles = new int[HEIGHT][WIDTH];
	private int[][] previousTiles = new int[HEIGHT][WIDTH];
	
	private static Rack remains;

	UserPanel userPanel;

	
	// remains ����
	public Rack() {

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++)
				myTiles[i][j] = -1;
		}
	}

	
	// player, ai�� rack ���� - remains�� �޴´�
	public Rack(Deck deck, int rackSize) {
		// TODO remains �� ���� tile �� random���� �̾Ƽ� rack�� �ִ´�.
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
