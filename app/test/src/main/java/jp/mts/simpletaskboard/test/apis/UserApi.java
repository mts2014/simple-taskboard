package jp.mts.simpletaskboard.test.apis;

import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.UserInputs;

import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONObject;


public class UserApi extends ApiBase {

	public boolean 存在するか(String email) {

		try {
			HttpResponse res = httpGet("/api/users?email=" + email)
					.execute()
					.returnResponse();
			if(res.getStatusLine().getStatusCode() == 404){
				return false;
			}

			JSONObjectWrapper json = toJson(res);
			JSONObject user = json.get("contents").get("user").value();
			return user != null && email.equals(user.get("email"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean 存在するか(String email, String userName) {
		return false;
	}

	public void 登録する(UserInputs inputs) {

		try {
			JSONObject requestJson = new JSONObject();
			requestJson.put("email", inputs.v(EMAIL));

			HttpResponse res = httpPost("/api/users")
				.bodyString(requestJson.toJSONString(), ContentType.APPLICATION_JSON)
				.execute()
				.returnResponse();

			JSONObjectWrapper json = toJson(res);
			JSONObject user = json.get("contents").get("user").value();
			assertThat(user).isNotNull();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
