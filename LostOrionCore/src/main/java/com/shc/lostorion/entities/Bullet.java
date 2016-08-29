package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.Resources;
import com.shc.lostorion.states.PlayState;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.math.Vector2;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.entity.Entity2D;
import com.shc.silenceengine.utils.GameTimer;
import com.shc.silenceengine.utils.TimeUtils;

/**
 * @author Sri Harsha Chilakapati
 */
public class Bullet extends Entity2D
{
    private boolean dead;

    private Vector2 speed = new Vector2();

    public Bullet(float x, float y, float rotation)
    {
        position.set(x, y);
        speed.set(0, -400);
        this.rotation = rotation;

        GameTimer timer = new GameTimer(10, TimeUtils.Unit.SECONDS);
        timer.setCallback(() -> dead = true);
        timer.start();

        final float width = Resources.Textures.BULLET.getWidth();
        final float height = Resources.Textures.BULLET.getHeight();

        addComponent(new BatchComponent(new Sprite(Resources.Textures.BULLET), 0));
        addComponent(new CollisionComponent2D(Resources.CollisionTags.BULLET, new Rectangle(width, height),
                this::onCollision));
    }

    private void onCollision(Entity2D self, CollisionComponent2D other)
    {
        if (other.entity instanceof Block)
            destroy();
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        if (dead)
        {
            destroy();
            return;
        }

        Vector2 temp = Vector2.REUSABLE_STACK.pop();
        position.add(temp.set(speed).rotate(rotation).scale(deltaTime));
        Vector2.REUSABLE_STACK.push(temp);
    }

    private void destroy()
    {
        dead = true;
        PlayState.SCENE.entities.remove(this);
        PlayState.SCENE.entities.add(new Explosion(position.x, position.y));
    }
}
