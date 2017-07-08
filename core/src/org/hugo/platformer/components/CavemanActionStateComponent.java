package org.hugo.platformer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Map;

public class CavemanActionStateComponent implements Component {
    public static enum State { WALKING, IDLE };
    public static enum Facing { LEFT, RIGHT };
    public State state;
    public Facing facing;
    public boolean changed;
    public Map<State, Animation> animations;
}
