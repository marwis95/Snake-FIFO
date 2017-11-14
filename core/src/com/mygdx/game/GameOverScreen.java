package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameOverScreen extends AbstractScreen implements InputProcessor{

	public Preferences pref;
	
	private Texture background;
	
	private Texture menu;
	private Texture nowa_gra;
	
	private Rectangle menu_rect;
	private Rectangle nowa_gra_rect;
	
	int fontSize = Math.round(Gdx.graphics.getWidth()/150);
	
	boolean rekord = false;
	boolean rekord_hard = false;
	boolean rekord_medium = false;
	boolean rekord_easy = false;
	
	public GameOverScreen(MyGdxGame game) {
		super(game);
		pref = Gdx.app.getPreferences("Snake");
		
		menu_rect = new Rectangle(
				0,
				0,
				Math.round(Gdx.graphics.getWidth()/2.5),
				Gdx.graphics.getWidth()/15
				);
		
		nowa_gra_rect = new Rectangle(
				Gdx.graphics.getWidth()-Math.round(Gdx.graphics.getWidth()/2.5),
				0,
				Math.round(Gdx.graphics.getWidth()/2.5),
				Gdx.graphics.getWidth()/15
				);
		
		LoadData();
		Init();
	}
	
	private void Init(){
        Gdx.input.setInputProcessor(this);
	}
	
	private void LoadData(){
	
		menu = new Texture("GameOver/menu.png");
		
		if(pref.getString("Language").contains("Pl")){
		nowa_gra = new Texture("MainMenu/PL/Nowa_gra.png");
		}
		
		if(pref.getString("Language").contains("Eng")){
		nowa_gra = new Texture("MainMenu/ENG/Nowa_gra.png");
		}
		
		if(pref.getString("Language").contains("Rus")){
		nowa_gra = new Texture("MainMenu/RUS/Nowa_gra.png");
		}
		
		
		if(pref.getString("Speed").contains("Easy")){
				//if(pref.getInteger("pkt_last") < pref.getInteger("pkt_easy")){
				if(Integer.parseInt(pref.getString("pkt_last")) < Integer.parseInt(pref.getString("pkt_easy"))){

					
					if(pref.getString("Language").contains("Pl")){
						background = new Texture("GameOver/win_pl.png");
						}
						
						if(pref.getString("Language").contains("Eng")){
						background = new Texture("GameOver/win_eng.png");
						}
						
						if(pref.getString("Language").contains("Rus")){
						background = new Texture("GameOver/win_rus.png");
						}
						
						rekord = true;
						rekord_easy = true;
						
				}else background = new Texture("GameOver/game_over.png");
			
		}
		
		///////////////////////////////////////////////EASY
		
		if(pref.getString("Speed").contains("Medium")){
			
			if(Integer.parseInt(pref.getString("pkt_last")) < Integer.parseInt(pref.getString("pkt_medium"))){
				
				if(pref.getString("Language").contains("Pl")){
					background = new Texture("GameOver/win_pl.png");
					}
					
					if(pref.getString("Language").contains("Eng")){
					background = new Texture("GameOver/win_eng.png");
					}
					
					if(pref.getString("Language").contains("Rus")){
					background = new Texture("GameOver/win_rus.png");
					}
				
					rekord = true;
					rekord_medium = true;
					
			}else background = new Texture("GameOver/game_over.png");
			
		}
		
		//////////////////////////////////////////////MEDIUM
		
		if(pref.getString("Speed").contains("Hard")){
			
			if(Integer.parseInt(pref.getString("pkt_last")) < Integer.parseInt(pref.getString("pkt_hard"))){
				
				if(pref.getString("Language").contains("Pl")){
					background = new Texture("GameOver/win_pl.png");
					}
					
					if(pref.getString("Language").contains("Eng")){
					background = new Texture("GameOver/win_eng.png");
					}
					
					if(pref.getString("Language").contains("Rus")){
					background = new Texture("GameOver/win_rus.png");
					}
				
					rekord = true;
					rekord_hard = true;
					
			}else background = new Texture("GameOver/game_over.png");
			
		}
		
		///////////////////////////////////////////HARD

		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		Update(delta);
		
		batch.begin();
		
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		
		//Fonts.getFont(5).draw(batch, "Last: " + pref.getString("pkt_last"), 0, 500);
		
		Fonts.getFont(fontSize).setColor(255/255f, 255/255f, 255/255f, 1f);
		
		if (rekord == false){
			
			if(pref.getString("Language").contains("Pl")){
				Fonts.getFont(fontSize).draw(batch, "Twój wynik to: " + pref.getString("last_score"), 0, Gdx.graphics.getHeight()/4 + Math.round(Fonts.getFont(fontSize).getBounds("abc").height * 1.5));
				Fonts.getFont(fontSize).draw(batch, "Spróbuj jeszcze raz :)", 0, Gdx.graphics.getHeight()/4);
				}
			
			if(pref.getString("Language").contains("Eng")){
				Fonts.getFont(fontSize).draw(batch, "Your score: " + pref.getString("last_score"), 0, Gdx.graphics.getHeight()/4 + Math.round(Fonts.getFont(fontSize).getBounds("abc").height * 1.5));
				Fonts.getFont(fontSize).draw(batch, "Try again :)", 0, Gdx.graphics.getHeight()/4);
				}
			
			if(pref.getString("Language").contains("Rus")){
				Fonts.getFont(fontSize).draw(batch, "Вы набрали: " + pref.getString("last_score") + " очков", 0, Gdx.graphics.getHeight()/4 + Math.round(Fonts.getFont(fontSize).getBounds("abc").height * 1.5));
				Fonts.getFont(fontSize).draw(batch, "Давайте еще раз :)", 0, Gdx.graphics.getHeight()/4);
				}
		}else{
			if(pref.getString("Language").contains("Pl")){
				Fonts.getFont(fontSize).draw(batch, "Nowy rekord: " + pref.getString("last_score"), 0, Gdx.graphics.getHeight()/4 + Math.round(Fonts.getFont(fontSize).getBounds("abc").height * 1.5));

				}
			
			if(pref.getString("Language").contains("Eng")){
				Fonts.getFont(fontSize).draw(batch, "New high score: " + pref.getString("last_score"), 0, Gdx.graphics.getHeight()/4 + Math.round(Fonts.getFont(fontSize).getBounds("abc").height * 1.5));

				}
			
			if(pref.getString("Language").contains("Rus")){
				Fonts.getFont(fontSize).draw(batch, "Новый рекорд: " + pref.getString("last_score") + " очков", 0, Gdx.graphics.getHeight()/4 + Math.round(Fonts.getFont(fontSize).getBounds("abc").height * 1.5));

				}
			
		}
		
		
		batch.draw(menu, menu_rect.x, menu_rect.y, menu_rect.width, menu_rect.height);
		
		batch.draw(nowa_gra, nowa_gra_rect.x, nowa_gra_rect.y, nowa_gra_rect.width, nowa_gra_rect.height);
		
		batch.end();
		
		shape.begin();
		
		//shape.rect(menu_rect.x, menu_rect.y, menu_rect.width, menu_rect.height);
		//shape.rect(nowa_gra_rect.x, nowa_gra_rect.y, nowa_gra_rect.width, nowa_gra_rect.height);
		
		shape.end();
		
	}
	
	private void Update(float delta) {
		// TODO Auto-generated method stub
		
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 position = new Vector3(screenX, screenY, 0);
		cam.unproject(position);
		
		if(menu_rect.contains(position.x, position.y)){
			Gdx.input.vibrate(40);
			menu = new Texture("GameOver/menu_pressed.png");
			
		}
		
		if(nowa_gra_rect.contains(position.x, position.y)){
			Gdx.input.vibrate(40);
			if(pref.getString("Language").contains("Pl")){
				nowa_gra = new Texture("MainMenu/PL/Nowa_gra_pressed.png");
			}
			
			if(pref.getString("Language").contains("Eng")){
				nowa_gra = new Texture("MainMenu/ENG/Nowa_gra_pressed.png");
			}
			
			if(pref.getString("Language").contains("Rus")){
				nowa_gra = new Texture("MainMenu/RUS/Nowa_gra_pressed.png");
			}
		}

		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		
		Vector3 position = new Vector3(screenX, screenY, 0);
		cam.unproject(position);
		
		if(menu_rect.contains(position.x, position.y)){
		game.setScreen(new MenuScreen(game));
		}
		
		if(nowa_gra_rect.contains(position.x, position.y)){
			game.setScreen(new GameScreen(game));
		}
		
		menu = new Texture("GameOver/menu.png");
		if(pref.getString("Language").contains("Pl")){
			nowa_gra = new Texture("MainMenu/PL/Nowa_gra.png");
		}
		
		if(pref.getString("Language").contains("Eng")){
			nowa_gra = new Texture("MainMenu/ENG/Nowa_gra.png");
		}
		
		if(pref.getString("Language").contains("Rus")){
			nowa_gra = new Texture("MainMenu/RUS/Nowa_gra.png");
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
