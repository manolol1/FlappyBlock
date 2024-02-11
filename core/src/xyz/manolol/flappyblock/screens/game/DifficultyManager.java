package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.Gdx;
import xyz.manolol.flappyblock.Constants;

public class DifficultyManager {
    private float obstacleHoleSize = Constants.OBSTACLE_HOLE_START_SIZE;
    private float obstacleSpeed = Constants.OBSTACLE_START_SPEED;

    private float timeSinceLastDifficultyIncrease = 0.0f;

    public void update (float delta) {
        timeSinceLastDifficultyIncrease += delta;

        if (timeSinceLastDifficultyIncrease > Constants.DIFFICULTY_INCREASE_INTERVAL) {
            obstacleHoleSize -= Constants.OBSTACLE_HOLE_SIZE_DECREASE;
            obstacleSpeed += Constants.OBSTACLE_SPEED_INCREASE;

            if (obstacleHoleSize < Constants.OBSTACLE_HOLE_SIZE_MIN) obstacleHoleSize = Constants.OBSTACLE_HOLE_SIZE_MIN;
            if (obstacleSpeed > Constants.OBSTACLE_SPEED_MAX) obstacleSpeed = Constants.OBSTACLE_SPEED_MAX;

            timeSinceLastDifficultyIncrease = 0.0f;

            Gdx.app.log("DIFFICULTY INCREASED",
                    "obstacleHoleSize: " + obstacleHoleSize + " | " + "obstacleSpeed: " + obstacleSpeed);
        }
    }

    public float getObstacleHoleSize() {
        return obstacleHoleSize;
    }

    public float getObstacleSpeed() {
        return obstacleSpeed;
    }
}
