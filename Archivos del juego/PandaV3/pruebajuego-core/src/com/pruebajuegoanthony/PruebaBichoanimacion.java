package com.pruebajuegoanthony;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class PruebaBichoanimacion extends Actor{
	TextureRegion txtReBichos;
	Sprite spBichos;
	Bichos listBichos;
	ArrayList<Bichos> listBichos1;
	TextureRegion frame;
	
	private int controlTiempo;
	private int tiempo;
	private int nivel=1;
	public boolean inmunidadP=false;
	private int controlInmunidad=0;
	
	private float duracion=0;
	public boolean paraAni=false;
	
	public PruebaBichoanimacion(TextureRegion txtRegion) {
		this.txtReBichos=txtRegion;
		this.spBichos=spBichos;
		this.listBichos=new Bichos(txtReBichos,spBichos);
		this.listBichos1=new ArrayList<Bichos>();
		controlTiempo=80;
		setHeight(40);
		setWidth(25);
	}
	public void act(float delta) {
		if (listBichos1.isEmpty()==false) {
			//listBichos.get(0).update();
			for (int i=0;i<listBichos1.size();i++) {
				if (nivel>0)
					listBichos1.get(i).update();
			}
			if (nivel==1) {
				//System.out.println("Tiempo: "+tiempo+" "+controlTiempo);
				if (tiempo==controlTiempo) {
					this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
					if (controlTiempo>100)
						nivel=2;
					controlTiempo=controlTiempo+80;
				}
			}
			if (nivel==2) { 
				if (tiempo==controlTiempo) {
					this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
					if (controlTiempo>500)
						nivel=3;
					controlTiempo=controlTiempo+50;
				}
			}
			if (nivel==3) {
				if (tiempo==controlTiempo) {
					this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
					this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
					if (controlTiempo>1500)
						nivel=4;
					controlTiempo=controlTiempo+50;
				}
			}
			if (nivel==4) {
				if (tiempo==controlTiempo) {
					this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
					this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
					for (Bichos item:listBichos1) {
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
			if (this.listBichos1.get(0).getPosY()<0) 
				listBichos1.remove(0);
		}
		else this.listBichos1.add(new Bichos(this.txtReBichos,spBichos));
		tiempo++;
		//this.listBichos.update();
		/*7for (Bichos item:listBichos1) {
			item.update();
		}*/
	}
	public void draw(Batch batch,float parentAlpha) {
		duracion += parentAlpha;
		if (listBichos1.isEmpty()==false) {
			for (Bichos item:listBichos1) {
				if (this.paraAni) {
					if (tiempo==controlTiempo) {
						this.paraAni=false;
					}
					frame =  (TextureRegion) item.dudeAnimation.getKeyFrame(duracion, false);
				}
				else frame =  (TextureRegion) item.dudeAnimation.getKeyFrame(duracion, true);
				batch.draw(frame, item.getPosX(), item.getPosY(), getOriginX(), getOriginY(),item.getHeight(),item.getWidth(), getScaleX(), getScaleY(), item.getRotation());
				
			}
		}
		//TextureRegion frame =  (TextureRegion) listBichos.dudeAnimation.getKeyFrame(duracion, true);
		//batch.draw(frame, listBichos.getPosX(), listBichos.getPosY(), getOriginX(), getOriginY(),listBichos.getHeight(),listBichos.getWidth(), getScaleX(), getScaleY(), getRotation());
		
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
