package com.pruebajuegoanthony;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bichos extends Sprite{
	
	Rectangle rctB;
	public int puntos;
	private float posX;
	private float posY;
	public float velocidad=1;
	public boolean eliminar=false;
	float[] pos= {100,143,223,265,346,387}; 
	
	
	
	//para borrar
	public Animation dudeAnimation;
	private static final int ANCHO = 3680;
	
	/** Alto real del dude. */
	private static final int ALTO = 140;
	private TextureRegion[] dudeFrames;
	private float stateTime;
	
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
	    /*if (randomPos==1 || randomPos==3 || randomPos==5) {
	    	this.flip(false, true);
	    	System.out.println("Entro a flip");
	    }*/
	    this.rotate(90);
	    
	    rctB=new Rectangle(pos[randomPos],600,25,40);
	    
	    //para borrar
	    TextureRegion[][] temp = this.split(ANCHO / 16, ALTO); // (1)
	    dudeFrames = new TextureRegion[temp.length * temp[0].length];
	    int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (randomPos==1 || randomPos==3 || randomPos==5) {
					temp[i][j].flip(false, true);
			    }
				dudeFrames[indice++] = temp[i][j];
			}
		}
		dudeAnimation = new Animation(0.7f, dudeFrames); // (3)
		
               
                
               
                //El statetime imaginarlo como un tiempo indefinido que usaremos para ir moviendo nuestros sprites.
                stateTime = 0f;
	}
	public void dibujar(Batch batch) {
		
	}
	public void update() {
		if (this.eliminar==false) {
			setPosY(getPosY() - velocidad);
			rctB.setY(getPosY()-velocidad);
			this.flip(false, true);
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
	public TextureRegion[] getDudeFrames() {
		return this.dudeFrames;
	}
}
