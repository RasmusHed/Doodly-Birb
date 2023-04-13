package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

    private String lastKeyPressed;
    private String name = "";

    public MyInputProcessor() {
        lastKeyPressed = "";
    }

    public String getLastKeyPressed() {
        return lastKeyPressed;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean keyDown(int keycode) {
        lastKeyPressed = Input.Keys.toString(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void writePlayerName(Score score) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            String lastKey = getLastKeyPressed();
            if (name.length() < 3) {
                name += lastKey;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                score.setPlayerName(name);
                HighscoreList.writeHighscore(score);
                System.out.println(score.toString());
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
                name = "";
            }
        }
    }
}