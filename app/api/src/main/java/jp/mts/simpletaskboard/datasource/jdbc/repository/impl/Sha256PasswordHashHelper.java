package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;

public class Sha256PasswordHashHelper implements PasswordHashHelper {

	public String hash(String plainPassword){
		return DigestUtils.sha256Hex(plainPassword);
	}
}
