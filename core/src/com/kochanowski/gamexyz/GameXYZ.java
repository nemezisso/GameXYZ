package com.kochanowski.gamexyz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kochanowski.gamexyz.Screens.MenuScreen;
import com.kochanowski.gamexyz.Screens.PlayScreen;
import com.kochanowski.gamexyz.Tools.Assets;

public class GameXYZ extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
	public static final float PPM = 100;

	public SpriteBatch batch;
	@Override
	public void create () {
		Assets.load();
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		Assets.dispose();
	}
}