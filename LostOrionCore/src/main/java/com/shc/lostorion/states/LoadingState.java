package com.shc.lostorion.states;

import com.shc.lostorion.Level;
import com.shc.lostorion.LostOrion;
import com.shc.lostorion.Resources;
import com.shc.silenceengine.audio.Sound;
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
    private long explosionId;
    private long rollerId;
    private long logoId;
    private long blackBoxId;

    private long levelTestId;

    private long robotoId;

    private long titleId;
    private long weirdId;
    private long laserId;
    private long explosionSndId;

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
        explosionId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/explosion-sheet.png"));
        rollerId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/rollers-sheet.png"));
        logoId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/logo.png"));
        blackBoxId = resourceLoader.define(Texture.class, FilePath.getResourceFile("textures/black-box.png"));
        robotoId = resourceLoader.define(BitmapFont.class, FilePath.getResourceFile("engine_resources/fonts/roboto32px.fnt"));
        levelTestId = resourceLoader.define(Level.class, FilePath.getResourceFile("levels/Test.lvl"));
        titleId = resourceLoader.define(Sound.class, FilePath.getResourceFile("sounds/music/title.ogg"));
        weirdId = resourceLoader.define(Sound.class, FilePath.getResourceFile("sounds/music/keeps_getting_weirder.ogg"));
        laserId = resourceLoader.define(Sound.class, FilePath.getResourceFile("sounds/effects/laser_pew.ogg"));
        explosionSndId = resourceLoader.define(Sound.class, FilePath.getResourceFile("sounds/effects/explode_long.ogg"));

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
            Resources.Textures.EXPLOSION_SHEET = resourceLoader.get(explosionId);
            Resources.Textures.ROLLERS_SHEET = resourceLoader.get(rollerId);
            Resources.Textures.LOGO = resourceLoader.get(logoId);
            Resources.Textures.BLACK_BOX = resourceLoader.get(blackBoxId);

            Resources.Fonts.ROBOTO = resourceLoader.get(robotoId);

            SpriteSheet particles = new SpriteSheet(Resources.Textures.PARTICLES_SHEET, 29, 57);

            Animation particleAnim = Resources.Animations.PARTICLES = new Animation();
            particleAnim.addFrame(particles.getCell(0, 0), 100, TimeUtils.Unit.MILLIS);
            particleAnim.addFrame(particles.getCell(0, 1), 100, TimeUtils.Unit.MILLIS);
            particleAnim.addFrame(particles.getCell(0, 2), 100, TimeUtils.Unit.MILLIS);

            SpriteSheet tiles = new SpriteSheet(Resources.Textures.TILES_SHEET, 96, 96);
            Resources.Textures.TILE_FREE = tiles.getCell(0, 0);
            Resources.Textures.TILE_BLOCKED = tiles.getCell(0, 1);

            SpriteSheet explosion = new SpriteSheet(Resources.Textures.EXPLOSION_SHEET, 32, 32);

            Animation explosionAnim = Resources.Animations.EXPLOSION = new Animation();
            explosionAnim.addFrame(explosion.getCell(0, 0), 100, TimeUtils.Unit.MILLIS);
            explosionAnim.addFrame(explosion.getCell(0, 1), 100, TimeUtils.Unit.MILLIS);
            explosionAnim.addFrame(explosion.getCell(1, 0), 100, TimeUtils.Unit.MILLIS);
            explosionAnim.addFrame(explosion.getCell(1, 1), 100, TimeUtils.Unit.MILLIS);

            SpriteSheet rollers = new SpriteSheet(Resources.Textures.ROLLERS_SHEET, 48, 96);

            Animation rollersAnim = Resources.Animations.ROLLER = new Animation();
            rollersAnim.addFrame(rollers.getCell(0, 0), 100, TimeUtils.Unit.MILLIS);
            rollersAnim.addFrame(rollers.getCell(0, 1), 100, TimeUtils.Unit.MILLIS);

            Resources.Levels.TEST = resourceLoader.get(levelTestId);

            Resources.Sounds.TITLE = resourceLoader.get(titleId);
            Resources.Sounds.WEIRD = resourceLoader.get(weirdId);
            Resources.Sounds.LASER = resourceLoader.get(laserId);
            Resources.Sounds.EXPLOSION = resourceLoader.get(explosionSndId);

            Resources.Sounds.TITLE.play(true);

            LostOrion.INSTANCE.setGameState(new IntroState());
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
