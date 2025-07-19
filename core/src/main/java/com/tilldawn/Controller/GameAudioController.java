package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;


public class GameAudioController {
    private static GameAudioController instance;

    private Music currentMusic;
    private HashMap<String, Sound> sounds = new HashMap<>();

    private GameAudioController() {
    }

    public static GameAudioController getInstance() {
        if (instance == null) {
            instance = new GameAudioController();
        }
        return instance;
    }

    public void playMusic(String path, boolean loop, float volume) {
        if (currentMusic != null) currentMusic.stop();
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        currentMusic.setLooping(loop);
        currentMusic.setVolume(volume);
        currentMusic.play();
    }

    public void stopMusic() {
        if (currentMusic != null) currentMusic.stop();
    }

    public void pauseMusic() {
        if (currentMusic != null) currentMusic.pause();
    }

    public void resumeMusic() {
        if (currentMusic != null) currentMusic.play();
    }

    public void playSound(String path) {
        Sound sfx = sounds.get(path);
        if (sfx == null) {
            sfx = Gdx.audio.newSound(Gdx.files.internal(path));
            sounds.put(path, sfx);
        }
        sfx.play();
    }

    public void dispose() {
        if (currentMusic != null) currentMusic.dispose();
        for (Sound s : sounds.values()) {
            s.dispose();
        }
    }
}
