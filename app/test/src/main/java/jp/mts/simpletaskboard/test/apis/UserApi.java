package jp.mts.simpletaskboard.test.apis;

import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;
import jp.mts.simpletaskboard.test.base.UserInputs;

import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONObject;


public class UserApi extends ApiBase {

	public boolean 存在するか(String email) {
		JSONObject user = searchUserByEmail(email);
		return user != null && email.equals(user.get("email"));
	}

	public boolean 存在するか(String email, String userName) {
		JSONObject user = searchUserByEmail(email);
		return user != null
				&& email.equals(user.get("email"))
				&& userName.equals(user.get("name"));
	}

	public void 登録する(UserInputs inputs) {

		try {
			JSONObject requestJson = new JSONObject();
			requestJson.put("email", inputs.v(EMAIL));
			requestJson.put("name", inputs.v(ユーザ名));
			requestJson.put("password", inputs.v(パスワード));
			requestJson.put("confirmPassword", inputs.v(確認パスワード));

			HttpResponse res = httpPost("/users")
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

	private JSONObject searchUserByEmail(String email){

		try {
			HttpResponse res = httpGet("/users?email=" + email)
					.execute()
					.returnResponse();
			if(res.getStatusLine().getStatusCode() == 500){
				throw new RuntimeException();
			}
			if(res.getStatusLine().getStatusCode() == 404){
				return null;
			}

			JSONObjectWrapper json = toJson(res);
			return json.get("contents").get("user").value();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
