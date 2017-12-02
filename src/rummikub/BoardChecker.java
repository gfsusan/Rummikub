package rummikub;

import java.util.ArrayList;

public class BoardChecker {

	private ArrayList<TileSet> setList;
	private Board board;

	public BoardChecker(Board board) {
		this.board = board;
	}

	// TODO �´� �� check
	// TileSet ����(check ���ϰ� ArrayList<TileSet>������)
	private void updateTileSets() {
		setList = new ArrayList<TileSet>();

		for (int i = 0; i < Board.HEIGHT; i++) { // ���� (i��)
			ArrayList<Integer> tempSet = new ArrayList<Integer>();
			for (int j = 0; j < Board.WIDTH; j++) { // i�� jĭ
				if (board.getCurrentTile(i, j) == -1) { // blank tile
					if (!tempSet.isEmpty()) {
						setList.add(new TileSet(tempSet)); // tempSet �ϼ�-> setList�� �߰�
						tempSet = new ArrayList<Integer>(); // tempSet �ʱ�ȭ
					}
				} else
					tempSet.add(board.getCurrentTile(i, j)); // tempSet�� (i,j) tileID ����
			}
		}

	}

	// check if Board has validSets
	public boolean checkBoard() {
		updateTileSets();
		for (int i = 0; i < setList.size(); i++) {
			if (!setList.get(i).isValidSet())
				return false;
		}

		return true;

	}
/*
	private int Sum30(int[][] prev) {
		int curSum = 0, prevSum = 0;

		for (int i = 0; i < Board.HEIGHT; i++) {
			for (int j = 0; j < Board.WIDTH; j++) {
				if (currentTiles[i][j] != -1)
					curSum += currentTiles[i][j];
				if (prev[i][j] != -1)
					prevSum += prev[i][j];
			}
		}
		return curSum - prevSum;
	}
*/
}