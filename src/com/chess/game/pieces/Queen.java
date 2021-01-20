package com.chess.game.pieces;

import com.chess.game.Spot;

public class Queen extends Piece {

	public Queen(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		// we can't move the piece to a spot that has
		// a piece of the same colour
		if (end.getPiece().isWhite() == this.isWhite()) {
			return false;
		}
		
		// The queen can get anywhere, except on friendly spots
		return true; 
	}

}
