package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.Sequence;

/**
 * This is the main screen for the game. All the fun happen here.
 */
public class MainStage extends Pantalla {
	public MainStage (pruebajuego game) {
		super(game);
	}
	
	//Creando Font para el socre y el nombre
	private BitmapFont fontName;
	private BitmapFont fontScore;
	private BitmapFont fontScoreOver;
	//Creo el stage
	Stage stage;
	Sprite sp;
	
	//Atributos y actores del juego
	ActorPanda panda;
	Texture texturePanda;
	TextureRegion txtRePanda;
	Texture vidasActPanda;
	
	ActorBichos enemiBichitos;
	Texture textbichos;
	Sprite spBichos;
	TextureRegion regionBichos;
	
	ActorItems actItems;
	Texture[] textItem= {new Texture("vidas.png"),
			new Texture("tiempo.png"),
			new Texture("shield.png")};
	Sprite[] spItem= {new Sprite(textItem[0],0, 0, 194, 182),
			new Sprite(textItem[1],0, 0, 98, 98),
			new Sprite(textItem[2],0, 0, 198, 232)};
	TextureRegion[] regionItem= {new TextureRegion(textItem[0],0, 0, 194, 182),
			new TextureRegion(textItem[1],0, 0, 98, 98),
			new TextureRegion(textItem[2],0, 0, 198, 232)};
	
	PruebaBichoanimacion pAni;
	
	private Texture fondo;
	private ShapeRenderer shaper;
	
	int cantEnemi;
	int width ;
	int height;
	boolean perdio=false;
	private OrthographicCamera cam;
	
	private PantallaGameOver gameover;
	//Se debe de crear una clase de transicion o pantalla de carga
	
