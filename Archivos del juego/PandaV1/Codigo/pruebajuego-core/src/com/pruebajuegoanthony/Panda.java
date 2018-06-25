package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Sprite del coche con información de velocidad y aceleración.
 * 
 * @author danirod
 */
public class Panda extends Sprite {
	
	/** Velocidad que lleva el coche. */
	public float velocidad = 0;
	public boolean salDer=false;
	public boolean salIzq=true;
	public int pos=0;
	public boolean girarD=true;
	public boolean girarI=false;

	/** Aceleración que lleva el coche. */
	public float aceleracion = 0;
	public float salto=100;
	
	public Panda() {
		super(new Texture("panda.png"), 0, 0, 215, 83);
	}
	
	/** Actualiza la posición y la velocidad del coche. */
	public void update() {
		float posicionX = getX();
		float posicionY = getY();
		float tiempo = Gdx.graphics.getDeltaTime();
		velocidad += aceleracion * tiempo;
		if (getY()>0 && getY()<Gdx.graphics.getHeight())
			posicionY += velocidad * tiempo ;
		else if (getY()>=Gdx.graphics.getHeight()) posicionY=getY()-1;
		else posicionY=getY()+1;
		if (posicionX>salto && (salIzq==false || girarI==false)) {
			posicionX += pos;
			System.out.println("Paro");
		}
		if (posicionX<salto && (salDer==false || girarD==false)) {
			posicionX += pos;
			System.out.println("Paro");
		}
		System.out.println("X: "+posicionX+" Y: "+posicionY+" velocidad: "+velocidad+" aceleracion: "+aceleracion+" tiempo: "+tiempo);
		setX(posicionX);
		setY(posicionY);
	}
	
	/** Acelera el coche si no está a tope ya. */
	public void Derecha() {
		aceleracion = 0;
		System.out.println("entro derecha: "+salDer+" salto: "+salto);
		if (Math.abs(velocidad) > 0.5f)
			velocidad *= 0.85f;
		else
			velocidad = 0;
		if (getX()<270) {
			if (salDer && getX()==salto) {
				flip(false,true );
				salto=salto+80;
				pos=5;
				salDer=false;
				salIzq=true;
				girarI=false;
				girarD=true;
			}
			if (salDer==false && getX()==salto) {
				salDer=true;
				salIzq=false;
				girarI=true;
				girarD=false;
				salto=salto+45;
				pos=5;
				System.out.println("entro al giro derecho");
				flip(false,true);
			}
		}
	}

	/** El coche avanza hacia atrás. */
	public void Izquierda() {
		aceleracion = 0;
		System.out.println("entro izquierda: "+salIzq+" salto: "+salto);
		if (Math.abs(velocidad) > 0.5f)
			velocidad *= 0.85f;
		else
			velocidad = 0;
		if (getX()>=19) {
			if (salIzq && getX()==salto) {
				flip(false,true );
				salto=salto-80;
				pos=-5;
				salIzq=false;
				salDer=true;
				girarI=true;
				girarD=false;
			}
			if(salIzq==false && getX()==salto) {
				salIzq=true;
				salto=salto-45;
				salDer=false;
				girarI=false;
				girarD=true;
				pos=-5;
				System.out.println("entro al giro izq");
				flip(false,true);
			}
		}
	}
	public void subir() {
		System.out.println("entro: "+aceleracion);
		aceleracion=600;
		//if (aceleracion <= 240f) aceleracion += 100f;
	}
	public void bajar() {
		
		aceleracion=-500;
		//if (aceleracion >= -240f) aceleracion -= 100f;
	}
	public void atacar() {
		
		if (aceleracion >= -800f) aceleracion -= 400f;
	}
	/** Se asegura de que el coche esté quieto o lo frena si no lo está. */
	public void frenarCoche() {
		// Como no se puede mover, la aceleración es 0 y las fuerzas
		// de rozamiento hacen que el coche se vaya frenando progresivamente.
		aceleracion = 0;
		// Como trabajamos con flotantes, tenemos que establecer un límite
		// de seguridad ya que los cálculos nunca tendrán buena precisión.
		if (Math.abs(velocidad) > 0.5f)
			velocidad *= 0.85f;
		else
			velocidad = 0;
	}
}