package com.shc.lostorion.android;

import com.shc.lostorion.LostOrion;
import com.shc.silenceengine.backend.android.AndroidRuntime;
import com.shc.silenceengine.backend.android.AndroidLauncher;

/**
 * @author Sri Harsha Chilakapati
 */
public class LostOrionLauncher extends AndroidLauncher
{
    @Override
    public void launchGame()
    {
        AndroidRuntime.start(new LostOrion());
    }
}