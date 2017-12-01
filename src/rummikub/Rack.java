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

	// remains 생성
	public Rack() {

	}

	// player, ai의 rack 생성 - remains를 받는다
	public Rack(Deck deck, int rackSize) {
		// 모든 카드를 blank tile로 설정
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++)
				myTiles[i][j] = -1;
		}

		// 
		for (int i = 0; i < INITIAL_DEAL; i++) {
			int tileIndex = deck.takeTileFromDeck();
			myTiles[i / WIDTH][i % WIDTH] = tileIndex;
		}
		
		// TODO 생성자에서 할 필요 ㅇ??? 턴 받을 때 하면 안되나???? 
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
