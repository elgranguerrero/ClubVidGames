package com.pruebajuegoanthony;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ActorItems extends Actor {
	TextureRegion[] txtReItems;
	Sprite[] spItems;
	Actions accion;
	ArrayList<Items> listItems;
	private int controlTiempo;
	private int tiempo;
	public int randomtext;
	
	public ActorItems(TextureRegion[] txtRegion,Sprite[] spItems) {
		this.txtReItems=txtRegion;
		this.spItems=spItems;
		this.controlTiempo=800;
		this.tiempo=0;
		this.listItems=new ArrayList<Items>();
		setHeight(40);
		setWidth(25);
	}
	public void act(float delta) {
		if (listItems.isEmpty()==false)
			for (Items val:listItems)
				val.update();
		if (tiempo==controlTiempo) {
			controlTiempo=controlTiempo+800;
			Random random = new Random();
		    randomtext=random.nextInt(3);
			listItems.add(new Items (txtReItems[randomtext],spItems[randomtext]));
		}
		tiempo++;
	}
	public void draw(Batch batch,float parentAlpha) {
		if (listItems.isEmpty()==false) { 
			for (Items item:listItems)
				batch.draw(item, item.getPosX(), item.getPosY(), getOriginX(), getOriginY(),item.getHeight(),item.getWidth(), getScaleX(), getScaleY(), getRotation());
		}
	}
	public void colision(ActorPanda obj) {
		Rectangle rctP=new Rectangle(obj.getX(),obj.getY(),obj.getHeight(),obj.getWidth());
		for(int i=0;i<this.listItems.size();i++) {
			if (this.listItems.get(i).Colision(rctP)) {
				System.out.println(listItems);
				this.listItems.get(i).setPosY(600);
				this.listItems.get(i).rctI.setY(600);
				this.listItems.remove(i);
			}
		}
	}
}
