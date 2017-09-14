package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealingPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.ToxicPowerUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private static int iterTime = 0;
    public static int secTime = 0;
    public static int bugEnemySpeedLimitInSec = 0;

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }

        Game.hpBar.setProgress(Game.shead.getHealth() / 100);

        if (GameEntity.getNumberOfEnemy() < 5){
            new SimpleEnemy(Snake.game);
        }

        if (secTime % 5 == 0 && GameEntity.getNumberOfEntitys("ToxicPowerUp") < 5) {
            new ToxicPowerUp(Snake.game);
        }

        if (secTime % 5 == 0 && GameEntity.getNumberOfEntitys("SimplePowerup") < 5) {
            new SimplePowerup(Snake.game);
        }

        if (secTime % 5 == 0 && GameEntity.getNumberOfEntitys("HealingPowerup") < 5) {
            new HealingPowerup(Snake.game);
        }

        if (secTime < bugEnemySpeedLimitInSec) {
            if (Game.shead.getSpeed() > 0.5) {
                Game.shead.setSpeed(Game.shead.getSpeed() - 0.01);
                }
        } else {
            Game.shead.setSpeed(2);
        }

        iterTime++;
        if (iterTime > 60) {secTime++; iterTime = 0;}

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
