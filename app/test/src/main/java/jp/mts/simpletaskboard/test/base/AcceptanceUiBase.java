package jp.mts.simpletaskboard.test.base;

import org.fluentlenium.adapter.FluentTest;

public class AcceptanceUiBase {

	private FluentTest fluentTest;

	public AcceptanceUiBase(FluentTest fluentTest) {
		this.fluentTest = fluentTest;
	}

	public FluentTest getFluentTest() {
		return fluentTest;
	}

}
