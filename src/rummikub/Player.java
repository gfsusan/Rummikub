package rummikub;

/**
 * represents the human player
 */
public class Player {
	protected Rack rack;

	public Player(Rack playerRack) {
		this.rack = playerRack;

	}

	public Rack getRack() {
		return rack;
	}

	public boolean hasRegistered() {
		return rack.hasRegistered();
	}

	public void drawFourTiles() {
		rack.drawFourTiles();
	}

}
