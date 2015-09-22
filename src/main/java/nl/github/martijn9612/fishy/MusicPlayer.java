package nl.github.martijn9612.fishy;

import java.util.HashMap;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * MusicPlayer class, responsible for loading and playing sound files.
 * @author Leon Noordam
 */
public class MusicPlayer {
	
	private static MusicPlayer musicPlayerInstance = null;
	private HashMap<String,Sound> soundResources = new HashMap<>();
	
	// The sound resources are defined here so they can be preloaded.
	public static final String BITE_SOUND_1 = "resources/sounds/bite1.ogg";
	public static final String BITE_SOUND_2 = "resources/sounds/bite2.ogg";
	public static final String BITE_SOUND_3 = "resources/sounds/bite3.ogg";
	public static final String BG_MUSIC_MENU = "resources/sounds/bg-menu-music.ogg";
	public static final String BG_MUSIC_LEVEL = "resources/sounds/bg-play-music.ogg";
	
	/**
	 * The MusicPlayer class is a singleton class, so it will always return the same instance.
	 * @return MusicPlayer instance
	 */
	public static MusicPlayer getInstance() {
		if(musicPlayerInstance == null) {
			musicPlayerInstance = new MusicPlayer();
		}
		return musicPlayerInstance;
	}
	
	/**
	 * The constructor will preload the defined sounds for better performance.
	 */
	private MusicPlayer() {
		loadSound(BITE_SOUND_1);
		loadSound(BITE_SOUND_2);
		loadSound(BITE_SOUND_3);
		loadSound(BG_MUSIC_MENU);
		loadSound(BG_MUSIC_LEVEL);
	}
	
	/**
	 * Loading sounds is only done internally.
	 * @param resource string that defines the path to the sound file.
	 */
	private void loadSound(String resource) {
		try {
			Sound sound = new Sound(resource);
			soundResources.put(resource, sound);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the sound file from the HashMap with loaded sounds.
	 * Throws a NullPointerException when the sound wasn't loaded.
	 * @param resource string that defines the path to the sound file.
	 * @return Sound instance
	 */
	private Sound getSound(String resource) {
		Sound sound = soundResources.get(resource);
		if(sound == null) {
			throw new NullPointerException("Given sound resource doesn't exist");
		}
		return sound;
	}
	
	/**
	 * Plays the sound that is referred by resource.
	 * @param resource string that defines the path to the sound file.
	 */
	public void playSound(String resource) {
		getSound(resource).play();
	}
	
	/**
	 * Loops the sound that is referred by resource.
	 * @param resource string that defines the path to the sound file.
	 */
	public void loopSound(String resource) {
		getSound(resource).loop();
	}
	
	/**
	 * Stops playing the sound that is referred by resource.
	 * @param resource string that defines the path to the sound file.
	 */
	public void stopSound(String resource) {
		getSound(resource).stop();
	}
	
}
