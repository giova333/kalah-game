package com.gladunalexander.kalah.service;

import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;

/**
 * Service layer, represents business logic
 * for API endpoints.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */
public interface GameService {

    /**
     * Creates new Kalah game.
     *
     * @return new game
     */
    NewGameDTO createNewGame();

    /**
     * Makes a move of current associated player.
     *
     * @param gameId unique identifier of a game
     * @param pitId  id of the pit selected to make a move
     * @return modified game
     * @throws com.gladunalexander.kalah.exception.GameTerminatedException
     *         if the game has been already terminated.
     *
     * @throws com.gladunalexander.kalah.exception.GameNotFoundException
     *         if provided game identifier is not associated with
     *         any game in database.
     *
     */
    GameDTO makeMove(int gameId, int pitId);
}
