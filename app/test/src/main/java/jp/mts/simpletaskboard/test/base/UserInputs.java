package jp.mts.simpletaskboard.test.base;

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
		if(!this.value.containsKey(key)){
			return key.getDefaultValue();
		}
		return this.value.get(key);
	}

	public boolean isSpecified(UserInputKey key){
		return this.value.containsKey(key);
	}

}
