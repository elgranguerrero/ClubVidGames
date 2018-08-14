package com.pruebajuegoanthony;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

public class PruebaActor extends Actor{
	
	TextureRegion re;
	Sprite spt;
	RotateToAction rotate;
	RotateByAction acc;
	MoveByAction move;
	public PruebaActor(TextureRegion re,Sprite spta) {
		/*rotate = new RotateToAction();
        rotate.setRotation(-720);
        rotate.setDuration(1f);*/
        //añadir acciones
        /*acc = new RotateByAction();
        acc.setAmount(360);
       acc.setDuration(1f);
       this.addAction(acc);*/
       //this.addAction(rotate);
       //Atributos del actor. Posicion, Tamaño, y Origen de rotación.
       setPosition(50,50);
       setHeight(spt.getHeight());
       setWidth(spt.getWidth());
       setOriginX(getWidth()/2);
       setOriginY(getHeight()/2);
       rotate = new RotateToAction();
       rotate.setRotation(90);
       rotate.setDuration(1f);
       //añadir acciones
       //this.addAction(move);
       //this.addAction(rotate);
       this.re=re;
       this.spt=spta;
	}
	
	public void act(float delta) {
		
	}
	public void draw(Batch batch,float parentAlpha) {
		//batch.draw(spt, getX(), getY());
		batch.draw(spt, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
