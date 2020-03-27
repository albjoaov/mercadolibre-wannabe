package com.mercadolibrewannabe;

import java.util.stream.IntStream;

public class Main {
	public static void main (String[] args) {
		IntStream limit = IntStream.iterate(10, x -> x + 5).limit(5);
		limit.forEach(System.out::println);
	}
}
