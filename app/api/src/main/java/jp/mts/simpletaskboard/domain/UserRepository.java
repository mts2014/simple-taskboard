package jp.mts.simpletaskboard.domain;

public interface UserRepository {

	User load(int id);

	User searchByEmail(String email);

	void save(User any);

}
