package org.schooner.dice.app.modules.usercase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.schooner.dice.app.domain.enums.CategoryEnum;
import org.schooner.dice.app.util.Calculate;

public class Score {

	/*
	 * returns the score of the dice for the specified category.
	 * we define as alternative the sum of the values in case that we don´t have points for an
	 * specific combination
	 */
	public int score(CategoryEnum category, List<Integer> diceRoll) {

		diceRoll.sort((x, y) -> x.compareTo(y));

		// All dice the same
		if (category.name().equals(CategoryEnum.SCHOONER.getCategory()) && diceRoll.stream().distinct().count() == 1)
			return CategoryEnum.SCHOONER.getScore();

		else {
			int sequenceCont = 1;
			List<Integer> sequences = new ArrayList<>();

			// calculate sequences
			for (int i = 0; i < diceRoll.size(); i++) {
				if (diceRoll.size() > i + 1 && diceRoll.get(i + 1) - diceRoll.get(i) == 1)
					sequenceCont++;
				else {
					if (sequenceCont > 1)
						sequences.add(sequenceCont);
					sequenceCont = 1;
				}
			}

			// Five sequential dice
			if (category.name().equals(CategoryEnum.LARGE_STRAIGHT.getCategory())
					&& sequences.stream().filter(item -> item > 4).collect(Collectors.toList()).size() > 0)
				return CategoryEnum.LARGE_STRAIGHT.getScore();

			// No duplicate numbers
			else if (category.name().equals(CategoryEnum.ALL_DIFFERENT.getCategory())
					&& diceRoll.stream().distinct().count() == diceRoll.size())
				return CategoryEnum.ALL_DIFFERENT.getScore();

			// Four sequential dice
			else if (category.name().equals(CategoryEnum.SMALL_STRAIGHT.getCategory())
					&& sequences.stream().filter(item -> item > 3).collect(Collectors.toList()).size() > 0)
				return CategoryEnum.SMALL_STRAIGHT.getScore();

			// Three of one number and two of another
			else if (category.name().equals(CategoryEnum.FULL_HOUSE.getCategory())
					&& Calculate.calculateInBaseARepetition(diceRoll, 3) > 0
					&& Calculate.calculateInBaseARepetition(diceRoll, 2) > 0)
				return CategoryEnum.FULL_HOUSE.getScore();

			// Any combination
			else if (category.name().equals(CategoryEnum.CHANCE.getCategory()))
				return CategoryEnum.CHANCE.getScore() != 0 ? CategoryEnum.CHANCE.getScore()
						: Calculate.returnSumValues(diceRoll);

			// At least three dice the same
			else if (category.name().equals(CategoryEnum.THREE_OF_A_KIND.getCategory())
					&& Calculate.calculateInBaseARepetition(diceRoll, 3) > 0)
				return CategoryEnum.THREE_OF_A_KIND.getScore() != 0 ? CategoryEnum.THREE_OF_A_KIND.getScore()
						: Calculate.returnSumValues(diceRoll);

			// At least four dice the same
			else if (category.name().equals(CategoryEnum.FOUR_OF_A_KIND.getCategory())
					&& Calculate.calculateInBaseARepetition(diceRoll, 4) > 0)
				return CategoryEnum.FOUR_OF_A_KIND.getScore() != 0 ? CategoryEnum.FOUR_OF_A_KIND.getScore()
						: Calculate.returnSumValues(diceRoll);

		}

		return 0;

	}
}
