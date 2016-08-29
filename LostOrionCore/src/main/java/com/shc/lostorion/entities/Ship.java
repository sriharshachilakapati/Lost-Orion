package com.shc.lostorion.entities;

import com.shc.lostorion.BatchComponent;
import com.shc.lostorion.LostOrion;
import com.shc.lostorion.Resources;
import com.shc.lostorion.states.PlayState;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.Vector2;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.entity.Entity2D;

import static com.shc.silenceengine.input.Keyboard.*;

/**
 * @author Sri Harsha Chilakapati
 */
public class Ship extends Entity2D
{
    public static Ship INSTANCE;

    private Vector2 speed = new Vector2();
    private CollisionComponent2D collision;

    public Ship(float x, float y)
    {
        INSTANCE = this;

        position.set(x, y);
        speed.set(0, 0);

        addComponent(new BatchComponent(new Sprite(Resources.Textures.SHIP), 1));

        Entity2D particles = new Entity2D();
        particles.position.y = 70;

        Sprite particleAnim = new Sprite(Resources.Animations.PARTICLES);
        particleAnim.setEndCallback(particleAnim::start);
        particleAnim.start();

        particles.addComponent(new BatchComponent(particleAnim, 0));
        addChild(particles);

        final float width = Resources.Textures.SHIP.getWidth();
        final float height = Resources.Textures.SHIP.getHeight();

        addComponent(collision = new CollisionComponent2D(Resources.CollisionTags.SHIP, new Rectangle(width, height),
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
        else if (other.entity instanceof Roller)
            LostOrion.INSTANCE.setGameState(new PlayState());
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        PlayState.GAME_CAMERA.center(position);

        if (Keyboard.isKeyTapped(KEY_SPACE))
            PlayState.SCENE.entities.add(new Bullet(position.x, position.y, rotation));

        if (Keyboard.isKeyDown(KEY_RIGHT))
            rotation += 90 * deltaTime;

        if (Keyboard.isKeyDown(KEY_LEFT))
            rotation -= 90 * deltaTime;

        speed.set(0);

        if (Keyboard.isKeyDown(KEY_UP))
            speed.y = -150;

        if (Keyboard.isKeyDown(KEY_DOWN))
            speed.y = 150;

        Vector2 temp = Vector2.REUSABLE_STACK.pop();
        position.add(temp.set(speed).rotate(rotation).scale(deltaTime));
        Vector2.REUSABLE_STACK.push(temp);
    }
}
