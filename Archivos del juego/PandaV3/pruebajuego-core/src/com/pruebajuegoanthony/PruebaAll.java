package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/*public class PruebaAll extends Pantalla {
	public PruebaAll(pruebajuego game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	Stage stage;
	Texture texture;
	Image img;
	
	
	Texture imgEmpezar;
	TextureRegion reEmpezar;
	Boton btnEmpezar;
	public void show () {
		stage = new Stage();
		texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		
		imgEmpezar=new Texture("ButonEmezar.png");
        reEmpezar=new TextureRegion(imgEmpezar,330,81);
        
        btnEmpezar=new Boton(reEmpezar, new Sprite(imgEmpezar,330,81));
        
		img = new Image(new TextureRegion(texture));
		img.setSize(100, 100);
		img.setOrigin(50, 50);
		img.setPosition(100, 100);

		// img.addAction(forever(sequence(delay(1.0f), new Action() {
		// public boolean act (float delta) {
		// System.out.println(1);
		// img.clearActions();
		// return true;
		// }
		// })));
		img.addAction(Actions.moveBy(200, 0,2));
		//img.addAction(Actions.after(Actions.scaleTo(2, 2, 2)));
		//img.addAction(Actions.rotateBy(-90, 2));
		btnEmpezar.addAction(Actions.moveTo(0, 100,5));
		stage.addActor(img);
		stage.addActor(btnEmpezar);
	}

	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		if (img.getY()>150) {
			img.setY(5);
			System.out.println("Entro");
		}
		stage.draw();
	}

	
}*/

public class PruebaAll extends Pantalla implements InputProcessor {
	static TextureRegion textureRegion;

	private Stage stage;
	private Stage stage1;
	private Stage stage2;
	private BitmapFont font;
	Skin skin;
	Boton actor;

	public PruebaAll(pruebajuego game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	public void show () {
		textureRegion = new TextureRegion(new Texture("ButonEmezar.png"),330,81);

		Gdx.input.setInputProcessor(this);

		stage1 = new Stage();
		stage1.getCamera().position.set(100, 100, 0);
		
		Group group = new Group();
		// group.setBounds(0, 0, 10, 10);
		// group.setOrigin(25, 50);
		group.setRotation(10);
		group.setScale(1.2f);
		stage1.addActor(group);

		actor = new Boton(textureRegion);
		//actor.setBounds(100, 50, 50, 100);
		//actor.setOrigin(25, 50);
		//actor.setRotation(-45);
		//actor.setScale(2f);
		actor.addAction(Actions.sequence(Actions.alpha(0,2),Actions.delay(3),Actions.alpha(1,2)));
		//actor.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(360, 8),Actions.fadeIn(2),Actions.rotateBy(-360,8))));
		group.addActor(actor);
		
		group.debugAll();

		stage2 = new Stage();
		font = new BitmapFont(Gdx.files.internal("FontArial/Arial-font.fnt"),
                Gdx.files.internal("FontArial/Arial-font.png"), false); 
		switchStage();
	}
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stage.act();
		stage.draw();
		if (actor.getActions().size==0) {
			game.setScreen(new MainStage(game));
		}
		game.batch.begin();
			font.draw(game.batch, "hola naruto",100,100);
		game.batch.end();
	}

	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		switchStage();
		return false;
	}

	public void resize (int width, int height) {
		stage1.getViewport().update(width, height, true);
		stage2.getViewport().update(width, height, true);
	}

	private void switchStage () {
		if (stage != stage2) {
			stage = stage2;
		} else {
			stage = stage1;
		}
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