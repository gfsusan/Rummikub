package rummikub;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RackListener implements MouseListener {

	Rack rack;
	
	public RackListener (Rack playersRack) {
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
		
		if (true/* ��� ����, ���忡 ������ �ʾҰ�, Ÿ���� ���������� ���� ����*/) {
			int x_pos = event.getX();
			int y_pos = event.getY();
			int widthIndex = x_pos/45;
			int heightIndex = y_pos/60;
			
			if (widthIndex < Rack.WIDTH && heightIndex < Rack.HEIGHT) {
				Tile pressedTile = Deck.getTile(rack.getTileID(widthIndex + heightIndex));
				int pTileID = pressedTile.getTileID();
				// TODO Ÿ�� ���忡 ���� true
				// TODO Ÿ�� ���忡���� Rummikub.putTileDown(widthIndex, heightIndex, ""); || Rummikub.putTileDown(pTileID, "");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
