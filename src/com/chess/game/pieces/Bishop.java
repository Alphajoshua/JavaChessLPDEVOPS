package chess.game.pieces;

import chess.Chess;
import chess.game.Board;
import chess.game.Spot;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Bishop extends Piece {   
    
    public Bishop(int x, int y, boolean white) {
        super(x, y, white);              
        this.setImage(new Image("chess/game/pieces/img/"+ (white ? "white_" : "black_") + this.getClass().getSimpleName() +".png"));
        relocate(x * Chess.TILE_SIZE, y * Chess.TILE_SIZE);
        setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ImageView imgView = new ImageView(getImage());
        getChildren().add(imgView);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // we can't move the piece to a spot that has
        // a piece of the same colour
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = (int) Math.abs(start.getX() - end.getX()); // Number of tiles crossed on the x axis
        int y = (int) Math.abs(start.getY() - end.getY()); // Number of tiles crossed on the y axis
        return x == y;
    }

    @Override
    public String toString() {
        String couleur = "Black";
        if (this.isWhite()) {
            couleur = "White";
        }
        return couleur + " " + this.getClass().getSimpleName();
    }

    @Override
    public boolean tryMove(int newX, int newY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
