package com.gladunalexander.kalah.repository;

import com.gladunalexander.kalah.domain.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * Data access layer.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */
public interface GameRepository extends CrudRepository<Game, Integer> { }
