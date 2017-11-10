package com.kochanowski.gamexyz.Tools;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Bartek on 2017-11-03.
 */
public class Assets {

    public static final AssetManager manager= new AssetManager();

    //menu file
    public static final String buttonAtlas= "menu/button.pack";
    public static final String buttonSkin= "menu/buttonStyle.json";
    public static final AssetDescriptor<Texture> menuBackground= new AssetDescriptor<Texture>("menu/darkRaven2.png",Texture.class);
    public static final AssetDescriptor<Music> intro= new AssetDescriptor<Music>("music/intro.mp3", Music.class);

    //map
    public static final AssetDescriptor<TiledMap> map= new AssetDescriptor<TiledMap>("maps/test1.tmx",TiledMap.class);

    //hero
    public static final AssetDescriptor<TextureAtlas> heroAtlas= new AssetDescriptor<TextureAtlas>("sprites/hero/Hero.atlas", TextureAtlas.class);
    public static Animation<TextureRegion> run;
    public static Animation<TextureRegion> idle;
    public static Animation<TextureRegion> hurt;
    public static Animation<TextureRegion> shoot;
    public static Animation<TextureRegion> death;
    public static Animation<TextureRegion> jumpFall;
    public static Animation<TextureRegion> jumpStart;
    public static Animation<TextureRegion> jumpOnAir;
    public static Animation<TextureRegion> knockback;


    public static void load(){
        manager.load(menuBackground);
        manager.load(intro);
        manager.load(buttonAtlas,TextureAtlas.class);
        manager.load(buttonSkin,Skin.class, new SkinLoader.SkinParameter(buttonAtlas));
        manager.load(heroAtlas);
        manager.finishLoading();

        loadHero();

        loadMap();
    }

    public static void loadMap(){
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load(map);
    }

    public static void loadHero(){
        //todo correct animation shoot, jumpStart and knockback
        run= loadAnimation("Run",3,5,645,552);
        idle= loadAnimation("Idle",2,6,449,452);
        jumpStart = loadAnimation("JumpStart",5,2,547,470);
        jumpFall= loadAnimation("JumpFall",1,1,542,432);
        jumpOnAir = loadAnimation("JumpOnAir",1,1,549,466);
        hurt = loadAnimation("Hurt",5,2,566,468);
        death = loadAnimation("Death",5,3,693,563);
        shoot = loadAnimation("Shoot",8,2,754,502);
        knockback = loadAnimation("Knockback",8,2,670,544);
    }

    private static Animation<TextureRegion> loadAnimation(String findRegion,int frameWidth, int frameHeight, int characterWidth, int characterHeight){
        TextureRegion region=Assets.manager.get(Assets.heroAtlas).findRegion(findRegion);
        Array<TextureRegion> frames= new Array<TextureRegion>();

        int textureWidth=1;
        int textureHeight=1;
        for(int i=0;i<frameWidth;i++) {
            for(int j=0;j<frameHeight;j++){
                frames.add(new TextureRegion(region,
                        textureWidth, textureHeight, characterWidth, characterHeight));
                textureWidth+=characterWidth;
            }
            textureWidth=1;
            textureHeight+=characterHeight;
        }

        return new Animation<TextureRegion>(0.1f,frames);
    }

    public static void dispose(){
        manager.dispose();
    }
}
