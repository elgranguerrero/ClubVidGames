package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	//Creo el stage
	Stage stage;
	Sprite sp;
	
	//Atributos y actores del juego
	ActorPanda panda;
	Texture texturePanda;
	TextureRegion txtRePanda;
	
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
	
	private Texture fondo;
	private ShapeRenderer shaper;
	int cantEnemi;
	int width ;
	int height;
	private OrthographicCamera cam;
	
	public void show() {
		//Crearndo texturas y sprites
		
		texturePanda= new Texture("panda.png");
		txtRePanda=new TextureRegion(texturePanda,0, 0, 215, 83);
		sp=new Sprite(texturePanda,0, 0, 215, 83);
		
		textbichos=new Texture("bichov1.png");
		regionBichos=new TextureRegion(textbichos,0, 0, 257, 148);
		spBichos=new Sprite(textbichos,0, 0, 257, 148);
		enemiBichitos=new ActorBichos(regionBichos,spBichos);
		
		fondo=new Texture("fondo.png");
		
		actItems=new ActorItems(regionItem,spItem);
		stage=new Stage();
		stage.setDebugAll(true);
		
		panda=new ActorPanda(txtRePanda,sp);
		
		stage.addActor(panda);
		stage.addActor(enemiBichitos);
		stage.addActor(actItems);
		panda.setPosition(100, 100);
		panda.rotateBy(90);
		
		// Creo un shape renderer.
		shaper = new ShapeRenderer();
		
		// Creo una cámara.
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(width,height);

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
		game.batch.draw(fondo,0,0,width,height);
		game.batch.end();
		stage.act();
		colision(enemiBichitos, actItems);
		stage.draw();
	}
	public void colision(ActorBichos bichos, ActorItems item) {
		Rectangle rectP= new Rectangle(panda.getX()+115,panda.getY()-60,panda.getWidth()-190,panda.getHeight()+35);
		for (int i=0;i<bichos.listBichos.size();i++) {
			Bichos b=bichos.listBichos.get(i);
			Rectangle rectB= new Rectangle(b.getPosX(),b.getPosY(),b.getWidth(),b.getHeight());
			if (rectB.overlaps(rectP) && panda.controlador.atk) {
				//b.eliminar=true;
				this.enemiBichitos.listBichos.remove(b);
				panda.score=panda.score+b.puntos;
				System.out.println("Hubo Colision");
				System.out.println("Bichos"+b.getPosX()+" "+b.getPosY()+" "+b.getHeight()+" "+b.getWidth());
				System.out.println("Bichos"+b.puntos);
				System.out.println("RectBBichos: "+rectB.getX()+" "+rectB.getY()+" "+rectB.getHeight()+" "+rectB.getWidth());
				System.out.println("RectPanda: "+rectP.getX()+" "+rectP.getY()+" "+rectP.getHeight()+" "+rectP.getWidth());
				System.out.println("Panda: "+panda.getX()+" "+panda.getY()+" "+panda.getHeight()+" "+panda.getWidth());
			}
			if (rectB.overlaps(rectP) && this.enemiBichitos.inmunidadP) {
				//b.eliminar=true;
				this.enemiBichitos.listBichos.remove(b);
				panda.vida=panda.vida-1;
				System.out.println("Hubo Colision");
				System.out.println("Bichos"+b.getPosX()+" "+b.getPosY()+" "+b.getHeight()+" "+b.getWidth());
				System.out.println("Bichos"+b.puntos);
				System.out.println("RectBBichos: "+rectB.getX()+" "+rectB.getY()+" "+rectB.getHeight()+" "+rectB.getWidth());
				System.out.println("RectPanda: "+rectP.getX()+" "+rectP.getY()+" "+rectP.getHeight()+" "+rectP.getWidth());
				System.out.println("Panda: "+panda.getX()+" "+panda.getY()+" "+panda.getHeight()+" "+panda.getWidth());
			
			}
		}
		for (int i=0;i<actItems.listItems.size();i++) {
			Items I=actItems.listItems.get(i);
			Rectangle rectI= new Rectangle(I.getPosX(),I.getPosY(),I.getWidth(),I.getHeight());
			if (rectI.overlaps(rectP) && actItems.randomtext==0) {
				//b.eliminar=true;
				this.actItems.listItems.remove(I);
				System.out.println("enro a vidas");
				panda.vida=panda.vida+1;
			}
			if (rectI.overlaps(rectP) && actItems.randomtext==1) {
				//b.eliminar=true;
				this.actItems.listItems.remove(I);
				System.out.println("enro a tiempo");
				this.enemiBichitos.setNivel(-(this.enemiBichitos.getNivel()));
				this.enemiBichitos.setTiempo(this.enemiBichitos.getTiempo()-200);
				
			}
			if (rectI.overlaps(rectP) && actItems.randomtext==2) {
				//b.eliminar=true;
				this.actItems.listItems.remove(I);
				this.enemiBichitos.inmunidadP=true;
				System.out.println("enro a shield");
			}
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
		texturePanda.dispose();
		textbichos.dispose();
		for(int i=0;i<3;i++) {
			textItem[i].dispose();
		}
		stage.dispose();
	}
}

//https://gamedev.stackexchange.com/questions/119218/how-to-rotate-an-image-on-touch-using-rotatetoaction-in-libgdx