package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

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
	private static final int ANCHO = 3680;
	
	/** Alto real del dude. */
	private static final int ALTO = 140;

    private float stateTime;
    private TextureRegion currentFrame;
    private Vector2 velocity;
    private Stage st;
    private Animacion ani;
    
	public PantallaAnimation(pruebajuego game) {
		super(game);
	}
	
	@Override
	public void show() {
		dude= new Texture("AnimacionBicho.png");
		dudeRegion = new TextureRegion(dude,ANCHO, ALTO);
		
		
		System.out.println("entro");
		TextureRegion[][] temp = dudeRegion.split(ANCHO / 16, ALTO); // (1)
		dudeFrames = new TextureRegion[temp.length * temp[0].length]; // (2)
		int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				dudeFrames[indice++] = temp[i][j];
			}
		}
		dudeAnimation = new Animation(0.1f, dudeFrames); // (3)
		
               
                
               
                //El statetime imaginarlo como un tiempo indefinido que usaremos para ir moviendo nuestros sprites.
                stateTime = 0f;
                 
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
			game.batch.draw(frame, 100, 100,60,25);
		game.batch.end();
		//Vamos a controlar a nuestro humano a base de teclado, no voy a hacer un controlador bueno
        //solo algo funcional para probar la animacion.
       
        /*velocity.x = 0;
        velocity.y = 0;
        if(Gdx.input.isKeyPressed(Keys.W) & !Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.D)) velocity.y = 1; 
        if(Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.D) & !Gdx.input.isKeyPressed(Keys.W)) velocity.x = -1;
        if(Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.W) & !Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.D)) velocity.y = -1;
        if(Gdx.input.isKeyPressed(Keys.D) & !Gdx.input.isKeyPressed(Keys.S) & !Gdx.input.isKeyPressed(Keys.A) & !Gdx.input.isKeyPressed(Keys.W)) velocity.x = 1;
       
       
        //Movemos nuestro Sprite
        setX(getX() + velocity.x);
        setY(getY() + velocity.y);
       	
        //comprobamos la velocidad y gracias al método getKeyFrame de la clase Animation recuperamos el frame que necesitamos en cada momento.
        //El true representa que es un movimiento cíclico.
       
        if(velocity.x> 0 & velocity.y == 0)    currentFrame = (TextureRegion) dudeAnimation.getKeyFrame(stateTime,true);
        if(velocity.x< 0 & velocity.y == 0)    currentFrame = (TextureRegion) dudeAnimation.getKeyFrame(stateTime,true);
        if(velocity.y> 0 & velocity.x == 0)    currentFrame = (TextureRegion) dudeAnimation.getKeyFrame(stateTime,true);
        if(velocity.y< 0 & velocity.x == 0)    currentFrame = (TextureRegion) dudeAnimation.getKeyFrame(stateTime,true);
       	
        //actualizamos nuestro stateTime y dibujamos el currentFrame.
        stateTime += Gdx.graphics.getDeltaTime();   
        batch.draw(currentFrame, getX(), getY(), getWidth()*2,getHeight()*2);*/
	}
	public void dispose() {

	}
}