package xyz.manolol.flappyblock.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xyz.manolol.flappyblock.screens.gameobjects.Player;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final ShapeRenderer shapeRenderer;

    private final Player player;

    public GameScreen() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(64, 36, camera);
        shapeRenderer = new ShapeRenderer();

        player = new Player();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.update(delta);
        player.draw(shapeRenderer);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {

    }
}
