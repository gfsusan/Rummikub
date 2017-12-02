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
