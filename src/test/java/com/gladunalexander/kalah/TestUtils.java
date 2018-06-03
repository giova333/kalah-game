package com.gladunalexander.kalah;

import com.gladunalexander.kalah.domain.enums.Player;

import java.util.Map;
import java.util.stream.IntStream;

import static com.gladunalexander.kalah.configuration.Settings.FIRST_PIT_INDEX;
import static com.gladunalexander.kalah.configuration.Settings.LAST_PIT_INDEX;

/**
 * Created by Alexander Gladun on 03/06/2018.
 */
public class TestUtils {

    private TestUtils() { }

    public static void fillBoard(int firstKalahStones, int secondKalahStones, int pitStones, Map<Integer, Integer> board) {
        IntStream.range(FIRST_PIT_INDEX, LAST_PIT_INDEX + 1)
                .forEach(pit -> {
                    int amount;
                    if (pit == Player.FIRST_PLAYER.getKalahId()) {
                        amount = firstKalahStones;
                    } else if (pit == Player.SECOND_PLAYER.getKalahId()) {
                        amount = secondKalahStones;

                    } else amount = pitStones;
                    board.put(pit, amount);
                });
    }

    public static void prepareBoardForResult(int firstKalahStones, int secondKalahStones, Map<Integer, Integer> board) {
        fillBoard(firstKalahStones, secondKalahStones, 0, board);
    }
}
