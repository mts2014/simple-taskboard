package jp.mts.simpletaskboard.matchers;

public class ConstraintViolationsMatchers {

	public static <T> ConstraintViolationsHasMessageMatcher<T> hasFieldMessage(String field, String messageTemplate){
		return new ConstraintViolationsHasMessageMatcher<T>(field, messageTemplate);
	}

	public static <T> ConstraintViolationsHasMessageMatcher<T> hasMessage(String messageTemplate){
		return new ConstraintViolationsHasMessageMatcher<T>(null, messageTemplate);
	}
}
