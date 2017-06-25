package org.hugo.platformer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import org.hugo.platformer.components.*;
import org.hugo.platformer.math.Polynomial;

import java.util.Random;

public class World {
    public PooledEngine engine;
    public final Random rand = new Random();
    public Entity caveman;

    public World(PooledEngine engine) {
        this.engine = engine;
    }

    public void create() {
        caveman = createCaveman();
    }
    public Entity createCaveman() {
        Entity caveman = engine.createEntity();
        PolynomialMovementComponent polynomialMovement = engine.createComponent(PolynomialMovementComponent.class);
        SpatialComponent spatial = engine.createComponent(SpatialComponent.class);
        //VisualAspectComponent aspect = engine.createComponent(VisualAspectComponent.class);
        AnimationComponent animation = engine.createComponent(AnimationComponent.class);

        polynomialMovement.position = Polynomial.constant(new Vector2(150, 150));
        polynomialMovement.position.add(new Vector2(25f, 0));
        //aspect.textureRegion = Assets.cavemanIdle1;
        animation.animation = Assets.cavemanIdle;

        caveman.add(spatial);
        caveman.add(polynomialMovement);
        caveman.add(engine.createComponent(ClockComponent.class));
        //caveman.add(aspect);
        caveman.add(animation);
        engine.addEntity(caveman);
        return caveman;
    }
}
