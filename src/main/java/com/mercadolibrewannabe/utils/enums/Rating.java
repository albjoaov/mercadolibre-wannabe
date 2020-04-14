package com.mercadolibrewannabe.utils.enums;

public enum RatingEnum {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

	private final Integer value;

	private static final RatingEnum[] valuesArray = values();

	RatingEnum (Integer i) {
		this.value = i;
	}

	public Integer getValue () {
		return value;
	}

	public static RatingEnum byValue(Integer value) {
		return valuesArray[value - 1];
	}
}
