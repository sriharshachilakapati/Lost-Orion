package com.shc.lostorion;

import com.shc.silenceengine.audio.Sound;
import com.shc.silenceengine.collision.CollisionTag;
import com.shc.silenceengine.graphics.Animation;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.SpriteRenderer;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.graphics.programs.DynamicProgram;

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
        public static Texture TILES_SHEET;
        public static Texture TILE_FREE;
        public static Texture TILE_BLOCKED;
    }

    public final static class Animations
    {
        public static Animation PARTICLES;
    }

    public final static class Fonts
    {
        public static BitmapFont ROBOTO;
    }

    public final static class Programs
    {
        public static DynamicProgram DYNAMIC;
    }

    public final static class Sounds
    {
        public static Sound TITLE;
        public static Sound WEIRD;
    }

    public final static class Renderers
    {
        public static DynamicRenderer    DYNAMIC;
        public static BitmapFontRenderer FONT;
        public static SpriteRenderer     SPRITE;
    }

    public final static class Levels
    {
        public static Level TEST;
    }

    public final static class CollisionTags
    {
        public static final CollisionTag SHIP   = new CollisionTag();
        public static final CollisionTag BULLET = new CollisionTag();
        public static final CollisionTag BLOCK  = new CollisionTag();
    }
}
