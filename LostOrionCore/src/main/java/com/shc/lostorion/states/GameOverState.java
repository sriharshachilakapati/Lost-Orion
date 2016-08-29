package com.shc.lostorion.states;

import com.shc.lostorion.LostOrion;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Primitive;
import com.shc.silenceengine.input.Keyboard;

/**
 * @author Sri Harsha Chilakapati
 */
public class GameOverState extends GameState
{
    private OrthoCam camera = new OrthoCam(1280, 720);

    @Override
    public void onEnter()
    {
        Resources.Sounds.WEIRD.stop();
        Resources.Sounds.TITLE.play(true);
    }

    @Override
    public void update(float delta)
    {
        if (Keyboard.isKeyTapped(Keyboard.KEY_SPACE))
        {
            Resources.Sounds.TITLE.stop();
            Resources.Sounds.WEIRD.play(true);
            LostOrion.INSTANCE.setGameState(new PlayState());
        }
    }

    @Override
    public void render(float delta)
    {
        camera.apply();

        Resources.Programs.DYNAMIC.use();
        DynamicRenderer dynamicRenderer = Resources.Renderers.DYNAMIC;
        Resources.Textures.LOGO.bind();
        dynamicRenderer.begin(Primitive.TRIANGLE_FAN);
        {
            dynamicRenderer.vertex(0, 0);
            dynamicRenderer.texCoord(0, 0);

            dynamicRenderer.vertex(1280, 0);
            dynamicRenderer.texCoord(1, 0);

            dynamicRenderer.vertex(1280, 720);
            dynamicRenderer.texCoord(1, 1);

            dynamicRenderer.vertex(0, 720);
            dynamicRenderer.texCoord(0, 1);
        }
        dynamicRenderer.end();

        dynamicRenderer.begin(Primitive.TRIANGLE_FAN);
        {
            dynamicRenderer.vertex(750, 0);
            dynamicRenderer.color(0, 0, 0, 0.95f);

            dynamicRenderer.vertex(1280, 0);
            dynamicRenderer.color(0, 0, 0, 0.95f);

            dynamicRenderer.vertex(1280, 720);
            dynamicRenderer.color(0, 0, 0, 0.95f);

            dynamicRenderer.vertex(750, 720);
            dynamicRenderer.color(0, 0, 0, 0.95f);
        }
        dynamicRenderer.end();

        BitmapFontRenderer renderer = Resources.Renderers.FONT;
        BitmapFont font = Resources.Fonts.ROBOTO;

        renderer.begin();
        {
            String text = "You scored: " + PlayState.SCORE + "\n"
                          + "\n\n"
                          + "Game made for LudumDare Jam 36\n"
                          + "\n\n"
                          + "Programming & Graphics:\n"
                          + "======================\n"
                          + "Sri Harsha Chilakapati\n\n"
                          + "Music, Sounds & Levels:\n"
                          + "======================\n"
                          + "Tyler Hancock\n\n"
                          + "Made with SilenceEngine 1.0.1\n\n\n\n\n\n"
                          + "PRESS SPACE TO PLAY AGAIN";

            renderer.render(font, text, 775, 10);
        }
        renderer.end();
    }

    @Override
    public void onLeave()
    {
        PlayState.SCORE = 0;
        PlayState.BOXES = 0;
        PlayState.CURRENT_LEVEL = -1;
    }
}
