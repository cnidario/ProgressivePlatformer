package org.hugo.platformer;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import org.hugo.platformer.components.*;

public class RenderEngine {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private TextureRegion background;
    private World world;

    public RenderEngine(SpriteBatch batch, World world) {
        this.batch = batch;
        cam = new OrthographicCamera(640, 480);
        cam.position.set(640/2, 480/2, 0);
        background = Assets.backgroundRegion;
        this.world = world;
    }

    public void render() {
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        renderBackground();
        renderVisualAspects();

        batch.end();
    }
    public void renderBackground() {
        batch.draw(background, 0, 0, 640, 480);
    }
    public void renderVisualAspects() {
        Family drawablesFamily = Family.all(TextureVisualAspectComponent.class, SpatialComponent.class).get();
        ComponentMapper<SpatialComponent> spatialM = ComponentMapper.getFor(SpatialComponent.class);
        ComponentMapper<TextureVisualAspectComponent> visualM = ComponentMapper.getFor(TextureVisualAspectComponent.class);
        ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);
        for(Entity e : world.engine.getEntitiesFor(drawablesFamily)) {
            SpatialComponent spatial = spatialM.get(e);
            TextureVisualAspectComponent visual = visualM.get(e);
            TransformComponent transform = transformM.get(e);
            TextureRegion tex = visual.textureRegion;
            if(transform != null) {
                float w = tex.getRegionWidth();
                float h = tex.getRegionHeight();
                float x = transform.flipX ? spatial.position.x + w : spatial.position.x;
                float y = spatial.position.y;
                if(transform.flipX) w = -w;

                batch.draw(tex, x, y, 0, 0, w, h,
                        transform.scaleX, transform.scaleY, transform.rotation);
            } else {
                batch.draw(tex, spatial.position.x, spatial.position.y);
            }
        }
    }
}
