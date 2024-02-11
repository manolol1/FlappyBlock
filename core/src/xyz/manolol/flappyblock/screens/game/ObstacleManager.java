package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.flappyblock.Constants;

import java.util.Iterator;

public class ObstacleManager {
    private final Array<Obstacle> obstacles;

    private final Player player;

    private float obstacleHoleSize = Constants.OBSTACLE_HOLE_START_SIZE;
    private float obstacleSpeed = Constants.OBSTACLE_START_SPEED;

    private float timeSinceLastDifficultyIncrease = 0.0f;

    private int score = 0;
    private boolean isGameOver = false;
    private final boolean easyMode;

    public ObstacleManager(Player player, boolean easyMode) {
        this.player = player;
        this.easyMode = easyMode;
        obstacles = new Array<>();
        spawnObstacle(Constants.WORLD_WIDTH / 2);
        spawnObstacle(Constants.WORLD_WIDTH / 2 + Constants.OBSTACLE_X_DISTANCE);
    }

    public void update(float delta, ShapeRenderer shapeRenderer) {
        if (obstacles.peek().posX < Constants.WORLD_WIDTH - Constants.OBSTACLE_X_DISTANCE) {
            spawnObstacle();
        }

        if (!easyMode) {
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

        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.posX -= obstacleSpeed * delta;

            if (obstacle.posX < 0 - Constants.OBSTACLE_WIDTH) iterator.remove();

            obstacle.draw(shapeRenderer);
        }
    }

    private void spawnObstacle() {
        obstacles.add(new Obstacle(Constants.WORLD_WIDTH, obstacleHoleSize));
        score++;
    }

    private void spawnObstacle(float posX) {
        obstacles.add(new Obstacle(posX, obstacleHoleSize));
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getScore() {
        return score;
    }
}
