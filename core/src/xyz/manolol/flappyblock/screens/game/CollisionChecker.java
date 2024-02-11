package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.utils.Array;

public class CollisionChecker {
    public boolean isColliding (Array<Obstacle> obstacles, Player player) {
        for (Obstacle obstacle : obstacles) {
            if (player.getRect().overlaps(obstacle.getBottom()) || player.getRect().overlaps(obstacle.getTop())) {
                return true;
            }
        }
        return false;
    }
}
