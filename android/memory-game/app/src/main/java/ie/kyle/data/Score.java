package ie.kyle.data;

/**
 ********************************************************************
 * An Implementation of the {link IScore} interface
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class Score implements IScore {

    private int _id;
    private String _name;
    private int _score;

    public Score(int id, String name, int score) {
        _id = id;
        _name = name;
        _score = score;
    }

    @Override
    public int getID() {
        return _id;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public int getScore() {
        return _score;
    }

    @Override
    public int compareTo(IScore another) {
        if(_score == another.getScore()) {
            return 0;
        }
        return (_score < another.getScore()) ? 1 : -1;
    }
}
