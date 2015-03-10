package jp.mts.simpletaskboard.test.base;

public interface PageId {

	default String getIdSelector(){
		return "#" + this.getIdValue();
	}
	String getIdValue();
}
