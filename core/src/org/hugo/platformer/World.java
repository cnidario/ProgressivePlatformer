package org.hugo.platformer;

import com.alwex.tree.QuadRectangle;
import com.alwex.tree.QuadTree;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
    public TiledMap map;
    public QuadTree<Entity> quadTree;

    public World(PooledEngine engine) {
        this.engine = engine;
        engine.addSystem(new SpatialPolynomialSystem());
        engine.addSystem(new ClockSystem());
        engine.addSystem(new CavemanInputControllerSystem());
        engine.addSystem(new StateMovementSystem());
        engine.addSystem(new AnimationSystem());
        QuadTree.maxItemByNode = 20;
        QuadTree.maxLevel = 10;
        quadTree = new QuadTree<Entity>(new QuadRectangle(0, 0, 20, 20), 0);
    }

    public void create() {
        caveman = createCaveman();
        map = createLevel();
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

        spatial.position = new Vector2(1, 6);
        polynomialMovement.position = Polynomial.constant(Vector2.Zero);
        polynomialMovement.position.add(new Vector2(0.1f, 0));
        aspect.textureRegion = Assets.cavemanIdle1;
        animation.animation = Assets.cavemanIdle;
        animation.enabled = true;
        animation.time = 0f;
        state.state = CavemanActionStateComponent.State.IDLE;
        state.changed = true;
        state.animations = new LinkedHashMap<CavemanActionStateComponent.State, Animation>();
        state.animations.put(CavemanActionStateComponent.State.IDLE, Assets.cavemanIdle);
        state.animations.put(CavemanActionStateComponent.State.WALKING, Assets.cavemanWalking);
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
        quadTree.insert(new QuadRectangle(1,1,1,1), caveman);
        return caveman;
    }
    public TiledMap createLevel() {
        TiledMap map = new TmxMapLoader().load("map1.tmx");
        return map;
    }
}
