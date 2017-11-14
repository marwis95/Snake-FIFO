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

public class Wyniki extends AbstractScreen implements InputProcessor {

	public Preferences pref;
	
	int fontSize = Math.round(Gdx.graphics.getWidth()/150);
	private Texture cup;
	private Texture background;
	Rectangle rekordy_rect;
	
	private Texture rekordy_pl;
	private Texture rekordy_eng;
	private Texture rekordy_rus;
	
	

	public Wyniki(MyGdxGame game) {
		super(game);

		LoadData();
		Init();
	}


	private void Init() {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
		cup = new Texture("cup.png");
		background = new Texture("tlo_snake.png");
		
		rekordy_pl = new Texture("Rekordy_pl.png");
		rekordy_eng = new Texture("Rekordy_eng.png");
		rekordy_rus = new Texture("Rekordy_rus.png");
		
		rekordy_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round(Gdx.graphics.getHeight() * 0.1), 
				Gdx.graphics.getWidth(), 
				Math.round(Gdx.graphics.getHeight() * 0.1)
				);
	}

	private void LoadData() {
		pref = Gdx.app.getPreferences("Snake");
		String rekord_pkt = pref.getString("maxPkt", "");
		
		if(rekord_pkt == ""){
			pref.putString("maxPkt", "0");
			pref.flush();
		}
		
		String rekord_size = pref.getString("maxSize", "");
		
		if(rekord_size == ""){
			pref.putString("maxSize", "0");
			pref.flush();
		}
		
		String rekord_time = pref.getString("maxTime", "");
		
		if(rekord_time == ""){
			pref.putString("maxTime", "0");
			pref.flush();
		}
		
	}

   
    
    
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		Update(delta);
		
		batch.begin();
		
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		batch.draw(cup, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - rekordy_rect.height);
		
		if(pref.getString("Language").contains("Pl")){
		batch.draw(rekordy_pl, rekordy_rect.x, rekordy_rect.y, rekordy_rect.width, rekordy_rect.height);
		}
		
		if(pref.getString("Language").contains("Eng")){
		batch.draw(rekordy_eng, rekordy_rect.x, rekordy_rect.y, rekordy_rect.width, rekordy_rect.height);
		}
		
		if(pref.getString("Language").contains("Rus")){
		batch.draw(rekordy_rus, rekordy_rect.x, rekordy_rect.y, rekordy_rect.width, rekordy_rect.height);
		}
		
//		Fonts.getFont(5).draw(batch, "Rekordy", 100, 700);
//		Fonts.getFont(fontSize).draw(batch, "Najwiecej pkt: " + pref.getString("maxPkt"), 0, 600);
//		Fonts.getFont(fontSize).draw(batch, "Najdlu¿szy waz: " + pref.getString("maxSize"), 0, 500);
//		Fonts.getFont(fontSize).draw(batch, "Najdlu¿sza gra: " + pref.getString("maxTime"), 0, 400);
		
		
		Fonts.getFont(fontSize).setColor(13/255f, 88/255f, 225/255f, 1f);
	
		Fonts.getFont(fontSize).draw(batch, "Easy: " + pref.getString("pkt_easy"), Gdx.graphics.getWidth()/2 - Fonts.getFont(fontSize).getBounds("Medium:123").width/2, Gdx.graphics.getHeight() - Math.round(rekordy_rect.height*2));
		Fonts.getFont(fontSize).draw(batch, "Medium: " + pref.getString("pkt_medium"), Gdx.graphics.getWidth()/2 - Fonts.getFont(fontSize).getBounds("Medium:123").width/2, Gdx.graphics.getHeight() - Math.round(rekordy_rect.height*2.5));
		Fonts.getFont(fontSize).draw(batch, "Hard: " + pref.getString("pkt_hard"), Gdx.graphics.getWidth()/2 - Fonts.getFont(fontSize).getBounds("Medium:123").width/2, Gdx.graphics.getHeight() - Math.round(rekordy_rect.height*3));
	
		
		batch.end();
		
		
		shape.begin();
		//shape.rect(rekordy_rect.x, rekordy_rect.y, rekordy_rect.width, rekordy_rect.height);
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
