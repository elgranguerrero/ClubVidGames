package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class FondoPantalla{
	/** Velocidad que lleva el coche. */
	public float velocidad = 0;

	/** Aceleración que lleva el coche. */
	public float aceleracion = 0;
	protected Texture fondo;
	public FondoPantalla() {
		fondo=new Texture("fondoPantalla.jpg");
	}
	
}
