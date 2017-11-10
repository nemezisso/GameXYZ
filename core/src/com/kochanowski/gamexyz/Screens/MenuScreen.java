package com.kochanowski.gamexyz.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kochanowski.gamexyz.GameXYZ;
import com.kochanowski.gamexyz.Tools.Assets;

public class MenuScreen extends AbstractScreen {

    public MenuScreen(GameXYZ game) {
        super(game);
        changeCamera();

        //todo change position finishLoading
        Assets.manager.finishLoading();

        //play intro music
        Assets.manager.get(Assets.intro).play();

        initButtons();
    }

    private void changeCamera(){
        viewPort= new StretchViewport(GameXYZ.WIDTH,GameXYZ.HEIGHT,camera);
        viewPort.apply();

        stage= new Stage(viewPort);
        Gdx.input.setInputProcessor(stage);
    }

    public void initButtons(){
        Table table=new Table(Assets.manager.get(Assets.buttonSkin, Skin.class));
        table.setFillParent(true);
        table.top();

        TextButton startButton= new TextButton("New game",Assets.manager.get(Assets.buttonSkin, Skin.class));
        startButton.pad(5);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.manager.get(Assets.intro).pause();
                game.setScreen(new PlayScreen(game));
            }
        });

        TextButton loadButton= new TextButton("Load game",Assets.manager.get(Assets.buttonSkin, Skin.class));
        loadButton.pad(5);
        loadButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.manager.get(Assets.intro).pause();
                //game.setScreen(new LoadGameScreen(game));
            }
        });

        TextButton instructionButton= new TextButton("Instruction", Assets.manager.get(Assets.buttonSkin, Skin.class));
        instructionButton.pad(5);
        instructionButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.manager.get(Assets.intro).pause();
                game.setScreen(new InstructionScreen(game));
            }
        });

        TextButton exitButton= new TextButton("Exit", Assets.manager.get(Assets.buttonSkin, Skin.class));
        exitButton.pad(5);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        table.center();
        table.setPosition(0,-70);
        table.add(startButton).spaceBottom(10).row();
        table.add(loadButton).spaceBottom(10).row();
        table.add(instructionButton).spaceBottom(10).row();
        table.add(exitButton).spaceBottom(10).row();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(Assets.manager.get(Assets.menuBackground),0,0);
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}