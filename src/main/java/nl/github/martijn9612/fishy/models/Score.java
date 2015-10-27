package nl.github.martijn9612.fishy.models;

import java.io.Serializable;

/**
 * Implements the Score class.
 * Software Engineering Methods Project - Group 11.
 */
public class Score implements Serializable, Comparable<Score> {
	private static final long serialVersionUID = 3727615178582246499L;
	private String name;
	private double score;
	
	/**
	 * Constructor method.
	 * @param name - name to be given to the new Score.
	 * @param score - score value to be given to the new Score.
	 */
	public Score(String name, double score) {
		this.name = name;
		this.score = score;
	}
	
	/**
	 * Get method for name.
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get method for score.
	 * @return score.
	 */
	public double getScore() {
		return score;
	}
	
	/**
	 * Set method for name.
	 * @param name - name to be set to.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set method for score.
	 * @param score - score to be set to.
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * CompareTo method that compares two scores and gives
	 * the difference between the two score values.
	 * @param other - score to be compared to.
	 * @return difference between the two score values.
	 */
	public int compareTo(Score other) {
		return (int) Math.round(other.getScore() - this.score);
	}
}