	public void show() {
		//Creando font para el score y el nombre
		fontName = new BitmapFont(); 
		fontName.setColor(0,0,0,1);
		
		fontScore= new BitmapFont();
		fontScore.setColor(0,0,0,1);
		
		fontScoreOver= new BitmapFont(Gdx.files.internal("FontArial/Arial-font.fnt"),
                Gdx.files.internal("FontArial/Arial-font.png"), false);
		fontScoreOver.setColor(0,0,0,1);
		//fontScoreOver.setColor(0,0,0,1);
		//Crearndo texturas y sprites
		texturePanda= new Texture("panda.png");
		txtRePanda=new TextureRegion(texturePanda,0, 0, 128, 83);
		sp=new Sprite(texturePanda,0, 0, 128, 83);
		vidasActPanda=new Texture("VidasPanda.png");
		
		//textbichos=new Texture("bichov1.png");
		textbichos=new Texture("AnimacionBicho.png");
		//regionBichos=new TextureRegion(textbichos,0, 0, 257, 148);
		//spBichos=new Sprite(textbichos,0, 0, 257, 148);
		regionBichos=new TextureRegion(textbichos,0, 0, 3680, 140);
		spBichos=new Sprite(textbichos,0, 0, 3680, 140);
		
		//enemiBichitos=new ActorBichos(regionBichos,spBichos);
		pAni=new PruebaBichoanimacion(regionBichos);
		
		fondo=new Texture("InterMain.png");
		
		actItems=new ActorItems(regionItem,spItem);
		stage=new Stage();
		
		panda=new ActorPanda(txtRePanda,sp,vidasActPanda);
		
		stage.addActor(panda);
		//stage.addActor(enemiBichitos);
		stage.addActor(pAni);
		stage.addActor(actItems);
		panda.setPosition(160, 100);
		panda.rotateBy(90);
		
		//gameover=new PantallaGameOver(game);
		
		// Creo un shape renderer.
		shaper = new ShapeRenderer();
		
		// Creo una cámara.
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(width,height);
		//addAction(Actions.sequence(Actions.alpha(0,2),Actions.delay(3),Actions.alpha(1,2)));
	}
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.5f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		// Muestro un origen de coordenadas.
		shaper.setProjectionMatrix(cam.combined);
		shaper.begin(ShapeType.Line);
		shaper.line(-1000, 0, 1000, 0);
		shaper.line(0, -1000, 0, 1000);
		for (int i = -1000; i <= 1000; i += 50) {
			shaper.line(-10, i, 10, i);
			shaper.line(i, -10, i, 10);
		}
		shaper.end();
		// Renderizo un coche.
		game.batch.begin();
			if (this.perdio) {
				fontScoreOver.draw(game.batch, "hola mundo", 100,100);
			}
			game.batch.draw(fondo,0,0,width,height);
			fontScore.draw(game.batch, ("Score: "+panda.getScore()), 125,685);
			fontName.draw(game.batch, "Jugador 1",125,700);
		game.batch.end();
		stage.act();
		if (this.perdio==false)
			colision(pAni, actItems);
		stage.draw();
		if (this.perdio) {
			gameover.act();
			gameover.draw();
			game.batch.begin();
			fontScoreOver.draw(game.batch, ("Score: "+panda.getScore()), 140,415);
			game.batch.end();
		}
	}
	//public void colision(ActorBichos bichos, ActorItems item) {
	public void colision(PruebaBichoanimacion bichos, ActorItems item) {
		Rectangle rectP= new Rectangle(panda.getX()+80,panda.getY(),panda.getWidth()-80,panda.getHeight());
		Rectangle rectPAtk= new Rectangle(panda.getX()+80,panda.getY()-20,panda.getWidth()-80,panda.getHeight()-18);
		for (int i=0;i<bichos.listBichos1.size();i++) {
			Bichos b=bichos.listBichos1.get(i);
			Rectangle rectB= new Rectangle(b.getPosX(),b.getPosY(),b.getWidth(),b.getHeight());
			if (rectB.overlaps(rectPAtk) && panda.controlador.atk ) {
				//b.eliminar=true;
				this.pAni.listBichos1.remove(b);
				panda.score=panda.score+b.puntos;
				System.out.println("Hubo Colision: entro al ataque");
			}
			else if (rectB.overlaps(rectP) && this.pAni.inmunidadP) {
				//b.eliminar=true;
				this.pAni.listBichos1.remove(b);
				System.out.println("Hubo Colision");
				System.out.println("Bichos"+b.getPosX()+" "+b.getPosY()+" "+b.getHeight()+" "+b.getWidth());
				System.out.println("Bichos"+b.puntos);
				System.out.println("RectBBichos: "+rectB.getX()+" "+rectB.getY()+" "+rectB.getHeight()+" "+rectB.getWidth());
				System.out.println("RectPanda: "+rectP.getX()+" "+rectP.getY()+" "+rectP.getHeight()+" "+rectP.getWidth());
				System.out.println("Panda: "+panda.getX()+" "+panda.getY()+" "+panda.getHeight()+" "+panda.getWidth());
			
			}
			else if (rectB.overlaps(rectP) ) {
				//b.eliminar=true;
				this.pAni.listBichos1.remove(b);
				panda.vida=panda.vida-1;
				panda.cambioVida(panda.vida);
				System.out.println("Hubo Colision en perdidas de vida");
				System.out.println("PanadaVida: "+panda.vida);
				this.pAni.inmunidadP=true;
				if (panda.vida<0) this.perdio=true;
			}
		}
		for (int i=0;i<actItems.listItems.size();i++) {
			Items I=actItems.listItems.get(i);
			Rectangle rectI= new Rectangle(I.getPosX(),I.getPosY(),I.getWidth()+20,I.getHeight());
			if (rectI.overlaps(rectP) && actItems.randomtext==0) {
				//b.eliminar=true;
				this.actItems.listItems.remove(I);
				System.out.println("enro a vidas");
				if (panda.vida<3) {
					panda.vida=panda.vida+1;
					panda.cambioVida(panda.vida);
				}
				System.out.println("vidas");
			}
			if (rectI.overlaps(rectP) && actItems.randomtext==1) {
				//b.eliminar=true;
				this.actItems.listItems.remove(I);
				System.out.println("enro a tiempo");
				this.pAni.setNivel(-(this.pAni.getNivel()));
				this.pAni.setTiempo(this.pAni.getTiempo()-200);
				this.pAni.paraAni=true;
			}
			if (rectI.overlaps(rectP) && actItems.randomtext==2) {
				//b.eliminar=true;
				this.actItems.listItems.remove(I);
				this.pAni.inmunidadP=true;
				System.out.println("enro a shield");
				
			}
		}
		if (panda.vida<0 && this.perdio) {
			System.out.println("Game Over");
			stage.addAction(Actions.sequence(Actions.fadeOut(2)));
			gameover=new PantallaGameOver(game);
			gameover.addAction(Actions.sequence(Actions.visible(true),
					Actions.parallel(Actions.moveBy(0, -400,2),Actions.fadeIn(2))));
			
			
		}
	}
	@Override
	public void resize(int width, int height) {
		// Cuando cambio el tamaño de la cámara la redimensiono.
		cam.setToOrtho(false, width, height);
	}
	public void hide() {
		stage.dispose();
	}
	public void dispose() {
		fontName.dispose();
		fontScore.dispose();
		fondo.dispose();
		vidasActPanda.dispose();
		texturePanda.dispose();
		textbichos.dispose();
		for(int i=0;i<3;i++) {
			textItem[i].dispose();
		}
		if (this.perdio==false)
			stage.dispose();
	}
}

//https://gamedev.stackexchange.com/questions/119218/how-to-rotate-an-image-on-touch-using-rotatetoaction-in-libgdx