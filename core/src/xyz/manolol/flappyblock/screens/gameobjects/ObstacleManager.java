package xyz.manolol.flappyblock.screens.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.flappyblock.Constants;

import java.util.Iterator;

public class ObstacleManager {
    Array<Obstacle> obstacles;

    Player player;

    private float obstacleHoleSize = Constants.OBSTACLE_HOLE_START_SIZE;
    private float obstacleSpeed = Constants.OBSTACLE_START_SPEED;

    private float timeSinceLastDifficultyIncrease = 0.0f;

    private boolean isGameOver = false;

    public ObstacleManager(Player player) {
        this.player = player;
        obstacles = new Array<>();
        spawnObstacle(Constants.WORLD_WIDTH / 2);
        spawnObstacle(Constants.WORLD_WIDTH / 2 + Constants.OBSTACLE_X_DISTANCE);
    }

    public void update(float delta, ShapeRenderer shapeRenderer) {
        if (obstacles.peek().posX < Constants.WORLD_WIDTH - Constants.OBSTACLE_X_DISTANCE) {
            spawnObstacle();
        }

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

        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.posX -= obstacleSpeed * delta;

            if (player.getRect().overlaps(obstacle.getBottom()) || player.getRect().overlaps(obstacle.getTop())) {
                isGameOver = true;
            }

            if (obstacle.posX < 0 - Constants.OBSTACLE_WIDTH) iterator.remove();

            obstacle.draw(shapeRenderer);
        }
    }

    private void spawnObstacle() {
        obstacles.add(new Obstacle(Constants.WORLD_WIDTH, obstacleHoleSize));
    }

    private void spawnObstacle(float posX) {
        obstacles.add(new Obstacle(posX, obstacleHoleSize));
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
