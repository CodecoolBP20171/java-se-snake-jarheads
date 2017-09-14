package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class HealingPowerup extends Powerup {

    public HealingPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupHealing);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getHealth() <100){
            snakeHead.changeHealth(10);
        }
        super.apply(snakeHead);
    }

    @Override
    public String getMessage() {
        return "+10 Health";
    }

}
