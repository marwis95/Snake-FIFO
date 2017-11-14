package com.mygdx.game;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;




public class MenuScreen extends AbstractScreen implements InputProcessor {
	Rectangle start_rect;
	Rectangle wyniki_rect;
	Rectangle ustawienia_rect;
	Rectangle info_rect;
	Rectangle wyjscie_rect;
	Rectangle snake_2015_rect;
	int fontSize = Math.round(Gdx.graphics.getWidth()/150);
	public Preferences pref;
	String speed;
	String temp_speed;
	String language;

	private Texture background;
	private Texture snake_2015;
	private Texture nowa_gra,  ustawienia, rekordy, wyjscie, informacje;
	private Texture nowa_gra_eng,  ustawienia_eng, rekordy_eng, wyjscie_eng, informacje_eng;
	private Texture nowa_gra_rus,  ustawienia_rus, rekordy_rus, wyjscie_rus, informacje_rus;
	
	 public MenuScreen(final MyGdxGame game) {
		super(game);

		Gdx.input.setInputProcessor(this);
	
//		TextBounds bounds = Fonts.bigFont.getBounds("Start");
//		TextBounds bounds_wyniki = Fonts.bigFont.getBounds("Rekordy");
//		TextBounds bounds_ustawienia = Fonts.bigFont.getBounds("Ustawienia");
//		TextBounds bounds_info = Fonts.bigFont.getBounds("Info");
//		TextBounds bounds_wyjscie = Fonts.bigFont.getBounds("Wyjscie");
		
		background = new Texture("tlo_snake.png");
		
		nowa_gra = new Texture("MainMenu/PL/Nowa_gra.png");
		rekordy = new Texture("MainMenu/PL/Rekordy.png");
		ustawienia = new Texture("MainMenu/PL/Ustawienia.png");
		informacje = new Texture("MainMenu/PL/Informacje.png");
		wyjscie = new Texture("MainMenu/PL/Wyjscie.png");
		
		nowa_gra_eng = new Texture("MainMenu/ENG/Nowa_gra.png");
		rekordy_eng = new Texture("MainMenu/ENG/Rekordy.png");
		ustawienia_eng = new Texture("MainMenu/ENG/Ustawienia.png");
		informacje_eng = new Texture("MainMenu/ENG/Informacje.png");
		wyjscie_eng = new Texture("MainMenu/ENG/Wyjscie.png");
		
		nowa_gra_rus = new Texture("MainMenu/RUS/Nowa_gra.png");
		rekordy_rus = new Texture("MainMenu/RUS/Rekordy.png");
		ustawienia_rus = new Texture("MainMenu/RUS/Ustawienia.png");
		informacje_rus = new Texture("MainMenu/RUS/Informacje.png");
		wyjscie_rus = new Texture("MainMenu/RUS/Wyjscie.png");

		snake_2015 = new Texture("snake_2015.png");
		
//		nowa_gra_pressed = new Texture("MainMenu/PL/Nowa_gra_pressed.png");
//		rekordy_pressed = new Texture("MainMenu/PL/Rekordy_pressed.png");
//		ustawienia_pressed = new Texture("MainMenu/PL/Ustawienia_pressed.png");
//		informacje_pressed = new Texture("MainMenu/PL/Informacje_pressed.png");
//		wyjscie_pressed = new Texture("MainMenu/PL/Wyjscie_pressed.png");
		
		pref = Gdx.app.getPreferences("Snake");
		String speed = pref.getString("Speed", "");
		String language = pref.getString("Language", "");
		String background = pref.getString("Background", "");
		String snake = pref.getString("Snake", "");
		String estop = pref.getString("Estop", "");
		
		if(pref.getString("pkt_last") == ""){
			pref.putString("pkt_last", "0");
		}
		
		if(pref.getString("pkt_hard") == ""){
			pref.putString("pkt_hard", "0");
		}
		
		if(pref.getString("pkt_medium") == ""){
			pref.putString("pkt_medium", "0");
		}
		
		if(pref.getString("pkt_easy") == ""){
			pref.putString("pkt_easy", "0");
		}
		
		System.out.println("Po zaladowaniu: " + speed);
		

		
		if(speed == ""){
			pref.putString("Speed", "Medium");
			pref.flush();
		}
		
		if(language == ""){
			pref.putString("Language", "Eng");
			pref.flush();
		}
		
		if(background == ""){
			pref.putString("Background", "Grass");
			pref.flush();
		}
		
		if(snake == ""){
			pref.putString("Snake", "snake1");
			pref.flush();
		}
		
		if(estop == ""){
			pref.putString("Estop", "estop1");
			pref.flush();
		}
		
		System.out.println(language);
	
//		 start_rect =  new Rectangle((480 - 250)/2, 700, 250, Fonts.getFont(fontSize).getLineHeight());
//		 wyniki_rect = new Rectangle((480 - 250)/2, 600, 250, Fonts.getFont(fontSize).getLineHeight());
//		 ustawienia_rect = new Rectangle((480 - 250)/2, 500, 250, Fonts.getFont(fontSize).getLineHeight());
//		 info_rect = new Rectangle((480 - 250)/2, 400, 250, Fonts.getFont(fontSize).getLineHeight());
//		 wyjscie_rect = new Rectangle((480 - 250)/2, 300, 250, Fonts.getFont(fontSize).getLineHeight());
		
		 start_rect = new Rectangle(
				 (Gdx.graphics.getWidth() - Math.round(Gdx.graphics.getWidth()*0.8))/2,
				 Gdx.graphics.getHeight() - (Math.round((Gdx.graphics.getWidth()*0.16) * 3)),
				 Math.round(Gdx.graphics.getWidth()*0.8),
				 Math.round(Gdx.graphics.getWidth()*0.16)
				 );
		 
		 wyniki_rect = new Rectangle(
				 (Gdx.graphics.getWidth() - Math.round(Gdx.graphics.getWidth()*0.8))/2,
				 Gdx.graphics.getHeight() - (Math.round((Gdx.graphics.getWidth()*0.16) * 4.5)),
				 Math.round(Gdx.graphics.getWidth()*0.8),
				 Math.round(Gdx.graphics.getWidth()*0.16)
				 );
		 
		 ustawienia_rect = new Rectangle(
				 (Gdx.graphics.getWidth() - Math.round(Gdx.graphics.getWidth()*0.8))/2,
				 Gdx.graphics.getHeight() - (Math.round((Gdx.graphics.getWidth()*0.16) * 6)),
				 Math.round(Gdx.graphics.getWidth()*0.8),
				 Math.round(Gdx.graphics.getWidth()*0.16)
				 );
		 
		 info_rect = new Rectangle(
				 (Gdx.graphics.getWidth() - Math.round(Gdx.graphics.getWidth()*0.8))/2,
				 Gdx.graphics.getHeight() - (Math.round((Gdx.graphics.getWidth()*0.16) * 7.5)),
				 Math.round(Gdx.graphics.getWidth()*0.8),
				 Math.round(Gdx.graphics.getWidth()*0.16)
				 );
		 
		 wyjscie_rect = new Rectangle(
				 (Gdx.graphics.getWidth() - Math.round(Gdx.graphics.getWidth()*0.8))/2,
				 Gdx.graphics.getHeight() - (Math.round((Gdx.graphics.getWidth()*0.16) * 9)),
				 Math.round(Gdx.graphics.getWidth()*0.8),
				 Math.round(Gdx.graphics.getWidth()*0.16)
				 );
		 
		snake_2015_rect = new Rectangle(
				0,
				Gdx.graphics.getHeight() - Math.round(Gdx.graphics.getHeight() * 0.1), 
				Gdx.graphics.getWidth(), 
				Math.round(Gdx.graphics.getHeight() * 0.1)
				);
		
	 }

	 
	private void create() {

		
	}

