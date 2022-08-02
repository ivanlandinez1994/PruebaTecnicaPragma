package org.schooner.dice.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.schooner.dice.app.domain.enums.CategoryEnum;
import org.schooner.dice.app.modules.usercase.Categories;
import org.schooner.dice.app.modules.usercase.Score;

public class SchoonerDiceApplication {

	public static void main(String[] args) {
		
		// i added a comment on README.txt to xplain why i repeat similar code on categories and score classes
		
		System.out.println("----score----");
		List<Integer> testScore = new ArrayList<>(Arrays.asList(1, 1, 1, 7, 7));
		Score score = new Score();
		System.out.println(score.score(CategoryEnum.FULL_HOUSE, testScore));
		
		System.out.println("\n----categories----");
		List<Integer> testCategories = new ArrayList<>(Arrays.asList(3, 3, 3, 6, 7));
		Categories categories = new Categories();
		System.out.println(categories.topCategories(testCategories));
		

	}

}
