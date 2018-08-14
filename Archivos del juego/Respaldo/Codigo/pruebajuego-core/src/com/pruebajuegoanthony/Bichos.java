package com.pruebajuegoanthony;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bichos extends Sprite{
	
	Rectangle rctB;
	public int puntos;
	private float posX;
	private float posY;
	public float velocidad=1;
	public boolean eliminar=false;
	float[] pos= {98,140,225,255,342,388};
	
	public Bichos(TextureRegion txt,Sprite spImg) {
		super(txt);
		Random random = new Random();
	    int randomNumber= random.nextInt(3);
	    if (randomNumber<=1) this.puntos=100;
	    else this.puntos=200;
	    int randomPos=random.nextInt(6);
	    this.setPosX(pos[randomPos]);
	    this.setPosY(600);
	    this.setSize(25, 40);
	    
	    rctB=new Rectangle(pos[randomPos],600,25,40);
	}
	public void update() {
		if (this.eliminar==false) {
			setPosY(getPosY() - velocidad);
			rctB.setY(getPosY()-velocidad);
		}
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
	public boolean Colision(Rectangle rectP) {
		return rctB.overlaps(rectP);
	}
	public String toString() {
		return "Bichos; ";
	}
}
