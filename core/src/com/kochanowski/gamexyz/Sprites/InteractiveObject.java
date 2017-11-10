package com.kochanowski.gamexyz.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.kochanowski.gamexyz.GameXYZ;

/**
 * Created by Bartek on 2017-10-29.
 */
public abstract class InteractiveObject {
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;

    public InteractiveObject(World world, TiledMap map, Rectangle rectangle) {
        this.world = world;
        this.map = map;
        this.bounds = rectangle;

        BodyDef bodyDef= new BodyDef();
        FixtureDef fixtureDef= new FixtureDef();
        PolygonShape shape= new PolygonShape();

        bodyDef.type= BodyDef.BodyType.StaticBody;
        bodyDef.position.set((rectangle.getX()+ rectangle.getWidth()/2)/ GameXYZ.PPM,
                (rectangle.getY()+ rectangle.getHeight()/2)/GameXYZ.PPM);

        body= world.createBody(bodyDef);
        shape.setAsBox(rectangle.getWidth()/2/GameXYZ.PPM,rectangle.getHeight()/2/GameXYZ.PPM);

        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);
    }
}
