package org.hugo.platformer.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import org.hugo.platformer.math.Polynomial;

public class PolynomialMovementComponent implements Component {
    public Polynomial<Vector2> position;
}
