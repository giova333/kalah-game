package com.gladunalexander.kalah.service;

import com.gladunalexander.kalah.domain.Game;
import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;
import com.gladunalexander.kalah.mapper.GameMapper;
import com.gladunalexander.kalah.repository.GameRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alexander Gladun on 02/06/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    private static final String URL = "http://host:port/games/1";

    @Mock
    private GameRepository repository;

    @Mock
    private GameFacade facade;

    @Mock
    private GameMapper mapper;

    private GameService service;

    @Before
    public void init() {
        service = new GameServiceImpl(repository, mapper, facade);
    }

    @Test
    public void testCreateGame() {
        Game created = new Game();
        created.setId(1);
        when(repository.save(any(Game.class))).thenReturn(created);
        when(mapper.toNewDTO(created)).thenReturn(new NewGameDTO(created.getId(), URL));

        NewGameDTO newGame = service.createNewGame();
        assertEquals(1, newGame.getId());
        assertEquals(URL, newGame.getUri());
    }

    @Test
    public void testMakeMove() {
        Game game = new Game();
        game.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(game));
        when(repository.save(game)).thenReturn(game);
        when(mapper.toDTO(game)).thenReturn(new GameDTO(game.getId(), URL, game.getBoard()));

        GameDTO gameDTO = service.makeMove(1, 3);

        verify(facade).makeMove(game, 3);
        assertEquals(1, gameDTO.getId());
        assertEquals(URL, gameDTO.getUrl());
        MatcherAssert.assertThat(gameDTO.getStatus(), CoreMatchers.is(game.getBoard()));
    }
}
