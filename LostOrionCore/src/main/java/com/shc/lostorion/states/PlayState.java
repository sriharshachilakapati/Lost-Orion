package com.shc.lostorion.states;

import com.shc.lostorion.Resources;
import com.shc.lostorion.SpriteBatch;
import com.shc.silenceengine.collision.broadphase.DynamicTree2D;
import com.shc.silenceengine.collision.colliders.SceneCollider2D;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.IGraphicsDevice;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.scene.Scene2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class PlayState extends GameState
{
    public static SpriteBatch SPRITE_BATCH = new SpriteBatch();

    private static OrthoCam hudCamera   = new OrthoCam(1280, 720);
    public static  OrthoCam GAME_CAMERA = new OrthoCam(1280, 720);

    public static Scene2D         SCENE;
    private       SceneCollider2D collider;

    @Override
    public void onEnter()
    {
        SCENE = new Scene2D();

        collider = new SceneCollider2D(new DynamicTree2D());
        collider.setScene(SCENE);

        collider.register(Resources.CollisionTags.SHIP, Resources.CollisionTags.BLOCK);
        collider.register(Resources.CollisionTags.BULLET, Resources.CollisionTags.BLOCK);

        Resources.Levels.TEST.create();
    }

    @Override
    public void update(float delta)
    {
        SilenceEngine.display.setTitle("LostOrion | RC: " + IGraphicsDevice.Data.renderCallsThisFrame);

        SCENE.update(delta);
        collider.checkCollisions();
    }

    @Override
    public void render(float delta)
    {
        GAME_CAMERA.apply();
        SCENE.render(delta);
        SPRITE_BATCH.renderToScreen();

        hudCamera.apply();
        BitmapFontRenderer fontRenderer = Resources.Renderers.FONT;
        BitmapFont font = Resources.Fonts.ROBOTO;

        fontRenderer.begin();
        {
            fontRenderer.render(font, "UPS: " + SilenceEngine.gameLoop.getUPS(), 10, 10);
            fontRenderer.render(font, "\nFPS: " + SilenceEngine.gameLoop.getFPS(), 10, 10);
        }
        fontRenderer.end();
    }
}
