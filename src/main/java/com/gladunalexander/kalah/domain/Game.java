package com.gladunalexander.kalah.domain;

import com.gladunalexander.kalah.domain.enums.Player;
import com.gladunalexander.kalah.domain.enums.Status;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

import static com.gladunalexander.kalah.configuration.Settings.*;

/**
 * Created by Alexander Gladun on 31/05/2018.
 */

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue
    private int id;

    @ElementCollection
    @MapKeyColumn(name="pitId")
    @Column(name="value")
    private Map<Integer, Integer> board;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    private Player player;

    public Game() {
        initializeBoard();
        status = Status.IN_PROGRESS;
        player = Player.FIRST_PLAYER;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getBoard() {
        return board;
    }

    public void setBoard(Map<Integer, Integer> board) {
        this.board = board;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void initializeBoard() {
        board = new HashMap<>();
        for (int i = FIRST_PIT_INDEX; i <= LAST_PIT_INDEX; i++) {
            int firstKhalIndex = Player.FIRST_PLAYER.getKalahId();
            int secondKhalIndex = Player.SECOND_PLAYER.getKalahId();
            int value = (i != firstKhalIndex && i != secondKhalIndex) ? INITIAL_STONES_QUANTITY : 0;
            board.put(i, value);
        }
    }
}
