package com.shc.lostorion;

import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.input.Keyboard;

public class LostOrion extends Game
{
    @Override
    public void init()
    {
        SilenceEngine.display.setTitle("LostOrion: SilenceEngine v1.0.1");
        SilenceEngine.display.setSize(1280, 720);
        SilenceEngine.display.centerOnScreen();
    }

    @Override
    public void update(float deltaTime)
    {
        if (Keyboard.isKeyTapped(Keyboard.KEY_ESCAPE))
            SilenceEngine.display.close();
    }
}