package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.flappyblock.Constants;

import java.util.Iterator;

public class ObstacleManager {
    private final Array<Obstacle> obstacles;

    private final Player player;
    private final DifficultyManager difficultyManager;

    private int score = 0;
    private boolean isGameOver = false;
    private final boolean easyMode;

    public ObstacleManager(Player player, boolean easyMode, DifficultyManager difficultyManager) {
        this.player = player;
        this.difficultyManager = difficultyManager;
        this.easyMode = easyMode;
        obstacles = new Array<>();
        spawnObstacle(Constants.WORLD_WIDTH / 2);
        spawnObstacle(Constants.WORLD_WIDTH / 2 + Constants.OBSTACLE_X_DISTANCE);
    }

    public void update(float delta) {
        if (obstacles.peek().posX < Constants.WORLD_WIDTH - Constants.OBSTACLE_X_DISTANCE) {
            spawnObstacle();
        }
        
        updateObstaclePositions(delta);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(shapeRenderer);
        }
    }

    private void updateObstaclePositions (float delta) {
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.posX -= difficultyManager.getObstacleSpeed() * delta;

            if (obstacle.posX < 0 - Constants.OBSTACLE_WIDTH) iterator.remove();
        }
    }

    private void spawnObstacle() {
        obstacles.add(new Obstacle(Constants.WORLD_WIDTH, difficultyManager.getObstacleHoleSize()));
        score++;
    }

    private void spawnObstacle(float posX) {
        obstacles.add(new Obstacle(posX, difficultyManager.getObstacleHoleSize()));
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getScore() {
        return score;
    }
}
