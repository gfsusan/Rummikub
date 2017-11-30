package rummikub;

/**
 * represents the human player
 */
public class Player {
	Rack rack;
	// tileTaken은 필요 X
	boolean isTurn, tilePlaced;

	public Player() {
		isTurn = true;
		tilePlaced = false;
		//TODO 생성자 수정
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
