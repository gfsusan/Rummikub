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

	// TODO �´� �� check
	public ArrayList<TileSet> getTileSets() {
		setList = new ArrayList<TileSet>();

		for (int i = 0; i < board.HEIGHT; i++) { // ���� (i��)
			ArrayList<Integer> tempSet = new ArrayList<Integer>();
			for (int j = 0; j < board.WIDTH; j++) { // i�� jĭ
				if (currentTiles[i][j] == -1) { // blank tile
					if (!tempSet.isEmpty()) {
						setList.add(new TileSet(tempSet)); // tempSet �ϼ�-> setList�� �߰�
						tempSet = new ArrayList<Integer>(); // tempSet �ʱ�ȭ
					}
				} else
					tempSet.add(currentTiles[i][j]); // tempSet�� (i,j) tileID ����
			}
		}
		return setList;
	}
	
	
}