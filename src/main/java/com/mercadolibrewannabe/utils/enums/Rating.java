package com.mercadolibrewannabe.utils.enums;

public enum Rating {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

	private final Integer value;

	private static final Rating[] valuesArray = values();

	Rating (Integer i) {
		this.value = i;
	}

	public Integer getValue () {
		return value;
	}

	public static Rating byValue(Integer value) {
		return valuesArray[value - 1];
	}
}
