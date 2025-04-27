package com.ulbs.atomic_chess.atomicchess_backend.controller;

import com.ulbs.atomic_chess.atomicchess_backend.model.GameState;
import com.ulbs.atomic_chess.atomicchess_backend.model.Move;
import com.ulbs.atomic_chess.atomicchess_backend.service.AiService;
import com.ulbs.atomic_chess.atomicchess_backend.service.GameService;
import org.springframework.web.bind.annotation.*;


//Endpoints like:
//
//POST /api/newgame → Start new game
//
//POST /api/move → Player move
//
//POST /api/ai-move → Let AI make a move
//
//GET /api/board → Get current board state


@RestController
@RequestMapping("/api")
public class ChessController {

    private final GameService gameService;
    private final AiService aiService;

    public ChessController(GameService gameService, AiService aiService) {
        this.gameService = gameService;
        this.aiService = aiService;
    }

    @PostMapping("/move")
    public GameState playerMove(@RequestBody Move move) {
        return gameService.playerMove(move);
    }

    @PostMapping("/ai-move")
    public GameState aiMove() {
        return aiService.makeMove();
    }
}