package com.space_invaders.global.difficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private List<Difficulty> difficulties;
	
	private Map<DifficultyType, String> difficultyStrings;
	
	
	private DifficultyManager() {
		difficultyStrings = new HashMap<DifficultyType, String>();
		difficultyStrings.put(DifficultyType.EASY, EASY);
		difficultyStrings.put(DifficultyType.NORMAL, NORMAL);
		difficultyStrings.put(DifficultyType.HARD, HARD);
		
		difficulties = new ArrayList<Difficulty>();
		difficulties.add(new EasyDifficulty());
		difficulties.add(new NormalDifficulty());
		difficulties.add(new HardDifficulty());
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
	
	public Difficulty getDifficultyChoice(DifficultyType type) {
		for(Difficulty current: difficulties) {
			if(current.getType() == type) {
				return current;
			}
		}
		throw new DifficultyException();
	}
	

}
