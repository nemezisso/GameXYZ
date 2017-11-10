package com.kochanowski.gamexyz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kochanowski.gamexyz.GameXYZ;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS=60;
		config.width=800;
		config.height=400;
		new LwjglApplication(new GameXYZ(), config);
	}
}
