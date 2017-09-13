package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private static int iterTime = 0;
    private static int secTime = 0;

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }

        if (GameEntity.getNumberOfEnemy() < 4){
            new SimpleEnemy(Snake.game);
        }

        iterTime++;
        if (iterTime > 60) {secTime++; iterTime = 0;}

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
