package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import xyz.manolol.flappyblock.Constants;

public class Obstacle {
    private final Rectangle top;
    private final Rectangle bottom;

    private float posX;

    public Obstacle(float posX, float holeSize) {
        // get random y position of the start of the hole
        float holeStart = MathUtils.random(
                Constants.OBSTACLE_HOLE_Y_DISTANCE,
                Constants.WORLD_HEIGHT - Constants.OBSTACLE_HOLE_Y_DISTANCE - holeSize
        );

        this.posX = posX;

        bottom = new Rectangle(posX, 0, Constants.OBSTACLE_WIDTH, holeStart);
        top = new Rectangle(posX, holeStart + holeSize,Constants.OBSTACLE_WIDTH, Constants.WORLD_HEIGHT);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        bottom.x = posX;
        top.x = posX;

        shapeRenderer.setColor(Constants.OBSTACLE_COLOR);
        shapeRenderer.rect(top.x, top.y, top.width, top.height);
        shapeRenderer.rect(bottom.x, bottom.y, bottom.width, bottom.height);
    }

    public Rectangle getTop() {
        return top;
    }

    public Rectangle getBottom() {
        return bottom;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }
}
