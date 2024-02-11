package xyz.manolol.flappyblock;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.flappyblock.screens.mainmenu.MainMenuScreen;

public class Main extends Game {

	public static Game GAME;

	@Override
	public void create () {
		GAME = this;

		VisUI.load();

		GAME.setScreen(new MainMenuScreen());
	}
}
