package xyz.manolol.flappyblock.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xyz.manolol.flappyblock.Constants;
import xyz.manolol.flappyblock.screens.gameobjects.ObstacleManager;
import xyz.manolol.flappyblock.screens.gameobjects.Player;
import xyz.manolol.flappyblock.utils.PrefsManager;

import static xyz.manolol.flappyblock.Main.GAME;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final ShapeRenderer shapeRenderer;

    private final Player player;
    private final ObstacleManager obstacleManager;
    private final PrefsManager prefs;

    private final boolean easyMode;

    private boolean newHighscore = false;

    public GameScreen(boolean easyMode) {
        this.easyMode = easyMode;

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();

        player = new Player();
        obstacleManager = new ObstacleManager(player, easyMode);
        prefs = new PrefsManager();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.update(delta);
        player.draw(shapeRenderer);

        obstacleManager.update(delta, shapeRenderer);

        if (obstacleManager.isGameOver()) {
            if (easyMode) {
                if (obstacleManager.getScore() > prefs.getEasyHighscore()) {
                    prefs.setEasyHighscore(obstacleManager.getScore());
                    newHighscore = true;
                }
            } else {
                if (obstacleManager.getScore() > prefs.getNormalHighscore()) {
                    prefs.setNormalHighscore(obstacleManager.getScore());
                    newHighscore = true;
                }
            }
            GAME.setScreen(new GameOverScreen(easyMode, obstacleManager.getScore(), newHighscore));
            return;
        }

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
        shapeRenderer.dispose();
    }
}
