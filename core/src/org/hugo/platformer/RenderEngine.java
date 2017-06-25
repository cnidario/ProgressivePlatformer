package org.hugo.platformer;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.hugo.platformer.components.AnimationComponent;
import org.hugo.platformer.components.ClockComponent;
import org.hugo.platformer.components.SpatialComponent;
import org.hugo.platformer.components.VisualAspectComponent;

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
        renderAnimations();

        batch.end();
    }
    public void renderBackground() {
        batch.draw(background, 0, 0, 640, 480);
    }
    public void renderVisualAspects() {
        Family drawablesFamily = Family.all(VisualAspectComponent.class, SpatialComponent.class).get();
        ComponentMapper<SpatialComponent> spatialM = ComponentMapper.getFor(SpatialComponent.class);
        ComponentMapper<VisualAspectComponent> visualM = ComponentMapper.getFor(VisualAspectComponent.class);
        for(Entity e : world.engine.getEntitiesFor(drawablesFamily)) {
            SpatialComponent spatial = spatialM.get(e);
            VisualAspectComponent visual = visualM.get(e);
            batch.draw(visual.textureRegion, spatial.position.x, spatial.position.y);
        }
    }
    public void renderAnimations() {
        Family animationsFamily = Family.all(AnimationComponent.class, SpatialComponent.class, ClockComponent.class).get();
        ComponentMapper<SpatialComponent> spatialM = ComponentMapper.getFor(SpatialComponent.class);
        ComponentMapper<AnimationComponent> animationM = ComponentMapper.getFor(AnimationComponent.class);
        ComponentMapper<ClockComponent> clockM = ComponentMapper.getFor(ClockComponent.class);
        for(Entity e : world.engine.getEntitiesFor(animationsFamily)) {
            AnimationComponent animation = animationM.get(e);
            SpatialComponent spatial = spatialM.get(e);
            ClockComponent clock = clockM.get(e);
            TextureRegion tex = animation.animation.getKeyFrame(clock.localTime);
            batch.draw(tex, spatial.position.x, spatial.position.y);
        }
    }
}
