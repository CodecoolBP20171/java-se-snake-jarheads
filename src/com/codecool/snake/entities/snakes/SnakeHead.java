package com.codecool.snake.entities.snakes;

import com.codecool.snake.*;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public void newGame(Pane pane, int xc, int yc) {
        health = 100;
        setX(xc);
        setY(yc);
        tail = this;
        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            GameEntity.clearAllExcept("SnakeHead");
            Globals.gameLoop.stop();
            gameOver(super.pane);

        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void gameOver(Pane pane){
        Text text = new Text(500,500, "GAME OVER \n Your score: " + String.valueOf(GameEntity.getNumberOfEntity("SnakeBody")));
        text.setFont(new Font(20));
        text.setWrappingWidth(200);
        pane.getChildren().add(text);
    }
}
