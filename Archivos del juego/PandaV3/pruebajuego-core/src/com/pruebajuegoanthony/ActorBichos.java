package com.pruebajuegoanthony;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.midi.Sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ActorBichos extends Actor {
	TextureRegion txtReBichos;
	Sprite spBichos;
	Actions accion;
	ArrayList<Bichos> listBichos;
	private int controlTiempo;
	private int tiempo;
	private int nivel=1;
	public boolean inmunidadP=false;
	private int controlInmunidad=0;
	
	//para borrar
	private float duracion=0;
	
	public ActorBichos(TextureRegion txtRegion,Sprite spBichos) {
		this.txtReBichos=txtRegion;
		this.spBichos=spBichos;
		//this.controlTiempo=20;
		this.controlTiempo=20;
		this.tiempo=0;
		this.listBichos=new ArrayList<Bichos>();
		this.txtReBichos.flip(true, false);
		this.rotateBy(90);
		setHeight(40);
		setWidth(25);
	}
	public void act(float delta) {
		if (listBichos.isEmpty()==false) {
			//listBichos.get(0).update();
			for (int i=0;i<listBichos.size();i++) {
				if (nivel>0)
					listBichos.get(i).update();
			}
			/*if (this.listBichos.get(0).getPosY()>20 && this.listBichos.get(0).getPosY()<500) {
				if(tiempo==controlTiempo) {
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					controlTiempo+=80;
				}
				tiempo++;
			}*/
			if (nivel==1) {
				if (tiempo==controlTiempo) {
					System.out.println("entro al nivel 1");
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					if (controlTiempo>500)
						nivel=2;
					controlTiempo=controlTiempo+80;
				}
			}
			if (nivel==2) {
				if (tiempo==controlTiempo) {
					System.out.println("entro al nivel 2");
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					if (controlTiempo>1000)
						nivel=3;
					controlTiempo=controlTiempo+50;
				}
			}
			if (nivel==3) {
				if (tiempo==controlTiempo) {
					System.out.println("entro al nivel 3");
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					if (controlTiempo>1500)
						nivel=4;
					controlTiempo=controlTiempo+50;
				}
			}
			if (nivel==4) {
				if (tiempo==controlTiempo) {
					System.out.println("entro al nivel 4");
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
					for (Bichos item:listBichos) {
						item.velocidad=2;
					}
					controlTiempo=controlTiempo+50;
				}
			}
			if (nivel<0) {
				if (tiempo==controlTiempo) {
					nivel=nivel*(-1);
					tiempo=tiempo-80;
				}
			}
			if (inmunidadP) {
				if (controlInmunidad==200) {
					inmunidadP=false;
					controlInmunidad=0;
				}
				this.controlInmunidad++;
			}
			if (this.listBichos.get(0).getPosY()<0) 
				listBichos.remove(0);
		}
		else this.listBichos.add(new Bichos(this.txtReBichos,spBichos));
		tiempo++;
	}
	public void draw(Batch batch,float parentAlpha) {
		if (listBichos.isEmpty()==false) { 
			for (Bichos item:listBichos) {
				TextureRegion frame =  (TextureRegion) item.dudeAnimation.getKeyFrame(duracion, true);
				batch.draw(item, item.getPosX(), item.getPosY(), getOriginX(), getOriginY(),item.getHeight(),item.getWidth(), getScaleX(), getScaleY(), getRotation());
			}
		}
	}
	public void colision(ActorPanda obj) {
		Rectangle rctP=new Rectangle(obj.getX(),obj.getY(),obj.getHeight(),obj.getWidth());
		for(int i=0;i<this.listBichos.size();i++) {
			if (this.listBichos.get(i).Colision(rctP)) {
				System.out.println(listBichos);
				this.listBichos.get(i).setPosY(600);
				this.listBichos.get(i).rctB.setY(600);
				this.listBichos.remove(i);
			}
		}
	}
	public void setNivel(int n) {
		this.nivel=n;
	}
	public int getNivel() {
		return this.nivel;
	}
	public void setTiempo(int t) {
		this.tiempo=t;
	}
	public int getTiempo() {
		return this.tiempo;
	}
}
