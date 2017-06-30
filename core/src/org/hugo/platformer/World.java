package org.hugo.platformer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import org.hugo.platformer.components.*;
import org.hugo.platformer.math.Polynomial;
import org.hugo.platformer.systems.CavemanInputControllerSystem;
import org.hugo.platformer.systems.ClockSystem;
import org.hugo.platformer.systems.SpatialPolynomialSystem;
import org.hugo.platformer.systems.StateMovementSystem;

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
        CavemanActionStateComponent state = engine.createComponent(CavemanActionStateComponent.class);
        CavemanInputControllerComponent input = engine.createComponent(CavemanInputControllerComponent.class);

        spatial.position = new Vector2(150, 150);
        polynomialMovement.position = Polynomial.constant(Vector2.Zero);
        polynomialMovement.position.add(new Vector2(25f, 0));
        //aspect.textureRegion = Assets.cavemanIdle1;
        animation.animation = Assets.cavemanIdle;
        state.state = CavemanActionStateComponent.State.IDLE;

        caveman.add(spatial);
        caveman.add(polynomialMovement);
        caveman.add(engine.createComponent(ClockComponent.class));
        //caveman.add(aspect);
        caveman.add(animation);
        caveman.add(state);
        caveman.add(input);

        engine.addEntity(caveman);
        return caveman;
    }
}
