package com.codecool.snake.entities.enemies;

import com.codecool.snake.GameLoop;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import javafx.geometry.Point2D;

public abstract class Enemy extends GameEntity implements Interactable, Animatable {

    protected Point2D heading;
    protected int damage;
    protected int speed;

    public Enemy(Pane pane) {
        super(pane);
        pane.getChildren().add(this);
        spawnToFreeLocation();
        double direction = getRandomDirection();
        setRotate(direction);
        speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private double getRandomDirection(){
        return Globals.rnd.nextDouble() * 360;
    }

    protected void bounceFromEdge() {
        if (isNearOfBounds(10) != "") {
            double direction = Globals.rnd.nextDouble() * 90;

            switch (isNearOfBounds(10)){
                case "left"     : direction += 45; break;
                case "right"    : direction += 180; break;
                case "top"      : direction += 90; break;
                case "bottom"   : direction += 315; break;
            }
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
        }
    }

    @Override
    public void step() {
        bounceFromEdge();
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        GameLoop.bugEnemySpeedLimitInSec = 5 + GameLoop.secTime;
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }


}
