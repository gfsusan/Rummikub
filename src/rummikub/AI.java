package rummikub;

import java.util.ArrayList;

public class AI extends Player {
	private Rack rack;
	private Boolean enrollment = false;// ��Ͽ��� ��Ÿ��
	private Board board;

	public AI(Board board, Rack myRack) {
		super(myRack);
		this.board = board;
		this.rack = myRack;
	}

	public void takeTurn() {
		// turn !
		System.out.println("AI start turn");
		rack.print();
		makeNumSet();
		System.out.println("AI makeNumSet");
		rack.print();
		makeColSet();
		System.out.println("AI makeColSet");
		rack.print();
		if (enrollment) {
			registerRemains();
		}
		System.out.println("AI registerRemains");
		rack.print();

		System.out.println("AI took its turn.");

		if (this.getRack().isEmpty())
			Rummikub.gameOver("AI rack is empty T^T");

		if (!hasRegistered()) {
			// ��ȭ����
			this.drawFourTiles();
		}
		System.out.println("AI drew four tiles");
		rack.print();

		rack.saveCurrentRack();
	}

	ArrayList<Integer> rackTile = new ArrayList<Integer>();

	private void setArr() {
		rackTile = rack.make2Dto1D();
	}

	private void makeNumSet() {// ���ڿ� ���ؼ�
		rack.sortByNumber();// ���ڿ� ���ؼ� sort

		ArrayList<Integer> tileArray = new ArrayList<Integer>();
		for (int i = 0; i < Rack.HEIGHT - 2; i++) {
			for (int j = 0; j < Rack.WIDTH - 2; j++) {
				if (rack.getTileAt(i, j) != 104 && rack.getTileAt(i, j) != 105
						&& (rack.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH))
								% 26 == (rack.getTileAt((i + j + 2) / Rack.WIDTH, (i + j + 2) % Rack.WIDTH) % 26)) {// 3����
					// ������
					if (rack.getTileAt((i + j + 1) / Rack.WIDTH,
							(i + j + 1) % Rack.WIDTH) != (rack.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH))
							&& rack.getTileAt((i + j + 2) / Rack.WIDTH, (i + j + 2) % Rack.WIDTH) != (rack
									.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH))) {

						if (i != Rack.HEIGHT - 3 && j != Rack.WIDTH - 3
								&& (rack.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH))
										% 26 == (rack.getTileAt((i + j + 3) / Rack.WIDTH, (i + j + 3) % Rack.WIDTH)
												% 26)) {// 4���� ������
							if (rack.getTileAt((i + j + 3) / Rack.WIDTH, (i + j + 3) % Rack.WIDTH) != (rack
									.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH))) {
								for (int index = 0; index < 4; index++) {
									tileArray.add(
											rack.getTileAt((i + j + index) / Rack.WIDTH, (i + j + index) % Rack.WIDTH));
									rack.removeTileAt((i + j + index) / Rack.WIDTH, (i + j + index) % Rack.WIDTH);
								}
								/*
								 * tileArray.add(rack.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH));
								 * tileArray.add(rack.getTileAt((i + j + 1) / Rack.WIDTH, (i + j + 1) %
								 * Rack.WIDTH)); tileArray.add(rack.getTileAt((i + j + 2) / Rack.WIDTH, (i + j +
								 * 2) % Rack.WIDTH)); tileArray.add(rack.getTileAt((i + j + 3) / Rack.WIDTH, (i
								 * + j + 3) % Rack.WIDTH));
								 * 
								 * for (int index = 0; index < 4; index++) { rack.removeTileAt((i+j +index) /
								 * Rack.WIDTH, (i+j+index) % Rack.WIDTH); }
								 */
							} else { // 3���� ���� ���
								/*
								 * tileArray.add(rack.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH));
								 * tileArray.add(rack.getTileAt((i + j + 1) / Rack.WIDTH, (i + j + 1) %
								 * Rack.WIDTH)); tileArray.add(rack.getTileAt((i + j + 2) / Rack.WIDTH, (i + j +
								 * 2) % Rack.WIDTH));
								 */
								for (int index = 0; index < 3; index++) {
									tileArray.add(
											rack.getTileAt((i + j + index) / Rack.WIDTH, (i + j + index) % Rack.WIDTH));
									rack.removeTileAt((i + j + index) / Rack.WIDTH, (i + j + index) % Rack.WIDTH);
								}
							}
							if (isEnrolled(tileArray)) { // board�� tile�� ������(���
								// ������)
								// tileArray = new ArrayList<Integer>();
								rack.saveCurrentRack();
							} else { // ��� ��������
								rack.reset();
								// rack.make1Dto2D(tileArray);
							}
						}

					}

				}
			}
		}

	}

	private void makeColSet() {
		rack.sortByColor();// ��������� sort

		ArrayList<Integer> tileArray = new ArrayList<Integer>();

		int index = 0;
		for (int i = 0; i < Rack.HEIGHT - 1; i++) {
			{
				for (int j = 0; j < Rack.WIDTH - 1; j++) {
					if (rack.getTileAt(i, j) != 25 || rack.getTileAt(i, j) != 51 || rack.getTileAt(i, j) != 77
							|| rack.getTileAt(i, j) != 103 || rack.getTileAt(i, j) != 104
							|| rack.getTileAt(i, j) != 105) {// ������ ���� �ƴϸ�, ��Ŀ�ƴϸ�
						if ((rack.getTileAt((i + j + 1) / Rack.WIDTH, (i + j + 1) % Rack.WIDTH) % 2 == 0)) {// id�� ¦���̸�
							if (rack.getTileAt((i + j + 1) / Rack.WIDTH,
									(i + j + 1) % Rack.WIDTH) == rack.getTileAt((i + j) / Rack.WIDTH,
											(i + j) % Rack.WIDTH) + 2
									|| rack.getTileAt((i + j + 1) / Rack.WIDTH, (i + j + 1) % Rack.WIDTH) == rack
											.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH) + 3) { // ���� �� ���ӵ� ����

								tileArray.add(index, rack.getTileAt(i, j));
								rack.removeTileAt(i, j);
								index++;
							} else {// Ȧ���̸�
								if (rack.getTileAt((i + j + 1) / Rack.WIDTH,
										(i + j + 1) % Rack.WIDTH) == rack.getTileAt((i + j) / Rack.WIDTH,
												(i + j) % Rack.WIDTH) + 1
										|| rack.getTileAt((i + j + 1) / Rack.WIDTH, (i + j + 1) % Rack.WIDTH) == rack
												.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH) + 1) { // ���� ��
																												// ���ӵ�
																												// ����

									tileArray.add(index, rack.getTileAt(i, j));
									rack.removeTileAt(i, j);
									index++;
								}
								if (rack.getTileAt((i + j + 1) / Rack.WIDTH, (i + j + 1) % Rack.WIDTH) == rack
										.getTileAt((i + j) / Rack.WIDTH, (i + j) % Rack.WIDTH) + 1) { // ���� �� ���ӵ� ����
									tileArray.add(index, rack.getTileAt(i, j));
									rack.removeTileAt(i, j);
									index++;
								} else { //
									tileArray.add(index, rack.getTileAt(i, j));
									if (index >= 2) {// 3�� �̻��̸�
										if (!isEnrolled(tileArray)) {// ��Ͼ�������
											// rack.make1Dto2D(tileArray);// ���� ���� �ٽ� rack�� �־��ش�.
											rack.reset();
										} else {
											rack.saveCurrentRack(); // TODO
											// tileArray = null;
										}

									} else
										rack.make1Dto2D(tileArray);
								}
								index = 0;
							}
						}
					}

				}
			}
		}
	}

	private void registerRemains() {
		// �����͵��� Ÿ�ϰ� ������
		// rackTile.add(1);//��1
		// rackTile.add(2);//��2
		// rackTile.add(3);//��2

		ArrayList<TileSet> tiles = board.getSet();
		TileSet tileSet;

		ArrayList<Boolean> numOrColor = new ArrayList<Boolean>();// num=t,color=f
		// �޾ƿ� Ÿ���� �������ڵ����� �ƴ� ����Ÿ������
		ArrayList<Integer> remove = new ArrayList<Integer>();

		int index = 0;

		for (int i = 0; i < tiles.size() - 1; i++) {
			tileSet = tiles.get(i);
			if (tileSet.isAllSameNum())
				numOrColor.add(i, true);

			else
				numOrColor.add(i, false);
		}
		for (int i = 0; i < tiles.size() - 1; i++) {
			for (int j = 0; j < rackTile.size() - 1; j++) {
				if (numOrColor.get(i)) {// �÷���
					if (tiles.get(i).getTileSet().get(0) % 26 != 0 || tiles.get(i).getTileSet().get(0) % 26 != 1) {// �Ǿ��߰�
						// �Ǿ��� 1�� �ƴϸ�
						if (tiles.get(i).getTileSet().get(0) % 2 == 0) {// �Ǿ��� ¦���̸�
							if (tiles.get(i).getTileSet().get(0) - 1 == rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH)
									|| tiles.get(i).getTileSet().get(0) - 2 == rack.getTileAt(j / Rack.WIDTH,
											j % Rack.WIDTH)) {// �Ǿռ�����
								// 1�Ǵ� 2�� �۴�

								tiles.get(i).getTileSet().add(0, rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH));// Ÿ��
																													// �Ǿտ�
																													// �߰�
								// board.addTileSet(tileSet);
								board.repaint();
								remove.add(index, j);
								// remove[index] = j;// �� ������ ������ �κ��� �����Ѵ�.
								index++;
							}
						} else {// �Ǿ��� Ȧ���̸�
							if (tiles.get(i).getTileSet().get(0) - 2 == rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH)
									|| tiles.get(i).getTileSet().get(0) - 3 == rack.getTileAt(j / Rack.WIDTH,
											j % Rack.WIDTH)) {// �Ǿռ�����
								// 2�Ǵ� 3�۴�
								tiles.get(i).getTileSet().add(0, rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH));// Ÿ��
																													// �Ǿտ��߰�

								board.repaint();
								remove.add(index, j);
								// remove[index] = j;// �� ������ ������ �κ��� �����Ѵ�.
								index++;
							}
						}

					}

				} else {// ���ڸ�
					if (tiles.get(i).getTileSet().size() == 3) {// Ÿ���� 3���� �̷����������
						if (tiles.get(i).getTileSet().get(0) % 26 == rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH)
								% 26) {// ���ڰ� ����

							if (tiles.get(i).getTileSet().get(0) != rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH)
									&& tiles.get(i).getTileSet().get(1) != rack.getTileAt(j / Rack.WIDTH,
											j % Rack.WIDTH)
									&& tiles.get(i).getTileSet().get(2) != rack.getTileAt(j / Rack.WIDTH,
											j % Rack.WIDTH)) {// ���� ��� �ٸ���

								tiles.get(i).getTileSet().add(rack.getTileAt(j / Rack.WIDTH, j % Rack.WIDTH));// Ÿ�Ͽ� �߰�

								board.repaint();
								remove.add(index, j);
								// remove[index] = j;// �� ������ ������ �ͼ��ڸ� �����صд�.
								index++;
							}

						}
					}
				}
			}
		}
		for (int i = remove.size() - 1; i > -1; i++) {// �� ������ �������ش�.
			rackTile.remove(remove.get(i));
		}

	}

	//
	private boolean isEnrolled(ArrayList<Integer> tempArr) {
		// ����ߴ��� ���ߴ��� �˷��ִ� �Լ�
		int sum = 0;
		if (enrollment) {// ����ߴ�
			board.addTileSet(new TileSet(tempArr));
			board.repaint();
			return true;// Ÿ�Ͽ� �־����.
		} else {// ��Ͼ�������
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