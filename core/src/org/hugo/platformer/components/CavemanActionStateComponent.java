package org.hugo.platformer.components;

import com.badlogic.ashley.core.Component;

public class CavemanActionStateComponent implements Component {
    public static enum State { WALKING_LEFT, WALKING_RIGHT, IDLE };
    public State state;
}
