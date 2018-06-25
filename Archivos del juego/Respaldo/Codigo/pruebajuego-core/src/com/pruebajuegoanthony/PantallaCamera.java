package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class PantallaCamera extends Pantalla {

	private Coche coche;
	private Panda panda;
	private Texture fondo;
	private Texture fondov2;
	private ShapeRenderer shaper;
	private ControladorVirtual controlador;
	private InputMultiplexer multiplexor;
	private InputProcessor teclado, raton;
	int width ;
	int height;
	private OrthographicCamera cam;
		
	public PantallaCamera(pruebajuego game) {
		super(game);
	}
	
	@Override
	public void show() {
		final float speed = 30.0f; // in degrees per second
	    final float radius = 10.0f; // the radius of the circle you'll be rotating
	    float angle = elapsedTime * speed;
		coche = new Coche();
		panda = new Panda();
		// Creo los controladores.
		controlador = new ControladorVirtual();
		teclado = new EntradaCocheTeclado(controlador);
		raton = new EntradaCocheRaton(controlador);
		
		// Para poder usar ambos a la vez creo multiplexores.
		multiplexor = new InputMultiplexer();
		multiplexor.addProcessor(raton);
		multiplexor.addProcessor(teclado);
		
		// setInputProcessor = poner un controlador o un multiplexor.
		Gdx.input.setInputProcessor(multiplexor);
		panda.setPosition(100, 100);
		panda.rotate(90);
		//panda.flip(false,true);
		
		fondo=new Texture("fondo.png");
		fondov2=new Texture("fondov2.png");
		// Creo un shape renderer.
		shaper = new ShapeRenderer();
		
		// Creo una cámara.
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(width,height);
	}
	private float elapsedTime = 0.0f;
	private Vector2 center = new Vector2(10, 10);
	@Override
	public void render(float delta) {
		// Limpio la pantalla
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
		game.batch.setProjectionMatrix(cam.combined);
		
		game.batch.begin();
		game.batch.draw(fondo,0,0,width,height);
			panda.draw(game.batch);
		game.batch.end();
				
				// update
		if (controlador.obedeceRaton) {
			// Si aún debo obedecer al ratón, continúo avanzando.
			float restante = controlador.objetivoX - coche.getX();
			if (restante > 0) {
				panda.subir();
			} else {
				panda.bajar();
			}
			// Si aún estoy lejos de mi objetivo sigo obedeciendo al ratón.
			controlador.obedeceRaton = (Math.abs(restante) > 10f);
		} else if (controlador.moverDerecha) {
			panda.Derecha();
		} else if (controlador.moverIzquierda) {
			panda.Izquierda();
		} else if (controlador.moverArriba) {
			panda.subir();
		} else if (controlador.moverAbajo) {
			panda.bajar();
		}else if (controlador.atk) {
			panda.atacar();
		}else {
			panda.frenarCoche();
		}
		panda.update();
		// Muevo la cámara si estoy pulsando las teclas apropiadas.
		/*if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 10);
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -10);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-10, 0);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(10, 0);
		}
		
		// También puedo hacer zoom.
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.01;
		} else if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			cam.zoom -= 0.01;
		}*/
	}
	
	@Override
	public void resize(int width, int height) {
		// Cuando cambio el tamaño de la cámara la redimensiono.
		cam.setToOrtho(false, width, height);
	}
	public void dispose() {
		fondo.dispose();
		fondov2.dispose();
		shaper.dispose();
	}
}