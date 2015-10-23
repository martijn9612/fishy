package nl.github.martijn9612.fishy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.github.martijn9612.fishy.models.Score;

/**
 * The ScoreController handles the logic for maintaining a list of high
 * scores and ensuring this list is persistently stored into a file.
 * 
 * @author Leon Noordam
 */
public class ScoreController {
	private static final String SCORE_FILE_PATH = "highscores.ser";
	private static ScoreController instance = null;
	
	private List<Score> scoreList = new ArrayList<Score>();
	private double temporaryPlayerScore = 0;
	
	/**
	 * Create a new instance of the ScoreController. The constructor
	 * searches for the saved scores and will load them when found.
	 */
	private ScoreController() {
		File savedScores = new File(SCORE_FILE_PATH);
		if(savedScores.isFile()) {
			unserialize();
		}
	}
	
	/**
	 * Get an instance of the ScoreController singleton.
	 * @return ScoreController instance.
	 */
	public static ScoreController getInstance() {
		if (instance == null) {
			instance = new ScoreController();
		}
		return instance;
	}
	
	/**
	 * Adds a new Score object to the list of scores. The list
	 * with scores is automatically sorted and written to a file.
	 * @param score instance of the Score class.
	 */
	public void addScore(Score score) {
		scoreList.add(score);
		Collections.sort(scoreList);
		serialize();
	}
	
	/**
	 * Removes a Score object from the list with scores. Changes
	 * made to the list are automatically written to a file.
	 * @param score instance of the Score class.
	 */
	public void removeScore(Score score) {
		int index = scoreList.indexOf(score);
		if (index >= 0) {
			scoreList.remove(index);
			serialize();
		}
	}
	
	/**
	 * Saves the stored score of last game in the high score records.
	 * @param playerName name of the player.
	 */
	public void savedStoredScore(String playerName) {
		Score newScore = new Score(playerName, temporaryPlayerScore);
		temporaryPlayerScore = 0;
		addScore(newScore);
	}
	
	/**
	 * Get the list of scores.
	 * @return List<Score> object with scores.
	 */
	public List<Score> getScoreList() {
		return scoreList;
	}
	
	/**
	 * Store the player score for access in other states.
	 * @param score high score of the last game.
	 */
	public void storePlayerScore(double score) {
		temporaryPlayerScore = score;
	}
	
	/**
	 * Get the stored player score in other states.
	 * @return double high score of the last game.
	 */
	public double getPlayerScore() {
		return temporaryPlayerScore;
	}

	/**
	 * Writes the arrayList defined as field in this class to a file.
	 */
	private void serialize() {
		try {
			FileOutputStream fileStream = new FileOutputStream(SCORE_FILE_PATH);
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(scoreList);
			objectStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the arrayList defined as field in this class from a file.
	 * The unchecked warning is suppressed because Java can't know the type.
	 */
	@SuppressWarnings("unchecked")
	private void unserialize() {
		try {
			FileInputStream fileInput = new FileInputStream(SCORE_FILE_PATH);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			scoreList = (ArrayList<Score>) objectInput.readObject();
			objectInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


}
