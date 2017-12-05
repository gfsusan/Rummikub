package rummikub;

/**
 * represents the human player
 */
public class Player {
	protected Rack rack;

	protected boolean firstReg;
	
	public Player(Rack playerRack) {
		this.rack = playerRack;
		firstReg=false;
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

	public boolean hasFirstReg() {
		return firstReg;
	}

	public void setFirstReg(boolean flag) {
		firstReg = flag;
	}


}
