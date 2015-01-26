package jp.mts.simpletaskboard.web.response;

import java.util.HashMap;
import java.util.Map;

public class RestResponse {

	private Map<String, Object> content = new HashMap<>();

	public RestResponse addContent(String key, Object value){
		content.put(key, value);
		return this;
	}

	public Map<String, Object> getContents(){
		return this.content;
	}

}
