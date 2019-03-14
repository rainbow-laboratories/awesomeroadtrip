package org.rainbowlabs.awesomeroadtrip.core;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class AwesomeRoadTrip implements ApplicationListener {
	Texture texture;
	SpriteBatch batch;
	Music music;

	@Override
	public void create () {
		texture = new Texture(Gdx.files.internal("worldmap.jpg"));
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("title_theme.wav", Files.FileType.Internal));
		music.setVolume(0.5f);
		music.play();
		music.setLooping(true);
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(texture, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();

	}

	@Override
	public void pause () {
		music.pause();
	}

	@Override
	public void resume () {
		music.play();
	}

	@Override
	public void dispose () {
		music.dispose();
	}
}
