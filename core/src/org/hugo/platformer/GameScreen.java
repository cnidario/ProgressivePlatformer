package org.hugo.platformer;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import org.hugo.platformer.systems.ClockSystem;
import org.hugo.platformer.systems.SpatialPolynomialSystem;

public class GameScreen extends ScreenAdapter {
    ProgressivePlatformerGame game;
    RenderEngine renderEngine;
    PooledEngine engine;
    World world;

    public GameScreen(ProgressivePlatformerGame game) {
        this.game = game;
        engine = new PooledEngine();
        world = new World(engine);
        world.create();
        renderEngine = new RenderEngine(game.batch, world);
    }
    public void update(float delta) {
        engine.update(delta);
    }

    @Override
    public void render(float delta) {
        update(delta);

        renderEngine.render();
    }
}
