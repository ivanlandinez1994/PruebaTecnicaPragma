package org.schooner.dice.app.modules.usercase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.schooner.dice.app.domain.enums.CategoryEnum;
import org.schooner.dice.app.util.Calculate;

public class Categories {

	/*
	 * returns the best scoring category of all qualifying categories, or a list if
	 * there is a tie for best category 
	 */
	public List<CategoryEnum> topCategories(List<Integer> diceRoll) {
		List<CategoryEnum> res = new ArrayList<>();
		diceRoll.sort((x, y) -> x.compareTo(y));

		// All dice the same
		if (diceRoll.stream().distinct().count() == 1)
			res.add(CategoryEnum.SCHOONER);
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
			if (sequences.stream().filter(item -> item > 4).collect(Collectors.toList()).size() > 0)
				res.add(CategoryEnum.LARGE_STRAIGHT);

			// No duplicate numbers
			else if (diceRoll.stream().distinct().count() == diceRoll.size())
				res.add(CategoryEnum.ALL_DIFFERENT);

			// Four sequential dice
			else if (sequences.stream().filter(item -> item > 3).collect(Collectors.toList()).size() > 0)
				res.add(CategoryEnum.SMALL_STRAIGHT);

			// Three of one number and two of another
			else if (Calculate.calculateInBaseARepetition(diceRoll, 3) > 0
					&& Calculate.calculateInBaseARepetition(diceRoll, 2) > 0)
				res.add(CategoryEnum.FULL_HOUSE);

			// At least four dice the same
			else if (Calculate.calculateInBaseARepetition(diceRoll, 4) > 0) {
				res.add(CategoryEnum.FOUR_OF_A_KIND);
				res.add(CategoryEnum.THREE_OF_A_KIND);
				res.add(CategoryEnum.CHANCE);
			}

			// At least three dice the same
			else if (Calculate.calculateInBaseARepetition(diceRoll, 3) > 0) {
				res.add(CategoryEnum.THREE_OF_A_KIND);
				res.add(CategoryEnum.CHANCE);
			}

		}
		return res;

	}

}
