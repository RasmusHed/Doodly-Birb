package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreList {
    private List<Score> highscoreList;

    public static boolean isHighscoreBadFormat() {
        boolean badFormat = false;
        for (String parsedHighscore : getHighscores()) {
            String[] nameAndScore = parsedHighscore.split(": ");
            if (nameAndScore[0].length() != 3){
                badFormat = true;
            }
            try {
                Integer.parseInt(nameAndScore[1]);
            } catch (NumberFormatException e){
                System.out.println("Highscore is not a number!");
                badFormat = true;
            }
        }
        return badFormat;
    }

    public List<Score> getHighscoreList() {
        return highscoreList;
    }

    public HighscoreList(List<Score> highscoreList) {
        this.highscoreList = highscoreList;
    }

    private static List<Score> addHighscore(Score score) {
        List<Score> highscores = new ArrayList<>();
        for (String parsedHighscore : getHighscores()) {
            String[] nameAndScore = parsedHighscore.split(": ");
            Score highscore = new Score(nameAndScore[0], Integer.parseInt(nameAndScore[1]));
            highscores.add(highscore);
        }
        highscores.add(score);
        Collections.sort(highscores);
        Collections.reverse(highscores);
        while (highscores.size() > 5) {
            highscores.remove(highscores.size() - 1);
        }
        return highscores;
    }

    public static void createHighscoreFile() {
        FileHandle file = Gdx.files.local("highscores.txt");
        file.writeString("---: 0", false);
    }

    public static void writeHighscore(Score score) {
        FileHandle file = Gdx.files.local("highscores.txt");
        List<Score> highscores = addHighscore(score);
        StringBuilder sb = new StringBuilder();
        for (Score highscore : highscores) {
            sb.append(highscore.getPlayerName() + ": " + highscore.getScore() + "\n");
        }
        String highscoreString = sb.toString();
        file.writeString(highscoreString, false);
    }

    public static String[] getHighscores() {

        FileHandle file = Gdx.files.local("highscores.txt");
        String filetext = file.readString();
        String[] highscores = filetext.split("\n");


        return highscores;
    }
}