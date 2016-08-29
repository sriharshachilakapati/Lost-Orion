package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.LostOrion;
import com.shc.lostorion.Resources;
import com.shc.lostorion.states.PlayState;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class BlackBox extends Entity2D
{
    public BlackBox(float x, float y)
    {
        position.set(x, y);
        scale.set(0.5f);
        addComponent(new BatchComponent(new Sprite(Resources.Textures.BLACK_BOX), 1));

        final float width = Resources.Textures.BLACK_BOX.getWidth();
        final float height = Resources.Textures.BLACK_BOX.getHeight();

        addComponent(new CollisionComponent2D(Resources.CollisionTags.BLACK_BOX, new Rectangle(width, height),
                this::onCollision));
    }

    private void onCollision(Entity2D self, CollisionComponent2D other)
    {
        PlayState.BOXES++;
        PlayState.SCORE += 10;
        LostOrion.INSTANCE.setGameState(new PlayState());
    }
}
