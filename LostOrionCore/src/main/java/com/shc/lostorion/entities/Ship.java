package com.shc.lostorion.entities;

import com.shc.lostorion.Resources;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.Vector2;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

import static com.shc.silenceengine.input.Keyboard.*;

/**
 * @author Sri Harsha Chilakapati
 */
public class Ship extends Entity2D
{
    private Vector2 speed = new Vector2();

    public Ship(float x, float y)
    {
        position.set(x, y);
        speed.set(0, 0);

        addComponent(new SpriteComponent(new Sprite(Resources.Textures.SHIP), Resources.Renderers.SPRITE));
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        if (Keyboard.isKeyDown(KEY_RIGHT))
            rotation += 90 * deltaTime;

        if (Keyboard.isKeyDown(KEY_LEFT))
            rotation -= 90 * deltaTime;

        speed.set(0);

        if (Keyboard.isKeyDown(KEY_UP))
            speed.y = -150;

        Vector2 temp = Vector2.REUSABLE_STACK.pop();
        position.add(temp.set(speed).rotate(rotation).scale(deltaTime));
        Vector2.REUSABLE_STACK.push(temp);
    }
}
