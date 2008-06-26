package com.defensio.responses;

public class GetStatsResponse extends DefensioResponse {

	private float _accuracy = 0;
	private int _nbSpam = 0;
	private int _nbHam = 0;
	private int _nbFalsePositives = 0;
	private int _nbFalseNegatives = 0;
	
	public float getAccuracy() {
		return _accuracy;
	}
	public void setAccuracy(float accuracy) {
		_accuracy = accuracy;
	}
	public int getNbFalseNegatives() {
		return _nbFalseNegatives;
	}
	public void setNbFalseNegatives(int nbFalseNegatives) {
		_nbFalseNegatives = nbFalseNegatives;
	}
	public int getNbFalsePositives() {
		return _nbFalsePositives;
	}
	public void setNbFalsePositives(int nbFalsePositives) {
		_nbFalsePositives = nbFalsePositives;
	}
	public int getNbHam() {
		return _nbHam;
	}
	public void setNbHam(int nbHam) {
		_nbHam = nbHam;
	}
	public int getNbSpam() {
		return _nbSpam;
	}
	public void setNbSpam(int nbSpam) {
		_nbSpam = nbSpam;
	}	
}
