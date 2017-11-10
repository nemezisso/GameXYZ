package com.kochanowski.gamexyz.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.kochanowski.gamexyz.GameXYZ;
import com.kochanowski.gamexyz.Screens.PlayScreen;
import com.kochanowski.gamexyz.Tools.Assets;

/**
 * Created by Bartek on 2017-10-29.
 */
public class Hero extends Sprite {

    public enum State{DEATH,HURT,IDLE,JUMPFALL,JUMPSTART,JUMPONAIR,KNOCKBACK,RUN,SHOOT};
    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningRight;

    private Animation<TextureRegion> run;
    private Animation<TextureRegion> idle;
    private Animation<TextureRegion> jumpFall;
    private Animation<TextureRegion> jumpStart;
    private Animation<TextureRegion> jumpOnAir;
    private Animation<TextureRegion> shoot;
    private Animation<TextureRegion> hurt;
    private Animation<TextureRegion> death;
    private Animation<TextureRegion> knockback;

    public World world;
    public Body bodyHero;

    public Hero(World world, PlayScreen playScreen) {
        super(Assets.manager.get(Assets.heroAtlas).findRegion("Idle"));
        this.world = world;
        currentState= State.IDLE;
        previousState= State.IDLE;
        stateTimer= 0;
        runningRight= true;

        run= Assets.run;
        idle= Assets.idle;
        jumpStart= Assets.jumpStart;
        jumpOnAir= Assets.jumpOnAir;
        jumpFall= Assets.jumpFall;
        shoot= Assets.shoot;
        death= Assets.death;
        hurt= Assets.hurt;
        knockback= Assets.knockback;

        defineHero();
        setBounds(0,0,80/GameXYZ.PPM,80/GameXYZ.PPM);
        //first texture idle
        setRegion(new TextureRegion(getTexture(),6205,1413,449,452));
    }

    private void defineHero() {
        BodyDef bodyDef= new BodyDef();
        bodyDef.position.set(100/GameXYZ.PPM,100/GameXYZ.PPM);
        bodyDef.type= BodyDef.BodyType.DynamicBody;

        bodyHero= world.createBody(bodyDef);

        FixtureDef fixtureDef= new FixtureDef();
        PolygonShape shape= new PolygonShape();
        shape.setAsBox(20/ GameXYZ.PPM,35/GameXYZ.PPM);

        fixtureDef.shape=shape;
        bodyHero.createFixture(fixtureDef);

    }

    public void update(float delta){
        setPosition((bodyHero.getPosition().x- getWidth()/2)+0.1f,bodyHero.getPosition().y - getHeight()/2);
        setRegion(getFrame(delta));
    }

    private TextureRegion getFrame(float delta){
        currentState= getState();

        TextureRegion region;
        switch (currentState){
            case JUMPSTART:
                region= jumpStart.getKeyFrame(stateTimer);
                break;
            case JUMPFALL:
                region= jumpFall.getKeyFrame(stateTimer,false);
                break;
            case JUMPONAIR:
                region= jumpOnAir.getKeyFrame(stateTimer);
                break;
            case HURT:
                region= hurt.getKeyFrame(stateTimer);
                break;
            case DEATH:
                region= death.getKeyFrame(stateTimer);
                break;
            case KNOCKBACK:
                region= knockback.getKeyFrame(stateTimer,true);
                break;
            case RUN:
                region= run.getKeyFrame(stateTimer,true);
                break;
            case SHOOT:
                region= shoot.getKeyFrame(stateTimer,true);
                break;
           default:
                region= idle.getKeyFrame(stateTimer,true);
                break;
        }

        if (bodyHero.getLinearVelocity().x<0 || !runningRight && !region.isFlipX()){
            region.flip(true,false);
            runningRight=false;
        }
        if (bodyHero.getLinearVelocity().x<0 || runningRight && region.isFlipX()){
            region.flip(true,false); //!region.isFlipX(), region.isFlipY()
            runningRight=true;
        }

        stateTimer= currentState== previousState ? stateTimer+delta : 0;
        previousState=currentState;
        return region;
    }
    private State getState(){
        if(bodyHero.getLinearVelocity().y>0 || bodyHero.getLinearVelocity().y<0 && previousState== State.JUMPSTART)
            return State.JUMPSTART;
        else if (bodyHero.getLinearVelocity().y<0)
            return State.JUMPFALL;
        else if(bodyHero.getLinearVelocity().x !=0)
            return State.RUN;
        else if(Gdx.input.isKeyPressed(Input.Keys.X))
            return State.SHOOT;
        else
            return State.IDLE;
    }
}
