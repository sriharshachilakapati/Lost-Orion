package com.shc.lostorion.desktop;

import com.shc.silenceengine.backend.lwjgl.LwjglRuntime;
import com.shc.lostorion.LostOrion;

public class LostOrionLauncher
{
    public static void main(String[] args)
    {
        LwjglRuntime.start(new LostOrion());
    }
}