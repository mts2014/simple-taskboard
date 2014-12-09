package jp.mts.simpletaskboard.test.helpers;

import org.fluentlenium.adapter.FluentTest;

public class AcceptanceUi {

	private FluentTest fluentTest;

	public AcceptanceUi(FluentTest fluentTest) {
		this.fluentTest = fluentTest;
	}

	public FluentTest getFluentTest() {
		return fluentTest;
	}

}
