package com.chess.game.pieces;

import com.chess.game.Spot;

/**
 * The board, players are playing on
 * @author Malik
 *
 */

public class Board { 
    Spot[][] boxes; 
  
    public Board() 
    { 
        this.resetBoard(); 
    }
    
    public Spot[][] getBoxes()
    {
    	return this.boxes;
    }
  
    public Spot getBox(int x, int y) throws Exception 
    {   
        if (x < 0 || x > 7 || y < 0 || y > 7) { 
            throw new Exception("Index out of bound"); 
        }   
        return boxes[x][y]; 
    } 
    
    public void resetBoard() 
    { 
        // initialize white side 
        boxes[0][0] = new Spot(0, 0, new Rook(true)); 
        boxes[0][1] = new Spot(0, 1, new Knight(true)); 
        boxes[0][2] = new Spot(0, 2, new Bishop(true)); 
        boxes[0][3] = new Spot(0, 3, new Queen(true));
        boxes[0][4] = new Spot(0, 4, new King(true));
        boxes[0][5] = new Spot(0, 5, new Bishop(true));
        boxes[0][6] = new Spot(0, 6, new Knight(true));
        boxes[0][7] = new Spot(0, 7, new Rook(true));
        for(int j = 0; j < 8; j++)
        {
        	boxes[1][j] = new Spot(1, j, new Pawn(true));
        }
  
        // initialize black side 
        boxes[7][0] = new Spot(7, 0, new Rook(false)); 
        boxes[7][1] = new Spot(7, 1, new Knight(false)); 
        boxes[7][2] = new Spot(7, 2, new Bishop(false)); 
        boxes[7][3] = new Spot(7, 3, new Queen(false)); 
        boxes[7][4] = new Spot(7, 4, new King(false)); 
        boxes[7][5] = new Spot(7, 5, new Bishop(false)); 
        boxes[7][6] = new Spot(7, 6, new Knight(false)); 
        boxes[7][7] = new Spot(7, 7, new Rook(false)); 
        
        for(int j = 0; j < 8; j++)
        {
        	boxes[6][j] = new Spot(1, j, new Pawn(true));
        }
  
        // initialize remaining spots without any piece 
        for (int i = 2; i < 6; i++) { 
            for (int j = 0; j < 8; j++) { 
                boxes[i][j] = new Spot(i, j, null);
            } 
        } 
    } 
} 