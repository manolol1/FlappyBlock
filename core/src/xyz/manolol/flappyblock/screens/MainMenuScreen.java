package xyz.manolol.flappyblock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.flappyblock.Constants;
import xyz.manolol.flappyblock.utils.FontManager;

import static xyz.manolol.flappyblock.Main.GAME;


public class MainMenuScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final Skin skin;
    private final FontManager fontManager;
    private final TooltipManager tooltipManager;
    private Label label;
    private TextButton textButton;

    private boolean easyMode = false;

    public MainMenuScreen() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        fontManager = new FontManager("fonts/Roboto-Regular.ttf");
        tooltipManager = new TooltipManager();
        tooltipManager.instant();
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // set tooltip font size
        // (I don't understand why TextTooltips have to be so much more complicated than buttons or labels)
        Label.LabelStyle textTooltipLabelStyle = new Label.LabelStyle();
        textTooltipLabelStyle.font = fontManager.getFont(30);
        TextTooltip.TextTooltipStyle textTooltipStyle = new TextTooltip.TextTooltipStyle();
        textTooltipStyle.label = textTooltipLabelStyle;
        textTooltipStyle.background = skin.getDrawable("default-pane");

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(100);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("[FOREST]FLAPPY[OLIVE]BLOCK", skin);
        root.add(label).padBottom(80).row();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(60);

        TextButton startGameButton = new TextButton("START GAME", skin);
        startGameButton.pad(15);
        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GAME.setScreen(new GameScreen(easyMode));
            }
        });
        root.add(startGameButton).width(550).padBottom(60).row();

        TextTooltip easyModeTooltip = new TextTooltip(
                "When Easy Mode is enabled, the difficulty always stays the same.",
                tooltipManager, textTooltipStyle
        );
        easyModeTooltip.setInstant(true);

        TextButton easyModeButton = new TextButton("Easy Mode: " + (easyMode ? "ON" : "OFF"), skin);
        easyModeButton.pad(15);
        easyModeButton.addListener(easyModeTooltip);
        easyModeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                easyMode = !easyMode;
                easyModeButton.setText("Easy Mode: " + (easyMode ? "ON" : "OFF"));
            }
        });
        root.add(easyModeButton).width(550).padBottom(60).row();

        TextButton exitGameButton = new TextButton("EXIT", skin);
        exitGameButton.pad(15);
        exitGameButton.addListener(easyModeTooltip);
        exitGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        root.add(exitGameButton).width(550).row();
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
