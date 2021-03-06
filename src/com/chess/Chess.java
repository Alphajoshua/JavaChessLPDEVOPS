/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.game.*;
import chess.game.pieces.*;
import java.awt.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author p2019111
 */
public class Chess extends Application {

    /**
     * @param args the command line arguments
     */
    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Spot[][] board = new Spot[WIDTH][HEIGHT];

    private Group spotGroup = new Group();
    private Group pieceGroup = new Group();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(spotGroup, pieceGroup);
        // White Side
        Piece p = null;
        p = new Rook(7, 7, true);
        Spot spot = new Spot((7 + 7) % 2 == 0, 7, 7, p); // set Piece to the spot
        board[7][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Knight(6, 7, true);
        spot = new Spot((6 + 7) % 2 == 0, 6, 7, p); // set Piece to the spot
        board[6][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Bishop(5, 7, true);
        spot = new Spot((5 + 7) % 2 == 0, 5, 7, p); // set Piece to the spot
        board[5][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new King(4, 7, true);
        spot = new Spot((4 + 7) % 2 == 0, 4, 7, p); // set Piece to the spot
        board[4][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Queen(3, 7, true);
        spot = new Spot((3 + 7) % 2 == 0, 3, 7, p); // set Piece to the spot
        board[3][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Bishop(2, 7, true);
        spot = new Spot((2 + 7) % 2 == 0, 2, 7, p); // set Piece to the spot
        board[2][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Knight(1, 7, true);
        spot = new Spot((1 + 7) % 2 == 0, 1, 7, p); // set Piece to the spot
        board[1][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Rook(0, 7, true);
        spot = new Spot((0 + 7) % 2 == 0, 0, 7, p); // set Piece to the spot
        board[0][7] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);

        // Black Side
        p = null;
        p = new Rook(7, 0, false);
        spot = new Spot((7 + 0) % 2 == 0, 7, 0, p); // set Piece to the spot
        board[7][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Knight(6, 0, false);
        spot = new Spot((6 + 0) % 2 == 0, 6, 0, p); // set Piece to the spot
        board[6][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Bishop(5, 0, false);
        spot = new Spot((5 + 0) % 2 == 0, 5, 0, p); // set Piece to the spot
        board[5][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new King(4, 0, false);
        spot = new Spot((4 + 0) % 2 == 0, 4, 0, p); // set Piece to the spot
        board[4][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Queen(3, 0, false);
        spot = new Spot((3 + 0) % 2 == 0, 3, 0, p); // set Piece to the spot
        board[3][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Bishop(2, 0, false);
        spot = new Spot((2 + 0) % 2 == 0, 2, 0, p); // set Piece to the spot
        board[2][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Knight(1, 0, false);
        spot = new Spot((1 + 0) % 2 == 0, 1, 0, p); // set Piece to the spot
        board[1][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);
        p = new Rook(0, 0, false);
        spot = new Spot((0 + 0) % 2 == 0, 0, 0, p); // set Piece to the spot
        board[0][0] = spot;
        spotGroup.getChildren().add(spot);
        pieceGroup.getChildren().add(p);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (y == 1) {
                    p = new Pawn(x, y, false);
                    spot = new Spot((x + y) % 2 == 0, x, y, p);
                    board[x][y] = spot;
                    spotGroup.getChildren().add(spot);
                    pieceGroup.getChildren().add(p);
                } else if (y == 6) {
                    p = new Pawn(x, y, true);
                    spot = new Spot((x + y) % 2 == 0, x, y, p);
                    board[x][y] = spot;
                    spotGroup.getChildren().add(spot);
                    pieceGroup.getChildren().add(p);
                } else {
                    spot = new Spot((x + y) % 2 == 0, x, y, null);
                    board[x][y] = spot;
                    spotGroup.getChildren().add(spot);
                }
            }
        }

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // GridPane chessBoard = new GridPane();
        primaryStage.setMaxHeight(TILE_SIZE * HEIGHT + 25);
        primaryStage.setMaxWidth(TILE_SIZE * WIDTH + 6);
        primaryStage.setResizable(false);
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();

        // Creating a new Board, automatically initialize all spots with the pieces
    }
    
    private int toBoard(double pixel) {
        return (int) (pixel * Chess.TILE_SIZE / 2) / Chess.TILE_SIZE;
    }
    
    public boolean tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece()) {
            if (board[newX][newY].getPiece().isWhite() == piece.isWhite()) {
                return false;
            }
        } else {
            int x0 = piece.toBoard(piece.getOldX());
            int y0 = piece.toBoard(piece.getOldY());
            if (piece.tryMove(newX, newY)) {
                if (board[newX][newY].getPiece().isWhite() != piece.isWhite()) {
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                }
            }
        }
        return false;
    }   

    public static void main(String[] args) throws Exception {
        launch(args);

        Board gameBoard = new Board();
        // Creating the player
        Player whitePlayer = new Player();
        whitePlayer.whiteSide = true;
        Player blackPlayer = new Player();
        blackPlayer.whiteSide = false;
        // Creating the game "referee", which will take care of the logic        
        Game game = new Game(gameBoard);
        game.initialize(whitePlayer, blackPlayer);
        /*
        //System.out.println(gameBoard.getBox(1,0).getPiece().toString());   
        game.playerMove(whitePlayer, 1, 0, 3, 0);
        //System.out.println(gameBoard.getBox(3, 0).getPiece().toString());
        game.playerMove(blackPlayer, 6, 0, 4 , 0);
        game.playerMove(whitePlayer, 3, 0, 4 ,0);  
         */
    }
}
