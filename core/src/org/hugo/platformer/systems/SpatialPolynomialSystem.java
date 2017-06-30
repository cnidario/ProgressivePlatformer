package org.hugo.platformer.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import org.hugo.platformer.components.PolynomialMovementComponent;
import org.hugo.platformer.components.SpatialComponent;

public class SpatialPolynomialSystem extends IteratingSystem {
    private ComponentMapper<PolynomialMovementComponent> polynomialMovementM;
    private ComponentMapper<SpatialComponent> spatialM;

    public SpatialPolynomialSystem() {
        super(Family.all(PolynomialMovementComponent.class, SpatialComponent.class).get());
        polynomialMovementM = ComponentMapper.getFor(PolynomialMovementComponent.class);
        spatialM = ComponentMapper.getFor(SpatialComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpatialComponent spatial = spatialM.get(entity);
        PolynomialMovementComponent polynomialMovement = polynomialMovementM.get(entity);
        spatial.position.add((Vector2) polynomialMovement.position.eval(deltaTime));
    }
}
