package com.game.save.editor.difficulty;

import java.util.HashMap;
import java.util.Map;

import com.game.save.editor.logging.Logger;

/**
 * Singleton for handling difficulties available
 * 
 * @author KyleWilliamson
 *
 */
public class DifficultyManager {
	
	
	public enum DifficultyType {
		EASY, NORMAL, HARD
	}
	
	private final String EASY = "Easy";
	private final String NORMAL = "Normal";
	private final String HARD = "Hard";
	
	private static DifficultyManager instance = new DifficultyManager();
	
	private Map<DifficultyType, DifficultyFactory> factoriesMap;
	
	private Map<DifficultyType, String> difficultyStrings;
	
	
	private DifficultyManager() {
		difficultyStrings = new HashMap<DifficultyType, String>();
		difficultyStrings.put(DifficultyType.EASY, EASY);
		difficultyStrings.put(DifficultyType.NORMAL, NORMAL);
		difficultyStrings.put(DifficultyType.HARD, HARD);
		
		factoriesMap = new HashMap<DifficultyType, DifficultyFactory>();
		factoriesMap.put(DifficultyType.EASY, new EasyDifficultyFactory());
		factoriesMap.put(DifficultyType.NORMAL, new NormalDifficultyFactory());
		factoriesMap.put(DifficultyType.HARD, new HardDifficultyFactory());
	}
	
	public static DifficultyManager getInstance() {
		return instance;
	}
	
	public Map<DifficultyType, String> getDifficultyMap() {
		return difficultyStrings;
	}
	
	public String[] getDifficultyStrings() {
		return new String[] {EASY, NORMAL, HARD};
	}
	
	/**
	 * Method that returns equivalent Difficulty object for the given type
	 * 
	 * @param type DifficultyType
	 * @return Difficulty
	 */
	public Difficulty getDifficultyChoice(DifficultyType type) {
		Logger.info("Getting Difficulty for Type");
		return factoriesMap.get(type).getDifficulty();
	}
	

}
