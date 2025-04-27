package com.ulbs.atomic_chess.atomicchess_backend.model;

public class Board {
    private String[][] board; // 8x8 board, each piece is a string like "wP" (white pawn), "bK" (black king)

    public Board() {
        board = new String[8][8];
    }

    public static Board initialSetup() {
        Board b = new Board();
        // Setup pawns
        for (int i = 0; i < 8; i++) {
            b.board[1][i] = "wP"; // white pawn
            b.board[6][i] = "bP"; // black pawn
        }
        // Setup Kings
        b.board[0][4] = "wK";
        b.board[7][4] = "bK";
        // You can expand to Rooks, Knights, Queens, Bishops later
        return b;
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        board[toX][toY] = board[fromX][fromY];
        board[fromX][fromY] = null;
    }

    public String[][] getBoard() {
        return board;
    }
}