package org.hugo.platformer.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import org.hugo.platformer.components.ClockComponent;

public class ClockSystem extends IteratingSystem {
    private ComponentMapper<ClockComponent> clockM = ComponentMapper.getFor(ClockComponent.class);

    public ClockSystem() {
        super(Family.all(ClockComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ClockComponent clock = clockM.get(entity);
        clock.localTime += deltaTime;
    }
}
