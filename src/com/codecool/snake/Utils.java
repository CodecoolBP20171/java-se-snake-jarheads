package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static double getDirectionBetweenTwo(GameEntity baseEntity, GameEntity otherEntity) {
        double oneSide = otherEntity.getX() - baseEntity.getX();
        double otherSide = otherEntity.getY() - baseEntity.getY();
        double angle = (Math.atan2(otherSide, oneSide) + Math.PI) / Math.PI * 180 - 90;
        return angle;


    }
}
