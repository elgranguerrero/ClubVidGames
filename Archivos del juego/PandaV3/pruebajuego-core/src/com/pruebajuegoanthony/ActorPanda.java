package com.pruebajuegoanthony;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

public class ActorPanda extends Actor{
	//Textura y animacion de vidas
	public TextureRegion actualVidas;
	TextureRegion[] regionesVidas;
	
	//Texture y animacion del panda
	TextureRegion panda;
	Sprite sp;
	private Animation pandaAnimation;
	
	public ControladorVirtual controlador;
	private InputMultiplexer multiplexor;
	private InputProcessor teclado, raton;
	
	private float duracion;
	public float velocidad = 0;
	public boolean salDer=false;
	public boolean salIzq=true;
	public int pos=0;
	public boolean girarD=true;
	public boolean girarI=false;
	float posicionX = getX();
	float posicionY = getY();
	/** Aceleración que lleva el coche. */
	public float aceleracion = 0;
	public float salto=160;
	public boolean elim=false;
	public int score;
	public int vida=3;
	
	private static final int ANCHO = 286;
	
	/** Alto real del dude. */
	private static final int ALTO = 309;
	
	public ActorPanda(TextureRegion panda,Sprite sp,Texture imgVidas) {
		//Textura de vidas
		TextureRegion[][] tmp=TextureRegion.split(imgVidas,
				imgVidas.getWidth()/1, imgVidas.getHeight()/4);
		//Region de la vida actual
		actualVidas = new TextureRegion(imgVidas,ANCHO, ALTO);
		
		
		System.out.println("entro");
		TextureRegion[][] temp = actualVidas.split(ANCHO, ALTO/4); // (1)
		regionesVidas = new TextureRegion[temp.length * temp[0].length]; // (2)
		int indice = 0;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				regionesVidas[indice++] = temp[i][j];
			}
		}
		this.actualVidas=this.regionesVidas[0];
		
		this.panda=panda;
		this.sp=sp;
		final float speed = 30.0f; // in degrees per second
		//Inicializacion de todas la caracteristicas del actor
		setPosition(50,50);
		setHeight(60);
		setWidth(96);
		//setHeight((sp.getHeight()*4)/5);
		//setWidth((sp.getWidth()*4)/5);
		setOriginX(getWidth()/2);
		setOriginY(getHeight()/2);
		/*rotate = new RotateToAction();
		rotate.setRotation(90);
		rotate.setDuration(1f);*/
		
	   // final float radius = 10.0f; // the radius of the circle you'll be rotating
	    pandaAnimation=new Animation(0.1f,this.panda);
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
		//panda.flip(false,true);
		duracion=0;
		score=0;
	}
	public void act(float delta) {
				
		if (controlador.moverDerecha) {
			Derecha();
		} else if (controlador.moverIzquierda) {
			Izquierda();
		} else if (controlador.moverArriba) {
			subir();
		} else if (controlador.moverAbajo) {
			bajar();
		}else if (controlador.atk) {
			atacar();
		}else {
			frenarCoche();
		}
		update();
	}
	public void draw(Batch batch,float parentAlpha) {
		duracion += Gdx.graphics.getDeltaTime();
		 if(posicionX> getX()) {   panda = (TextureRegion) pandaAnimation.getKeyFrame(duracion,true);
		
		 }
	     if(posicionX< getX()) {  panda = (TextureRegion) pandaAnimation.getKeyFrame(duracion,true);
	     }
	     if(posicionY> getY())  {  panda = (TextureRegion) pandaAnimation.getKeyFrame(duracion,true);
	     }
	     if(posicionY< getY())   { panda = (TextureRegion) pandaAnimation.getKeyFrame(duracion,true);
	     }
	    
		batch.draw(panda, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		
		batch.draw(this.actualVidas, 121, 657,50, 10);
	}
	 public void update() {
			posicionX = getX();
			posicionY = getY();
			float tiempo = Gdx.graphics.getDeltaTime();
			velocidad += aceleracion * tiempo;
			//Sube y baja y restricciones para no pasarse
			if (getY()>0 && getY()<Gdx.graphics.getHeight())
				posicionY += velocidad * tiempo ;
			else if (getY()>=Gdx.graphics.getHeight()) {posicionY=(float) (getY()-1);
					aceleracion=-1;
					velocidad=-1;}
			else {posicionY=getY()+1;
				aceleracion=-1;
				velocidad=-1;
			}
			//Saltos en arboles y restriccion para no seguir saltando
			if (posicionX>salto && (salIzq==false || girarI==false)) {
				posicionX += pos;
			}
			if (posicionX<salto && (salDer==false || girarD==false)) {
				posicionX += pos;
			}
			setX(posicionX);
			setY(posicionY);
		}
		
		/** Acelera el coche si no está a tope ya. */
		public void Derecha() {
			aceleracion = 0;
			if (Math.abs(velocidad) > 0.5f)
				velocidad *= 0.85f;
			else
				velocidad = 0;
			if (getX()<290) {
				if (salDer && getX()==salto) {
					panda.flip(false,true );
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
					panda.flip(false,true);
				}
			}
		}

		/** El coche avanza hacia atrás. */
		public void Izquierda() {
			aceleracion = 0;
			if (Math.abs(velocidad) > 0.5f)
				velocidad *= 0.85f;
			else
				velocidad = 0;
			if (getX()>=50) {
				if (salIzq && getX()==salto) {
					panda.flip(false,true );
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
					panda.flip(false,true);
				}
			}
		}
		public void subir() {
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
		public  String toString() {
			return "Panda; ";
		}
		public void cambioVida(int pos) {
			if (pos>=0 && pos<=3)
				this.actualVidas=this.regionesVidas[3-pos];
		}
		public String getScore() {
			String sr= this.score+"";
			return sr;
		}
}
