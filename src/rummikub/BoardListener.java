package rummikub;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoardListener implements MouseListener, MouseMotionListener {

	Board board;
	static int tileID = 0;
	int fromX = 0, fromY = 0, toX = 0, toY = 0;

	public BoardListener(Board board) {
		this.board = board;
	}

	public static void setTileID(int id) {
		tileID = id;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub

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

		
		/*if (true/* 루미 큐브 보드에 add되면 ) {
			
			if ((xIndex < Board.WIDTH) && (yIndex < Board.HEIGHT)) {
				if (board.isEmptyCell(xIndex, yIndex)) {
					board.addTile(xIndex, yIndex);
				} else {
					System.out.println("not an empty cell - cannot put tile here (" + xIndex + ", " + yIndex + ")");
				}

			}
			else {
				System.out.println("not existing place");
			}
		} else { // moved in the board
			if ((xIndex < Board.WIDTH) && (yIndex < Board.HEIGHT)) {
			;	
			}

		}
		*/

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

}
