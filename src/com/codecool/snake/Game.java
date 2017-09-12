package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.ToxicPowerUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class Game extends Pane {
    public static SnakeHead shead;

    public Game() {
        shead = new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new ToxicPowerUp(this);
        new ToxicPowerUp(this);
        new ToxicPowerUp(this);
        new ToxicPowerUp(this);

    }

    public void start() {
        Scene scene = getScene();
        addButtons();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void addButtons() {
        // Button 1 - ReStart
        Button btn = new Button();
        btn.setText("New Game");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("- NewGame -");
                restartGame();
            }
        });
        getChildren().add(btn);
    }

    private void restartGame() {
        this.getChildren().remove(2);
        GameEntity.clearAllExcept("SnakeHead");
        shead.newGame(this,500,500);
        Globals.gameLoop.start();
    }

}
