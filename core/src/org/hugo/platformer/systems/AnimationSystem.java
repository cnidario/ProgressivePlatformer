package org.hugo.platformer.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.hugo.platformer.components.AnimationComponent;
import org.hugo.platformer.components.TextureVisualAspectComponent;

public class AnimationSystem extends IteratingSystem {
    ComponentMapper<AnimationComponent> animationM = ComponentMapper.getFor(AnimationComponent.class);
    ComponentMapper<TextureVisualAspectComponent> aspectM = ComponentMapper.getFor(TextureVisualAspectComponent.class);

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, TextureVisualAspectComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animation = animationM.get(entity);
        TextureVisualAspectComponent aspect = aspectM.get(entity);
        animation.time += deltaTime;
        if(animation.enabled) {
            aspect.textureRegion = animation.animation.getKeyFrame(animation.time);
        }
    }
}
