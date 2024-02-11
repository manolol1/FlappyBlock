package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.utils.Array;

public class CollisionChecker {
    public boolean isColliding (Array<Obstacle> obstacles, Player player) {
        return player.getRect().overlaps(obstacles.first().getBottom())
                || player.getRect().overlaps(obstacles.first().getTop());
    }
}
