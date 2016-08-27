package com.shc.lostorion;

import com.shc.silenceengine.graphics.Animation;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.SpriteRenderer;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Program;
import com.shc.silenceengine.graphics.opengl.Texture;

/**
 * @author Sri Harsha Chilakapati
 */
public final class Resources
{
    public final static class Textures
    {
        public static Texture SHIP;
        public static Texture BULLET;
        public static Texture PARTICLES_SHEET;
    }

    public final static class Animations
    {
        public static Animation PARTICLES;
    }

    public final static class Programs
    {
        public static Program DYNAMIC;
    }

    public final static class Renderers
    {
        public static DynamicRenderer    DYNAMIC;
        public static BitmapFontRenderer FONT;
        public static SpriteRenderer     SPRITE;
    }
}
