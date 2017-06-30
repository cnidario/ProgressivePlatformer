package org.hugo.platformer.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import org.hugo.platformer.components.CavemanActionStateComponent;
import org.hugo.platformer.components.PolynomialMovementComponent;
import org.hugo.platformer.components.SpatialComponent;
import org.hugo.platformer.math.Polynomial;

public class StateMovementSystem extends IteratingSystem {
    private ComponentMapper<CavemanActionStateComponent> stateM = ComponentMapper.getFor(CavemanActionStateComponent.class);
    private ComponentMapper<PolynomialMovementComponent> polyMovementM = ComponentMapper.getFor(PolynomialMovementComponent.class);
    private ComponentMapper<SpatialComponent> spatialM = ComponentMapper.getFor(SpatialComponent.class);

    public StateMovementSystem() {
        super(Family.all(CavemanActionStateComponent.class, PolynomialMovementComponent.class, SpatialComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CavemanActionStateComponent state = stateM.get(entity);
        PolynomialMovementComponent polyMovement = polyMovementM.get(entity);
        SpatialComponent spatial = spatialM.get(entity);
        polyMovement.position = Polynomial.constant(Vector2.Zero);
        switch(state.state) {
            case IDLE: break;
            case WALKING_LEFT: polyMovement.position.add(Vector2.X.cpy().scl(-170f)); break;
            case WALKING_RIGHT: polyMovement.position.add(Vector2.X.cpy().scl(170f)); break;
        }
    }
}
