package jp.mts.simpletaskboard.datasource.jdbc.repository.impl;

public interface PasswordHashHelper {

	String hash(String plainPassword);

}
