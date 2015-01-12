package jp.mts.simpletaskboard.domain;

public class User {

	private int id;
	private String email;

	public User(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
