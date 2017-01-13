package pl.sda.receiver.messages;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Equation {
	String id;
	String problem;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

}
