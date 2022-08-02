package org.schooner.dice.app.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Calculate {

	/*
	 * calculate how many times a number is repeated in base on parameter
	 * (repetition)
	 */
	public static long calculateInBaseARepetition(List<Integer> list, int repetition) {
		return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
				.stream().filter(element -> element.getValue() == repetition).count();
	}

	// return sum of values
	public static int returnSumValues(List<Integer> list) {
		return list.stream().mapToInt(Integer::intValue).sum();
	}
}
