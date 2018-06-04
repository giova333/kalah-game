package com.gladunalexander.kalah.mapper;

import com.gladunalexander.kalah.domain.Game;
import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;
import java.util.Map;

/**
 * Converts Entity to data transfer object.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */

@Component
public class GameMapper {

    private String gameUrl;

    private final Environment environment;

    public GameMapper(Environment environment) {
        this.environment = environment;
        this.gameUrl = environment.getActiveProfiles()[0].equals("prod")
                ? environment.getProperty("prod.game.url") : environment.getProperty("dev.game.url");
    }

    public GameDTO toDTO(Game game) {
        int id = game.getId();
        Map<Integer, Integer> status = game.getBoard();
        String url = generateGameUrl(id);
        return new GameDTO(id, url, status);
    }

    public NewGameDTO toNewDTO(Game game) {
        int id = game.getId();
        String url = generateGameUrl(id);
        return new NewGameDTO(id, url);
    }

    private String generateGameUrl(int gameId) {
        return new UriTemplate(gameUrl).expand(gameId).toString();
    }
}
