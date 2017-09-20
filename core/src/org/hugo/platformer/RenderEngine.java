package org.hugo.platformer;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.hugo.platformer.components.*;

public class RenderEngine {
    private SpriteBatch batch;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera cam;
    private World world;

    public RenderEngine(SpriteBatch batch, World world) {
        this.batch = batch;
        cam = new OrthographicCamera(640, 480);
        //cam.position.set(0, 0, 0);
        cam.setToOrtho(false, 20, 15);
        this.world = world;
        mapRenderer = new OrthogonalTiledMapRenderer(world.map, 1/32f);
        mapRenderer.setView(cam);
    }

    public void render() {
        float x = cam.position.x;
        float y = cam.position.y;
        float z = cam.position.z;
        //cam.position.set(x + 0.1f, y, z);
        cam.update();

        renderLevel();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        renderVisualAspects();

        batch.end();
    }
    public void renderLevel() {
        mapRenderer.render();
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
                float w = tex.getRegionWidth()/32f;
                float h = tex.getRegionHeight()/32f;
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
