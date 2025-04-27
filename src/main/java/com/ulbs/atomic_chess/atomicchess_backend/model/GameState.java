package com.ulbs.atomic_chess.atomicchess_backend.model;

public class GameState {
    private Board board;
    private boolean whiteTurn;
    private String status; // "ONGOING", "CHECKMATE", "STALEMATE"

    // Getters and Setters
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }

    public boolean isWhiteTurn() { return whiteTurn; }
    public void setWhiteTurn(boolean whiteTurn) { this.whiteTurn = whiteTurn; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}