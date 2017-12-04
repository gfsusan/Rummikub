package rummikub;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoardListener implements MouseListener, MouseMotionListener {

	private Board board;
	static int tileID = 0;
	private int fromX = 0, fromY = 0, toX = 0, toY = 0;
	private int pressedTileID = -1;
	private boolean dragging = false;
	private static boolean pressedInBoard = false;

	public BoardListener(Board board) {
		this.board = board;
	}

	public static void setTileID(int id) {
		tileID = id;
	}

	public static boolean isHoldingFromBoard() {
		return pressedInBoard;
	}

	public static void setIsHoldingTileFromBoard(boolean holdingState) {
		pressedInBoard = holdingState;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		if (!dragging && isHoldingFromBoard()) {
			fromX = event.getX() / 45;
			fromY = event.getY() / 60;
			dragging = true;
		}

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
			if (!board.isEmptyCell(yIndex, xIndex)) {
				int id = board.getTileAt(yIndex, xIndex);
				System.out.println(id);
				Rummikub.setWhichTile(id);
				Rummikub.setTileFromBoard(true);
				board.removeTileAt(yIndex, xIndex);
				Rummikub.toggleAdding();
			}
		}
		// 놓는 경우
		else {
			if (board.isEmptyCell(yIndex, xIndex)) {
				board.addTileAt(yIndex, xIndex);
				Rummikub.toggleAdding();
				Rummikub.setTileFromBoard(false);
			}
			
		}

		/*
		 * if (Rack.isHoldingFromRack()) { // rack->board if ((xIndex < Board.WIDTH) &&
		 * (yIndex < Board.HEIGHT)) { // board 범위 내 if (board.isEmptyCell(xIndex,
		 * yIndex)) { // cell is empty board.addTile(xIndex, yIndex);
		 * System.out.println("tile is added on the board"); } else { // cell is not
		 * empty System.out.println("not an empty cell - cannot put tile here (" +
		 * xIndex + ", " + yIndex + ")"); }
		 * 
		 * } else { // board 범위 밖 System.out.println("not existing place"); } } else {
		 * // board->board if ((xIndex < Board.WIDTH) && (yIndex < Board.HEIGHT)) { if
		 * (!board.isEmptyCell(xIndex, yIndex)) { // cell has a tile pressedTileID =
		 * board.getCurrentTile(xIndex, yIndex); // tile 안 없애도 되나? } else { // cell is
		 * empty ; } } }
		 */
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		/*
		 * if (dragging && isHoldingFromBoard()) { // board->board toX =
		 * event.getX()/45; toY = event.getY()/60;
		 * 
		 * if(toX < Board.WIDTH && toY < Board.HEIGHT) { if (!board.isEmptyCell(toX,
		 * toY)) { // 옮기려는 곳이 이미 타일이 있음 //board.moveTile(fromX, fromY, toX, toY); } else
		 * {
		 * 
		 * }
		 * 
		 * } }
		 */

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

}
