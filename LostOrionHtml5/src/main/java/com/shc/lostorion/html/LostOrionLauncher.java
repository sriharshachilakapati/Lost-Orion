package com.shc.lostorion.html;

import com.google.gwt.core.client.EntryPoint;
import com.shc.silenceengine.backend.gwt.GwtRuntime;
import com.shc.lostorion.LostOrion;

public class LostOrionLauncher implements EntryPoint
{
    @Override
    public void onModuleLoad()
    {
        GwtRuntime.start(new LostOrion());
    }
}