package com.shc.lostorion;

import com.shc.lostorion.entities.BlackBox;
import com.shc.lostorion.entities.Block;
import com.shc.lostorion.entities.Floor;
import com.shc.lostorion.entities.Roller;
import com.shc.lostorion.entities.Ship;
import com.shc.lostorion.states.PlayState;
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

    public void create()
    {
        Scene2D scene = PlayState.SCENE;
        scene.entities.clear();

        final float tileSize = 96;
        float x = tileSize / 2, y = tileSize / 2;

        for (char ch : levelText.toCharArray())
        {
            if (ch == '\r')
                continue;

            if (ch == '\n')
            {
                y += tileSize;
                x = tileSize / 2;
                continue;
            }

            else if (ch == 'S')
                scene.entities.add(new Ship(x, y));

            else if (ch == 'B')
                scene.entities.add(new Block(x, y));

            else if (ch == 'R')
                scene.entities.add(new Roller(x, y));

            else if (ch == 'E')
                scene.entities.add(new BlackBox(x, y));

            if (ch != 'B' && ch != 'N')
                scene.entities.add(new Floor(x, y));

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
