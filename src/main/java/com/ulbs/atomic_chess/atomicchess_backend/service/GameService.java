package com.ulbs.atomic_chess.atomicchess_backend.service;

import com.ulbs.atomic_chess.atomicchess_backend.model.Board;
import com.ulbs.atomic_chess.atomicchess_backend.model.Move;
import com.ulbs.atomic_chess.atomicchess_backend.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameState gameState;

    public GameService() {
        startNewGame();
    }

    // Start a fresh game
    public void startNewGame() {
        this.gameState = new GameState();
        this.gameState.setBoard(Board.initialSetup());
        this.gameState.setWhiteTurn(true);
        this.gameState.setStatus("ONGOING");
    }

    // Player makes a move
    public GameState playerMove(Move move) {
        if (gameState == null) {
            throw new IllegalStateException("Game not started!");
        }

        if (isMoveLegal(move)) {
            applyMove(move);
            checkGameStatus();
            switchTurn();
        } else {
            throw new IllegalArgumentException("Illegal move!");
        }

        return gameState;
    }

    // Simple move validation (can get complex later!)
    private boolean isMoveLegal(Move move) {
        // For now: only allow any move inside board limits
        int fromX = move.getFromX();
        int fromY = move.getFromY();
        int toX = move.getToX();
        int toY = move.getToY();

        return isInsideBoard(fromX, fromY) && isInsideBoard(toX, toY);
    }

    private boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    // Actually apply the move
    private void applyMove(Move move) {
        Board board = gameState.getBoard();
        board.movePiece(move.getFromX(), move.getFromY(), move.getToX(), move.getToY());
    }

    private void checkGameStatus() {
        // Later: implement checkmate, stalemate detection
        // For now: always ongoing
        gameState.setStatus("ONGOING");
    }

    private void switchTurn() {
        gameState.setWhiteTurn(!gameState.isWhiteTurn());
    }

    // Get the current state
    public GameState getGameState() {
        return gameState;
    }
}