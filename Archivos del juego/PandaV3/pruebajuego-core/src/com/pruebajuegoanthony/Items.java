package com.pruebajuegoanthony;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Items extends Sprite{
	
	Rectangle rctI;
	private float posX;
	private float posY;
	public float velocidad=1;
	public boolean eliminar=false;
	float[] pos= {150,275};
	
	public Items(TextureRegion txt,Sprite spImg) {
		super(txt);
		Random random = new Random();
	    int randomPos=random.nextInt(2);
	    this.setPosX(pos[randomPos]);
	    this.setPosY(600);
	    this.setSize(25, 40);
	    
	    rctI=new Rectangle(pos[randomPos],600,25,40);
	}
	
	public void update() {
		if (this.eliminar==false) {
			setPosY(getPosY() - velocidad);
			rctI.setY(getPosY()-velocidad);
		}
	}
	public boolean Colision(Rectangle rectP) {
		return rctI.overlaps(rectP);
	}
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}
}
