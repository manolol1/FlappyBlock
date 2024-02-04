package xyz.manolol.flappyblock.screens.gameobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.flappyblock.Constants;

import java.util.Iterator;

public class ObstacleManager {
    Array<Obstacle> obstacles;

    private float obstacleHoleSize = Constants.OBSTACLE_HOLE_START_SIZE;
    private float obstacleSpeed = Constants.OBSTACLE_START_SPEED;

    public ObstacleManager() {
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

            obstacle.draw(shapeRenderer);
        }
    }

    private void spawnObstacle() {
        obstacles.add(new Obstacle(Constants.WORLD_WIDTH, obstacleHoleSize));
    }
}
