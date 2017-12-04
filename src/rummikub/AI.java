package rummikub;

public class AI extends Player {
	private Rack rack;

	public AI(Rack myRack) {
		super(myRack);
		this.rack = myRack;
	}

	public void takeTurn() {
		// turn !
		System.out.println("AI took its turn.");
		getRack().repaint();
	}
}
