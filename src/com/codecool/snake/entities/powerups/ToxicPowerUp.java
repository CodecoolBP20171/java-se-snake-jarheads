package com.codecool.snake.entities.powerups;

import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ToxicPowerUp extends GameEntity implements Interactable, Animatable {

    private int creationTime = GameLoop.secTime;

    public ToxicPowerUp(Pane pane) {
        super(pane);
        setImage(Globals.powerupToxic);
        pane.getChildren().add(this);

        spawnToFreeLocation();
        System.out.println(creationTime);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(2);
        destroy();
    }

    @Override
    public String getMessage() { return "Lost from your tail :'("; }

    @Override
    public void step() {
        Random rnd = new Random();
        if (GameLoop.secTime-creationTime > rnd.nextInt(40) + 10){
            destroy();
        }
    }
}
