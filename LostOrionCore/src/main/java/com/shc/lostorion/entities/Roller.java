package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.math.Vector2;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.entity.Entity2D;
import com.shc.silenceengine.utils.MathUtils;

/**
 * @author Sri Harsha Chilakapati
 */
public class Roller extends Entity2D
{
    private Vector2 speed = new Vector2();
    private CollisionComponent2D collision;

    public Roller(float x, float y)
    {
        speed.set(80, 0);
        position.set(x, y);

        Sprite sprite = new Sprite(Resources.Animations.ROLLER);
        sprite.setEndCallback(sprite::start);
        sprite.start();

        addComponent(new BatchComponent(sprite, 0));

        final float width = sprite.getCurrentFrame().getWidth();
        final float height = sprite.getCurrentFrame().getHeight();

        addComponent(collision = new CollisionComponent2D(Resources.CollisionTags.ROLLER, new Rectangle(width, height),
                this::onCollision));
    }

    private void onCollision(Entity2D self, CollisionComponent2D other)
    {
        if (other.entity instanceof Block)
        {
            Rectangle tBounds = collision.polygon.getBounds();
            Rectangle oBounds = other.polygon.getBounds();

            final float iWidth = tBounds.getIntersectionWidth(oBounds);
            final float iHeight = tBounds.getIntersectionHeight(oBounds);

            if (iWidth >= iHeight)
            {
                if (position.y >= other.entity.position.y)
                    position.y += iHeight;
                else
                    position.y -= iHeight;
            }

            if (iHeight >= iWidth)
            {
                if (position.x >= other.entity.position.x)
                    position.x += iWidth;
                else
                    position.x -= iWidth;
            }
        }
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        Vector2 temp = Vector2.REUSABLE_STACK.pop();
        temp.set(Ship.INSTANCE.position).subtract(position);

        rotation = MathUtils.atan2(temp.y, temp.x);

        position.add(temp.set(speed).rotate(rotation).scale(deltaTime));
        Vector2.REUSABLE_STACK.push(temp);
    }
}
