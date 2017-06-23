package org.hugo.platformer;

import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter {
    ProgressivePlatformerGame game;
    RenderEngine renderEngine;

    public GameScreen(ProgressivePlatformerGame game) {
        this.game = game;
        renderEngine = new RenderEngine(game.batch);
    }
    public void update(float delta) {
    }

    @Override
    public void render(float delta) {
        update(delta);

        renderEngine.render();
    }
}
