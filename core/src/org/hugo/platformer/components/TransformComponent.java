package org.hugo.platformer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Affine2;

public class TransformComponent implements Component {
    public float scaleX, scaleY, rotation;
    public boolean flipX, flipY;
}