	@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(1, 0, 0, 1);
			super.render(delta);
			

			batch.begin();
			batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			
			batch.draw(snake_2015, snake_2015_rect.x, snake_2015_rect.y, snake_2015_rect.width, snake_2015_rect.height);
			
			if(pref.getString("Language").contains("Pl")){
			batch.draw(nowa_gra, start_rect.x, start_rect.y, start_rect.width, start_rect.height);
			batch.draw(rekordy,  wyniki_rect.x, wyniki_rect.y, wyniki_rect.width, wyniki_rect.height);
			batch.draw(ustawienia, ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			batch.draw(informacje, info_rect.x, info_rect.y, info_rect.width, info_rect.height);
			batch.draw(wyjscie, wyjscie_rect.x, wyjscie_rect.y, wyjscie_rect.width, wyjscie_rect.height);
			}
			
			if(pref.getString("Language").contains("Eng")){
			batch.draw(nowa_gra_eng, start_rect.x, start_rect.y, start_rect.width, start_rect.height);
			batch.draw(rekordy_eng,  wyniki_rect.x, wyniki_rect.y, wyniki_rect.width, wyniki_rect.height);
			batch.draw(ustawienia_eng, ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			batch.draw(informacje_eng, info_rect.x, info_rect.y, info_rect.width, info_rect.height);
			batch.draw(wyjscie_eng, wyjscie_rect.x, wyjscie_rect.y, wyjscie_rect.width, wyjscie_rect.height);
			}
			
			if(pref.getString("Language").contains("Rus")){
			batch.draw(nowa_gra_rus, start_rect.x, start_rect.y, start_rect.width, start_rect.height);
			batch.draw(rekordy_rus,  wyniki_rect.x, wyniki_rect.y, wyniki_rect.width, wyniki_rect.height);
			batch.draw(ustawienia_rus, ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			batch.draw(informacje_rus, info_rect.x, info_rect.y, info_rect.width, info_rect.height);
			batch.draw(wyjscie_rus, wyjscie_rect.x, wyjscie_rect.y, wyjscie_rect.width, wyjscie_rect.height);
			}
			
//			if(pref.getString("Language").contains("Pl")){
//			Fonts.getFont(fontSize).draw(batch, "Nowa gra", start_rect.x, start_rect.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Rekordy", wyniki_rect.x, wyniki_rect.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Ustawienia", ustawienia_rect.x, ustawienia_rect.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Info", info_rect.x, info_rect.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Wyjście", wyjscie_rect.x, wyjscie_rect.y + Fonts.getFont(fontSize).getLineHeight());
//			}
			
//			if(pref.getString("Language").contains("Eng")){
//			Fonts.getFont(fontSize).draw(batch, "New game", start.x, start.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Records", wyniki.x, wyniki.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Settings", ustawienia.x, ustawienia.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Info", info.x, info.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Exit", wyjscie.x, wyjscie.y + Fonts.getFont(fontSize).getLineHeight());
//			}
//			
//			if(pref.getString("Language").contains("Rus")){
//			Fonts.getFont(fontSize).draw(batch, "Новая игра", start.x, start.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Рекорд", wyniki.x, wyniki.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Настройки", ustawienia.x, ustawienia.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Информация", info.x, info.y + Fonts.getFont(fontSize).getLineHeight());
//			
//			Fonts.getFont(fontSize).draw(batch, "Выход", wyjscie.x, wyjscie.y + Fonts.getFont(fontSize).getLineHeight());
//			}
			
			//Fonts.getFont(fontSize).draw(batch, "Блядь ąąąą", wyjscie.x, wyjscie.y + Fonts.getFont(fontSize).getLineHeight()-80);
			
			
			batch.end();
			
			shape.begin();
			//shape.rect(start_rect.x, start_rect.y, start_rect.width, start_rect.height);
			//shape.rect(wyniki_rect.x, wyniki_rect.y, wyniki_rect.width, wyniki_rect.height);
			//shape.rect(ustawienia_rect.x, ustawienia_rect.y, ustawienia_rect.width, ustawienia_rect.height);
			//shape.rect(info_rect.x, info_rect.y, info_rect.width, info_rect.height);
			//shape.rect(wyjscie_rect.x, wyjscie_rect.y, wyjscie_rect.width, wyjscie_rect.height);
			shape.end();
			
	 }




	@Override
	public boolean keyDown(int keycode) {		
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
		
		if(pref.getString("Language").contains("Pl")){
		if(start_rect.contains(position.x, position.y)){
			nowa_gra = new Texture("MainMenu/PL/Nowa_gra_pressed.png");
			Gdx.input.vibrate(40);
		}
		
		if(wyniki_rect.contains(position.x, position.y)){
			rekordy = new Texture("MainMenu/PL/Rekordy_pressed.png");
			Gdx.input.vibrate(40);
		}
		
		if(ustawienia_rect.contains(position.x, position.y)){
			ustawienia = new Texture("MainMenu/PL/Ustawienia_pressed.png");
			Gdx.input.vibrate(40);
		}
			
		if(info_rect.contains(position.x, position.y)){
			informacje = new Texture("MainMenu/PL/Informacje_pressed.png");
			Gdx.input.vibrate(40);
		}
			
		if(wyjscie_rect.contains(position.x, position.y)){
			wyjscie = new Texture("MainMenu/PL/Wyjscie_pressed.png");
			Gdx.input.vibrate(40);
		}
		}
		
		
		if(pref.getString("Language").contains("Eng")){
		if(start_rect.contains(position.x, position.y)){
			nowa_gra_eng = new Texture("MainMenu/ENG/Nowa_gra_pressed.png");
			Gdx.input.vibrate(40);
		}
		
		if(wyniki_rect.contains(position.x, position.y)){
			rekordy_eng = new Texture("MainMenu/ENG/Rekordy_pressed.png");
			Gdx.input.vibrate(40);
		}
		
		if(ustawienia_rect.contains(position.x, position.y)){
			ustawienia_eng = new Texture("MainMenu/ENG/Ustawienia_pressed.png");
			Gdx.input.vibrate(40);
		}
			
		if(info_rect.contains(position.x, position.y)){
			informacje_eng = new Texture("MainMenu/ENG/Informacje_pressed.png");
			Gdx.input.vibrate(40);
		}
			
		if(wyjscie_rect.contains(position.x, position.y)){
			wyjscie_eng = new Texture("MainMenu/ENG/Wyjscie_pressed.png");
			Gdx.input.vibrate(40);
		}
		}
		
		
		if(pref.getString("Language").contains("Rus")){
		if(start_rect.contains(position.x, position.y)){
			nowa_gra_rus = new Texture("MainMenu/RUS/Nowa_gra_pressed.png");
			Gdx.input.vibrate(40);
		}
		
		if(wyniki_rect.contains(position.x, position.y)){
			rekordy_rus = new Texture("MainMenu/RUS/Rekordy_pressed.png");
			Gdx.input.vibrate(40);
		}
		
		if(ustawienia_rect.contains(position.x, position.y)){
			ustawienia_rus = new Texture("MainMenu/RUS/Ustawienia_pressed.png");
			Gdx.input.vibrate(40);
		}
			
		if(info_rect.contains(position.x, position.y)){
			informacje_rus = new Texture("MainMenu/RUS/Informacje_pressed.png");
			Gdx.input.vibrate(40);
		}
			
		if(wyjscie_rect.contains(position.x, position.y)){
			wyjscie_rus = new Texture("MainMenu/RUS/Wyjscie_pressed.png");
			Gdx.input.vibrate(40);
		}
		}
		
		
		return false;
	}




	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 position = new Vector3(screenX, screenY, 0);
		cam.unproject(position);
		
		if(pref.getString("Language").contains("Pl")){
		if(start_rect.contains(position.x, position.y)){
			game.setScreen(new Loading_screen(game));
		}
		
		if(wyniki_rect.contains(position.x, position.y)){
			game.setScreen(new Wyniki(game));
		}
		
		if(ustawienia_rect.contains(position.x, position.y)){
			game.setScreen(new Ustawienia(game));
		}
		
		if(info_rect.contains(position.x, position.y)){
			System.out.println("Info");
		}
		
		if(wyjscie_rect.contains(position.x, position.y)){
			Gdx.app.exit();
		}
		
		nowa_gra = new Texture("MainMenu/PL/Nowa_gra.png");
		rekordy = new Texture("MainMenu/PL/Rekordy.png");
		ustawienia = new Texture("MainMenu/PL/Ustawienia.png");
		informacje = new Texture("MainMenu/PL/Informacje.png");
		wyjscie = new Texture("MainMenu/PL/Wyjscie.png");
		
		}
		
		
		if(pref.getString("Language").contains("Eng")){
		if(start_rect.contains(position.x, position.y)){
			game.setScreen(new Loading_screen(game));
		}
		
		if(wyniki_rect.contains(position.x, position.y)){
			game.setScreen(new Wyniki(game));
		}
		
		if(ustawienia_rect.contains(position.x, position.y)){
			game.setScreen(new Ustawienia(game));
		}
		
		if(info_rect.contains(position.x, position.y)){
			System.out.println("Info");
		}
		
		if(wyjscie_rect.contains(position.x, position.y)){
			Gdx.app.exit();
		}
		
		nowa_gra_eng = new Texture("MainMenu/ENG/Nowa_gra.png");
		rekordy_eng = new Texture("MainMenu/ENG/Rekordy.png");
		ustawienia_eng = new Texture("MainMenu/ENG/Ustawienia.png");
		informacje_eng = new Texture("MainMenu/ENG/Informacje.png");
		wyjscie_eng = new Texture("MainMenu/ENG/Wyjscie.png");
		
		}
		
		if(pref.getString("Language").contains("Rus")){
		if(start_rect.contains(position.x, position.y)){
			game.setScreen(new Loading_screen(game));
		}
		
		if(wyniki_rect.contains(position.x, position.y)){
			game.setScreen(new Wyniki(game));
		}
		
		if(ustawienia_rect.contains(position.x, position.y)){
			game.setScreen(new Ustawienia(game));
		}
		
		if(info_rect.contains(position.x, position.y)){
			System.out.println("Info");
		}
		
		if(wyjscie_rect.contains(position.x, position.y)){
			Gdx.app.exit();
		}
		
		nowa_gra_rus = new Texture("MainMenu/RUS/Nowa_gra.png");
		rekordy_rus = new Texture("MainMenu/RUS/Rekordy.png");
		ustawienia_rus = new Texture("MainMenu/RUS/Ustawienia.png");
		informacje_rus = new Texture("MainMenu/RUS/Informacje.png");
		wyjscie_rus = new Texture("MainMenu/RUS/Wyjscie.png");
		
		}
		
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
