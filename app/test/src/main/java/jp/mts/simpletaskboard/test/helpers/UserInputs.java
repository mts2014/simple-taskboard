package jp.mts.simpletaskboard.test.helpers;

import java.util.HashMap;
import java.util.Map;

public class UserInputs {

	private Map<UserInputKey, String> value = new HashMap<>();

	private UserInputs(){}

	public static UserInputs $in() {
		return new UserInputs();
	}

	public UserInputs v(UserInputKey key, String value) {
		this.value.put(key, value);
		return this;
	}

	public String v(UserInputKey key) {
		return this.value.get(key);
	}

}
