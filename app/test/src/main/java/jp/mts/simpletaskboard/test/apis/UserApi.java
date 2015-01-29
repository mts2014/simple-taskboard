package jp.mts.simpletaskboard.test.apis;

import static jp.mts.simpletaskboard.test.inputkeys.UserRegisterKey.*;
import static org.fest.assertions.api.Assertions.*;

import java.util.List;

import jp.mts.simpletaskboard.test.base.UserInputs;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONObject;

import com.google.common.collect.Lists;


public class UserApi extends ApiBase {

	public void 存在する(String email) {
		_存在するか(email, null, true);
	}
	public void 存在しない(String email) {
		_存在するか(email, null, false);
	}

	public void 存在する(String email, String userName) {
		_存在するか(email, userName, true);
	}
	public void 存在しない(String email, String userName) {
		_存在するか(email, userName, false);
	}
	private void _存在するか(String email, String userName, boolean expected) {
		JSONObject user = searchUserByEmail(email);
		boolean existsUser = user != null
				&& email.equals(user.get("email"))
				&& (StringUtils.isEmpty(userName) || userName.equals(user.get("name")));
		assertThat(existsUser).isEqualTo(expected);
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
	public List<String> 登録時の検証をする(UserInputs inputs) {
		try {
			JSONObject requestJson = new JSONObject();
			requestJson.put("email", inputs.v(EMAIL));
			requestJson.put("name", inputs.v(ユーザ名));
			requestJson.put("password", inputs.v(パスワード));
			requestJson.put("confirmPassword", inputs.v(確認パスワード));

			HttpResponse res = httpPost("/users?validate")
				.bodyString(requestJson.toJSONString(), ContentType.APPLICATION_JSON)
				.execute()
				.returnResponse();

			if(res.getStatusLine().getStatusCode() == 400){
				JSONObjectWrapper json = toJson(res);
				return json.getArray("errors")
						.convert(e -> e.getString("userMessage"));
			}

			return Lists.newArrayList();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
