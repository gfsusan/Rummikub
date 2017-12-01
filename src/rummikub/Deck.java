package rummikub;

import java.util.ArrayList;

public class Deck {

	// Tile생성하고,
	private static Tile[] deck;
	private static ArrayList<Integer> remains;

	private static final int deckSize = 106;

	// creates complete deck of tiles
	public Deck() {
		deck = new Tile[deckSize];
		remains = new ArrayList<Integer>();
		int tileID = 0;
		int repeat;

		// create color number tile
		for (int color = 0; color < 4; color++) {
			for (int tileNum = 1; tileNum < 14; tileNum++) {
				repeat = 0;
				while (repeat < 2) {
					deck[tileID] = new Tile(tileID, tileNum);
					remains.add(tileID);
					tileID++;
					repeat++;
				}
			}
		}

		// create joker tile
		for (int tileNum = 1; tileNum < 3; tileNum++) {
			deck[tileID] = new Tile(tileID, tileNum);
			remains.add(tileID);
			tileID++;
		}
	}

	// TODO return tile??? when use?
	public static Tile getTile(int tileID) {
		return deck[tileID];
	}

	public static int takeTileFromDeck() {
		int random = (int) (Math.random() * remains.size());
		int rVal = remains.get(random);
		remains.remove(random);
		
		return rVal;
	}

}