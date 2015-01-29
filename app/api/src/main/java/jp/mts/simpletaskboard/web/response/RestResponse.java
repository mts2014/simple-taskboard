package jp.mts.simpletaskboard.web.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fest.util.Lists;

public class RestResponse {

	private Map<String, Object> content = new HashMap<>();
	private List<ApiError> errors = Lists.newArrayList();

	public RestResponse(){
	}

	public RestResponse addContent(String key, Object value){
		content.put(key, value);
		return this;
	}

	public RestResponse addError(ApiError error){
		errors.add(error);
		return this;
	}

	public Map<String, Object> getContents(){
		return this.content;
	}
	public List<ApiError> getErrors(){
		return this.errors;
	}

}
