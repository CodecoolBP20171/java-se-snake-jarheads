package com.codecool.snake.entities;

import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.weapons.BeamOfDeath;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(SnakeHead snakeHead);
    void apply(BeamOfDeath beamOfDeath);

    String getMessage();

}
