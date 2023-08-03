package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.main.Backgrounds;
import com.mygdx.game.main.Main;
import com.mygdx.game.main.units.BaseCharacter;

import java.util.ArrayList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Music music;
	Main game;
	boolean play;
	
	@Override
	public void create () {
		play = true;
		game = new Main();
		game.main();

		batch = new SpriteBatch();
		background = new Texture("backgrounds/" +
				Backgrounds.values()[new Random().nextInt(Backgrounds.values().length)] + ".png");
		music = Gdx.audio.newMusic(
				Gdx.files.internal("music/" + (new Random().nextInt(1,5)) + ".mp3"));
		music.setLooping(true);
		music.setVolume(0.25f);
		music.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		int kx = Gdx.graphics.getWidth()/11;
		int ky = Gdx.graphics.getHeight()/14;
		ArrayList<BaseCharacter> characters = new ArrayList<>(game.allTeam);
		characters.sort((first, second) -> second.position.y - first.position.y);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (BaseCharacter character: characters) {
			if (!character.alive) continue;
			if (character.leftSide) {
				batch.draw(character.image, character.position.x * kx, character.position.y * ky);
			}
			else {
				batch.draw(character.image, character.position.x * kx + character.image.getWidth(),
						character.position.y * ky, -character.image.getWidth(), character.image.getHeight());
			}
		}
		batch.end();

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && play){
			if (game.run()) {
				play = false;
				Gdx.graphics.setTitle("Game over!");
				music.stop();
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		music.dispose();
		for (BaseCharacter character: game.allTeam) {
			character.image.dispose();
		}
	}
}
