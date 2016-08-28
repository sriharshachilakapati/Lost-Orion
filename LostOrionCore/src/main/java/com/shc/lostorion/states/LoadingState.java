package com.shc.lostorion.states;

import com.shc.lostorion.Level;
import com.shc.lostorion.LostOrion;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.ResourceLoader;
import com.shc.silenceengine.graphics.Animation;
import com.shc.silenceengine.graphics.Color;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.SpriteRenderer;
import com.shc.silenceengine.graphics.SpriteSheet;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Primitive;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.graphics.programs.DynamicProgram;
import com.shc.silenceengine.io.FilePath;
import com.shc.silenceengine.utils.MathUtils;
import com.shc.silenceengine.utils.TimeUtils;

/**
 * @author Sri Harsha Chilakapati
 */
public class LoadingState extends GameState
{
    private ResourceLoader resourceLoader;

    private long bulletId;
    private long shipId;
    private long particlesId;
    private long tilesId;

    private long levelTestId;

    private long robotoId;

    @Override
    public void onEnter()
    {
        // Helper to load the levels using the ResourceLoader
        ResourceLoader.setHelper(Level.class, Level::loadHelper);

        resourceLoader = new ResourceLoader();

        bulletId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/bullet.png"));
        shipId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/ship.png"));
        particlesId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/particles-sheet.png"));
        tilesId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/tiles-sheet.png"));
        robotoId = resourceLoader.define(BitmapFont.class, FilePath.getResourceFile("engine_resources/fonts/roboto32px.fnt"));
        levelTestId = resourceLoader.define(Level.class, FilePath.getResourceFile("levels/Test.lvl"));

        DynamicProgram.create(dynamicProgram ->
        {
            Resources.Programs.DYNAMIC = dynamicProgram;
            Resources.Renderers.DYNAMIC = new DynamicRenderer(500);

            dynamicProgram.applyToRenderer(Resources.Renderers.DYNAMIC);

            SpriteRenderer.create(spriteRenderer ->
            {
                Resources.Renderers.SPRITE = spriteRenderer;

                BitmapFontRenderer.create(fontRenderer ->
                {
                    Resources.Renderers.FONT = fontRenderer;

                    resourceLoader.start();
                });
            });
        });
    }

    @Override
    public void update(float delta)
    {
        if (resourceLoader.isDone())
        {
            Resources.Textures.BULLET = resourceLoader.get(bulletId);
            Resources.Textures.SHIP = resourceLoader.get(shipId);
            Resources.Textures.PARTICLES_SHEET = resourceLoader.get(particlesId);
            Resources.Textures.TILES_SHEET = resourceLoader.get(tilesId);

            Resources.Fonts.ROBOTO = resourceLoader.get(robotoId);

            SpriteSheet particles = new SpriteSheet(Resources.Textures.PARTICLES_SHEET, 29, 57);

            Animation particleAnim = Resources.Animations.PARTICLES = new Animation();
            particleAnim.addFrame(particles.getCell(0, 0), 100, TimeUtils.Unit.MILLIS);
            particleAnim.addFrame(particles.getCell(0, 1), 100, TimeUtils.Unit.MILLIS);
            particleAnim.addFrame(particles.getCell(0, 2), 100, TimeUtils.Unit.MILLIS);

            SpriteSheet tiles = new SpriteSheet(Resources.Textures.TILES_SHEET, 96, 96);
            Resources.Textures.TILE_FREE = tiles.getCell(0, 0);
            Resources.Textures.TILE_BLOCKED = tiles.getCell(0, 1);

            Resources.Levels.TEST = resourceLoader.get(levelTestId);

            LostOrion.INSTANCE.setGameState(new PlayState());
        }
    }

    @Override
    public void render(float delta)
    {
        DynamicRenderer renderer = Resources.Renderers.DYNAMIC;

        if (renderer != null)
        {
            Resources.Programs.DYNAMIC.use();
            Resources.Programs.DYNAMIC.applyToRenderer(renderer);

            final float percentage = MathUtils.convertRange(resourceLoader.getPercentage(), 0, 100, -0.8f, 0.8f);

            renderer.begin(Primitive.TRIANGLE_STRIP);
            {
                renderer.vertex(-0.8f, -0.7f);
                renderer.color(Color.ORANGE);

                renderer.vertex(percentage, -0.7f);
                renderer.color(Color.RED);

                renderer.vertex(-0.8f, -0.8f);
                renderer.color(Color.RED);

                renderer.vertex(percentage, -0.8f);
                renderer.color(Color.ORANGE);
            }
            renderer.end();
        }
    }
}
