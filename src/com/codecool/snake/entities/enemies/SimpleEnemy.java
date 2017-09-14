package com.codecool.snake.entities.enemies;


import com.codecool.snake.Globals;
import javafx.scene.layout.Pane;


public class SimpleEnemy extends Enemy {


    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        this.speed = 1;
        this.damage = 10;
    }

}
