package com.kochanowski.gamexyz.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Bartek on 2017-10-29.
 */
public class Stones extends InteractiveObject {

    public Stones(World world, TiledMap map, Rectangle rectangle) {
        super(world, map, rectangle);

    }
}
