package com.shc.lostorion;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.opengl.GLContext;
import com.shc.silenceengine.input.Keyboard;

import static com.shc.silenceengine.graphics.IGraphicsDevice.Constants.*;

public class LostOrion extends Game
{
    @Override
    public void init()
    {
        SilenceEngine.display.setTitle("LostOrion: SilenceEngine v1.0.1");
        SilenceEngine.display.setSize(1280, 720);
        SilenceEngine.display.centerOnScreen();

        // Enable GL Context blending of colors
        GLContext.enable(GL_BLEND);
        GLContext.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void update(float deltaTime)
    {
        if (Keyboard.isKeyTapped(Keyboard.KEY_ESCAPE))
            SilenceEngine.display.close();
    }
}