package com.pruebajuegoanthony;

import com.badlogic.gdx.Screen;

/**
 * Pantalla abstracta. En la mayoría de los casos nos interesa construir una
 * pantalla abstracta no implementable pero que disponga de características
 * comunes que queremos que las distintas pantallas de nuestro juego hereden
 * (OOP, tíos).
 * 
 * @author danirod
 */
public abstract class Pantalla implements Screen {
	
	/**
	 * Juego al que pertenece la pantalla. Es muy interesante conectar una
	 * pantalla con el juego sea de la forma que sea ya que de este modo
	 * el juego puede acceder a cosas como el contexto (el SpriteBatch que
	 * he dejado ahí) o simplemente llamar a setScreen.
	 */
	protected pruebajuego game;
	
	public Pantalla(pruebajuego game) {
		this.game = game;
	}
	
	// Para no tener que implementar tantos métodos por nuestra cuenta,
	// dejo los métodos vacíos. Si una pantalla quiere usar un método,
	// lo extiende. Pero si no quiere usarlo, no tiene que extenderlo.

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}