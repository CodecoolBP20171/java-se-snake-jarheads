package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private int speed = 1;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        speed = 1;
        spawnToFreeLocation();
        Random rnd = new Random();
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {

        if (isNearOfBounds(10) != "") {
            Random rnd = new Random();
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
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
