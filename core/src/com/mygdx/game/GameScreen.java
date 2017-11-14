package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SimpleDirectionGestureDetector.DirectionListener;

public class GameScreen extends AbstractScreen implements InputProcessor, DirectionListener {
	
	public Preferences pref;
	
	public GameScreen(MyGdxGame game) {
		super(game);

		LoadData();
		Init();
	}



	Texture img;
	private Texture SnakeTexture;
	private LinkedList<kwadrat> snakeArray;
	private int size = 5;
	private int dlugosc = 5;
	private float timeHelper;
	private float timeHelper2;
	public char lastKey = 'w';
	public char preLastKey;
	public boolean canMove=true;
	
	private Texture belkaTexture;
	private belka belka1;
	private BitmapFont font;
	
	private Texture robakTexture;
	private Robak robakSmall;
	int size_kw = Math.round(Gdx.graphics.getWidth()/15);
	int ile_wezy = Gdx.graphics.getHeight()/size_kw;
	int reszta = Gdx.graphics.getHeight() - (ile_wezy * size_kw);
	
	private int robakSmallXCoor = new MathUtils().random(14) * size_kw ;
	private int robakSmallYCoor = new MathUtils().random(ile_wezy - 2) * size_kw;
	private int time;

	int reszta_zero = reszta;
	
	private Robak_big robakBig;
	private Texture robakBigTexture;

	int los;
	double lifeTime = 0;
	boolean bigExist = false;
	
	int pkt = 0;
	
	int fontBelkaSize = Math.round(Gdx.graphics.getWidth()/300);
	
	int wysokosc_belki;
	
	boolean robakSmallOK = false;
	boolean robakBigOK = false;
	
	private Texture background;
	private Texture pause;
	private Texture run_button;
	private Texture back; 
	private Texture estop;
	private Texture calc;
	
	Rectangle pause_button;
	Rectangle back_button;
	Rectangle estop_button;
	boolean pause_ons = false, back_ons = false;
	boolean run = true;
	private double predkosc;
	boolean estop_is_pressed=false;
	//double predkosc;
	String czas;
	
	private void LoadData() {
		pref = Gdx.app.getPreferences("Snake");
		
		
		robakTexture = new Texture("robak.png");
		belkaTexture = new Texture("belka.png");
		robakBigTexture = new Texture("robak_big.png");
		calc = new Texture("calc.png");
		pause = new Texture("pause.png");
		run_button = new Texture("run.png");
		back = new Texture("back.png");
		estop = new Texture("estop.png");
		
		if(pref.getString("Background").contains("Grass")){
		background = new Texture("grass.jpg");
		}
		
		if(pref.getString("Background").contains("Sand")){
		background = new Texture("sand.jpg");
		}
		
		if(pref.getString("Background").contains("Asphalt")){
		background = new Texture("asphalt.jpg");
		}
		
		
		if(pref.getString("Snake").contains("Snake1")){
		SnakeTexture = new Texture("snake1.png");
		}
		
		if(pref.getString("Snake").contains("Snake2")){
		SnakeTexture = new Texture("snake2.png");
		}
		
		if(pref.getString("Snake").contains("Snake3")){
		SnakeTexture = new Texture("snake3.png");
		}
		
	}

	
	
