package jp.mts.simpletaskboard.test.apis;

import jp.mts.simpletaskboard.test.base.UserInputs;

import org.apache.http.client.fluent.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;


public class UserApi {

	public boolean 存在するか？(String email) {

		try {
			String response = Request.Get("http://localhost:8080/api/users?email=" + email)
					.execute().returnContent().asString();
			JSONArray responseJsonArray = (JSONArray)JSONValue.parse(response);

			if(responseJsonArray.size() > 1){
				throw new IllegalStateException("a email must belong to one user");
			}
			return responseJsonArray.size() == 1;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean 存在するか？(String email, String userName) {
		return false;
	}

	public void 登録する(UserInputs inputs) {

	}

}
