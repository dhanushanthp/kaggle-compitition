package org.comp.core;

import java.util.List;
import java.util.Map;

public class Domain {
	private String id;
	private String question;
	private String correctAns;
	private Map<String, String> answers;
	private List<String> answersR;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Map<String, String> getAnswers() {
		return answers;
	}
	public void setAnswers(Map<String, String> answers) {
		this.answers = answers;
	}
	/**
	 * @return the correctAns
	 */
	public String getCorrectAns() {
		return correctAns;
	}
	/**
	 * @param correctAns the correctAns to set
	 */
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	/**
	 * @return the answersR
	 */
	public List<String> getAnswersR() {
		return answersR;
	}
	/**
	 * @param answersR the answersR to set
	 */
	public void setAnswersR(List<String> answersR) {
		this.answersR = answersR;
	}
}