	private void Init() {
		
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
		
		Gdx.input.setInputProcessor(this);
		snakeArray = new LinkedList<kwadrat>();
		robakSmall = new Robak(robakTexture);
		robakBig = new Robak_big(robakBigTexture);
		belka1 = new belka(belkaTexture);

		robakSmall.height = size_kw;
		robakSmall.width = size_kw;
		
		
		robakBig.height = size_kw * 2;
		robakBig.width = size_kw * 2;
		
		System.out.println("H: " + robakBig.height + " W: " + robakBig.width);
		
		robakBig.x = -3000;
		robakBig.y = -3000;
		
		if (reszta == 0) reszta_zero = size_kw;
		System.out.println(reszta_zero);
		
		wysokosc_belki = size_kw + reszta;
		
		
		pause_button = new Rectangle(Gdx.graphics.getWidth() - wysokosc_belki, Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		
		back_button = new Rectangle(0, Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		
		estop_button = new Rectangle(Gdx.graphics.getWidth() - Math.round(wysokosc_belki * 2.5), Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);	
		
		for (int i = 0; i<size; i++){
			kwadrat kw = new kwadrat(SnakeTexture);
			kw.height = size_kw;
			kw.width = size_kw;
			kw.x = 0;
			kw.y = i*size_kw;
			snakeArray.add(kw);
		}
		
		
		String speed;
		pref = Gdx.app.getPreferences("Snake");
		speed = pref.getString("Speed", "Medium");
		pref.flush();
		System.out.println("Gamescreen: " + speed);
		System.out.println(speed);
		if(speed.contains("Easy")){
			predkosc = 0.2;
			System.out.println("Wybrano EASY!!!");
		}
		
		if(speed.contains("Medium")){
			predkosc = 0.15;
			System.out.println("Wybrano MEDIUM!!!");
		}
		
		if(speed.contains("Hard")){
			predkosc = 0.1;
			System.out.println("Wybrano HARD!!!");
		}

		Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(this));
		

	}


	
	private void move() {
		
		int size = snakeArray.size();
		
		kwadrat nowy = new kwadrat(SnakeTexture);
		
		switch(lastKey){

		case 'w':
			nowy.x = snakeArray.get(size - 1).x;
			nowy.y = snakeArray.get(size - 1).y + size_kw;
			break;
		
		case 's':
			nowy.x = snakeArray.get(size - 1).x;
			nowy.y = snakeArray.get(size - 1).y - size_kw;
			break;
		
		case 'a':
			nowy.y = snakeArray.get(size - 1).y;
			nowy.x = snakeArray.get(size - 1).x - size_kw;
			break;
		
		case 'd':
				nowy.y = snakeArray.get(size - 1).y;
				nowy.x = snakeArray.get(size - 1).x + size_kw;
			break;
		}
		
		if(nowy.x > size_kw * 14) {
			nowy.x = 0;
		}
		
		if(nowy.x < 0){
			nowy.x = 14 * size_kw;
		}
		

		
		if(nowy.y > size_kw * (ile_wezy - 1) - reszta_zero) {
			nowy.y = 0;
		}
		
		if(nowy.y < 0 ){
			nowy.y = size_kw * (ile_wezy - 2 )  ;
		}
		
		
		if((snakeArray.get(size - 1).x == robakSmallXCoor) && (snakeArray.get(size - 1).y == robakSmallYCoor)){
			
			while(robakSmallOK == false){
				robakSmallXCoor = new MathUtils().random(14) * size_kw ;
				robakSmallYCoor = new MathUtils().random(ile_wezy - 2) * size_kw ;
				robakSmallOK = true;
				for(int i = 0; i<snakeArray.size(); i++){
					if((snakeArray.get(i).x == robakSmallXCoor) && (snakeArray.get(i).y == robakSmallYCoor)){
						robakSmallOK = false;
						System.out.println("Bug zlapany :D");
						break;
					}
				}
				
			}
			
			robakSmallOK = false;
			
			System.out.println("X: " + robakSmallXCoor + "   Y: " + robakSmallYCoor);
			dlugosc +=1;
			pkt = pkt + 3;
			kwadrat dodaj = new kwadrat(SnakeTexture);
			dodaj.x = -300;
			dodaj.y = -300;
			dodaj.height = size_kw;
			dodaj.width = size_kw;
			snakeArray.add(dlugosc-1, dodaj);
			
			los =  new MathUtils().random(5);
			
			System.out.println(los);
			
			
			if((los == 4)){
				while(robakBigOK == false){
					
				robakBig.x = new MathUtils().random(13) * size_kw ;
				robakBig.y = new MathUtils().random(ile_wezy - 3) * size_kw;
				bigExist = true;
				//lifeTime = 200;
				lifeTime = predkosc * 1000;
				System.out.println(lifeTime);
				robakBigOK = true;
				for(int i = 0; i<snakeArray.size(); i++){
					if((snakeArray.get(i).x == robakSmallXCoor) || (snakeArray.get(i).y == robakSmallYCoor) || (snakeArray.get(i).y-size_kw == robakSmallYCoor) || (snakeArray.get(i).y-size_kw == robakSmallYCoor) ||  (snakeArray.get(i).y+size_kw == robakSmallYCoor) ||  (snakeArray.get(i).y+size_kw == robakSmallYCoor)){
						robakSmallOK = false;
						System.out.println("BIG Bug zlapany :D");
						break;
					}
				}
				
				}
				robakBigOK = false;
				
			}
	
			
		}
		

		snakeArray.add(nowy);
		
		for(int i = size - 2; i >= 1; i--) {
			snakeArray.get(i).copyFrom(snakeArray.get(i));
		}

		snakeArray.remove(0);
		
		
		snakeArray.get(size - 1).height = size_kw;
		snakeArray.get(size - 1).width = size_kw;
		
		if ((snakeArray.get(size - 1).overlaps(robakBig))){
			System.out.println("W¹¿ zjadl du¿ego robala");
			robakBig.x = -3000;
			robakBig.y = -3000;
			pkt += lifeTime;
			bigExist = false;
			lifeTime = 0;
		}
		
		for (int i = 0; i<size - 1; i++){
			if ((snakeArray.get(i).x == snakeArray.get(size-1).x) && (snakeArray.get(i).y == snakeArray.get(size-1).y) && (snakeArray.get(i).x >=0) && snakeArray.get(i).y>=0){
				//game.setScreen(new MenuScreen(game));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("W¹¿ siê ugryz³ !!!");
				System.out.println("x: " + snakeArray.get(i).x + " y: " + snakeArray.get(i).y);
				
				
//				int pkt_int;
//				String pkt_string;
//				pref = Gdx.app.getPreferences("Snake");
//				pkt_string = pref.getString("maxPkt", "0");
//				pkt_int = Integer.parseInt(pkt_string);
//
//				
//				
//				int size_int;
//				String size_string;
//				pref = Gdx.app.getPreferences("Snake");
//				size_string = pref.getString("maxSize", "0");
//				size_int = Integer.parseInt(size_string);
//				
//				int time_int;
//				String time_string;
//				pref = Gdx.app.getPreferences("Snake");
//				time_string = pref.getString("maxTime", "0");
//				time_int = Integer.parseInt(time_string);
//				
//				if(pkt_int<pkt){
//				pref = Gdx.app.getPreferences("Snake");
//				String temp = Integer.toString(pkt);
//				pref.putString("maxPkt", temp);
//				pref.flush();
//				}
//				
//				if(size_int<size){
//				pref = Gdx.app.getPreferences("Snake");
//				String temp_size = Integer.toString(size);
//				pref.putString("maxSize", temp_size);
//				pref.flush();
//				}
//				
//				if(time_int<(time/5)){
//				pref = Gdx.app.getPreferences("Snake");
//				String temp_time = Integer.toString(time/5);
//				pref.putString("maxTime", temp_time);
//				pref.flush();
//				}
				
				
				int pkt_int;
				String pkt_string;
				
		
				
				pref = Gdx.app.getPreferences("Snake");
				
				//String temp_old = Integer.toString(pkt);
				//pref.putString("pkt_last", temp_old);
				//pref.flush();
				
				pref.putString("last_score", Integer.toString(pkt));
				pref.flush();
				
				if(pref.getString("Speed").contains("Easy")){
					pkt_string = pref.getString("pkt_easy", "0");
					pkt_int = Integer.parseInt(pkt_string);
					
					pref.putString("pkt_last", pref.getString("pkt_easy"));
					pref.flush();
					
					if(pkt_int<pkt){
						String temp = Integer.toString(pkt);
						pref.putString("pkt_easy", temp);
						pref.flush();
					}
				}
				
				
				if(pref.getString("Speed").contains("Medium")){
					pkt_string = pref.getString("pkt_medium", "0");
					pkt_int = Integer.parseInt(pkt_string);
					
					pref.putString("pkt_last", pref.getString("pkt_medium"));
					pref.flush();
					
					if(pkt_int<pkt){
						String temp = Integer.toString(pkt);
						pref.putString("pkt_medium", temp);
						pref.flush();
					}
				}
				
				
				if(pref.getString("Speed").contains("Hard")){
					pkt_string = pref.getString("pkt_hard", "0");
					pkt_int = Integer.parseInt(pkt_string);
					
					pref.putString("pkt_last", pref.getString("pkt_hard"));
					pref.flush();
					
					if(pkt_int<pkt){
						String temp = Integer.toString(pkt);
						pref.putString("pkt_hard", temp);
						pref.flush();
					}
				}
				
//				String temp = Integer.toString(pkt);
//				pref.putString("pkt_last", temp);
//				pref.flush();
				
				game.setScreen(new GameOverScreen(game));
				
			}
		}
		
		
	}
	
	
	
	private void Update(float delta) {
		if(run == true){
		timeHelper += delta;
		timeHelper2 += delta;
		}
		
		if(timeHelper2 > 0.2f){
			time +=1;
			timeHelper2 = 0;
		}
		
		if(timeHelper > predkosc){
				move();
			timeHelper = 0;
		}
		
		
		if ((bigExist = true) && (lifeTime>0)){
			
			if (timeHelper > 0.01f){
				lifeTime -= 1;
			}
			
			if (lifeTime == 0){
				robakBig.x = -3000;
				robakBig.y = -3000;
				bigExist = false;
			}
			
		}
		
		if(Gdx.input.isTouched()) {
			Vector3 position = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(position);
				
			if(pause_button.contains(position.x, position.y)){
				
				if(pause_ons == false){
					System.out.println("pause");
					pause_ons = true;
					if(run == true){
					run = false;
					}else{
						run = true;
					}
				}
				
			}
			
			if(back_button.contains(position.x, position.y)){
				if(back_ons == false){
				System.out.println("back");
				game.setScreen(new MenuScreen(game));
				back_ons = true;
				}
			}
			
			if(estop_button.contains(position.x, position.y)){
				System.out.println("ESTOP");
				estop_is_pressed = true;
				run = false;
			}

		}else{
			pause_ons = false;
			back_ons = false;
		}
		
	}
		

	@Override
	public boolean keyDown(int keycode) {		
		if((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)){
			game.setScreen(new MenuScreen(game));
			System.out.println("back");
		}
		
		switch(keycode) {
		case Keys.A:
			if(lastKey != 'd')
				lastKey = 'a';
			break;
		case Keys.D:
			if(lastKey != 'a')
				lastKey = 'd';
			break;
		case Keys.W:
			if(lastKey != 's')
				lastKey = 'w';
			break;
		case Keys.S:
			if(lastKey != 'w')
				lastKey = 's';
			break;
		}
		return false;
	}



	//Rectangle down =  new Rectangle((480 - (480/2)) /2 , 0 , 480/2, 800/5);
	//Rectangle up =  new Rectangle((480 - (480/2)) /2 , 800 - (800/5) , 480/2, 800/5);
	//Rectangle left = new Rectangle(0, (800 - (800/2)) /2, 480/4, 800/2);
	//Rectangle right = new Rectangle(480 - (480/4), (800 - (800/2)) /2, 480/4, 800/2);
	
//	Rectangle down =  new Rectangle(480 - (2 * 75), 0 , 75, 75);
//	Rectangle up =  new Rectangle(480 - (2*75), (2*75)  , 75, 75);
//	Rectangle left = new Rectangle(480 - (3*75), 75 , 75, 75);
//	Rectangle right = new Rectangle(480 - 75, 75 , 75, 75);


	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
 		
		Vector3 pos = new Vector3(screenX, screenY, 0);
		cam.unproject(pos);
		
		float areaWidth = Gdx.graphics.getWidth() * 0.5f;
		float areaHeight = Gdx.graphics.getHeight() * 0.5f;
		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
			
		
//		if(left.contains(pos.x, pos.y))
//			keyDown(Keys.A);
//		
//		if(right.contains(pos.x, pos.y))
//			keyDown(Keys.D);
//			
//		if(down.contains(pos.x, pos.y))
//			keyDown(Keys.S);
//		
//		if(up.contains(pos.x, pos.y))
//			keyDown(Keys.W);
			
		
		return true;
	}

	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void show() {	
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		Update(delta);

		
		batch.begin();
		
		
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-belka1.height);
		
