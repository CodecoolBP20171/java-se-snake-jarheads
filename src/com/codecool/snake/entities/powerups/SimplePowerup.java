package com.codecool.snake.entities.powerups;

import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable, Animatable {

    private int creationTime = GameLoop.secTime;

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);

        spawnToFreeLocation();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

    @Override
    public void step() {
        Random rnd = new Random();
        if (GameLoop.secTime-creationTime > rnd.nextInt(40) + 10){
            destroy();
        }
    }
}
