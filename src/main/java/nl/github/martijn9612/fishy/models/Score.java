package nl.github.martijn9612.fishy.models;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
	private static final long serialVersionUID = 3727615178582246499L;
	private String name;
	private double score;
	
	public Score(String name, double score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(double score) {
		this.score = score;
	}

	public int compareTo(Score other) {
		return (int) Math.round(other.getScore() - this.score);
	}
}
