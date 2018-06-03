package com.gladunalexander.kalah.controller;

import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;
import com.gladunalexander.kalah.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Rest endpoints for Kalah.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */

@RestController
@RequestMapping("/games")
@Api(value="Kalah", description = "Game endpoints ", tags=("kalah"))
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="Create the game", notes="Creates new Kalah game")
    public NewGameDTO createNewGame() {
        return service.createNewGame();
    }

    @PutMapping("/{gameId}/pits/{pitId}")
    @ApiOperation(value="Make a move")
    public GameDTO makeMove(@PathVariable int gameId,
                            @PathVariable int pitId) {

        return service.makeMove(gameId, pitId);
    }
}
