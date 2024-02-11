package xyz.manolol.flappyblock.screens.game;

import xyz.manolol.flappyblock.utils.PrefsManager;

public class HighscoreManager {
    private final PrefsManager prefs;

    private boolean isNewHighscore;

    public HighscoreManager() {
        prefs = new PrefsManager();
    }

    public void updateHighscore (int score, boolean easyMode) {
        if (easyMode) {
            if (score > prefs.getEasyHighscore()) {
                prefs.setEasyHighscore(score);
                isNewHighscore = true;
            }
        } else {
            if (score > prefs.getNormalHighscore()) {
                prefs.setNormalHighscore(score);
                isNewHighscore = true;
            }
        }
    }

    public boolean isNewHighscore() {
        return isNewHighscore;
    }
}
