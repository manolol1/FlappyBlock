package xyz.manolol.flappyblock.screens.gameobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.flappyblock.Constants;
import xyz.manolol.flappyblock.screens.GameOverScreen;

import java.util.Iterator;

import static xyz.manolol.flappyblock.Main.GAME;

public class ObstacleManager {
    Array<Obstacle> obstacles;

    Player player;

    private float obstacleHoleSize = Constants.OBSTACLE_HOLE_START_SIZE;
    private float obstacleSpeed = Constants.OBSTACLE_START_SPEED;

    private boolean isGameOver = false;

    public ObstacleManager(Player player) {
        this.player = player;
        obstacles = new Array<>();
        spawnObstacle();
    }

    public void update(float delta, ShapeRenderer shapeRenderer) {
        if (obstacles.peek().posX < Constants.WORLD_WIDTH - Constants.OBSTACLE_X_DISTANCE) {
            spawnObstacle();
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

    public boolean isGameOver() {
        return isGameOver;
    }
}
