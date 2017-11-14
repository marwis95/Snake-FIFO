package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Robak extends Rectangle{
	
	private Texture texture;
	
	public Robak(Texture texture){
		this.texture = texture;
		this.x = x;
		this.y = y;
	}
	
	public void draw(SpriteBatch batch){
		//batch.draw(texture,x,y);
		batch.draw(texture, x, y,  Math.round(Gdx.graphics.getWidth()/15), Math.round(Gdx.graphics.getWidth()/15));
	}
	

}
