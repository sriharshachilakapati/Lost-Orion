package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class Roller extends Entity2D
{
    public Roller(float x, float y)
    {
        position.set(x, y);

        Sprite sprite = new Sprite(Resources.Animations.ROLLER);
        sprite.setEndCallback(sprite::start);
        sprite.start();

        addComponent(new BatchComponent(sprite, 0));
    }
}
