package org.hugo.platformer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Map;

public class CavemanActionStateComponent implements Component {
    public static enum State { WALKING_LEFT, WALKING_RIGHT, IDLE };
    public State state;
    public boolean changed;
    public Map<State, Animation> animations;
}
