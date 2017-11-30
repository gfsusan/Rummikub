package rummikub;

public class Deck {

	private static Tile[] deck;
	private static final int deckSize = 106;

	// creates complete deck of tiles
	public Deck() {
		deck = new Tile[deckSize];
		int tileID = 0;
		int repeat;

		// create color number tile
		for (int color = 0; color < 4; color++) {
			for (int tileNum = 1; tileNum < 14; tileNum++) {
				repeat = 0;
				while (repeat < 2) {
					deck[tileID] = new Tile(tileID, tileNum);
					tileID++;
					repeat++;
				}
			}
		}

		// create joker tile
		for (int tileNum = 1; tileNum < 3; tileNum++) {
			deck[tileID] = new Tile(tileID, tileNum);
			tileID++;
		}
	}

	// TODO return tile??? when use?
	public static Tile getTile(int num) {
		return deck[num];
	}

}