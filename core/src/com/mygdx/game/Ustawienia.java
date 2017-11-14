package com.mygdx.game;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;

public class Ustawienia extends AbstractScreen implements InputProcessor {

	public Preferences pref;
	
	int fontSize = Math.round(Gdx.graphics.getWidth()/150);
	TextBounds bounds_ustawienia = Fonts.getFont(fontSize * 2).getBounds("Ustawienia");
	TextBounds bounds_predkosc = Fonts.getFont(fontSize).getBounds("Predkosc");
	TextBounds bounds_jezyk = Fonts.getFont(fontSize).getBounds("Język");
	
	//TextBounds bounds_predkosc = Fonts.getFont(fontSize).getBounds("A").width * 8;
	//TextBounds bounds_jezyk = Fonts.getFont(fontSize).getBounds("A").width * 5;
			
	private Texture background;
	
	private Texture easy;
	private Texture medium;
	private Texture hard;
	
	private Texture pl;
	private Texture eng;
	private Texture rus;
	
	private Texture grass;
	private Texture sand;
	private Texture asphalt;
	
	private Texture snake1;
	private Texture snake2;
	private Texture snake3;
	
	private Texture estop1;
	private Texture estop2;
	private Texture estop3;
	
	private Texture ustawienia_pl;
	private Texture ustawienia_eng;
	private Texture ustawienia_rus;
	
	private Texture predkosc_pl;
	private Texture predkosc_eng;
	private Texture predkosc_rus;
	
	private Texture jezyk_pl;
	private Texture jezyk_eng;
	private Texture jezyk_rus;
	
	private Texture estop;
	
	private Texture tlo_pl;
	private Texture tlo_eng;
	private Texture tlo_rus;
	
	private Texture snake_pl;
	private Texture snake_eng;
	private Texture snake_rus;
	
	private Texture robaki_pl;
	private Texture robaki_eng;
	private Texture robaki_rus;
	
	Rectangle easy_rect;
	Rectangle medium_rect;
	Rectangle hard_rect;
	
	Rectangle pl_rect;
	Rectangle eng_rect;
	Rectangle rus_rect;
	
	Rectangle grass_rect;
	Rectangle sand_rect;
	Rectangle asphalt_rect;
	
	Rectangle snake1_rect;
	Rectangle snake2_rect;
	Rectangle snake3_rect;
	
	Rectangle estop1_rect;
	Rectangle estop2_rect;
	Rectangle estop3_rect;
	
	Rectangle ustawienia_rect;
	Rectangle predkosc_rect;
	Rectangle jezyk_rect;
	Rectangle tlo_rect;
	Rectangle snake_rect;
	Rectangle robaki_rect;
	
	Rectangle estop_rect;

	
	String speed;
	String language;
	String bg;
	String snk;
	String est;

	
	int size_kw = Math.round(Gdx.graphics.getWidth()/8);

	int bigFont;
	public Ustawienia(MyGdxGame game) {
		super(game);
		
		pref = Gdx.app.getPreferences("Snake");
		speed = pref.getString("Speed");
		System.out.println(speed);
		
		background = new Texture("tlo_snake.png");
		
		predkosc_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - (Math.round(Gdx.graphics.getHeight() * 0.12)) * 2,
				Gdx.graphics.getWidth(),
				Math.round(Gdx.graphics.getHeight() * 0.12)
				);
		
