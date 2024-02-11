package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.flappyblock.Constants;

import java.util.Iterator;

public class ObstacleManager {
    private final Array<Obstacle> obstacles;
    private final DifficultyManager difficultyManager;

    private int score = 0;

    public ObstacleManager(DifficultyManager difficultyManager) {
        this.difficultyManager = difficultyManager;
        obstacles = new Array<>();

        spawnInitialObstacles();
    }

    public void update(float delta) {
        spawnObstacleIfNeeded();
        updateObstaclePositions(delta);
        removeOldObstacles();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(shapeRenderer);
        }
    }

    private void spawnInitialObstacles() {
        spawnObstacle(Constants.WORLD_WIDTH / 2);
        spawnObstacle(Constants.WORLD_WIDTH / 2 + Constants.OBSTACLE_X_DISTANCE);
    }

    private void spawnObstacleIfNeeded() {
        if (obstacles.peek().posX < Constants.WORLD_WIDTH - Constants.OBSTACLE_X_DISTANCE) {
            spawnObstacle();
            score++;
        }
    }

    private void spawnObstacle() {
        spawnObstacle(Constants.WORLD_WIDTH);
    }

    private void spawnObstacle(float posX) {
        obstacles.add(new Obstacle(posX, difficultyManager.getObstacleHoleSize()));
    }

    private void updateObstaclePositions(float delta) {
        for (Obstacle obstacle : obstacles) {
            obstacle.posX -= difficultyManager.getObstacleSpeed() * delta;
        }
    }

    private void removeOldObstacles() {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            if (obstacle.posX < 0 - Constants.OBSTACLE_WIDTH) iterator.remove();
        }
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getScore() {
        return score;
    }
}
