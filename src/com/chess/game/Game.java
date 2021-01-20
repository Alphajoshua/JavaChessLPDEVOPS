package com.chess.game;

import java.util.List;

import com.chess.game.pieces.Board;
import com.chess.game.pieces.King;
import com.chess.game.pieces.Piece;
import com.chess.game.Spot;

public class Game {
	private Player[] players;
	private Board board;
	private Player playerCurrentTurn;
	private GameStatus status;
	private List<Move> movesPlayed;

	private void initialize(Player p1, Player p2) {
		players[0] = p1;
		players[1] = p2;

		board.resetBoard();

		if (p1.isWhiteSide()) {
			this.playerCurrentTurn = p1;
		} else {
			this.playerCurrentTurn = p2;
		}

		movesPlayed.clear();
	}

	public boolean isEnd() {
		return this.getStatus() != GameStatus.ACTIVE;
	}

	public GameStatus getStatus() {
		return this.status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws Exception {
		Spot startBox = board.getBox(startX, startY);
		Spot endBox = board.getBox(startY, endY);
		Move move = new Move(player, startBox, endBox);
		return this.makeMove(move, player);
	}

	/***
	 * Check if the king is threatened on the board,
	 * iterate all over the board to find the king of the current player,
	 * check if a piece could kill the king
	 * 
	 * @return true if the king is checked, false if safe
	 * @throws Exception
	 */
	public boolean checkKingSafety() throws Exception {
		boolean kingColour = this.playerCurrentTurn.isWhiteSide();
		int kingX = 0;
		int kingY = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; i < 8; i++) {
				Piece piece = this.board.getBoxes()[i][j].getPiece();
				if (piece instanceof King && piece.isWhite() == kingColour) {
					kingX = i;
					kingY = j;
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; i < 8; i++) {
				if (this.board.getBoxes()[i][j].getPiece() != null) {
					Piece piece = this.board.getBoxes()[i][j].getPiece();
					if (piece.canMove(this.board, this.board.getBoxes()[i][j], this.board.getBox(kingX, kingY))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Permit a player to make a move, the turns are alternating. White is the first
	 * play
	 * 
	 * @param move
	 * @param player
	 * @return true if the move is valid, false otherwise
	 */

	private boolean makeMove(Move move, Player player) {
		Piece sourcePiece = move.getStart().getPiece();
		if (sourcePiece == null) {
			return false;
		}

		// valid player?
		if (player != playerCurrentTurn) {
			return false;
		}

		if (sourcePiece.isWhite() != player.isWhiteSide()) {
			return false;
		}

		// valid move?
		if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
			return false;
		}

		// kill? (can not be a King)
		Piece destPiece = move.getEnd().getPiece();
		if (destPiece != null && !(destPiece instanceof King)) {
			destPiece.setKilled(true);
			move.setPieceKilled(destPiece);
		}

		// castling?
		if (sourcePiece != null && sourcePiece instanceof King && ((King) sourcePiece).isCastlingDone()) {
			move.setCastlingMove(true);
		}

		// store the move
		movesPlayed.add(move);

		// move piece from the starting box to end box
		move.getEnd().setPiece(move.getStart().getPiece());
		move.getStart().setPiece(null);

		// TO DO
		// MAKE CONDITIONNAL DEPENDING ON TURN TO CHECK IF THE KING IS THREATEN OR NOT
		if (destPiece != null && destPiece instanceof King) {
			if (player.isWhiteSide()) {
				this.setStatus(GameStatus.WHITE_WIN);
			} else {
				this.setStatus(GameStatus.BLACK_WIN);
			}
		}

		// set the current turn to the other player
		if (this.playerCurrentTurn == players[0]) {
			this.playerCurrentTurn = players[1];
		} else {
			this.playerCurrentTurn = players[0];
		}

		return true;
	}
}
