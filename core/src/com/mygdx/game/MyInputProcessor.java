package com.mygdx.game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {
    private String lastKeyPressed;
    private String name;

    public MyInputProcessor() {
        lastKeyPressed = "";
    }

    public String getLastKeyPressed() {
        return lastKeyPressed;
    }

    public String getName() {
        return name;
    }
    public void setName() {
        name += lastKeyPressed;
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
}