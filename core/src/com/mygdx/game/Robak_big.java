package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Robak_big extends Rectangle{
	
	private Texture texture;
	
	public Robak_big(Texture texture){
		this.x = x;
		this.y = y;
		this.texture = texture;
	}

	public void draw(SpriteBatch batch){
		//batch.draw(texture,x,y);
		batch.draw(texture, x, y,  (Math.round(Gdx.graphics.getWidth()/15)) * 2, (Math.round(Gdx.graphics.getWidth()/15)) * 2);
	}
	
	public Texture getTexture() {
		return texture;
	}
}
