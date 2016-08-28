package com.shc.lostorion;

import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.graphics.SpriteRenderer;
import com.shc.silenceengine.math.Transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Sri Harsha Chilakapati
 */
public class SpriteBatch
{
    private List<Integer>   indices    = new ArrayList<>();
    private List<Sprite>    sprites    = new ArrayList<>();
    private List<Integer>   layers     = new ArrayList<>();
    private List<Transform> transforms = new ArrayList<>();

    public void addSprite(Sprite sprite, Transform transform, int layer)
    {
        indices.add(indices.size());
        sprites.add(sprite);
        transforms.add(transform);
        layers.add(layer);
    }

    public void renderToScreen()
    {
        Collections.sort(indices, (i1, i2) ->
        {
            Sprite s1 = sprites.get(i1);
            Sprite s2 = sprites.get(i2);

            int l1 = layers.get(i1);
            int l2 = layers.get(i2);

            if (l1 == l2)
                return s1.getCurrentFrame().getID() - s2.getCurrentFrame().getID();

            return l1 - l2;
        });

        SpriteRenderer renderer = Resources.Renderers.SPRITE;
        renderer.begin();
        {
            for (int i : indices)
            {
                Sprite s = sprites.get(i);
                Transform t = transforms.get(i);

                renderer.render(s, t);
            }
        }
        renderer.end();

        indices.clear();
        sprites.clear();
        transforms.clear();
        layers.clear();
    }
}
