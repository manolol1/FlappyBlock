package xyz.manolol.flappyblock.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PrefsManager {
    Preferences prefs;

    public PrefsManager() {
        prefs = Gdx.app.getPreferences("FlappyBlockData");
    }

    public void setNormalHighscore (int highscore) {
        prefs.putInteger("normalHighscore", highscore);
        prefs.flush();
    }

    public int getNormalHighscore() {
        return prefs.getInteger("normalHighscore", 0);
    }

    public void setEasyHighscore (int highscore) {
        prefs.putInteger("easyHighscore", highscore);
        prefs.flush();
    }

    public int getEasyHighscore() {
        return prefs.getInteger("easyHighscore", 0);
    }
}