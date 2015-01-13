package jp.mts.simpletaskboard.test.apis;

import static jp.mts.simpletaskboard.test.base.AcceptanceTestConfig.*;

import java.io.InputStreamReader;

import jp.mts.simpletaskboard.test.lib.appconfig.AppConfig;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class ApiBase {

	private String url(String relativeUrl){
		return AppConfig.value(api_base_url) + relativeUrl;
	}
	protected Request httpGet(String relativeUrl){
		return Request.Get(url(relativeUrl));
	}
	protected Request httpPost(String relativeUrl){
		return Request.Post(url(relativeUrl));
	}
	protected JSONObjectWrapper toJson(HttpResponse res){
		try {
			return new JSONObjectWrapper((JSONObject)JSONValue.parse(
					new InputStreamReader(res.getEntity().getContent())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	public static class JSONObjectWrapper {
		private JSONObject value;

		public JSONObjectWrapper(JSONObject value) {
			this.value = value;
		}

		public JSONObjectWrapper get(String key){
			return new JSONObjectWrapper((JSONObject)value.get(key));
		}

		public JSONObject value(){
			return value;
		}
	}

}
