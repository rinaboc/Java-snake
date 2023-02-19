package utils;

import java.awt.*;

public class Constants {
    public static class Directions{
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int RIGHT = -1;
        public static final int DOWN = -2;
    }

    public static class Page{
        public static final int GAME = 0;
        public static final int MENU = 1;
        public static final int GAME_OVER = 2;
    }

    public static class ColorPalette{
        public static final Color PRIMARY_COLOR = new Color(220, 170, 100);
        public static final Color SECONDARY_COLOR = new Color(120, 100, 250);
    }

    public static class SoundEffects{
        public static final int GAME_BGM = 0;
        public static final int EAT_SFX = 1;
        public static final int GAME_OVER_SFX = 2;
        public static final int GAME_START_SFX = 3;
    }

    public static class PlayerColors{
        public static final Color PURPLE = new Color(120, 150, 250);
        public static final float[] PURPLE_COLORMASK = new float[]{0.9f, 0.85f, 1.4f};

        public static final Color RED = new Color(250, 100, 130);
        public static final float[] RED_COLORMASK = new float[]{1.4f, 0.9f, 0.85f};

        public static final Color GREEN = new Color(120, 250, 130);
        public static final float[] GREEN_COLORMASK = new float[]{0.9f, 1.4f, 0.85f};

        public static final Color GLASS = new Color(240, 220, 240);
        public static final float[] GLASS_COLORMASK = new float[]{1.3f, 1.4f, 1.3f};
    }
}
