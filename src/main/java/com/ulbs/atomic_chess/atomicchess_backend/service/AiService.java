package com.ulbs.atomic_chess.atomicchess_backend.service;

import com.ulbs.atomic_chess.atomicchess_backend.model.Board;
import com.ulbs.atomic_chess.atomicchess_backend.model.GameState;
import com.ulbs.atomic_chess.atomicchess_backend.model.Move;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AiService {

    private final GameService gameService;
    private final Random random = new Random();

    public AiService(GameService gameService) {
        this.gameService = gameService;
    }

    public GameState makeMove() {
        GameState gameState = gameService.getGameState();
        Board board = gameState.getBoard();
        List<Move> possibleMoves = findAllPossibleMoves(board);

        if (possibleMoves.isEmpty()) {
            gameState.setStatus("STALEMATE");
            return gameState;
        }

        // Pick a random move
        Move move = possibleMoves.get(random.nextInt(possibleMoves.size()));
        gameService.playerMove(move); // Apply move through GameService

        return gameService.getGameState();
    }

    // Very basic random move generator
    private List<Move> findAllPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        String[][] state = board.getBoard();

        for (int fromX = 0; fromX < 8; fromX++) {
            for (int fromY = 0; fromY < 8; fromY++) {
                String piece = state[fromX][fromY];
                if (piece != null && piece.startsWith("b")) { // AI plays black
                    // Try simple forward move (for pawns)
                    int toX = fromX - 1;
                    int toY = fromY;
                    if (toX >= 0 && state[toX][toY] == null) {
                        moves.add(createMove(fromX, fromY, toX, toY));
                    }
                    // Try capture left
                    if (toX >= 0 && toY - 1 >= 0 && state[toX][toY - 1] != null && state[toX][toY - 1].startsWith("w")) {
                        moves.add(createMove(fromX, fromY, toX, toY - 1));
                    }
                    // Try capture right
                    if (toX >= 0 && toY + 1 < 8 && state[toX][toY + 1] != null && state[toX][toY + 1].startsWith("w")) {
                        moves.add(createMove(fromX, fromY, toX, toY + 1));
                    }
                }
            }
        }
        return moves;
    }

    private Move createMove(int fromX, int fromY, int toX, int toY) {
        Move move = new Move();
        move.setFromX(fromX);
        move.setFromY(fromY);
        move.setToX(toX);
        move.setToY(toY);
        return move;
    }
}