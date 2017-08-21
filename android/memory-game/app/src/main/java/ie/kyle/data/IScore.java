package ie.kyle.data;

/**
 ********************************************************************
 * A generic interface for a score modal object
 *
 * @extends {@link Comparable<IScore>} to allow it to be sorted
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IScore extends Comparable<IScore>  {

    /**
     * Get the unique identifier given to this object
     *
     * @return id identifier for this score object
     */
    int getID();

    /**
     * Get the name of the player who achieved this score
     *
     * @return name of player
     */
    String getName();

    /**
     * Get the actual integer score value achieved
     *
     * @return integer score value
     */
    int getScore();
}
