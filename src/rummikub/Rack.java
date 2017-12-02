package rummikub;

import java.util.ArrayList;
import java.util.Collections;

public class Rack {
	private static final int INITIAL_DEAL = 14;
	private static final int DRAW_NUM = 4;

	private ArrayList<Integer> currentTiles = new ArrayList<Integer>();
	private ArrayList<Integer> previousTiles = new ArrayList<Integer>();

	public Rack() {
		for (int i = 0; i < INITIAL_DEAL; i++)
			currentTiles.add(Deck.takeTileFromDeck());

		saveCurrentRack();
	}

	public void saveCurrentRack() {
		previousTiles = new ArrayList<Integer>(currentTiles);
	}

	public int getTileID(int index) {
		return currentTiles.get(index);
	}

	public void reset() {
		currentTiles = new ArrayList<Integer>(previousTiles);
	}

	public void drawFourTiles() {
		for (int i = 0; i < DRAW_NUM; i++) {
			currentTiles.add(Deck.takeTileFromDeck());
		}
	}

	public void removeTile(int index) {
		currentTiles.remove(index);
	}

	// TODO public int getTileNumber(int tileIndex)

	public int getSize() {
		return currentTiles.size();
	}

	public void sortByNumber() {
		// TODO ;
		for (int i = currentTiles.size(); i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if(currentTiles.get(j)%26 > currentTiles.get(j+1)%26) {
					currentTiles.add(j, currentTiles.get(j+1));
					currentTiles.remove(j+2);
				}
			}
		} 
	}

	public void sortByColor() {
		Collections.sort(currentTiles);
	}
}
/*
public class Rack {
	// default size
	public static final int HEIGHT = 4;
	public static final int WIDTH = 12;
	private static final int INITIAL_DEAL = 14;

	// holds the tileID
	private int[][] currentTiles = new int[HEIGHT][WIDTH];
	private int[][] previousTiles = new int[HEIGHT][WIDTH];
	
	UserPanel userPanel;

	// player, ai의 rack 생성 - remains를 받는다
	public Rack() {
		// 모든 카드를 blank tile로 설정
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++)
				currentTiles[i][j] = -1;
		}

		// deck으로부터 tile을 14개 받는다
		for (int i = 0; i < INITIAL_DEAL; i++) {
			currentTiles[i / WIDTH][i % WIDTH] = Deck.takeTileFromDeck();
		}

		// TODO 생성자에서 할 필요 ㅇ??? 턴 받을 때 하면 안되나????
		saveCurrentRack();
	}

	// 현재타일 저장
	public void saveCurrentRack() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				previousTiles[i][j] = currentTiles[i][j];
			}
		}
	}

	public int getTileID(int i, int j) {
		return currentTiles[i][j];
	}

	// 저장되어 있던 tiles 불러오기
	public void reset() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				currentTiles[i][j] = previousTiles[i][j];
			}
		}
	}

	public void drawFourTiles() {
		int count = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (currentTiles[i][j] != -1 && count < DRAW_NUM) {
					currentTiles[i][j] = Deck.takeTileFromDeck();
					count++;
					// TODO Rack이 꽉 차면??? -> 한사람이 48개 이상 가지고있기는 어렵다 (안해)
				}
			}
		}
	}

	public void removeTile(int indexI, int indexJ) {
		currentTiles[indexI][indexJ] = -1;
	}

	// TODO public int getTileNumber(int tileIndex)

	// 같은숫자끼리 sort
	// 111, 222, 333, ...
	public void sortByNumber() {

	}

	// 같은색깔끼리 sort
	// 123456789, 123456789, ...
	public void sortByColor() { // = sort by index
		// 배열 복사.... time complexity.... sorry....
		int[][] temp = new int[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				temp[i][j] = currentTiles[i][j];
				currentTiles[i][j] = -1;
			}
		}

		// 같은 색끼리 묶기
		int[] count = new int[5];
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (temp[i][j] == -1)
					continue;
				switch (temp[i][j] / 26) {
				case 0: {
					currentTiles[0][count[0]] = temp[i][j];
					count[0]++;
					break;
				}
				case 1: {
					currentTiles[1][count[1]] = temp[i][j];
					count[1]++;
					break;
				}
				case 2: {
					currentTiles[2][count[2]] = temp[i][j];
					count[2]++;
					break;
				}
				case 3: {
					currentTiles[3][count[3]] = temp[i][j];
					count[3]++;
					break;
				}
				case 4: {
					for (int k = HEIGHT - 1; k >= 0; k--)
						for (int l = WIDTH - 1; l >= 0; l--)
							if (currentTiles[i][j] == -1) {
								currentTiles[i][j] = temp[i][j];
								count[4]++;
							}
				}
				}
			}
		}
	}

}

*/