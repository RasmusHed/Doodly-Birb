package com.mygdx.game;

public class Settings {
    //DELTA
    public static int DELTATIME = 120;
    //SCREEN
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    //TUBES
    public static final int FIRST_TUBE_SPAWN_POSITION = 350;
    public static int TUBE_SPACING = 175;
    public static final int TUBE_COUNT = 5;
    public static final int TUBE_WIDTH = 52;
    public static final int TUBE_HEIGHT = 320;
    public static int GAP_POSITION_FLUCTUATION = 250;
    public static int TUBE_GAP = 175;
    public static final int LOWEST_OPENING = 30;
    //BIRB
    public static final float GRAVITY = -0.234f;
    public static final float JUMP = 15;
    public static final int BIRB_HITBOX_HEIGHT = 45;
    public static final int BIRB_HITBOX_WIDTH = 45;

    public static void setDifficulty(String difficulty){
        if (difficulty.equals("EASY")){
            DELTATIME = 60;
            TUBE_GAP = 200;
            GAP_POSITION_FLUCTUATION = 230;
        }
        if (difficulty.equals("MEDIUM")){
            DELTATIME = 120;
            TUBE_GAP =175;
            GAP_POSITION_FLUCTUATION = 250;
        }
        if (difficulty.equals("HARD")){
            DELTATIME = 180;
            TUBE_GAP = 150;
            GAP_POSITION_FLUCTUATION = 270;
        }
    }

}
