package jp.mts.simpletaskboard.test.apis;

import static jp.mts.simpletaskboard.test.base.AcceptanceTestConfig.*;

import java.io.InputStreamReader;
import java.util.List;

import jp.mts.simpletaskboard.test.lib.appconfig.AppConfig;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.common.collect.Lists;


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

		public String getString(String key){
			return (String)value.get(key);
		}

		public JSONArrayWrapper getArray(String key){
			return new JSONArrayWrapper((JSONArray)value.get(key));
		}

		public JSONObject value(){
			return value;
		}
	}

	public static class JSONArrayWrapper {
		private JSONArray value;

		public JSONArrayWrapper(JSONArray value) {
			this.value = value;
		}

		public JSONObjectWrapper getObject(int i){
			return new JSONObjectWrapper((JSONObject)this.value.get(i));
		}

		public JSONArray value(){
			return value;
		}

		public <T> List<T> convert(Converter<T> converter){
			List<T> list = Lists.newArrayList();
			for(Object e : value){
				list.add(converter.converr(new JSONObjectWrapper((JSONObject)e)));
			}
			return list;
		}

		public static interface Converter<T> {
			T converr(JSONObjectWrapper e);
		}

	}

}
