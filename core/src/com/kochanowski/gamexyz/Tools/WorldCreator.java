package com.kochanowski.gamexyz.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.kochanowski.gamexyz.Sprites.Ground;
import com.kochanowski.gamexyz.Sprites.Stones;

/**
 * Created by Bartek on 2017-10-29.
 */
public class WorldCreator {

    public WorldCreator(World world, TiledMap map) {

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle= ((RectangleMapObject) object).getRectangle();

            new Ground(world,map,rectangle);
        }

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle= ((RectangleMapObject) object).getRectangle();

            new Stones(world,map,rectangle);
        }
    }
}
