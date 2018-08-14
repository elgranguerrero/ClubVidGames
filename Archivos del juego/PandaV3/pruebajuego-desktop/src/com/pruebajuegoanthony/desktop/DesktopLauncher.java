package com.pruebajuegoanthony.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pruebajuegoanthony.pruebajuego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=460;
		config.height=700;
		new LwjglApplication(new pruebajuego(), config);
	}
}
   