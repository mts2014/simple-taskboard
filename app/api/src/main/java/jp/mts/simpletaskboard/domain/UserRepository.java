package jp.mts.simpletaskboard.domain;

public interface UserRepository {

	User searchByEmail(String email);

}
