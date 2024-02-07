package xyz.manolol.flappyblock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
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
import xyz.manolol.flappyblock.utils.FontManager;

import static xyz.manolol.flappyblock.Main.GAME;


public class GameOverScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final Skin skin;
    private final FontManager fontManager;
    private Label label;
    private TextButton textButton;

    private final boolean easyMode;

    public GameOverScreen(boolean easyMode) {
        this.easyMode = easyMode;

        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        fontManager = new FontManager("fonts/Roboto-Regular.ttf");

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(100);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("[FIREBRICK]GAME OVER", skin);
        root.add(label);

        root.row().pad(50);

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(60);
        textButton = new TextButton("TRY AGAIN", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GAME.setScreen(new GameScreen(easyMode));
            }
        });
        root.add(textButton).width(400).row();

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
