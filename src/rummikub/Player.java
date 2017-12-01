package rummikub;

/**
 * represents the human player
 */
public class Player {
	Rack rack;
	// tileTaken¿∫ « ø‰ X
	boolean isTurn, tilePlaced;

	public Player(Rack playerRack) {
		this.rack = playerRack;
		isTurn = true;
		tilePlaced = false;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public boolean isTurn() {
		return this.isTurn;
	}

	public boolean tilePlaced() {
		return this.tilePlaced;
	}

}
