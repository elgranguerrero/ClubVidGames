package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Pantalla de la animación. Usada para el episodio de animaciones.
 * 
 * @author danirod
 */
public class Animacion extends Actor{
	
	/** Atlas que guarda los recursos. */
	private Texture dude;
	
	/** Textura del dude. */
	private TextureRegion dudeRegion;
	
	/** Fotogramas de la animación. */
	private TextureRegion[] dudeFrames;
	
	/** Estructura de datos de la animación. */
	@SuppressWarnings("rawtypes")
	private Animation dudeAnimation;
	
	/** Contador de tiempo. Usado para la animación. */
	private float duracion = 0;
	
	/** Ancho real del dude. */
	private static final int ANCHO = 252;
	
	/** Alto real del dude. */
	private static final int ALTO = 49;

    private float stateTime;
    private TextureRegion currentFrame;
    private Vector2 velocity;
    private pruebajuego game;
    private float x;
    private float y;
    TextureRegion frame;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Animacion() {
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
		x=getX();
		y=getY();
		frame =  dudeFrames[0];
		
	}
	public void act(float delta) {
		System.out.println("entro a act");
	}
	public void draw(Batch batch,float parentAlpha) {
		System.out.println("entro a drwa");
		duracion += Gdx.graphics.getDeltaTime();
		actualizar();
		 if(x> 0 & y == 0) {   frame = (TextureRegion) dudeAnimation.getKeyFrame(duracion,true);
		 System.out.println("Entro al frame de rigth");}
	     if(x< 0 & y == 0) {  frame = (TextureRegion) dudeAnimation.getKeyFrame(duracion,true);
	     System.out.println("Entro al frame de left");
	     frame.flip(true, false);}
	     if(y> 0 & x == 0)  {  frame = (TextureRegion) dudeAnimation.getKeyFrame(duracion,true);
	     System.out.println("Entro al frame de arriba");
	     }
	     if(y< 0 & x == 0)   { frame = (TextureRegion) dudeAnimation.getKeyFrame(duracion,true);
	     System.out.println("Entro al frame de abajo");}
	       	
		batch.draw(frame,getX(), getY());
	}
	public void actualizar() {
		x=0;
		y=0;
		if(Gdx.input.isKeyPressed(Keys.W) & !Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.D)) {y = 1;
		 	System.out.println("Arriba");
		} 
        if(Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.D) & !Gdx.input.isKeyPressed(Keys.W)) {x = -1;
        System.out.println("izquierda");}
        if(Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.W) & !Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.D)) {y = -1;
        System.out.println("abajo");}
        if(Gdx.input.isKeyPressed(Keys.D) & !Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.W)) {x = 1;
        System.out.println("derecha");}
       
       
        //Movemos nuestro Sprite
        setX(getX() + x);
        setY(getY() + y);
        System.out.println("Entro al actualizar"+ "getX: "+getX()+" getY: "+getY()+" X: "+x+" Y: "+y);
	}
}