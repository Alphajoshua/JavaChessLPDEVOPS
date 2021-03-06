package chess.game;

import chess.Chess;
import chess.game.pieces.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a spot on the board
 * Allows to retrieve the piece on it
 * @author Malik
 *
 */

public class Spot extends Rectangle { 
    private Piece piece;
    private int x; 
    private int y;
  
    public Spot(boolean light, int x, int y, Piece piece) 
    { 
        this.setPiece(piece); 
        this.setX(x); 
        this.setY(y);
        setWidth(Chess.TILE_SIZE);
        setHeight(Chess.TILE_SIZE);
        
        relocate(x * Chess.TILE_SIZE, y * Chess.TILE_SIZE);
        
        setFill(light ? Color.valueOf("#F1D9B5") : Color.valueOf("#B58863"));
    } 
  
    /**
     * 
     * @return the Chess Piece on this spot
     */
    public Piece getPiece() 
    {       
        return this.piece; 
    } 
    
    public boolean hasPiece()
    {
        if(this.getPiece() != null)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Put the p Piece on this spot
     * @param p
     */
  
    public void setPiece(Piece p) 
    { 
        this.piece = p; 
    }     
  
    public void setX(int x) 
    { 
        this.x = x; 
    } 
  
  
    public void setY(int y) 
    { 
        this.y = y; 
    } 
} 
