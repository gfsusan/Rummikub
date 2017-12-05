package rummikub;

import java.util.ArrayList;

public class AI extends Player {
	private Rack rack;
	private Boolean enrollment = false;// ��Ͽ��� ��Ÿ��
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

		// ���ӵ� ���� set ã�Ƽ� ���
		makeColSet();
		System.out.println("AI makeColSet");
		rack.print();

		// �ݺ��� ���� set ã�Ƽ� ���
		makeNumSet();
		System.out.println("AI makeNumSet");
		rack.print();

		// ��ϵǾ� �ִ� set�� �߰�
		addToExistingSet();
		System.out.println("AI addToExistingSet");
		rack.print();

		// board check �ؾ��Ѵ�!! - setFirstReg���ֱ� ������
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
		rack.sortByColor();// ��������� sort
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

	private void makeNumSet() {// ���ڿ� ���ؼ�
		rack.sortByNumber();// ��������� sort
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
		for (int i = 0; i < Rack.HEIGHT; i++) {
			for (int j = 0; j < Rack.WIDTH - 2; j++) {
				if (rack.currentTiles[i][j] != -1) {
					for (TileSet tileSet : setList) {
						TileSet temp = new TileSet(tileSet.getTileSet());
						tileSet.addFront(rack.currentTiles[i][j]);
						if (tileSet.isValidSet()) {
							int location = board.getTileSetLocation(temp);// add tile
							board.addTileIfSpaciousLeft(rack.currentTiles[i][j], location / Board.WIDTH,
									location % Board.WIDTH);
							rack.removeTileAt(i, j);
						}
					}
				}
			}

		}
	}
}