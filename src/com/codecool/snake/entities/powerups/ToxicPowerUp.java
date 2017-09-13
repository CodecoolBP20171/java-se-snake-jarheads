package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ToxicPowerUp extends GameEntity implements Interactable {

    public ToxicPowerUp(Pane pane) {
        super(pane);
        setImage(Globals.powerupToxic);
        pane.getChildren().add(this);

        spawnToFreeLocation();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(2);
        destroy();
    }

    @Override
    public String getMessage() { return "Lost from your tail :'("; }
}
