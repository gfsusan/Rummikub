package rummikub;

import java.util.ArrayList;

public class AI extends Player {
	private Rack rack;
	private Boolean enrollment = false;// 등록여부 나타냄
	private Board board;

	private ArrayList<TileSet> setList;

	public AI(Board board, Rack myRack) {
		super(myRack);
		this.board = board;
		this.rack = myRack;
	}

	public void takeTurn() {
		// initialize rack
		rack.saveCurrentRack();

		// turn !
		System.out.println("AI start turn");
		rack.print();

		// 연속된 숫자 set 찾아서 등록
		makeColSet();
		System.out.println("AI makeColSet");
		rack.print();

		// 반복된 숫자 set 찾아서 등록
		makeNumSet();
		System.out.println("AI makeNumSet");
		rack.print();

		// 등록되어 있는 set에 추가
		addToExistingSet();
		System.out.println("AI addToExistingSet");
		rack.print();

		// board check 해야한다!! - setFirstReg해주기 때문에
		System.out.println("AI took its turn.");
		if (board.check(this)) {
			// if player's rack is empty, player wins
			if (rack.isEmpty())
				Rummikub.gameOver("AI rack is empty! T^T");
			// else, if player has not registered, draw four tiles from deck
			else if (!hasRegistered()) {
				drawFourTiles();
				System.out.println("AI drew four tiles");
			}
			// initialize rack
			rack.saveCurrentRack();

		} else {
			board.reset();
			rack.reset();
			drawFourTiles();
		}
		

		// save board after ai's turn
		board.saveCurrentTiles();

	}

	private void makeColSet() {
		rack.sortByColor();// 색깔순으로 sort
		for (int i = 0; i < Rack.HEIGHT; i++) {
			for (int j = 0; j < Rack.WIDTH - 2; j++) {
				TileSet temp = new TileSet();
				temp.add(rack.getTileAt(i, j));
				temp.add(rack.getTileAt(i, j + 1));
				temp.add(rack.getTileAt(i, j + 2));

				if (temp.isValidSet()) {
					Board.addTileSet(temp);
					rack.removeTileAt(i, j);
					rack.removeTileAt(i, j + 1);
					rack.removeTileAt(i, j + 2);
					// skip next two blank tiles
					j = j + 2;
				}
			}
		}
		rack.sortByColor();
	}

	private void makeNumSet() {// 숫자에 대해서
		rack.sortByNumber();// 색깔순으로 sort
		for (int i = 0; i < Rack.HEIGHT; i++) {
			for (int j = 0; j < Rack.WIDTH - 2; j++) {
				TileSet temp = new TileSet();
				temp.add(rack.getTileAt(i, j));
				temp.add(rack.getTileAt(i, j + 1));
				temp.add(rack.getTileAt(i, j + 2));

				if (temp.isValidSet()) {
					Board.addTileSet(temp);
					rack.removeTileAt(i, j);
					rack.removeTileAt(i, j + 1);
					rack.removeTileAt(i, j + 2);
					// skip next two blank tiles
					j = j + 2;
				}
			}
		}
		rack.sortByNumber();
	}

	private void addToExistingSet() {
		setList = Board.getSet();

	}

	private boolean isEnrolled(ArrayList<Integer> tempArr) {
		// 등록했는지 안했는지 알려주는 함수
		int sum = 0;
		if (enrollment) {// 등록했다
			board.addTileSet(new TileSet(tempArr));
			board.repaint();
			return true;// 타일에 넣어줬다.
		} else {// 등록안했으면
			for (int i = 0; i < tempArr.size() - 1; i++) {
				sum += tempArr.get(i);
			}
			if (sum >= 30) {
				board.addTileSet(new TileSet(tempArr));
				board.repaint();
				enrollment = true;
				return true;
			} else
				return false;
		}

	}
}