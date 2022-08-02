package org.schooner.dice.app.domain.enums;

public enum CategoryEnum {

	THREE_OF_A_KIND("THREE_OF_A_KIND", 0, "At least three dice the same"),
	FOUR_OF_A_KIND("FOUR_OF_A_KIND", 0, "At least four dice the same"),
	FULL_HOUSE("FULL_HOUSE", 25, "Three of one number and two of another"),
	SMALL_STRAIGHT("SMALL_STRAIGHT", 30, "Four sequential dice"),
	ALL_DIFFERENT("ALL_DIFFERENT", 35, "No duplicate numbers"),
	LARGE_STRAIGHT("LARGE_STRAIGHT", 40, "Five sequential dice"), 
	SCHOONER("SCHOONER", 50, "All dice the same"),
	CHANCE("CHANCE", 0, "Any combination");

	private String category;
	private int score;
	private String qualifies;

	CategoryEnum(final String category, final int score, final String qualifies) {
		this.category = category;
		this.score = score;
		this.qualifies = qualifies;
	}
	
	public String getCategory() {
		return category;
	}

	public int getScore() {
		return score;
	}

	public String getQualifies() {
		return qualifies;
	}
	
	
}
