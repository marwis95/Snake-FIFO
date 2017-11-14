package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		Fonts.sizes = new int[]{10,20,30,40,50,60,70,80};
		Fonts.helperInit();
		setScreen(new MenuScreen(this));
	}

}
