package jp.mts.simpletaskboard.web.response;

public enum ErrorId {
	e001("指定されたメールアドレスはすでに登録されています。"),
	e002("IDまたはパスワードが間違っています。"),
	;

	private String message;

	private ErrorId(String message) {
		this.message = message;
	}

	public String message(){
		return message;
	}

}
