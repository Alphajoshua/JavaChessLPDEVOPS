package com.chess.game;

import com.chess.game.pieces.Piece;

/**
 * Represents a spot on the board
 * Allows to retrieve the piece on it
 * @author Malik
 *
 */

public class Spot { 
    private Piece piece;
    private int x; 
    private int y; 
  
    public Spot(int x, int y, Piece piece) 
    { 
        this.setPiece(piece); 
        this.setX(x); 
        this.setY(y);
    } 
  
    /**
     * 
     * @return the Chess Piece on this spot
     */
    public Piece getPiece() 
    { 
        return this.piece; 
    } 
    
    /**
     * Put the p Piece on this spot
     * @param p
     */
  
    public void setPiece(Piece p) 
    { 
        this.piece = p; 
    } 
  
    public int getX() 
    { 
        return this.x; 
    } 
  
    public void setX(int x) 
    { 
        this.x = x; 
    } 
  
    public int getY() 
    { 
        return this.y; 
    } 
  
    public void setY(int y) 
    { 
        this.y = y; 
    } 
} 
