package ie.kyle.data;

import java.util.List;

/**
 ********************************************************************
 * A generic interface for an {@link IScore} Data Access Object
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IScoreDAO {

    /**
     * Retrieve a {@link List} of all {@link IScore} objects
     *
     * @return list containing all scores saved {@link List<IScore>}
     */
    List<IScore> getAllScores();

    /**
     * Retrieve an {@link IScore} object with its unique identifier
     *
     * @param id unique identifier to find object by#
     *
     * @return retrieved {@link IScore} object with the same id
     */
    IScore getScore(int id);

    /**
     * Update a saved {@link IScore} object
     *
     * Stores if not found
     *
     * @param score {@link IScore} object to update/store
     */
    void updateScore(IScore score);

    /**
     * Delete given {@link IScore} object from the the DAO
     *
     * @param score{@link IScore} object to delete
     */
    void deleteScore(IScore score);

    /**
     * Factory method for creating an {@link IScore} object
     *
     * This object is not stored within the dao by default
     *
     * @param name name of the player who achieved the score
     * @param score integer value of the score achieved
     *
     * @return created {@link IScore} object
     */
    IScore createScore(String name, int score);
}
