package rummikub;

import java.util.ArrayList;

public class TileSet {

	private ArrayList<Integer> set = new ArrayList<Integer>();

	public TileSet(ArrayList<Integer> rset) {
		this.set = rset;
	}

	// 이 함수 부르기 전에 처음 "등록"했는지 체크하고 이 함수 부르기
	public boolean isValidSet() {
		if (this.getSize() >= 3) { // 3개 이상
			if (isAllSameNum()) // 같은 숫자 다른 색
				return true;
			else
				return (isSequence()); // return if 같은 색, 연속된 숫자
		} else
			return false; // 3개 미만
	}

	// tile이 모두 같은 수이고 색이 겹치지는 않는 지 check
	private boolean isAllSameNum() {
		int num = -1; // repeated value
		int jkCounter = 0; // joker 개수
		int colorNum = 0; // colorCount가 1인 count 개수
		int[] colorCount = new int[4]; // 각 color의 tiles 개수

		// 조커가 아닌 첫 타일의 value를 num에 저장
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) < 104) {
				num = Deck.getTile(set.get(i)).getTileNum();
				break;
			}
		}

		//
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) >= 104) { // 조커이면 jkCounter 1 증가
				jkCounter++;
				continue;
			} else { // 1~13 숫자
				if (num != Deck.getTile(set.get(i)).getTileNum()) { // 숫자가 같지 않으면
					num = -1;
					break;
				} else { // 숫자가 같으면
					int color = Deck.getTile(set.get(i)).getIntTileColor(); // i번째 tile의 color 반환
					colorCount[color]++;
				}
			}
		}

		if (num != -1) { // 숫자가 모두 같으면
			for (int i = 0; i < 4; i++) { // color의 true 개수
				if (colorCount[i] == 2)
					return false;
				else if (colorCount[i] == 1)
					colorNum++;
			}
			return (colorNum == set.size() - jkCounter);

		} else
			return false;

	}

	// tile이 모두 같은 색이고 연속된 수임을 check
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

	public void print() {
		for (int i = 0; i < set.size(); i++)
			System.out.print(set.get(i) + " ");
		System.out.print("\n");
	}
}