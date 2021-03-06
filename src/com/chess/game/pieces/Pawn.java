package chess.game.pieces;

import chess.Chess;
import javafx.scene.image.Image;
import chess.game.Board;
import chess.game.Spot;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Pawn extends Piece {

    public boolean isFirstMove = true; // True if a Pawn can use his 2 spots jump for his first move

    public Pawn(int x, int y, boolean white) {
        super(x, y, white);
        this.setImage(new Image("chess/game/pieces/img/" + (white ? "white_" : "black_") + this.getClass().getSimpleName() + ".png"));
        relocate(x * Chess.TILE_SIZE, y * Chess.TILE_SIZE);
        setBackground(new Background(new BackgroundImage(getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        ImageView imgView = new ImageView(getImage());
        getChildren().add(imgView);
    }

    @Override
    public boolean tryMove(int newX, int newY) {
        if (isFirstMove) {
            int x = (int) Math.abs(newX - getOldX()); // Number of rows crossed
            int y = (int) Math.abs(newY - getOldY()); // Number of columns crossed on the y axis			
            if (y == 0 && (x == 1 || x == 2)) {
                isFirstMove = !isFirstMove;
                return true;
            }
        }
        int x = (int) Math.abs(newX - getOldX()); // Number of rows crossed
        int y = (int) Math.abs(newY - getOldY()); // Number of columns crossed on the y axis	
        return y == 0 && x == 1;
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        // TO DO : Check if he can eat a piece on an adjacent diagonal                    
        if (end.getPiece() != null) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                return false;
            }
        }

        if (isFirstMove) {
            int x = (int) Math.abs(start.getX() - end.getX()); // Number of rows crossed
            int y = (int) Math.abs(start.getY() - end.getY()); // Number of columns crossed on the y axis			
            if (y == 0 && (x == 1 || x == 2)) {
                isFirstMove = !isFirstMove;
                return true;
            }
        }
        int x = (int) Math.abs(start.getX() - end.getX()); // Number of rows crossed
        int y = (int) Math.abs(start.getY() - end.getY()); // Number of columns crossed on the y axis
        return y == 0 && x == 1;
    }

    @Override
    public String toString() {
        String couleur = "Black";
        if (this.isWhite()) {
            couleur = "White";
        }
        return couleur + " " + this.getClass().getSimpleName();
    }
}
