package com.codecool.snake.entities.weapons;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.SelfDestructable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class BeamOfDeath extends GameEntity implements Animatable {

    double direction;
    GameEntity sHead;


    public BeamOfDeath(Pane pane){
        super(pane);
        pane.getChildren().add(this);
        sHead = Globals.snake;
        setImage(Globals.beamOfDeath);
        setX(sHead.getX());
        setY(sHead.getY());
        //setRotate(sHead.getRotate() - 90);
    }

    @Override
    public void step() {
        Point2D diffVector =  Utils.directionToVector(sHead.getRotate(), 600);
        setX(sHead.getX() - 580 + diffVector.getX());
        setY(sHead.getY() - 20 + diffVector.getY());
        setRotate(sHead.getRotate() - 90);
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);

                }
            }
        }
    }
}
