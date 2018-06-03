package com.gladunalexander.kalah.service;

import com.gladunalexander.kalah.domain.Game;
import com.gladunalexander.kalah.domain.enums.Status;
import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;
import com.gladunalexander.kalah.exception.GameNotFoundException;
import com.gladunalexander.kalah.exception.GameTerminatedException;
import com.gladunalexander.kalah.mapper.GameMapper;
import com.gladunalexander.kalah.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Basic {@link GameService} implementation.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository repository;
    private final GameMapper mapper;
    private final GameFacade facade;

    public GameServiceImpl(GameRepository repository, GameMapper mapper, GameFacade facade) {
        this.repository = repository;
        this.mapper = mapper;
        this.facade = facade;
    }

    @Override
    @Transactional
    public NewGameDTO createNewGame() {
        Game created = repository.save(new Game());
        return mapper.toNewDTO(created);
    }

    @Override
    @Transactional
    public GameDTO makeMove(int gameId, int pitId) {
        Game game = repository.findById(gameId).orElseThrow(
                () -> new GameNotFoundException("Game with id: " + gameId + " not found."));
        checkGameStatus(game);
        facade.makeMove(game, pitId);
        Game afterMove = repository.save(game);
        return mapper.toDTO(afterMove);
    }

    private void checkGameStatus(Game game) {
        Status status = game.getStatus();
        if (status != Status.IN_PROGRESS) {
            throw new GameTerminatedException("Game has been already terminated with status:" + status, status);
        }
    }
}
