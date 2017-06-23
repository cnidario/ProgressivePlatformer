package org.hugo.platformer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderEngine {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private TextureRegion background;

    public RenderEngine(SpriteBatch batch) {
        this.batch = batch;
        cam = new OrthographicCamera(640, 480);
        cam.position.set(640/2, 480/2, 0);
        background = Assets.backgroundRegion;
    }

    public void render() {
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        batch.draw(background, 0, 0, 640, 480);

        batch.end();
    }
}
