package jp.mts.simpletaskboard.web.response;

import java.util.List;

import org.fest.util.Lists;

public class ApiError {

	private String errorId;
	private String userMessage;
	private List<String> fields = Lists.newArrayList();

	public ApiError(String errorId, String message, String field) {
		this.errorId = errorId;
		this.userMessage = message;
		this.fields.add(field);
	}

	public String getErrorId() {
		return errorId;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public List<String> getFields() {
		return Lists.newArrayList(fields);
	}

}
