package chess.game.pieces;

import chess.Chess;
import chess.game.Board;
import chess.game.Spot;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * Represent a chess piece
 *
 * @author Malik
 *
 */
public abstract class Piece extends StackPane {
    
    public Image image;
    // Movement controls variables
    private double mouseX, mouseY;
    private double oldX, oldY;

    private boolean killed = false;
    private boolean white = false; // False = Black Piece

    public Piece(int x, int y, boolean white) {
        this.setWhite(white);
        move(x, y);
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
        setOnMouseReleased(e -> {
            oldX = e.getSceneX();
            oldY = e.getSceneY();
            
            int newX = toBoard(this.getLayoutX());
            int newY = toBoard(this.getLayoutY());

            // move(newX, newY);            
        });
    }

    public int toBoard(double pixel) {
        return (int) (pixel * Chess.TILE_SIZE / 2) / Chess.TILE_SIZE;
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        String couleur = "Black";
        if (this.isWhite()) {
            couleur = "White";
        }
        return couleur + " " + this.getClass().getSimpleName();
    }

    public abstract boolean canMove(Board board,
            Spot start, Spot end);

    public abstract boolean tryMove(int newX, int newY);

    public void move(int x, int y) {
        oldX = x * Chess.TILE_SIZE;
        oldY = y * Chess.TILE_SIZE;
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }

    public double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public double getOldX() {
        return oldX;
    }

    public void setOldX(double oldX) {
        this.oldX = oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public void setOldY(double oldY) {
        this.oldY = oldY;
    }
}
