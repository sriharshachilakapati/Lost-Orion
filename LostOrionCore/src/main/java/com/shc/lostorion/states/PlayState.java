package com.shc.lostorion.states;

import com.shc.lostorion.Resources;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;

/**
 * @author Sri Harsha Chilakapati
 */
public class PlayState extends GameState
{
    private OrthoCam  camera = new OrthoCam(1280, 720);


    @Override
    public void update(float delta)
    {
    }

    @Override
    public void render(float delta)
    {
        camera.apply();

        BitmapFontRenderer fontRenderer = Resources.Renderers.FONT;
        BitmapFont font = Resources.Fonts.ROBOTO;

        fontRenderer.begin();
        {
            fontRenderer.render(font, "UPS: " + SilenceEngine.gameLoop.getUPS(), 10, 10);
            fontRenderer.render(font, "\nFPS: " + SilenceEngine.gameLoop.getFPS(), 10, 10);
        }
        fontRenderer.end();
    }
}
