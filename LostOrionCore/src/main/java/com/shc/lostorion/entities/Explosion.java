package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.Resources;
import com.shc.lostorion.states.PlayState;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class Explosion extends Entity2D
{
    public Explosion(float x, float y)
    {
        position.set(x, y);

        Sprite sprite = new Sprite(Resources.Animations.EXPLOSION);
        sprite.setEndCallback(() -> PlayState.SCENE.entities.remove(this));
        sprite.start();

        addComponent(new BatchComponent(sprite, 2));
    }
}
