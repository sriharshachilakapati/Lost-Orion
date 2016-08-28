package com.shc.lostorion;

import com.shc.lostorion.states.PlayState;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.scene.components.IComponent2D;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class BatchComponent implements IComponent2D
{
    public Sprite      sprite;
    public int         layer;

    private Entity2D entity;

    public BatchComponent(Sprite sprite, int layer)
    {
        this.sprite = sprite;
        this.layer = layer;
    }

    @Override
    public void init(Entity2D entity)
    {
        this.entity = entity;
    }

    @Override
    public void update(float deltaTime)
    {
        sprite.update(deltaTime);
    }

    @Override
    public void render(float deltaTime)
    {
        PlayState.SPRITE_BATCH.addSprite(sprite, entity.transformComponent.transform, layer);
    }

    @Override
    public void dispose()
    {
    }
}
