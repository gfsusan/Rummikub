package rummikub;

import java.util.ArrayList;

public class BoardChecker {

	private ArrayList<TileSet> setList;
	private Board board;

	public BoardChecker(Board board) {
		this.board = board;
	}

	// TODO 맞는 지 check
	// TileSet 저장(check 안하고 ArrayList<TileSet>형으로)
	private void updateTileSets() {
		setList = new ArrayList<TileSet>();

		for (int i = 0; i < Board.HEIGHT; i++) { // 가로 (i줄)
			ArrayList<Integer> tempSet = new ArrayList<Integer>();
			for (int j = 0; j < Board.WIDTH; j++) { // i줄 j칸
				if (board.getCurrentTile(i, j) == -1) { // blank tile
					if (!tempSet.isEmpty()) {
						setList.add(new TileSet(tempSet)); // tempSet 완성-> setList에 추가
						tempSet = new ArrayList<Integer>(); // tempSet 초기화
					}
				} else
					tempSet.add(board.getCurrentTile(i, j)); // tempSet에 (i,j) tileID 저장
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