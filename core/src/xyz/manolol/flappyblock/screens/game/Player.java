package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import xyz.manolol.flappyblock.Constants;

public class Player {
    private Rectangle rect;
    private float flapTimeLeft = 0;

    public Player() {
        rect = new Rectangle(Constants.PLAYER_SPAWN_X, Constants.PLAYER_SPAWN_Y, Constants.PLAYER_SIZE, Constants.PLAYER_SIZE);
    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched() && flapTimeLeft < 0.01f) {
            flapTimeLeft = Constants.FLAP_TIME;
        }

        if (flapTimeLeft < 0.0f) rect.y += Constants.GRAVITY * delta;

        if (flapTimeLeft > 0) {
            rect.y += Constants.FLAP_STRENGTH * delta;
        }

        flapTimeLeft -= delta;

        // keep player inside world bounds
        rect.y = MathUtils.clamp(rect.y, 0, Constants.WORLD_HEIGHT - Constants.PLAYER_SIZE);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Constants.PLAYER_COLOR);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
    }

    public Rectangle getRect() {
        return rect;
    }
}
