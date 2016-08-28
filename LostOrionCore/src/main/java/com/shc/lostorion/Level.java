package com.shc.lostorion;

import com.shc.lostorion.entities.Ship;
import com.shc.silenceengine.core.IResource;
import com.shc.silenceengine.core.ResourceLoader;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.scene.Scene2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class Level implements IResource
{
    private String levelText;

    public Level(String levelText)
    {
        this.levelText = levelText;
    }

    public void create(Scene2D scene)
    {
        float x = 0, y = 0;
        final float tileSize = 96;

        for (char ch : levelText.toCharArray())
        {
            if (ch == '\r')
                continue;

            if (ch == '\n')
            {
                y += tileSize;
                x = 0;
                continue;
            }

            if (ch == 'S')
                scene.entities.add(new Ship(x, y));

            x += tileSize;
        }
    }

    public static void loadHelper(FilePath location, ResourceLoader.ISubmitter<Level> submitter)
    {
        SilenceEngine.io.getFileReader().readTextFile(location, text -> submitter.submit(new Level(text), location));
    }

    @Override
    public void dispose()
    {
    }
}
