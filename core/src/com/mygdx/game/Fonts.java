package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {
	
	protected static int [] sizes = new int[]{5,10};
	protected static String file = "arial.ttf";
	public static BitmapFont [] fonts;

	
	public static void helperInit() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		
		fonts = new BitmapFont[sizes.length];
		
		param.characters += "ąężźćłśĄĘŻŹĆŁŚ";
		param.characters += "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
		
		for(int i = 0; i < sizes.length; i++) {
			param.size = sizes[i];
			fonts[i] = generator.generateFont(param);
			fonts[i].getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
	
	public static BitmapFont getFont(int abstractSize) {
		if(abstractSize < fonts.length)
			return fonts[ abstractSize ];
		else
			return fonts[ fonts.length - 1 ];
	}
}
