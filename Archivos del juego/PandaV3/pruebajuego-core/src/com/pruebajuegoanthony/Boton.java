package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/*public class Boton extends Actor{
	TextureRegion panda;
	Sprite sp;
	private Animation pandaAnimation;
	public ControladorVirtual controlador;
	private InputMultiplexer multiplexor;
	private InputProcessor teclado, raton;
	
	
	public Boton(TextureRegion panda,Sprite sp) {
		this.panda=panda;
		this.sp=sp;
		final float speed = 30.0f; // in degrees per second
		//Inicializacion de todas la caracteristicas del actor
		setPosition(50,50);
		setHeight(sp.getHeight());
		setWidth(sp.getWidth());
		setOriginX(getWidth()/2);
		setOriginY(getHeight()/2);*/
		/*rotate = new RotateToAction();
		rotate.setRotation(90);
		rotate.setDuration(1f);*/
		// Creo los controladores.
		/*controlador = new ControladorVirtual();
		teclado = new EntradaCocheTeclado(controlador);
		raton = new EntradaCocheRaton(controlador);
		
		// Para poder usar ambos a la vez creo multiplexores.
		multiplexor = new InputMultiplexer();
		multiplexor.addProcessor(raton);
		multiplexor.addProcessor(teclado);
		
		// setInputProcessor = poner un controlador o un multiplexor.
		Gdx.input.setInputProcessor(multiplexor);
		//panda.flip(false,true);
		
	}
	public void act(float delta) {
				
		if (controlador.moverArriba) {
			final int destX = controlador.objetivoX; 
			final int destY = Gdx.graphics.getHeight() - controlador.objetivoY; 
			System.out.println("entro"+destX+" "+destY);
			// Creamos la accion de moverse
			MoveToAction actionMove = new MoveToAction();
			actionMove.setPosition(240, 554);
			// añadimos una interpolacion al movimiento
			//actionMove.setInterpolator(DecelerateInterpolator.$(2.0f));
			this.addAction(Actions.moveTo(40, 500));
			this.moveBy(0, 5);
			this.addAction( Actions.parallel(Actions.rotateBy(360, 0.5f), Actions.moveTo(320, 100, 0.5f)));
			// y se la damos al sprite
			this.addAction(actionMove);
		}
	}
	public void draw(Batch batch,float parentAlpha) {
		
		batch.draw(panda, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}*/
public class Boton extends Actor {
	TextureRegion textureRegion;
	public Boton(TextureRegion textureRegion){
		this.textureRegion=textureRegion;
	}
	public void draw (Batch batch, float parentAlpha) {
		batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
			getRotation());
	}
}

