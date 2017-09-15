package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.SelfDestructable;
import com.codecool.snake.entities.enemies.HomingEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SupplyEnemy;
import com.codecool.snake.entities.powerups.HealingPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.ToxicPowerUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    public static int iterTime = 0;
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
            if (gameObject instanceof SelfDestructable) {
                SelfDestructable destructableObject = (SelfDestructable) gameObject;
                destructableObject.selfDestruct();
            }
        }

        Game.hpBar.setProgress(Game.shead.getHealth() / 100);

        spawnEnemies();
        spawnPowerups();

        iterTime++;
        if (iterTime % 60 == 0) {secTime++;}

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    private void spawnEnemies(){
        if (GameEntity.getNumberOfEnemy() < 6){
            new SimpleEnemy(Snake.game);
        }

        if (secTime < bugEnemySpeedLimitInSec) {
            if (Game.shead.getSpeed() > 0.5) {
                Game.shead.setSpeed(Game.shead.getSpeed() - 0.01);
            }
        } else {
            Game.shead.setSpeed(2);
        }
        if (GameEntity.getNumberOfEntity("HomingEnemy") < 2) {
            new HomingEnemy(Snake.game );
        }

        if(secTime % 5 == 0 && GameEntity.getNumberOfEntity("SupplyEnemy") == 0){
            new SupplyEnemy(Snake.game);
        }

    }

    private void spawnPowerups(){
        if(secTime % 5 == 0){
            if (GameEntity.getNumberOfEntitys("SimplePowerup") < 5) {
                new SimplePowerup(Snake.game);
            }
            if (GameEntity.getNumberOfEntitys("ToxicPowerUp") < 5) {
                new ToxicPowerUp(Snake.game);
            }
            if (GameEntity.getNumberOfEntitys("HealingPowerup") < 5) {
                new HealingPowerup(Snake.game);
            }

        }

    }
}
