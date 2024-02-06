package xyz.manolol.flappyblock;

import com.badlogic.gdx.graphics.Color;

public class Constants {
    public static final float WORLD_WIDTH = 64;
    public static final float WORLD_HEIGHT = 36;

    public static final float PLAYER_SIZE = 2.0f;
    public static final float PLAYER_SPAWN_X = 5.0f;
    public static final float PLAYER_SPAWN_Y = 20.0f;
    public static final Color PLAYER_COLOR = Color.FOREST;
    public static final float FLAP_STRENGTH = 45.0f;
    public static final float FLAP_TIME = 0.06f;
    public static final float GRAVITY = -7.0f;

    public static final float OBSTACLE_WIDTH = 2.5f;
    public static final float OBSTACLE_HOLE_Y_DISTANCE = 8.0f; // minimum distance of the hole from top/bottom
    public static final float OBSTACLE_HOLE_START_SIZE = 10.0f;
    public static final Color OBSTACLE_COLOR = Color.FIREBRICK;
    public static final float OBSTACLE_X_DISTANCE = 20.0f;
    public static final float OBSTACLE_START_SPEED = 5.0f;

    public static final float DIFFICULTY_INCREASE_INTERVAL = 5.0f; // seconds
    public static final float OBSTACLE_HOLE_SIZE_DECREASE = 0.1f;
    public static final float OBSTACLE_SPEED_INCREASE = 0.5f;
    public static final float OBSTACLE_HOLE_SIZE_MIN= 6.0f;
    public static final float OBSTACLE_SPEED_MAX = 12.0f;
}
