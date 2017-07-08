package org.hugo.platformer.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.hugo.platformer.Assets;
import org.hugo.platformer.components.CavemanActionStateComponent;
import org.hugo.platformer.components.CavemanInputControllerComponent;

public class CavemanInputControllerSystem extends IteratingSystem {
    private ComponentMapper<CavemanInputControllerComponent> inputM = ComponentMapper.getFor(CavemanInputControllerComponent.class);
    private ComponentMapper<CavemanActionStateComponent> stateM = ComponentMapper.getFor(CavemanActionStateComponent.class);

    public CavemanInputControllerSystem() {
        super(Family.all(CavemanInputControllerComponent.class, CavemanActionStateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CavemanInputControllerComponent input = inputM.get(entity);
        CavemanActionStateComponent state = stateM.get(entity);
        CavemanActionStateComponent.State oldState = state.state;
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            state.state = CavemanActionStateComponent.State.WALKING;
            state.facing = CavemanActionStateComponent.Facing.LEFT;
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            state.state = CavemanActionStateComponent.State.WALKING;
            state.facing = CavemanActionStateComponent.Facing.RIGHT;
        } else {
            state.state = CavemanActionStateComponent.State.IDLE;
        }

        state.changed = state.state != oldState;
    }
}
