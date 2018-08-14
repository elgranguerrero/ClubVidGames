package com.pruebajuegoanthony;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Clase principal. Es un Game, están diseñados para soportar varias
 * pantallas y disponen de un método (setScreen) para cambiar entre una
 * pantalla y otra cómodamente.
 * 
 * @author danirod
 */
public class pruebajuego extends Game {

	public SpriteBatch batch;
	
	public Pantalla coche;
	public Pantalla animacion;
	public Pantalla auto;
	public Pantalla camera;
	public Pantalla mainStage;
	public Pantalla pruebaAnimacion;
	@Override
	public void create() {
		// Creamos algunas cosas sencillas...
		batch = new SpriteBatch();
		
		// Creamos las pantallas
		coche = new PantallaCoche(this);
		animacion = new PantallaAnimation(this);
		auto=new PantallaAuto(this);
		camera=new PantallaCamera(this);
		mainStage=new MainStage(this);
		pruebaAnimacion=new PruebaAnimacion(this);
		// Hoy nos interesa esta pantalla.
		setScreen(animacion);
	}
	public void dispose() {
		//mainStage.dispose();
		//pruebaAnimacion.dispose();
		//camera.dispose();
		animacion.dispose();
		//auto.dispose();
	}
}
