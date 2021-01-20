package com.chess.game.pieces;

import com.chess.game.Spot;

/**
 * Represents a King Piece
 * @author Malik
 *
 */

public class King extends Piece {
	private boolean castlingDone = false; 
	private boolean isChecked = false;
	  
    public King(boolean white) 
    { 
        super(white); 
    } 
  
    public boolean isCastlingDone() 
    { 
        return this.castlingDone; 
    } 
  
    public void setCastlingDone(boolean castlingDone) 
    { 
        this.castlingDone = castlingDone; 
    } 
    
    /***
     * 
     */
    

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		
		// we can't move the piece to a Spot that
		// has a piece of the same color
		if (end.getPiece().isWhite() == this.isWhite()) {
			return false;
		}

		int x = Math.abs(start.getX() - end.getX()); // Number of tiles crossed on the x axis
		int y = Math.abs(start.getY() - end.getY()); // Number of tiles crossed on the y axis
		if (x + y == 1) {
			// TO-DO
			// check if this move will not result in the king
			// being attacked if so return true
			return true;
		}	
		
		return false;
	}

}
