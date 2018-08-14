package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PruebaGameOver extends Pantalla implements InputProcessor{
	public PruebaGameOver(pruebajuego game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	Stage stage;
	Table table;
	Image img;
	Boton retry;
	Boton salir;
	PantallaGameOver over;
	public void show () {
		retry = new Boton(new TextureRegion(new Texture("ButonRetry.png")));
		retry.setBounds(100, 50, 50, 100);
		salir= new Boton(new TextureRegion(new Texture("ButonVolver.png")));
		
		stage= new Stage();
		img=new Image(new Texture("Game Over.png"));
		table=new Table();
		table.top();//table at top of our stage
        table.setFillParent(true);//table is now fill all the stage
		table.add(img);
		//table.add(retry).expand();
		//table.add(salir);
		stage.addActor(table);
		stage.addActor(retry);
	
		stage.addAction(Actions.visible(false));
		//stage.addAction(Actions.sequence(Actions.show(),Actions.fadeIn(7)));
		//stage.addAction(Actions.parallel(Actions.show(),Actions.fadeOut(5)));
		//stage.addAction(Actions.parallel(Actions.visible(true),Actions.fadeIn(8)));
		stage.addAction(Actions.sequence(Actions.visible(true),Actions.parallel(Actions.moveBy(0, -100,2),Actions.fadeIn(2))));
		stage.setDebugAll(true);
		over= new PantallaGameOver(game);
		
		over.addAction(Actions.sequence(Actions.visible(true),Actions.moveBy(0, -100,2)));
	}
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		//stage.act();
		//stage.draw();
		over.draw();
		over.act();
	}

	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
