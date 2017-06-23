package org.hugo.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;

    public static void load() {
        background = new Texture(Gdx.files.internal("background/background-1.png"));
        backgroundRegion = new TextureRegion(background, 0, 0, 800, 600);
    }
}
