package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH - 20 || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT- 20 || getY() < 0) {
            return true;
        }
        return false;
    }

    protected String isNearOfBounds(int border) {
        if (getX() > Globals.WINDOW_WIDTH - 20 - border) return "right";
        if (getX() < border ) return "left";
        if (getY() > Globals.WINDOW_HEIGHT - 20 - border) return "bottom";
        if (getY() < border ) return "top";
        return "";
    }

    public String getGameObjectsName(){
       return this.imageProperty().getBean().getClass().getSimpleName();
    }

    public static void clearAllExcept(String... args){
        for (GameEntity entity : Globals.gameObjects) {
            boolean argsIn = false;
            for (String arg : args){
                if(entity.getGameObjectsName().equals(arg)) {
                    argsIn = true;
                }
            }
            if (!argsIn) {
                entity.destroy();
            }
        }
    }

    public static int getNumberOfEntity(String searchedEntity) {
        int numsOfEntity = 0;
        for (GameEntity entity : Globals.gameObjects) {
            if (entity.getGameObjectsName().equals(searchedEntity)) {
                numsOfEntity++;
            }
        }
        return numsOfEntity;
    }

    public static int getNumberOfEnemy(){
        String[] args = {"SimpleEnemy"};
        return getNumberOfEntitys(args);
    }

    public static int getNumberOfPowerUp(){
        String[] args = {"SimplePowerup","ToxicPowerUp"};
        return getNumberOfEntitys(args);
    }

    public static int getNumberOfEntitys(String... args) {
        int numsOfEntity = 0;
        for (GameEntity entity : Globals.gameObjects) {
            for (String arg : args){
                if(entity.getGameObjectsName().equals(arg)) {
                numsOfEntity++;
                }
            }
        }
        return numsOfEntity;
    }

    public static void clearEntity(String... args){
        for (GameEntity entity : Globals.gameObjects) {
            boolean argsIn = false;
            for (String arg : args){
                if(entity.getGameObjectsName().equals(arg)) {
                    argsIn = true;
                }
            }
            if (argsIn) {
                entity.destroy();
            }
        }
    }

    public boolean isIntersectsOthers(){
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    public void spawnToFreeLocation(){
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        while (isIntersectsOthers()) {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }
    }

}