		jezyk_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round((Math.round(Gdx.graphics.getHeight() * 0.12)) * 3.2),
				Gdx.graphics.getWidth(),
				Math.round(Gdx.graphics.getHeight() * 0.12)
				);
		
		estop_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round((Math.round(Gdx.graphics.getHeight() * 0.12)) * 4.4),
				Gdx.graphics.getWidth(),
				Math.round(Gdx.graphics.getHeight() * 0.12)
				);
		
		tlo_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round((Math.round(Gdx.graphics.getHeight() * 0.12)) * 5.6),
				Gdx.graphics.getWidth(),
				Math.round(Gdx.graphics.getHeight() * 0.12)
				);
		
		snake_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round((Math.round(Gdx.graphics.getHeight() * 0.12)) * 6.8),
				Gdx.graphics.getWidth(),
				Math.round(Gdx.graphics.getHeight() * 0.12)
				);
		
		robaki_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round((Math.round(Gdx.graphics.getHeight() * 0.12)) * 8.0),
				Gdx.graphics.getWidth(),
				Math.round(Gdx.graphics.getHeight() * 0.12)
				);
		
		easy_rect = new Rectangle(
				((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2,
				predkosc_rect.y + 10,
				size_kw,
				size_kw
				);
		
		medium_rect = new Rectangle(
				(Gdx.graphics.getWidth() - size_kw)/2,
				predkosc_rect.y + 10,
				size_kw,
				size_kw
				);
		
		hard_rect = new Rectangle(
				Gdx.graphics.getWidth() - (((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2) - size_kw,
				predkosc_rect.y + 10,
				size_kw,
				size_kw
				);
		
		
		pl_rect = new Rectangle(
				((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2,
				jezyk_rect.y + 10,
				size_kw,
				size_kw
				);
		
		eng_rect = new Rectangle(
				(Gdx.graphics.getWidth() - size_kw)/2,
				jezyk_rect.y + 10,
				size_kw,
				size_kw
				);
		
		rus_rect = new Rectangle(
				Gdx.graphics.getWidth() - (((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2) - size_kw,
				jezyk_rect.y + 10,
				size_kw,
				size_kw
				);
		
		grass_rect = new Rectangle(
				((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2,
				tlo_rect.y + 10,
				size_kw,
				size_kw
				);
		
		sand_rect = new Rectangle(
				(Gdx.graphics.getWidth() - size_kw)/2,
				tlo_rect.y + 10,
				size_kw,
				size_kw
				);
		
		asphalt_rect = new Rectangle(
				Gdx.graphics.getWidth() - (((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2) - size_kw,
				tlo_rect.y + 10,
				size_kw,
				size_kw
				);
		
		snake1_rect = new Rectangle(
				((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2,
				snake_rect.y + 10,
				size_kw,
				size_kw
				);
		
		snake2_rect = new Rectangle(
				(Gdx.graphics.getWidth() - size_kw)/2,
				snake_rect.y + 10,
				size_kw,
				size_kw
				);
		
		snake3_rect = new Rectangle(
				Gdx.graphics.getWidth() - (((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2) - size_kw,
				snake_rect.y + 10,
				size_kw,
				size_kw
				);
		
		estop1_rect = new Rectangle(
				((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2,
				estop_rect.y + 10,
				size_kw,
				size_kw
				);
		
		estop2_rect = new Rectangle(
				(Gdx.graphics.getWidth() - size_kw)/2,
				estop_rect.y + 10,
				size_kw,
				size_kw
				);
		
		estop3_rect = new Rectangle(
				Gdx.graphics.getWidth() - (((Gdx.graphics.getWidth() - size_kw)/2)/2 - size_kw/2) - size_kw,
				estop_rect.y + 10,
				size_kw,
				size_kw
				);
		
		ustawienia_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round(Gdx.graphics.getHeight() * 0.1), 
				Gdx.graphics.getWidth(), 
				Math.round(Gdx.graphics.getHeight() * 0.1)
				);
		


		LoadData();
		Init();
	}


	private void Init() {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
		
	}

	private void LoadData() {

		easy = new Texture("Ustawienia/easy.png");
		medium = new Texture("Ustawienia/medium.png");
		hard = new Texture("Ustawienia/hard.png");
		
		pl = new Texture("Ustawienia/pl.png");
		eng = new Texture("Ustawienia/eng.png");
		rus = new Texture("Ustawienia/rus.png");
		
		grass = new Texture("Ustawienia/grass.png");
		sand = new Texture("Ustawienia/sand.png");
		asphalt = new Texture("Ustawienia/asphalt.png");
		
		snake1 = new Texture("Ustawienia/snake1.png");
		snake2 = new Texture("Ustawienia/snake2.png");
		snake3 = new Texture("Ustawienia/snake3.png");
		
		estop1 = new Texture("Ustawienia/calc.png");
		estop2 = new Texture("Ustawienia/calendar.png");
		estop3 = new Texture("Ustawienia/black.png");
		
		ustawienia_pl = new Texture("Ustawienia/Ustawienia_pl.png");
		ustawienia_eng = new Texture("Ustawienia/Ustawienia_eng.png");
		ustawienia_rus = new Texture("Ustawienia/Ustawienia_rus.png");
		
		predkosc_pl = new Texture("Ustawienia/Predkosc_pl.png");
		predkosc_eng = new Texture("Ustawienia/Predkosc_eng.png");
		predkosc_rus = new Texture("Ustawienia/Predkosc_rus.png");
		
		jezyk_pl = new Texture("Ustawienia/Jezyk_pl.png");
		jezyk_eng = new Texture("Ustawienia/Jezyk_eng.png");
		jezyk_rus = new Texture("Ustawienia/Jezyk_rus.png");
		
		estop = new Texture("Ustawienia/Estop.png");
		
		tlo_pl = new Texture("Ustawienia/Tlo_pl.png");
		tlo_eng = new Texture("Ustawienia/Tlo_eng.png");
		tlo_rus = new Texture("Ustawienia/Tlo_rus.png");
		
		//snake_pl = new Texture("Ustawienia/Snake_pl.png");
		//snake_eng = new Texture("Ustawienia/Snake_eng.png");
		//snake_rus = new Texture("Ustawienia/Snake_rus.png");
		
		snake_pl = new Texture("Ustawienia/Snake_pl.png");
		snake_eng = new Texture("Ustawienia/snake_eng.png");
		snake_rus = new Texture("Ustawienia/Snake_rus.png");
		
		robaki_pl = new Texture("Ustawienia/Robaki_pl.png");
		robaki_eng = new Texture("Ustawienia/Robaki_eng.png");
		robaki_rus = new Texture("Ustawienia/Robaki_rus.png");
	}

   
    
    
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		Update(delta);
		
		batch.begin();
		
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
//		if(pref.getString("Language").contains("Pl")){
//		if(fontSize*2 < 10){
//		Fonts.getFont(fontSize *2 ).draw(batch, "Ustawienia", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize *2).getBounds("a").width * 10)/2, Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight()/2);
//		} else{
//				Fonts.getFont(10).draw(batch, "Ustawienia", (Gdx.graphics.getWidth() - Fonts.getFont(10).getBounds("a").width * 10)/2, Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight()/2);
//		}
//
//		Fonts.getFont(fontSize).draw(batch, "Predkość", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize).getBounds("a").width * 8)/2  , Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight() * 2);
//		
//		
//		batch.draw(easy, easy_rect.x, easy_rect.y, easy_rect.width, easy_rect.height);
//		batch.draw(medium, medium_rect.x, medium_rect.y, medium_rect.width, medium_rect.height);
//		batch.draw(hard, hard_rect.x, hard_rect.y, hard_rect.width, hard_rect.height);
//		
//
//		Fonts.getFont(fontSize).draw(batch, "Język", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize).getBounds("a").width * 5)/2  , Gdx.graphics.getHeight() - Math.round(Gdx.graphics.getWidth()/5 * 2.5));
//		}
		
		
		
		
		
//		if(pref.getString("Language").contains("Eng")){
//		if(fontSize*2 < 10){
//		Fonts.getFont(fontSize *2 ).draw(batch, "Settings", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize *2).getBounds("a").width * 7)/2, Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight()/2);
//		} else{
//				Fonts.getFont(10).draw(batch, "Settings", (Gdx.graphics.getWidth() - Fonts.getFont(10).getBounds("a").width * 7)/2, Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight()/2);
//		}
//
//		Fonts.getFont(fontSize).draw(batch, "Speed", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize).getBounds("a").width * 5)/2  , Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight() * 2);
//		
//		
//		batch.draw(easy, easy_rect.x, easy_rect.y, easy_rect.width, easy_rect.height);
//		batch.draw(medium, medium_rect.x, medium_rect.y, medium_rect.width, medium_rect.height);
//		batch.draw(hard, hard_rect.x, hard_rect.y, hard_rect.width, hard_rect.height);
//		
//
//		Fonts.getFont(fontSize).draw(batch, "Language", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize).getBounds("a").width * 8)/2  , Gdx.graphics.getHeight() - Math.round(Gdx.graphics.getWidth()/5 * 2.5));
//		}
		
		
		
		
		
		
//		if(pref.getString("Language").contains("Rus")){
//		if(fontSize*2 < 10){
//		Fonts.getFont(fontSize *2 ).draw(batch, "Настройки", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize *2).getBounds("a").width * 9)/2, Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight()/2);
//		} else{
//				Fonts.getFont(10).draw(batch, "Настройки", (Gdx.graphics.getWidth() - Fonts.getFont(10).getBounds("a").width * 9)/2, Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight()/2);
//		}
//
//		Fonts.getFont(fontSize).draw(batch, "Скорость", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize).getBounds("a").width * 8)/2  , Gdx.graphics.getHeight() - Fonts.getFont(fontSize).getLineHeight() * 2);
//		
//		
//		batch.draw(easy, easy_rect.x, easy_rect.y, easy_rect.width, easy_rect.height);
//		batch.draw(medium, medium_rect.x, medium_rect.y, medium_rect.width, medium_rect.height);
//		batch.draw(hard, hard_rect.x, hard_rect.y, hard_rect.width, hard_rect.height);
//		
//
//		Fonts.getFont(fontSize).draw(batch, "Язык", (Gdx.graphics.getWidth() - Fonts.getFont(fontSize).getBounds("a").width * 4)/2  , Gdx.graphics.getHeight() - Math.round(Gdx.graphics.getWidth()/5 * 2.5));
//		}
		
		if(pref.getString("Language").contains("Pl")){
			batch.draw(ustawienia_pl, ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			batch.draw(predkosc_pl, predkosc_rect.x, predkosc_rect.y, predkosc_rect.width, predkosc_rect.height);
			batch.draw(jezyk_pl, jezyk_rect.x, jezyk_rect.y, jezyk_rect.width, jezyk_rect.height);
			batch.draw(estop, estop_rect.x, estop_rect.y, estop_rect.width, estop_rect.height);
			batch.draw(tlo_pl, tlo_rect.x, tlo_rect.y, tlo_rect.width, tlo_rect.height);
			batch.draw(snake_pl, snake_rect.x, snake_rect.y, snake_rect.width, snake_rect.height);
			batch.draw(robaki_pl, robaki_rect.x, robaki_rect.y, robaki_rect.width, robaki_rect.height);
			
		}
		
		
		if(pref.getString("Language").contains("Eng")){
			batch.draw(ustawienia_eng, ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			batch.draw(predkosc_eng, predkosc_rect.x, predkosc_rect.y, predkosc_rect.width, predkosc_rect.height);
			batch.draw(jezyk_eng, jezyk_rect.x, jezyk_rect.y, jezyk_rect.width, jezyk_rect.height);
			batch.draw(estop, estop_rect.x, estop_rect.y, estop_rect.width, estop_rect.height);
			batch.draw(tlo_eng, tlo_rect.x, tlo_rect.y, tlo_rect.width, tlo_rect.height);
			batch.draw(snake_eng, snake_rect.x, snake_rect.y, snake_rect.width, snake_rect.height);
			batch.draw(robaki_eng, robaki_rect.x, robaki_rect.y, robaki_rect.width, robaki_rect.height);
			
		}
		
		if(pref.getString("Language").contains("Rus")){
			batch.draw(ustawienia_rus, ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			batch.draw(predkosc_rus, predkosc_rect.x, predkosc_rect.y, predkosc_rect.width, predkosc_rect.height);
			batch.draw(jezyk_rus, jezyk_rect.x, jezyk_rect.y, jezyk_rect.width, jezyk_rect.height);
			batch.draw(estop, estop_rect.x, estop_rect.y, estop_rect.width, estop_rect.height);
			batch.draw(tlo_rus, tlo_rect.x, tlo_rect.y, tlo_rect.width, tlo_rect.height);
			batch.draw(snake_rus, snake_rect.x, snake_rect.y, snake_rect.width, snake_rect.height);
			batch.draw(robaki_rus, robaki_rect.x, robaki_rect.y, robaki_rect.width, robaki_rect.height);
			
		}
		
		
		batch.draw(easy, easy_rect.x, easy_rect.y, easy_rect.width, easy_rect.height);
		batch.draw(medium, medium_rect.x, medium_rect.y, medium_rect.width, medium_rect.height);
		batch.draw(hard, hard_rect.x, hard_rect.y, hard_rect.width, hard_rect.height);
		
		
		
		batch.draw(pl, pl_rect.x, pl_rect.y, pl_rect.width, pl_rect.height);
		batch.draw(eng, eng_rect.x, eng_rect.y, eng_rect.width, eng_rect.height);
		batch.draw(rus, rus_rect.x, rus_rect.y, rus_rect.width, rus_rect.height);
		
		batch.draw(grass, grass_rect.x, grass_rect.y, grass_rect.width, grass_rect.height);
		batch.draw(sand, sand_rect.x, sand_rect.y, sand_rect.width, sand_rect.height);
		batch.draw(asphalt, asphalt_rect.x, asphalt_rect.y, asphalt_rect.width, asphalt_rect.height);
				
		batch.draw(snake1, snake1_rect.x, snake1_rect.y, snake1_rect.width, snake1_rect.height);
		batch.draw(snake2, snake2_rect.x, snake2_rect.y, snake2_rect.width, snake2_rect.height);
		batch.draw(snake3, snake3_rect.x, snake3_rect.y, snake3_rect.width, snake3_rect.height);
		
		batch.draw(estop1, estop1_rect.x, estop1_rect.y, estop1_rect.width, estop1_rect.height);
		batch.draw(estop2, estop2_rect.x, estop2_rect.y, estop2_rect.width, estop2_rect.height);
		batch.draw(estop3, estop3_rect.x, estop3_rect.y, estop3_rect.width, estop3_rect.height);
		
		batch.end();
		

		speed = pref.getString("Speed");
		language = pref.getString("Language");
		bg = pref.getString("Background");
		snk = pref.getString("Snake");
		est = pref.getString("Estop");
		
		
		shape.begin();

		//shape.rect(easy_rect.x, easy_rect.y, easy_rect.width, easy_rect.height);
		//shape.rect(medium_rect.x, medium_rect.y, medium_rect.width, medium_rect.height);
		//shape.rect(hard_rect.x, hard_rect.y, hard_rect.width, hard_rect.height);
		
		shape.end();

		shape.begin(ShapeType.Filled);
		
		if(speed.contains("Easy")){
			shape.rectLine(easy_rect.x, easy_rect.y-3, easy_rect.x + easy_rect.width, easy_rect.y-3, 6);
		}
		
		if(speed.contains("Medium")){
			shape.rectLine(medium_rect.x, medium_rect.y-3, medium_rect.x + medium_rect.width, medium_rect.y-3, 6);
		}
		
		if(speed.contains("Hard")){
			shape.rectLine(hard_rect.x, hard_rect.y-3, hard_rect.x + hard_rect.width, hard_rect.y-3, 6);
		}
		
		if(language.contains("Pl")){
			shape.rectLine(pl_rect.x, pl_rect.y-3, pl_rect.x + pl_rect.width, pl_rect.y-3, 6);
		}
		
		if(language.contains("Eng")){
			shape.rectLine(eng_rect.x, eng_rect.y-3, eng_rect.x + eng_rect.width, eng_rect.y-3, 6);
		}
		
		if(language.contains("Rus")){
			shape.rectLine(rus_rect.x, rus_rect.y-3, rus_rect.x + rus_rect.width, rus_rect.y-3, 6);
		}
		
		if(bg.contains("Grass")){
			shape.rectLine(grass_rect.x, grass_rect.y-3, grass_rect.x + grass_rect.width, grass_rect.y-3, 6);
		}
		
		if(bg.contains("Sand")){
			shape.rectLine(sand_rect.x, sand_rect.y-3, sand_rect.x + sand_rect.width, sand_rect.y-3, 6);
		}
		
		if(bg.contains("Asphalt")){
			shape.rectLine(asphalt_rect.x, asphalt_rect.y-3, asphalt_rect.x + asphalt_rect.width, asphalt_rect.y-3, 6);
		}
		
		if(snk.contains("Snake1")){
			shape.rectLine(snake1_rect.x, snake1_rect.y-3, snake1_rect.x + snake1_rect.width, snake1_rect.y-3, 6);
		}
		
		if(snk.contains("Snake2")){
			shape.rectLine(snake2_rect.x, snake2_rect.y-3, snake2_rect.x + snake2_rect.width, snake2_rect.y-3, 6);
		}
		
		if(snk.contains("Snake3")){
			shape.rectLine(snake3_rect.x, snake3_rect.y-3, snake3_rect.x + snake3_rect.width, snake3_rect.y-3, 6);
		}
		
		if(est.contains("estop1")){
			shape.rectLine(estop1_rect.x, estop1_rect.y-3, estop1_rect.x + estop1_rect.width, estop1_rect.y-3, 6);
		}
		
		if(est.contains("estop2")){
			shape.rectLine(estop2_rect.x, estop2_rect.y-3, estop2_rect.x + estop2_rect.width, estop2_rect.y-3, 6);
		}
		
		if(est.contains("estop3")){
			shape.rectLine(estop3_rect.x, estop3_rect.y-3, estop3_rect.x + estop3_rect.width, estop3_rect.y-3, 6);
		}
		
		shape.end();
	}




	private void Update(float delta) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public boolean keyDown(int keycode) {
		
		if((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)){
			game.setScreen(new MenuScreen(game));
			System.out.println("back");
		}
				
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Gdx.input.vibrate(40);
		Vector3 position = new Vector3(screenX, screenY, 0);
		cam.unproject(position);

		if(easy_rect.contains(position.x, position.y)){
			easy = new Texture("Ustawienia/easy_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Easy");
			pref.putString("Speed", "Easy");
			pref.flush();
		}
		
		if(medium_rect.contains(position.x, position.y)){
			medium = new Texture("Ustawienia/medium_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Medium");
			pref.putString("Speed", "Medium");
			pref.flush();
		}
		
		if(hard_rect.contains(position.x, position.y)){
			hard = new Texture("Ustawienia/hard_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Hard");
			pref.putString("Speed", "Hard");
			pref.flush();
		}
		
		if(pl_rect.contains(position.x, position.y)){
			pl = new Texture("Ustawienia/pl_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Polski");
			pref.putString("Language", "Pl");
			pref.flush();
		}
		
		if(eng_rect.contains(position.x, position.y)){
			eng = new Texture("Ustawienia/eng_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Angielski");
			pref.putString("Language", "Eng");
			pref.flush();
		}
		
		if(rus_rect.contains(position.x, position.y)){
			rus = new Texture("Ustawienia/rus_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Ruski");
			pref.putString("Language", "Rus");
			pref.flush();
		}
		
		
		if(grass_rect.contains(position.x, position.y)){
			grass = new Texture("Ustawienia/grass_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Grass");
			pref.putString("Background", "Grass");
			pref.flush();
		}
		
		if(sand_rect.contains(position.x, position.y)){
			sand = new Texture("Ustawienia/sand_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Sand");
			pref.putString("Background", "Sand");
			pref.flush();
		}

		
		if(asphalt_rect.contains(position.x, position.y)){
			asphalt = new Texture("Ustawienia/asphalt_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Asphalt");
			pref.putString("Background", "Asphalt");
			pref.flush();
		}
		
		if(snake1_rect.contains(position.x, position.y)){
			snake1 = new Texture("Ustawienia/snake1_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Snake1");
			pref.putString("Snake", "Snake1");
			pref.flush();
		}
		
		
		if(snake2_rect.contains(position.x, position.y)){
			snake2 = new Texture("Ustawienia/snake2_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Snake2");
			pref.putString("Snake", "Snake2");
			pref.flush();
		}
		
		
		if(snake3_rect.contains(position.x, position.y)){
			snake3 = new Texture("Ustawienia/snake3_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("Snake3");
			pref.putString("Snake", "Snake3");
			pref.flush();
		}
		
		if(estop1_rect.contains(position.x, position.y)){
			estop1 = new Texture("Ustawienia/calc_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("estop1");
			pref.putString("Estop", "estop1");
			pref.flush();
		}
		
		if(estop2_rect.contains(position.x, position.y)){
			estop2 = new Texture("Ustawienia/calendar_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("estop2");
			pref.putString("Estop", "estop2");
			pref.flush();
		}
		
		if(estop3_rect.contains(position.x, position.y)){
			estop3 = new Texture("Ustawienia/black_pressed.png");
			Gdx.input.vibrate(40);
			System.out.println("estop3");
			pref.putString("Estop", "estop3");
			pref.flush();
		}

		
		return false;
	}




	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 position = new Vector3(screenX, screenY, 0);
		cam.unproject(position);

		if(easy_rect.contains(position.x, position.y)){
			//easy = new Texture("Ustawienia/easy.png");
		}
		
		if(medium_rect.contains(position.x, position.y)){
			//medium = new Texture("Ustawienia/medium.png");
			System.out.println("Medium");
			pref.putString("Speed", "Medium");
			pref.flush();
		}
		
		if(hard_rect.contains(position.x, position.y)){
			//hard = new Texture("Ustawienia/hard.png");
			System.out.println("Hard");
			pref.putString("Speed", "Hard");
			pref.flush();
		}
		
		if(pl_rect.contains(position.x, position.y)){
			//pl = new Texture("Ustawienia/pl.png");
			System.out.println("Polski");
			pref.putString("Language", "Pl");
			pref.flush();
		}
		
		if(eng_rect.contains(position.x, position.y)){
			//eng = new Texture("Ustawienia/eng.png");
			System.out.println("Angielski");
			pref.putString("Language", "Eng");
			pref.flush();
		}
		
		if(rus_rect.contains(position.x, position.y)){
			//rus = new Texture("Ustawienia/rus.png");
			System.out.println("Ruski");
			pref.putString("Language", "Rus");
			pref.flush();
		}
		
		easy = new Texture("Ustawienia/easy.png");
		medium = new Texture("Ustawienia/medium.png");
		hard = new Texture("Ustawienia/hard.png");
		pl = new Texture("Ustawienia/pl.png");
		eng = new Texture("Ustawienia/eng.png");
		rus = new Texture("Ustawienia/rus.png");
		grass = new Texture("Ustawienia/grass.png");
		sand = new Texture("Ustawienia/sand.png");
		asphalt = new Texture("Ustawienia/asphalt.png");
		snake1 = new Texture("Ustawienia/snake1.png");
		snake2 = new Texture("Ustawienia/snake2.png");
		snake3 = new Texture("Ustawienia/snake3.png");
		estop1 = new Texture("Ustawienia/calc.png");
		estop2 = new Texture("Ustawienia/calendar.png");
		estop3 = new Texture("Ustawienia/black.png");
		
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