		belka1.x = 0;
		belka1.y = Gdx.graphics.getHeight() - (reszta + size_kw);
		belka1.draw(batch);


		
		robakSmall.x = robakSmallXCoor;
		robakSmall.y = robakSmallYCoor;
		robakSmall.draw(batch);

		robakBig.draw(batch);
		
		if(run == true){
		batch.draw(pause, Gdx.graphics.getWidth() - wysokosc_belki, Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		}else{
			batch.draw(run_button, Gdx.graphics.getWidth() - wysokosc_belki, Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		}
		
		batch.draw(back, 0, Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		
		batch.draw(estop, Gdx.graphics.getWidth() - Math.round(wysokosc_belki * 2.5), Gdx.graphics.getHeight() - wysokosc_belki, wysokosc_belki, wysokosc_belki);
		
		for (kwadrat kw : snakeArray){
			kw.draw(batch);
		}
		
		
		if (((time/5)%60) <= 9){
			czas = "0" + Integer.toString((time/5)%60);
		}else{
			czas = Integer.toString((time/5)%60);
		}
		
		if (lifeTime != 0){
			
		Fonts.getFont(fontBelkaSize).draw(batch, "Points: " + pkt  + "  " + "Time: " + ((time/5)/60) + ":" + czas + " Bonus: " + lifeTime, back_button.width  , Gdx.graphics.getHeight() - ((belka1.height - Fonts.getFont(fontBelkaSize).getLineHeight()) /2) - Fonts.getFont(fontBelkaSize).getLineHeight() );
		
		}else{
			
			Fonts.getFont(fontBelkaSize).draw(batch, "Points: " + pkt  + "  " + "Time: " + ((time/5)/60) + ":" + czas, back_button.width  , Gdx.graphics.getHeight() - ((belka1.height - Fonts.getFont(fontBelkaSize).getLineHeight()) /2) - Fonts.getFont(fontBelkaSize).getLineHeight() );
		
		}
		
		
		if(estop_is_pressed == true){
			batch.draw(calc, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-belka1.height);
		}
		
		batch.end();
		
		
		shape.begin();
//			shape.rect(down.x, down.y, down.width, down.height);
//			shape.rect(up.x, up.y, up.width, up.height);
//			shape.rect(left.x, left.y, left.width, left.height);
//			shape.rect(right.x, right.y, right.width, right.height);
			if(estop_is_pressed == false){
			shape.rect(pause_button.x, pause_button.y, pause_button.width, pause_button.height);
			shape.rect(back_button.x, back_button.y, back_button.width, back_button.height);
			shape.rect(estop_button.x, estop_button.y, estop_button.width, estop_button.height);
			}
		shape.end();
	}



	@Override
	public void onUp() {
		keyDown(Keys.W);
	}

	@Override
	public void onRight() {
		keyDown(Keys.D);

	}

	@Override
	public void onLeft() {
		keyDown(Keys.A);

	}

	@Override
	public void onDown() {
		keyDown(Keys.S);
		if(estop_is_pressed==true){
			estop_is_pressed = false;
			run = true;
		}
	}

}
