package jp.mts.simpletaskboard.web.response;

import java.util.HashMap;
import java.util.Map;

public class RestResponse {

	private Map<String, Object> content = new HashMap<>();

	public void addContent(String key, Object value){
		content.put(key, value);
	}

	public Map<String, Object> getContents(){
		return this.content;
	}

}