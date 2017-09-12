package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Snake extends Application {
    private static Stage gameStage;

    public static Stage getGameStage() {
        return gameStage;
    }

    public static void setGameStage(Stage gameStage) {
        Snake.gameStage = gameStage;
    }

    @Override
    public void start(Stage primaryStage) {
        setGameStage(primaryStage);

        Game game = new Game();

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }

}
