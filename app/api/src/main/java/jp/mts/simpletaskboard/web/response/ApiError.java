package jp.mts.simpletaskboard.web.response;

import java.util.Arrays;
import java.util.List;

import org.fest.util.Lists;

public class ApiError {

	private String errorId;
	private String userMessage;
	private List<String> fields = Lists.newArrayList();

	private ApiError(){}

	public static ApiError ofErrorId(ErrorId errorId, String... fields){
		ApiError e = new ApiError();
		e.errorId = errorId.name();
		e.userMessage = errorId.message();
		e.fields.addAll(Arrays.asList(fields));
		return e;
	}
	public static ApiError ofMessage(String message, String... fields){
		ApiError e = new ApiError();
		e.errorId = null;
		e.userMessage = message;
		e.fields.addAll(Arrays.asList(fields));
		return e;
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
