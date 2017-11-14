package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class button extends Rectangle{
	
	private Texture texture;
	
	public button(Texture texture){
		this.texture = texture;
		this.x = x;
		this.y = y;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(texture,x,y);
	}

}
