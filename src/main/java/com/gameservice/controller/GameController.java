package com.gameservice.controller;

import com.gameservice.entity.Game;
import com.gameservice.request.CreateGameRequest;
import com.gameservice.request.UpdateGameRequest;
import com.gameservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Game> addGame(@RequestBody CreateGameRequest createGameRequest) {
        return ResponseEntity.ok().body(gameService.addGame(createGameRequest));
    }

    @GetMapping // Path Params vs Path Variables
    public ResponseEntity<Game> getGame(@RequestParam(value = "id") String gameId) {
        return ResponseEntity.ok().body(gameService.getGame(gameId));
    }

    @GetMapping("/all") // Path Params vs Path Variables
    public ResponseEntity<List<Game>> getAllGame() {
        return ResponseEntity.ok().body(gameService.getAllGame());
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable(value = "gameId") String gameId) {
        return ResponseEntity.ok().body(gameService.deleteGame(gameId));
    }

    @PutMapping
    public ResponseEntity<String> updateGame(@RequestParam(value = "id") String gameId, @RequestBody UpdateGameRequest updateGameRequest) {
        return ResponseEntity.ok().body(gameService.updateGame(gameId, updateGameRequest));
    }

}
