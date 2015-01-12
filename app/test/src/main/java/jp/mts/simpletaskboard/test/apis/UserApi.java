package jp.mts.simpletaskboard.test.apis;

import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;

import java.io.InputStreamReader;

import jp.mts.simpletaskboard.test.base.UserInputs;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class UserApi {

	public boolean 存在するか？(String email) {

		try {
			HttpResponse res = Request.Get("http://localhost:8080/api/users?email=" + email)
					.execute()
					.returnResponse();
			if(res.getStatusLine().getStatusCode() == 404){
				return false;
			}

			JSONObject response = (JSONObject)JSONValue.parse(
					new InputStreamReader(res.getEntity().getContent()));

			JSONObject user = (JSONObject)((JSONObject)response.get("contents")).get("user");
			return user != null && email.equals(user.get("email"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean 存在するか？(String email, String userName) {
		return false;
	}

	public void 登録する(UserInputs inputs) {

		try {
			JSONObject json = new JSONObject();
			json.put("email", inputs.v(EMAIL));

			String res = Request.Post("http://localhost:8080/api/users")
				.bodyString(json.toJSONString(), ContentType.APPLICATION_JSON)
				.execute()
				.returnContent().asString();
			JSONObject response = (JSONObject)JSONValue.parse(res);

			JSONObject user = (JSONObject)((JSONObject)response.get("contents")).get("user");
			assertThat(user).isNotNull();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
