package org.hugo.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture caveman;
    public static TextureRegion cavemanIdle1;
    public static Animation<TextureRegion> cavemanIdle;

    public static void load() {
        background = new Texture(Gdx.files.internal("background/background-1.png"));
        backgroundRegion = new TextureRegion(background, 0, 0, 800, 600);
        caveman = new Texture(Gdx.files.internal("characters/playable/caverman.png"));
        cavemanIdle1 = new TextureRegion(caveman, 15, 8, 60, 57);
        cavemanIdle = new Animation<TextureRegion>(0.15f,
                new TextureRegion(caveman, 15, 8, 60, 57),
                new TextureRegion(caveman, 112, 8, 60, 57),
                new TextureRegion(caveman, 209, 8, 60, 57),
                new TextureRegion(caveman, 306, 8, 60, 57),
                new TextureRegion(caveman, 403, 8, 60, 57),
                new TextureRegion(caveman, 500, 8, 60, 57));
        cavemanIdle.setPlayMode(Animation.PlayMode.LOOP);
    }
}
