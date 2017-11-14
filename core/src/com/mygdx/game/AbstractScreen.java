package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class AbstractScreen implements Screen {

	ShapeRenderer shape;
	OrthographicCamera cam;
	SpriteBatch batch = new SpriteBatch();
	MyGdxGame game;
	 public AbstractScreen(MyGdxGame game) {
		 this.game = game;
		shape = new ShapeRenderer() {{
			setAutoShapeType(true);
			setColor(Color.BLACK);
		}};
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) {{
			setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}};
		
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		shape.setProjectionMatrix(cam.combined);
		
		batch.begin();

		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}


}
