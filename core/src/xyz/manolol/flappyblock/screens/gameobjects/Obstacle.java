package xyz.manolol.flappyblock.screens.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import xyz.manolol.flappyblock.Constants;

public class Obstacle {
    private Rectangle top;
    private Rectangle bottom;

    private float holeStart;

    protected float posX;

    public Obstacle(float posX, float holeSize) {
        holeStart = MathUtils.random(Constants.OBSTACLE_HOLE_Y_DISTANCE, Constants.WORLD_HEIGHT - Constants.OBSTACLE_HOLE_Y_DISTANCE);

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
}
