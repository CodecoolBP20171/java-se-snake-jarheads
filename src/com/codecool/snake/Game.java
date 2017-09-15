package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SnailEnemy;
import com.codecool.snake.entities.powerups.HealingPowerup;
import com.codecool.snake.entities.enemies.HomingEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.ToxicPowerUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.weapons.BeamOfDeath;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Game extends Pane {
    public static SnakeHead shead;
    public static ProgressBar hpBar;

    public Game() {
        shead = new SnakeHead(this, 500, 500);
        startStateEntities();
    }

    private void startStateEntities() {
        for (int i = 0; i < 4; i++) {
            new HomingEnemy(this);
            new SimplePowerup(this);
            new ToxicPowerUp(this);
            new SnailEnemy(this);
            new HealingPowerup(this);

        }
    }

    public void start() {
        Scene scene = getScene();
        addHBox();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case D: Globals.dKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case D: Globals.dKeyDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void addHBox() {
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
        // health bar
        hpBar = new ProgressBar(1);
        Label label = new Label("Healt: ");

        // common box
        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(btn, label, hpBar);
        getChildren().add(hb);
    }

    private void restartGame() {
        this.getChildren().remove(2);
        GameEntity.clearAllExcept("SnakeHead");
        shead.newGame(this,500,500);
        startStateEntities();
        Globals.gameLoop.start();
    }
}
