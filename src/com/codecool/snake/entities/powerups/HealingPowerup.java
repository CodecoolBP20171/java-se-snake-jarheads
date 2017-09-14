package com.codecool.snake.entities.powerups;

import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class HealingPowerup extends GameEntity implements Interactable, Animatable{

    private int creationTime = GameLoop.secTime;

    public HealingPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupHealing);
        pane.getChildren().add(this);
        spawnToFreeLocation();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getHealth() <100){
            snakeHead.changeHealth(10);
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 Health";
    }

    @Override
    public void step() {
        Random rnd = new Random();
        if (GameLoop.secTime-creationTime > rnd.nextInt(40) + 10){
            destroy();
        }
    }
}
