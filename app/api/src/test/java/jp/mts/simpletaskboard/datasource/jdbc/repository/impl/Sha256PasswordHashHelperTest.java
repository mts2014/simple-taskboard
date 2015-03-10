package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import static org.fest.assertions.api.Assertions.*;
import mockit.Expectations;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Sha256PasswordHashHelperTest {
	public static void main(String[] args) {
		System.out.println(new Sha256PasswordHashHelper().hash("pass"));
	}

	@Test
	public void test() {
		Sha256PasswordHashHelper sut = new Sha256PasswordHashHelper();
		new Expectations(DigestUtils.class) {{
			DigestUtils.sha256Hex("hoge");
				result = "foo";
		}};

		String actual = sut.hash("hoge");

		assertThat(actual).isEqualTo("foo");
	}

}
