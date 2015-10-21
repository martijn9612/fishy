package nl.github.martijn9612.fishy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import nl.github.martijn9612.fishy.models.Score;

public class ScoreController {
	private static final String SCORE_FILE_PATH = "highscores.ser";

	private ArrayList<Score> scoreList = new ArrayList<Score>();
	
	public ScoreController() {
		File savedScores = new File(SCORE_FILE_PATH);
		if(savedScores.isFile()) {
			unserialize();
		}
	}

	public void addScore(Score score) {
		scoreList.add(score);
		serialize();
	}

	public void removeScore(Score score) {
		int index = scoreList.indexOf(score);
		if (index >= 0) {
			scoreList.remove(index);
			serialize();
		}
	}

	public ArrayList<Score> getScoreList() {
		return scoreList;
	}
	
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
