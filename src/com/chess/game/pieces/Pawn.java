package com.chess.game.pieces;

import com.chess.game.Spot;

public class Pawn extends Piece {

	public boolean isFirstMove = true; // True if a Pawn can use his 2 spots jump for his first move

	public Pawn(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		
		// TO DO : Check if he can eat a piece on an adjacent diagonal

		if (end.getPiece().isWhite() == this.isWhite()) {
			return false;
		}

		if (isFirstMove) {
			int x = Math.abs(start.getX() - end.getX()); // Number of tiles crossed on the x axis
			int y = Math.abs(start.getY() - end.getY()); // Number of tiles crossed on the y axis
			if (x == 0 && y == 1 || y == 2) {
				isFirstMove = !isFirstMove;
				return true;
			}
		}
		
		int x = Math.abs(start.getX() - end.getX()); // Number of tiles crossed on the x axis
		int y = Math.abs(start.getY() - end.getY()); // Number of tiles crossed on the y axis
		return x == 0 && y == 1;
	}

}
