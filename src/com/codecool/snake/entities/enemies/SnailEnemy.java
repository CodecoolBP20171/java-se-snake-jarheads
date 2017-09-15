package com.codecool.snake.entities.enemies;

import com.codecool.snake.GameLoop;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class SnailEnemy extends Enemy {



    public SnailEnemy(Pane pane) {
        super(pane);

        setImage(Globals.snailEnemy);
        speed = 0.3;
    }

    @Override
    public void step() {
        if (Globals.rnd.nextInt(100) > 97 ) {
            double direction = Globals.rnd.nextDouble() * 360;

            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
        }
        bounceFromEdge();
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        GameLoop.bugEnemySpeedLimitInSec = 5 + GameLoop.secTime;
        destroy();
    }

    @Override
    public String getMessage() {
        return "Slowed down";
    }
}