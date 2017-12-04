package rummikub;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RackListener implements MouseListener {

	private Rack rack;

	public RackListener(Rack playersRack) {
		this.rack = playersRack;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		int x_pos = event.getX();
		int y_pos = event.getY();
		int xIndex = x_pos / 45;
		int yIndex = y_pos / 60;

		// 집는 경우
		if (!Rummikub.isAddingTile()) {
			if (!rack.isEmptyCell(xIndex, yIndex)) {
				Rummikub.setWhichTile(rack.getTileAt(xIndex, yIndex));
				Rummikub.setTileFromBoard(false);
				//rack.removeTileAt(xIndex, yIndex);
			}	
		}
		// 놓는 경우
		else {
			if (rack.isEmptyCell(xIndex, yIndex)) {
				//rack.addTileAt(xIndex, yIndex);
			}
		}
		Rummikub.toggleAdding();
	}


	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
