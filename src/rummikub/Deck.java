package rummikub;

import java.io.IOException;
import java.util.ArrayList;

public class Deck {

	// Tile�����ϰ�,
	private static Tile[] deck;
	private static Tile blank;
	private static ArrayList<Integer> remains;

	private static final int deckSize = 106;

	// creates complete deck of tiles
	public Deck() throws IOException {
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

		blank = new Tile();
	}

	public static Tile getTile(int tileID) {
		return deck[tileID];
	}

	public static Tile getBlankTile() {
		return blank;
	}

	public static int takeTileFromDeck() {
		int random;
		int rID;

		if (remains.size() != 0) {
			random = (int) (Math.random() * remains.size());
			rID = remains.get(random);
			remains.remove(random);

			System.out.println("tile taken from Deck : " + rID);
			return rID;
		} else {
			Rummikub.gameOver("No card in Deck!");
			return -1;
		}
	}

	public static boolean isEmpty() {
		return remains.size() == 0;
	}

}