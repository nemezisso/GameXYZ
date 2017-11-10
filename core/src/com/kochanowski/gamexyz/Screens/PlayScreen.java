package com.kochanowski.gamexyz.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kochanowski.gamexyz.GameXYZ;
import com.kochanowski.gamexyz.Sprites.Hero;
import com.kochanowski.gamexyz.Tools.Assets;
import com.kochanowski.gamexyz.Tools.WorldCreator;

/**
 * Created by Bartek on 2017-10-25.
 */
public class PlayScreen extends AbstractScreen {

    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    private Hero hero;

    public PlayScreen(GameXYZ game) {
        super(game);

        //todo- implementation of multiple maps
        renderer= new OrthogonalTiledMapRenderer(Assets.manager.get(Assets.map),1/GameXYZ.PPM);
        camera.position.set(viewPort.getWorldWidth()/2,viewPort.getWorldHeight()/2,0);

        world= new World(new Vector2(0,-9.81f),true);
        box2DDebugRenderer= new Box2DDebugRenderer();

        new WorldCreator(world, Assets.manager.get(Assets.map));
        //HERO
        hero=new Hero(world, this);

    }


    //camera.position.x<72 - maximum map size
    private void handleInput(float delta) {
            if (Gdx.input.isKeyPressed(Input.Keys.W))
                hero.bodyHero.applyLinearImpulse(new Vector2(0, 1f), hero.bodyHero.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.D ) && hero.bodyHero.getLinearVelocity().x <= 2 )
                hero.bodyHero.applyLinearImpulse(new Vector2(4f, 0), hero.bodyHero.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.A) && hero.bodyHero.getLinearVelocity().x <= 2)
                hero.bodyHero.applyLinearImpulse(new Vector2(-0.1f, 0), hero.bodyHero.getWorldCenter(), true);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        renderer.render();

        box2DDebugRenderer.render(world,camera.combined);

        spriteBatch.begin();
        hero.draw(spriteBatch);
        spriteBatch.end();

    }

    public void update(float delta){
        handleInput(delta);

        world.step(1/60f,6,2);

        hero.update(delta);

        camera.position.x= hero.bodyHero.getPosition().x;

        camera.update();
        renderer.setView(camera);
    }
    @Override
    public void dispose() {
        super.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
