package org.hugo.platformer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import org.hugo.platformer.components.*;
import org.hugo.platformer.math.Polynomial;
import org.hugo.platformer.systems.*;
import java.util.LinkedHashMap;
import java.util.Random;

public class World {
    public PooledEngine engine;
    public final Random rand = new Random();
    public Entity caveman;

    public World(PooledEngine engine) {
        this.engine = engine;
        engine.addSystem(new SpatialPolynomialSystem());
        engine.addSystem(new ClockSystem());
        engine.addSystem(new CavemanInputControllerSystem());
        engine.addSystem(new StateMovementSystem());
        engine.addSystem(new AnimationSystem());
    }

    public void create() {
        caveman = createCaveman();
    }
    public Entity createCaveman() {
        Entity caveman = engine.createEntity();
        PolynomialMovementComponent polynomialMovement = engine.createComponent(PolynomialMovementComponent.class);
        SpatialComponent spatial = engine.createComponent(SpatialComponent.class);
        TextureVisualAspectComponent aspect = engine.createComponent(TextureVisualAspectComponent.class);
        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        CavemanActionStateComponent state = engine.createComponent(CavemanActionStateComponent.class);
        CavemanInputControllerComponent input = engine.createComponent(CavemanInputControllerComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);

        spatial.position = new Vector2(150, 150);
        polynomialMovement.position = Polynomial.constant(Vector2.Zero);
        polynomialMovement.position.add(new Vector2(25f, 0));
        aspect.textureRegion = Assets.cavemanIdle1;
        animation.animation = Assets.cavemanIdle;
        animation.enabled = true;
        animation.time = 0f;
        state.state = CavemanActionStateComponent.State.IDLE;
        state.changed = true;
        state.animations = new LinkedHashMap<CavemanActionStateComponent.State, Animation>();
        state.animations.put(CavemanActionStateComponent.State.IDLE, Assets.cavemanIdle);
        state.animations.put(CavemanActionStateComponent.State.WALKING_LEFT, Assets.cavemanWalking);
        state.animations.put(CavemanActionStateComponent.State.WALKING_RIGHT, Assets.cavemanWalking);
        transform.scaleX = transform.scaleY = 1;
        transform.rotation = 0;
        transform.flipX = transform.flipY = false;

        caveman.add(spatial);
        caveman.add(polynomialMovement);
        caveman.add(engine.createComponent(ClockComponent.class));
        caveman.add(animation);
        caveman.add(aspect);
        caveman.add(state);
        caveman.add(input);
        caveman.add(transform);

        engine.addEntity(caveman);
        return caveman;
    }
}
