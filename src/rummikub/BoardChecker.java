package rummikub;

import java.util.ArrayList;

public class BoardChecker {

	private Board board;
	private ArrayList<TileSet> setList;
	private int[][] currentTiles;

	public BoardChecker(Board board) {
		this.board = board;
		currentTiles = new int[board.HEIGHT][board.WIDTH];
	}

	// TODO 맞는 지 check
	public ArrayList<TileSet> getTileSets() {
		setList = new ArrayList<TileSet>();

		for (int i = 0; i < board.HEIGHT; i++) { // 가로 (i줄)
			ArrayList<Integer> tempSet = new ArrayList<Integer>();
			for (int j = 0; j < board.WIDTH; j++) { // i줄 j칸
				if (currentTiles[i][j] == -1) { // blank tile
					if (!tempSet.isEmpty()) {
						setList.add(new TileSet(tempSet)); // tempSet 완성-> setList에 추가
						tempSet = new ArrayList<Integer>(); // tempSet 초기화
					}
				} else
					tempSet.add(currentTiles[i][j]); // tempSet에 (i,j) tileID 저장
			}
		}
		return setList;
	}
	
	
}