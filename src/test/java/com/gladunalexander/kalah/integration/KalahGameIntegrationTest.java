package com.gladunalexander.kalah.integration;

import com.gladunalexander.kalah.domain.enums.Status;
import com.gladunalexander.kalah.dto.ExceptionDTO;
import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;
import com.gladunalexander.kalah.repository.GameRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gladunalexander.kalah.TestUtils.prepareBoardForResult;

/**
 * Integration test that simulates games between two players,
 * all moves are taken from the real example.
 *
 * Created by Alexander Gladun on 02/06/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KalahGameIntegrationTest {

    private static final String CREATE_GAME_ENDPOINT = "/games";
    private static final String MAKE_MOVE_ENDPOINT = "/games/{gameId}/pits/";
    private static final int FIRST_GAME_FIRST_PLAYER_STONES = 23;
    private static final int FIRST_GAME_SECOND_PLAYER_STONES = 49;
    private static final int SECOND_GAME_FIRST_PLAYER_STONES = 58;
    private static final int SECOND_GAME_SECOND_PLAYER_STONES = 14;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository repository;

    @Value("#{'${first.game.indexes}'.split(',')}")
    private List<Integer> firstGameIndexes;

    @Value("#{'${second.game.indexes}'.split(',')}")
    private List<Integer> secondGameIndexes;

    private final Map<Integer, Integer> firstGameResult = new HashMap<>();

    private final Map<Integer, Integer> secondGameResult = new HashMap<>();

    private String url;

    @Before
    public void prepareGame() {
        NewGameDTO game = restTemplate
                .exchange(CREATE_GAME_ENDPOINT, HttpMethod.POST, null, NewGameDTO.class)
                .getBody();
        url = new UriTemplate(MAKE_MOVE_ENDPOINT).expand(game.getId()).toString();
    }

    @Test
    public void playFirstGame() {
        prepareBoardForResult(FIRST_GAME_FIRST_PLAYER_STONES, FIRST_GAME_SECOND_PLAYER_STONES, firstGameResult);
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer pit : firstGameIndexes) {
            GameDTO game = restTemplate.exchange(url + pit, HttpMethod.PUT, null, GameDTO.class).getBody();
            result = game.getStatus();
        }
        Assertions.assertThat(result.size()).isEqualTo(14);
        MatcherAssert.assertThat(result, CoreMatchers.is(firstGameResult));

        ResponseEntity<ExceptionDTO> response = restTemplate.exchange(url + 1, HttpMethod.PUT, null, ExceptionDTO.class);
        ExceptionDTO body = response.getBody();
        Assert.assertEquals(Status.SECOND_PLAYER_WIN, body.getGameStatus());
    }

    @Test
    public void playSecondGame() {
        prepareBoardForResult(SECOND_GAME_FIRST_PLAYER_STONES, SECOND_GAME_SECOND_PLAYER_STONES, secondGameResult);
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer pit : secondGameIndexes) {
            GameDTO game = restTemplate.exchange(url + pit, HttpMethod.PUT, null, GameDTO.class).getBody();
            result = game.getStatus();
        }
        Assertions.assertThat(result.size()).isEqualTo(14);
        MatcherAssert.assertThat(result, CoreMatchers.is(secondGameResult));

        ResponseEntity<ExceptionDTO> response = restTemplate.exchange(url + 1, HttpMethod.PUT, null, ExceptionDTO.class);
        ExceptionDTO body = response.getBody();
        Assert.assertEquals(Status.FIRST_PLAYER_WIN, body.getGameStatus());
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }
}
