package com.chess.game.pieces;

import com.chess.game.Spot;

public class Bishop extends Piece {

	public Bishop(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		// we can't move the piece to a spot that has
		// a piece of the same colour
		if (end.getPiece().isWhite() == this.isWhite()) {
			return false;
		}

		int x = Math.abs(start.getX() - end.getX()); // Number of tiles crossed on the x axis
		int y = Math.abs(start.getY() - end.getY()); // Number of tiles crossed on the y axis
		return x == y;
	}

}
