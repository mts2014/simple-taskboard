package jp.mts.simpletaskboard.matchers;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fest.util.Lists;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ConstraintViolationsHasMessageMatcher<T> extends TypeSafeMatcher<Set<ConstraintViolation<T>>> {

	private String fieldName;
	private String messageTemplate;

	public ConstraintViolationsHasMessageMatcher(
			String fieldName, String messageTemplate) {
		this.fieldName = fieldName;
		this.messageTemplate = messageTemplate;
	}

	@Override
	public void describeTo(Description matchDescription) {
		matchDescription
			.appendText("constraint vaiolations contains (");

		if(fieldName != null){
			matchDescription
				.appendValue(fieldName)
				.appendText(", ");
		}
		matchDescription
			.appendValue(messageTemplate)
			.appendText(")");
	}

	@Override
	protected void describeMismatchSafely(
			Set<ConstraintViolation<T>> violations, Description mismatchDescription) {

		violations.forEach(v -> {
			mismatchDescription
				.appendText("(");
			if(fieldName != null){
				mismatchDescription
					.appendValue(v.getPropertyPath())
					.appendText(", ");
			}
			mismatchDescription
				.appendValue(v.getMessageTemplate())
				.appendText(")");
		});
	}

	@Override
	protected boolean matchesSafely(Set<ConstraintViolation<T>> violations) {
		return violations.stream()
			.filter(v -> {
				if(fieldName == null){
					return v.getMessageTemplate().equals(messageTemplate);
				}

				List<String> matchedPaths = Lists.newArrayList();
				v.getPropertyPath().forEach(p -> {
					if(p.getName().equals(fieldName)) matchedPaths.add(p.getName());
				});

				return !matchedPaths.isEmpty()
						&& v.getMessageTemplate().equals(messageTemplate);
			})
			.count() > 0;
	}

}
