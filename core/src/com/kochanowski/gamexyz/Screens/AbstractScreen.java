package com.kochanowski.gamexyz.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kochanowski.gamexyz.GameXYZ;

public abstract class AbstractScreen implements Screen {
    protected World world;
    protected GameXYZ game;

    protected Stage stage;
    protected SpriteBatch spriteBatch;

    protected Viewport viewPort;
    protected OrthographicCamera camera;

    public AbstractScreen(GameXYZ game){
        this.game=game;
        spriteBatch= new SpriteBatch();

        camera= new OrthographicCamera();
        viewPort= new StretchViewport(GameXYZ.WIDTH/GameXYZ.PPM,GameXYZ.HEIGHT/GameXYZ.PPM,camera);
        viewPort.apply();
        camera.position.set(viewPort.getWorldWidth()/2,viewPort.getScreenHeight()/2,0);
        camera.update();

        stage= new Stage(viewPort);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
//        delta+=Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width,height);
    }

    @Override
    public void show() {
    }

    @Override
    public void pause()
    {
//        game.pause();
    }

    @Override
    public void resume() {
 //       game.resume();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        game.dispose();
    }
    public World getWorld() {
        return world;
    }
}
