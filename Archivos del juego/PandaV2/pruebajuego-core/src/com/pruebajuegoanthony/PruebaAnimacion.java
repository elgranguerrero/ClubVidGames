package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PruebaAnimacion extends Pantalla{
	
	Stage stage;
	Sprite sp;
	PruebaActor panda;
	Texture texturePanda;
	TextureRegion txtRePanda;
	Sprite s;
	private Texture fondo;
	int width ;
	int height;
	private OrthographicCamera cam;
	public PruebaAnimacion(pruebajuego game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	public void show() {
		texturePanda=new Texture("panda.png");
		txtRePanda=new TextureRegion(texturePanda,0,0,215,83);
		s=new Sprite(texturePanda,0,0,215,83);
		panda=new PruebaActor(txtRePanda,s);
		stage=new Stage();
		stage.addActor(panda);
		cam=new OrthographicCamera();
		panda.setPosition(100, 100);
		panda.rotateBy(90f);
	}
	public void render(float delta) {
		//panda.rotateBy(3f);
		Gdx.gl.glClearColor(0.4f, 0.5f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		// Renderizo un coche.
		stage.act(delta);
		stage.draw();
	}
	public void dispose() {
		texturePanda.dispose();
		stage.dispose();
	}
}
