package pl.sda.receiver.messages;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Solve {
	private String id;
	private String answer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
