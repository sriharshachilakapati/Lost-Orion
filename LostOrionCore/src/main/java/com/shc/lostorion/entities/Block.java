package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class Block extends Entity2D
{
    public Block(float x, float y)
    {
        position.set(x, y);
        addComponent(new BatchComponent(new Sprite(Resources.Textures.TILE_BLOCKED), 1));
    }
}
