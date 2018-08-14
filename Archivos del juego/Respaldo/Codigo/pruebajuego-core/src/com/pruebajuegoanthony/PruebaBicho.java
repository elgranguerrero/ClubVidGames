package com.pruebajuegoanthony;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PruebaBicho extends Actor{
	private int puntos;
	private TextureRegion bicho;
	private float velocidad;
	private Rectangle rectP;
	private Rectangle rectB;
	float[] pos= {98,140,225,255,342,388};
	
	public PruebaBicho(TextureRegion bicho, ActorPanda panda){
		bicho.flip(true, false);
		this.bicho=bicho;
		this.rectB=new Rectangle(0,600,25,40);
		this.rectP=new Rectangle(panda.getX(),panda.getY(),panda.getHeight(),panda.getWidth());
		velocidad=1;
		Random random = new Random();
		int randomPosX=random.nextInt(6);
		int randomPosY=ThreadLocalRandom.current().nextInt(600, 700);
	    this.setX(pos[randomPosX]);
	    this.setY(randomPosY);
	    this.setSize(25, 40);
	    this.rotateBy(90);
	}
	
	public void act(float delta) {
		update();
		
		if (rectB.overlaps(rectP)) {
			this.setY(600);
			rectB.setY(600);
			System.out.println("Entro a la colision de bicho");
		}
	}
	public void draw(Batch batch,float parentAlpha) {
		batch.draw(bicho, getX(), getY(), getOriginX(), getOriginY(),getHeight(),getWidth(), getScaleX(), getScaleY(), getRotation());
		//batch.draw(rectB, getX(), getY());
	}
	public void update() {
		this.setY(getY()-velocidad);
		rectB.setY(getY()-velocidad);
	}
}
