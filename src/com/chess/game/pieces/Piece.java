package com.chess.game.pieces;

import com.chess.game.Spot;

/**
 * Represent a chess piece
 * @author Malik
 *
 */

public abstract class Piece { 
	  
    private boolean killed = false; 
    private boolean white = false; // False = Black Piece
  
    public Piece(boolean white) 
    { 
        this.setWhite(white); 
    }
  
    public boolean isWhite() 
    { 
        return this.white; 
    } 
  
    public void setWhite(boolean white) 
    { 
        this.white = white; 
    } 
  
    public boolean isKilled() 
    { 
        return this.killed; 
    } 
  
    public void setKilled(boolean killed) 
    { 
        this.killed = killed; 
    }
  
    public abstract boolean canMove(Board board,  
                                 Spot start, Spot end);
} 
