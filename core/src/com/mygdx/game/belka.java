package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class belka extends Rectangle{
	
	private Texture texture;
	
	
	public belka(Texture texture){
		
		this.texture = texture;
		this.x = x;
		this.y = y;
		
	}
	
	
	public void draw(SpriteBatch batch){

		int size_kw = Math.round(Gdx.graphics.getWidth()/15);
		int ile_wezy = Gdx.graphics.getHeight()/size_kw;
		int reszta = Gdx.graphics.getHeight() - (ile_wezy * size_kw);
		
		batch.draw(texture, x, y,  Gdx.graphics.getWidth(), size_kw + reszta);
	}
	

}
