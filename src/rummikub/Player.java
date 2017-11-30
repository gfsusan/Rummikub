package rummikub;

/**
 * represents the human player
 */
public class Player {
	Rack rack;
	// tileTaken�� �ʿ� X
	boolean isTurn, tilePlaced;

	public Player() {
		isTurn = true;
		tilePlaced = false;
		//TODO ������ ����
		rack = new Rack();
	}

	public Player(Rack playerRack) {
		this();
		this.rack=playerRack;
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
