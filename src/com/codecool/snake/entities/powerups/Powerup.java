package com.codecool.snake.entities.powerups;

import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.SelfDestructable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public abstract class Powerup extends GameEntity implements Interactable, SelfDestructable {

    private int creationTime = GameLoop.secTime;

    public Powerup(Pane pane) {
        super(pane);
        pane.getChildren().add(this);
        spawnToFreeLocation();
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

    @Override
    public void selfDestruct() {
        if (GameLoop.secTime - creationTime > Globals.rnd.nextInt(40) + 10) {
            destroy();
        }
    }
}
