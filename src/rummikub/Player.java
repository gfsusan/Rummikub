package rummikub;

/**
 * represents the human player
 */
public class Player {
	Rack rack;
	// tileTaken�� �ʿ� X
	boolean isTurn, tilePlaced;

	public Player(Rack playerRack) {
		this.rack = playerRack;
		isTurn = true;
		tilePlaced = false;
	}

	// set player's turn
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public void setTilePlaced(boolean tilePlaced) {
		this.tilePlaced=tilePlaced;
	}
	
	//
	public boolean isTurn() {
		return this.isTurn;
	}
	
	public boolean tilePlaced() {
		return this.tilePlaced;
	}

}
