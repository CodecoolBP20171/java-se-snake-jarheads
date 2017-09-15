package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class SupplyEnemy extends Enemy {

    public SupplyEnemy(Pane pane) {
        super(pane);

        setImage(Globals.gun);
        damage = 0;
        speed = 0.2;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        double dir = Utils.getDirectionBetweenTwo(this, Globals.snake) -180;
        setRotate(dir);
        heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.setReadyToFire(true);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Press 'D' to FIRE!";
    }
}