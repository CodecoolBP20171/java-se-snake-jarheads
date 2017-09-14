package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.scene.layout.Pane;


public class HomingEnemy extends Enemy {

    public HomingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.imperialStarDestroyer);
        damage = 20;
        speed = 1;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        double dir = Utils.getDirectionBetweenTwo(this, Globals.snake);
        setRotate(dir);
        heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }
}