package xyz.manolol.flappyblock.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.flappyblock.Constants;
import xyz.manolol.flappyblock.screens.gameover.GameOverScreen;
import xyz.manolol.flappyblock.utils.FontManager;
import xyz.manolol.flappyblock.utils.PrefsManager;

import static xyz.manolol.flappyblock.Main.GAME;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera gameCamera;
    private final OrthographicCamera uiCamera;
    private final FitViewport gameViewport;
    private final FitViewport uiViewport;
    private final ShapeRenderer shapeRenderer;

    private final Player player;
    private final ObstacleManager obstacleManager;
    private final CollisionChecker collisionChecker;
    private final HighscoreManager highscoreManager;


    private final Stage stage;
    private final Skin skin;
    private final FontManager fontManager;
    private Label label;
    private Table table;

    private final boolean easyMode;

    private boolean newHighscore = false;

    public GameScreen(boolean easyMode) {
        this.easyMode = easyMode;

        gameCamera = new OrthographicCamera();
        uiCamera = new OrthographicCamera();
        gameViewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, gameCamera);
        uiViewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, uiCamera);
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(uiViewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        fontManager = new FontManager("fonts/Roboto-Regular.ttf");

        player = new Player();
        obstacleManager = new ObstacleManager(player, easyMode);
        collisionChecker = new CollisionChecker();
        highscoreManager = new HighscoreManager();

        // Score Text
        skin.get(Label.LabelStyle.class).font = fontManager.getFont(80);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("", skin);
        table = new Table();
        table.setFillParent(true);
        table.add(label).pad(50);
        table.top().right();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // GAME
        gameViewport.apply();
        shapeRenderer.setProjectionMatrix(gameCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        player.update(delta);
        player.draw(shapeRenderer);

        obstacleManager.update(delta);
        obstacleManager.draw(shapeRenderer);

        // Check if game is over
        if (collisionChecker.isColliding(obstacleManager.getObstacles(), player)) {
            highscoreManager.updateHighscore(obstacleManager.getScore(), easyMode);

            GAME.setScreen(new GameOverScreen(easyMode, obstacleManager.getScore(), highscoreManager.isNewHighscore()));
            return;
        }

        shapeRenderer.end();

        // UI
        uiViewport.apply();
        label.setText(obstacleManager.getScore());
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        uiViewport.update(width,height, true);
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
