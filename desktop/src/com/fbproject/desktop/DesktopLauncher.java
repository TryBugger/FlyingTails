package com.fbproject.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fbproject.FlappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = FlappyBird.HEIGHT;
		config.width = FlappyBird.WIDTH;
		config.title = FlappyBird.TITLE;

		config.forceExit = true;

		new LwjglApplication(new FlappyBird(), config);
	}
}
