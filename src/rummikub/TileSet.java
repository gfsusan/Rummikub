package rummikub;

import java.util.ArrayList;

public class TileSet {

	private ArrayList<Integer> set = new ArrayList<Integer>();

	public TileSet(ArrayList<Integer> rset) {
		this.set = rset;
	}

	// �� �Լ� �θ��� ���� ó�� "���"�ߴ��� üũ�ϰ� �� �Լ� �θ���
	public boolean isValidSet() {
		if (this.getSize() >= 3) { // 3�� �̻�
			if (isAllSameNum()) // ���� ���� �ٸ� ��
				return true;
			else
				return (isSequence()); // return if ���� ��, ���ӵ� ����
		} else
			return false; // 3�� �̸�
	}

	// tile�� ��� ���� ���̰� ���� ��ġ���� �ʴ� �� check
	private boolean isAllSameNum() {
		int num = -1; // repeated value
		int jkCounter = 0; // joker ����
		int colorNum = 0; // colorCount�� 1�� count ����
		int[] colorCount = new int[4]; // �� color�� tiles ����

		// ��Ŀ�� �ƴ� ù Ÿ���� value�� num�� ����
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) < 104) {
				num = Deck.getTile(set.get(i)).getTileNum();
				break;
			}
		}

		//
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) >= 104) { // ��Ŀ�̸� jkCounter 1 ����
				jkCounter++;
				continue;
			} else { // 1~13 ����
				if (num != Deck.getTile(set.get(i)).getTileNum()) { // ���ڰ� ���� ������
					num = -1;
					break;
				} else { // ���ڰ� ������
					int color = Deck.getTile(set.get(i)).getIntTileColor(); // i��° tile�� color ��ȯ
					colorCount[color]++;
				}
			}
		}

		if (num != -1) { // ���ڰ� ��� ������
			for (int i = 0; i < 4; i++) { // color�� true ����
				if (colorCount[i] == 2)
					return false;
			}
			return (colorNum == set.size() - jkCounter);

		} else
			return false;

	}

	// tile�� ��� ���� ���̰� ���ӵ� ������ check
	private boolean isSequence() {
		int color = -1, startValue = -1, startIndex = -1;

		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) < 104) {
				startIndex = i;
				startValue = Deck.getTile(set.get(i)).getTileNum();
				color = Deck.getTile(set.get(i)).getIntTileColor();
				break;
			}
		}

		for (int i = startIndex; i < set.size(); i++) {
			if (set.get(i) >= 104) {
				startValue++;
				continue;
			} else if (startValue != Deck.getTile(set.get(i)).getTileNum()
					|| color != Deck.getTile(set.get(i)).getIntTileColor())
				return false;
			else {
				startValue++;
			}
		}
		return true;

	}

	public int getSize() {
		return set.size();
	}

	public ArrayList<Integer> getTileSet() {
		return this.set;
	}
}