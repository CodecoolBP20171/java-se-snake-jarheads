package com.codecool.snake.entities.enemies;

import com.codecool.snake.GameLoop;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.Random;

public class SnailEnemy extends GameEntity implements Animatable, Interactable {
    private Point2D heading;
    private static final int damage = 10;
    private double speed = 0.1;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public SnailEnemy(Pane pane) {
        super(pane);

        setImage(Globals.snailEnemy);
        pane.getChildren().add(this);
        speed = 0.3;
        spawnToFreeLocation();
        Random rnd = new Random();
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {

        Random rnd = new Random();
        if (isNearOfBounds(10) != "" || rnd.nextInt(100) > 97 ) {
            rnd = new Random();
            double direction = rnd.nextDouble() * 90;

            switch (isNearOfBounds(10)){
                case "left"     : direction += 45; break;
                case "right"    : direction += 180; break;
                case "top"      : direction += 90; break;
                case "bottom"   : direction += 315; break;

            }
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
        }
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
        return "10 damage";
    }
}