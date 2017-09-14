package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class ToxicPowerUp extends Powerup {


    public ToxicPowerUp(Pane pane) {
        super(pane);
        setImage(Globals.powerupToxic);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.removePart(2);
        super.apply(snakeHead);
    }

    @Override
    public String getMessage() { return "Lost from your tail :'("; }
}
