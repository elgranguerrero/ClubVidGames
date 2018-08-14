package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaInicio extends Pantalla implements InputProcessor{
	
	Texture fondo;
	Texture imgPalito;
	TextureRegion rePalito;
	Texture imgEmpezar;
	TextureRegion reEmpezar;
	Texture imgConfig;
	TextureRegion reConfig;
	Texture imgSalir;
	TextureRegion reSalir;
	Sprite sp;
	
	private Stage stage;
	Boton btnEmpezar;
	Boton btnConfig;
	Boton btnSalir;
	
	
	int width ;
	int height;
	
    public PantallaInicio(pruebajuego  game) {
        super(game);
        
    }

    @Override
    public void show() {
    	
    	Gdx.input.setInputProcessor(this);
    	
    	width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
    	fondo=new Texture("FondoInterFaz.png");
    	imgPalito=new Texture("Palito.png");
    	rePalito=new TextureRegion(imgPalito,27,310);
    	imgEmpezar=new Texture("ButonEmezar.png");
        reEmpezar=new TextureRegion(imgEmpezar,330,63);
        sp=new Sprite(imgEmpezar,330,81);
        imgConfig=new Texture("ButonConfig.png");
        reConfig=new TextureRegion(imgConfig,330,80);
        imgSalir=new Texture("ButonSalir.png");
        reSalir=new TextureRegion(imgSalir,325,81);
         
        stage = new Stage();
         
        btnEmpezar = new Boton(reEmpezar);
        btnEmpezar.setPosition(152, 370);
        btnEmpezar.setWidth(330/2);
        btnEmpezar.setHeight(63/2);
        btnEmpezar.setOrigin(btnEmpezar.getWidth()/2, btnEmpezar.getHeight()/2);
 		//actor.setRotation(-45);
        btnEmpezar.setScale(2f);
 		//actor.addAction(Actions.rotateBy(5, 1, Interpolation.swingOut));
 		//actor.addAction(Actions.rotateBy(1,1));
 		//actor.addAction(Actions.after(Actions.rotateBy(-1,1)));
 		//actor.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1), Actions.rotateBy(-1,1))));
 		//actor.addAction(Actions.forever(Actions.rotateBy(5,3,Interpolation.elasticOut)));
        //btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
 		//actor.addAction(Actions.sequence(Actions.moveBy(0, 100,2),Actions.fadeOut(2),Actions.rotateBy(45,2)));
 		//actor.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.swing), Actions.rotateBy(-2,1,Interpolation.swing),Actions.rotateBy(1,1,Interpolation.swing))));
 		
        btnConfig = new Boton(reConfig);
        btnConfig.setPosition(148, 287);
        btnConfig.setWidth(330/2);
        btnConfig.setHeight(80/2);
        btnConfig.setOrigin(btnConfig.getWidth()/2, btnConfig.getHeight()/2);
        btnConfig.setScale(2f);
        //btnConfig.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
 		 
        btnSalir = new Boton(reSalir);
        btnSalir.setPosition(152, 200);
        btnSalir.setWidth(325/2);
        btnSalir.setHeight(81/2);
        btnSalir.setOrigin(btnSalir.getWidth()/2, btnSalir.getHeight()/2);
        btnSalir.setScale(2f);
        //btnSalir.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
 		 
        stage.addActor(btnEmpezar);
        stage.addActor(btnConfig);
        stage.addActor(btnSalir);
		
    }

    @Override
    public void hide() {
        // When the screen is no more visible, you have to remember to unset the input processor.
        // Otherwise, input might act weird, because even if you aren't using this screen, you are
        // still using the stage for handling input.
        //Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
    	fondo.dispose();
    	imgPalito.dispose();
    	imgEmpezar.dispose();
    	imgConfig.dispose();
    	imgSalir.dispose();
        stage.dispose();
        //this.dispose();
    }

    @Override
    public void render(float delta) {
        // Just render things.
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    	game.batch.begin();
		game.batch.draw(fondo,0,0,width,height);
		game.batch.draw(rePalito,width/2,0,27,470);
		game.batch.end();
		stage.act();
		stage.draw();
		
    }
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
    	//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
    	if (71<Gdx.input.getX() && 393>Gdx.input.getX()
				&& 288<Gdx.input.getY() && 345>Gdx.input.getY() ) {
			//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
			System.out.println("EntroEmp");
			game.setScreen(new MainStage(game));
		}
    	
    	if (71<Gdx.input.getX() && 393>Gdx.input.getX()
				&& 360<Gdx.input.getY() && 423>Gdx.input.getY() ) {
			//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
			System.out.println("EntroConfig");
		}
    	
    	if (71<Gdx.input.getX() && 393>Gdx.input.getX()
				&& 448<Gdx.input.getY() && 517>Gdx.input.getY() ) {
			//btnEmpezar.addAction(Actions.forever(Actions.sequence(Actions.rotateBy(1,1,Interpolation.elasticOut), Actions.rotateBy(-2,1),Actions.rotateBy(1,1,Interpolation.elasticOut))));
			System.out.println("EntroSalir");
			Gdx.app.exit();
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
		//System.out.println("Mouse"+Gdx.input.getX()+" "+Gdx.input.getY()+" "+btnEmpezar.getOriginX());
		
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
