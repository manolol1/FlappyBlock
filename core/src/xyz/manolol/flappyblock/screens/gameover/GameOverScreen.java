package xyz.manolol.flappyblock.screens.gameover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.flappyblock.Constants;
import xyz.manolol.flappyblock.screens.mainmenu.MainMenuScreen;
import xyz.manolol.flappyblock.screens.game.GameScreen;
import xyz.manolol.flappyblock.utils.FontManager;
import xyz.manolol.flappyblock.utils.PrefsManager;

import static xyz.manolol.flappyblock.Main.GAME;


public class GameOverScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final Skin skin;
    private final PrefsManager prefs;
    private Label label;
    private TextButton textButton;

    private final boolean easyMode;
    private final boolean newHighscore;

    public GameOverScreen(boolean easyMode, int score, boolean newHighscore) {
        this.easyMode = easyMode;
        this.newHighscore = newHighscore;

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        FontManager fontManager = new FontManager("fonts/Roboto-Regular.ttf");
        prefs = new PrefsManager();

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(100);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("[FIREBRICK]GAME OVER", skin);
        root.add(label).padBottom(40).row();

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(60);
        label = new Label("Score: " + score, skin);
        root.add(label).padBottom(30).row();

        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        skin.get(Label.LabelStyle.class).font = fontManager.getFont(60);
        if (newHighscore) {
            if (easyMode) {
                label = new Label("[FOREST]NEW [WHITE]Highscore (easy): " + prefs.getEasyHighscore(), skin);
            } else {
                label = new Label("[FOREST]NEW [WHITE]Highscore (normal): " + prefs.getNormalHighscore(), skin);
            }
        } else {
            if (easyMode) {
                label = new Label("Highscore (EASY): " + prefs.getEasyHighscore(), skin);
            } else {
                label = new Label("Highscore (NORMAL): " + prefs.getNormalHighscore(), skin);
            }
        }
        root.add(label).padBottom(60).row();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(60);
        textButton = new TextButton("TRY AGAIN", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GAME.setScreen(new GameScreen(easyMode));
            }
        });
        root.add(textButton).padBottom(50).width(400).row();

        textButton = new TextButton("MAIN MENU", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GAME.setScreen(new MainMenuScreen());
            }
        });
        root.add(textButton).width(400);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}
