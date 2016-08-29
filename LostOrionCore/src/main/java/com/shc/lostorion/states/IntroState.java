package com.shc.lostorion.states;

import com.shc.lostorion.LostOrion;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Primitive;
import com.shc.silenceengine.input.Keyboard;

/**
 * @author Sri Harsha Chilakapati
 */
public class IntroState extends GameState
{
    private OrthoCam camera = new OrthoCam(1280, 720);

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
            String text = "One century ago...\n\n"
                          + "Humans tried to form a colony on Mars\n"
                          + "They launched Orion.\n\n"
                          + "Unfortunately...\n\n"
                          + "They lost the signals with Orion\n"
                          + "and we were unable to find even its\n"
                          + "destroyed parts.\n\n"
                          + "Now...\n\n"
                          + "After a century, we got signals from\n"
                          + "the great Orion again.. and you are\n"
                          + "sent to collect the BLACK BOXES from\n"
                          + "Orion's twelve sectors to investigate.\n\n\n\n"
                          + "Press SPACE TO START";

            renderer.render(font, text, 775, 10, Color.WHITE_SMOKE);
        }
        renderer.end();
    }
}
