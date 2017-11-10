package com.kochanowski.gamexyz.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Bartek on 2017-10-29.
 */
public class Ground extends InteractiveObject {
    public Ground(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }
}
