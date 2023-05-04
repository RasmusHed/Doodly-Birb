package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.DoodlyBirb;
import com.mygdx.game.utils.Settings;
import com.mygdx.game.sprites.Background;

public class OptionsScreen implements Screen {
        private static final int EASY_X = 270;
        private static final int EASY_Y = 290;
        private static final int MEDIUM_X = 270;
        private static final int MEDIUM_Y = 210;
        private static final int HARD_X = 270;
        private static final int HARD_Y = 130;
        final DoodlyBirb game;
        final Background background;

        OrthographicCamera camera;

    public OptionsScreen(final DoodlyBirb game) {
            this.game = game;

            background = new Background(0, 0);

            camera = new OrthographicCamera();
            camera.setToOrtho(false, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);

        }


        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {
            camera.update();
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();

            game.batch.draw(background.getBackgroundImage(), background.getBackgroundPosition().x, background.getBackgroundPosition().y);
            game.accentFont.draw(game.batch, "Choose difficulty:", 100, 390);
            game.mainFont.draw(game.batch, "Easy", EASY_X, EASY_Y);
            game.mainFont.draw(game.batch, "Medium", MEDIUM_X, MEDIUM_Y);
            game.mainFont.draw(game.batch, "Hard", HARD_X, HARD_Y);
            game.batch.end();

            // Easy button
            if (Gdx.input.justTouched() && Gdx.input.getX() >= EASY_X && Gdx.input.getX() <= EASY_X + 100 && Gdx.input.getY() >= EASY_Y - 90 && Gdx.input.getY() <= EASY_Y - 55) {
                Settings.setDifficulty("EASY");
                game.setScreen(new MainMenuScreen(game));
            }

            // Medium button
            if (Gdx.input.justTouched() && Gdx.input.getX() >= MEDIUM_X && Gdx.input.getX() <= MEDIUM_X + 195 && Gdx.input.getY() >= MEDIUM_Y + 70 && Gdx.input.getY() <= MEDIUM_Y + 110) {
                Settings.setDifficulty("MEDIUM");
                game.setScreen(new MainMenuScreen(game));
            }

            // Hard button
            if (Gdx.input.justTouched() && Gdx.input.getX() >= HARD_X && Gdx.input.getX() <= HARD_X + 120 && Gdx.input.getY() >= HARD_Y + 230 && Gdx.input.getY() <= HARD_Y + 270) {
                Settings.setDifficulty("HARD");
                game.setScreen(new MainMenuScreen(game));
            }
        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {

        }
}
