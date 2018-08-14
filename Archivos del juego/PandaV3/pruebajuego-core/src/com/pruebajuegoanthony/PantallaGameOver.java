package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PantallaGameOver extends Stage  implements InputProcessor{
	Image imgOver;
	Boton retry;
	Boton salir;
	pruebajuego game;
	
	PantallaGameOver(pruebajuego game){
		
		imgOver= new Image(new Texture ("Game Over.png"));
		imgOver.setBounds(25, 700, 400, 200);
		retry = new Boton(new TextureRegion(new Texture("ButonRetry.png")));
		retry.setBounds(80, 700+30, 130, 50);
		salir= new Boton(new TextureRegion(new Texture("ButonVolver.png")));
		salir.setBounds(250, 700+30, 130, 50);
		this.addActor(imgOver);
		this.addActor(retry);
		this.addActor(salir);
		this.addAction(Actions.visible(false));
		System.out.println("Entro a pantalla gameOver");
		
		this.game=game;
		Gdx.input.setInputProcessor(this);
	}
	 public boolean touchDown (int screenX, int screenY, int pointer, int button) {
	    	//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
	    	if (90<Gdx.input.getX() && 200>Gdx.input.getX()
					&& 327<Gdx.input.getY() && 368>Gdx.input.getY() ) {
				//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
				System.out.println("EntroEmp");
				game.setScreen(new MainStage(game));
				this.dispose();
			}
	    	
	    	if (266<Gdx.input.getX() && 371>Gdx.input.getX()
					&& 327<Gdx.input.getY() && 368>Gdx.input.getY() ) {
				//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
	    		game.setScreen(new PantallaInicio(game));
	    		this.dispose();
			}
	    	
	    	return false;
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
			//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
			return false;
		}
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			//System.out.println("BtnEmpezar: "+btnEmpezar.getX() + " "+(btnEmpezar.getX()+btnEmpezar.getWidth()) + " "+btnEmpezar.getY() +" "+ (btnEmpezar.getY() +btnEmpezar.getHeight()));
			//System.out.println("Mouse"+Gdx.input.getX()+" "+Gdx.input.getY());
			
			return false;
		}
		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
}
