package com.gladunalexander.kalah.controller;

import com.gladunalexander.kalah.dto.GameDTO;
import com.gladunalexander.kalah.dto.NewGameDTO;
import com.gladunalexander.kalah.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Alexander Gladun on 02/06/2018.
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class GameControllerTest {

    private static final String URL = "http://host:port/games/1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void testCreateNewGame() throws Exception {
        NewGameDTO newGame = new NewGameDTO(1, URL);
        given(gameService.createNewGame()).willReturn(newGame);

        mockMvc.perform(post("/games"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.uri").value(newGame.getUri()));
    }

    @Test
    public void testMakeMove() throws Exception {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(1);
        gameDTO.setUrl(URL);
        given(gameService.makeMove(1, 3)).willReturn(gameDTO);

        mockMvc.perform(put("/games/1/pits/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.url").value(gameDTO.getUrl()));
    }
}
