package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Pantalla de la animación. Usada para el episodio de animaciones.
 * 
 * @author danirod
 */
public class PantallaAnimation extends Pantalla {
	
	/** Atlas que guarda los recursos. */
	private Texture dude;
	
	/** Textura del dude. */
	private TextureRegion dudeRegion;
	
	/** Fotogramas de la animación. */
	private TextureRegion[] dudeFrames;
	
	/** Estructura de datos de la animación. */
	private Animation dudeAnimation;
	
	/** Contador de tiempo. Usado para la animación. */
	private float duracion = 0;
	
	/** Ancho real del dude. */
	private static final int ANCHO = 252;
	
	/** Alto real del dude. */
	private static final int ALTO = 49;

	public PantallaAnimation(pruebajuego game) {
		super(game);
	}
	
	@Override
	public void show() {
		dude= new Texture("dudewalking.png");
		dudeRegion = new TextureRegion(dude,ANCHO, ALTO);
		
		TextureRegion[][] temp = dudeRegion.split(ANCHO / 9, ALTO); // (1)
		dudeFrames = new TextureRegion[temp.length * temp[0].length]; // (2)
		int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				dudeFrames[indice++] = temp[i][j];
			}
		}
		dudeAnimation = new Animation(0.1f, dudeFrames); // (3)
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		
		// Obtengo el fotograma. Notar cómo primero incremento la variable
		// duracion. Cuando obtengo mi fotograma, lo hago diciendole a 
		// Animation cuántos segundos han pasado desde que empezó la animación.
		// Él hace sus cálculos por su cuenta y devuelve el fotograma que
		// debería ir ahora.
		duracion += delta;
		TextureRegion frame =  (TextureRegion) dudeAnimation.getKeyFrame(duracion, true);
		
		game.batch.begin();
			game.batch.draw(frame, 100, 100);
		game.batch.end();
	}
}