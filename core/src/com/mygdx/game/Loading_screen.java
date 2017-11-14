package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Loading_screen extends AbstractScreen{


	public Loading_screen(MyGdxGame game) {
		super(game);

		
		LoadData();
		Init();
	}	
	
	private void LoadData(){
		
	}
	
	private void Init(){
		
	}
	
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		Update(delta);
		
		batch.begin();
		
		Fonts.getFont(5).draw(batch, "LOADING...", 0, 500);
		
		
		batch.end();
		
		game.setScreen(new GameScreen(game));
		
	}

	private void Update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}
