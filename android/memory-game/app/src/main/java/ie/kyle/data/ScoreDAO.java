package ie.kyle.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ie.kyle.database.ISQLQueryConstants;
import ie.kyle.database.MemGameDBHelper;
import ie.kyle.database.ScoreTableData;

/**
 ********************************************************************
 * A singleton implementation of the {@link IScoreDAO} interface
 *
 * This DAO uses a local SQLite database to store its {@link IScore} data
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ScoreDAO implements IScoreDAO {

    private static final String MAX_ID_QUERY = ISQLQueryConstants.SELECT_MAX +
            ISQLQueryConstants.OPEN_PARENTHESES + ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID + ISQLQueryConstants.CLOSE_PARENTHESES +
            ISQLQueryConstants.FROM + ScoreTableData.ScoreTableInfo.TABLE_NAME;

    private static ScoreDAO _instance;

    private MemGameDBHelper _dbHelper;
    private SQLiteDatabase _dbWriter;
    private SQLiteDatabase _dbReader;

    private int _nextId;

    private final static String[] PROJECTION = {
            ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID,
            ScoreTableData.ScoreTableInfo.COLUMN_NAME_NAME,
            ScoreTableData.ScoreTableInfo.COLUMN_NAME_SCORE
    };

    /**
     * Setup the necessary database objects
     *
     * @param context context of the activity using this
     */
    private ScoreDAO(Context context) {
        _dbHelper = new MemGameDBHelper(context);
        _dbWriter = _dbHelper.getWritableDatabase();
        _dbReader = _dbHelper.getReadableDatabase();
        _nextId = getMaxID();
        _nextId++;
    }

    /**
     * Retrieve singleton instance of this class
     *
     * @param context
     *          context required to create the singleton if it has not been instantiated yet
     *
     * @return ScoreDAO
     *              Singleton instance of this class
     */
    public static ScoreDAO getInstance(Context context) {
        if(_instance == null) {
            _instance = new ScoreDAO(context);
        }
        return _instance;
    }

    @Override
    public List<IScore> getAllScores() {
        String sortOrder = ScoreTableData.ScoreTableInfo.COLUMN_NAME_SCORE;
        Cursor c = _dbReader.query(ScoreTableData.ScoreTableInfo.TABLE_NAME, PROJECTION, null, null, null, null, sortOrder);
        List<IScore> scoreList = new ArrayList<IScore>();
        while(c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID));
            int score = c.getInt(c.getColumnIndexOrThrow(ScoreTableData.ScoreTableInfo.COLUMN_NAME_SCORE));
            String name = c.getString(c.getColumnIndexOrThrow(ScoreTableData.ScoreTableInfo.COLUMN_NAME_NAME));
            scoreList.add(new Score(id, name, score));
        }
        return scoreList;
    }

    @Override
    public IScore getScore(int id) {
        String selection = ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID + ISQLQueryConstants.EQUALS;
        String[] selectionArgs = { String.valueOf(id) };
        Cursor c = _dbReader.query(ScoreTableData.ScoreTableInfo.TABLE_NAME, PROJECTION, selection, selectionArgs, null, null, null);

        c.moveToFirst();

        int sID = c.getInt(c.getColumnIndexOrThrow(ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID));
        int score = c.getInt(c.getColumnIndexOrThrow(ScoreTableData.ScoreTableInfo.COLUMN_NAME_SCORE));
        String name = c.getString(c.getColumnIndexOrThrow(ScoreTableData.ScoreTableInfo.COLUMN_NAME_NAME));
        return new Score(sID, name, score);
    }

    @Override
    public void updateScore(IScore score) {
        ContentValues values = new ContentValues();
        values.put(ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID, score.getID());
        values.put(ScoreTableData.ScoreTableInfo.COLUMN_NAME_NAME, score.getName());
        values.put(ScoreTableData.ScoreTableInfo.COLUMN_NAME_SCORE, score.getScore());

        String selection = ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID + ISQLQueryConstants.EQUALS;
        String[] selectionArgs = { String.valueOf(score.getID()) };

       int count =  _dbWriter.update(
               ScoreTableData.ScoreTableInfo.TABLE_NAME,
               values,
               selection,
               selectionArgs
       );
        if(count == 0) {
            _dbWriter.insert(ScoreTableData.ScoreTableInfo.TABLE_NAME, null, values);
        }
    }

    @Override
    public void deleteScore(IScore score) {
        String selection = ScoreTableData.ScoreTableInfo.COLUMN_NAME_ID + ISQLQueryConstants.EQUALS;
        String[] selectionArgs = { String.valueOf(score.getID()) };

        _dbWriter.delete(ScoreTableData.ScoreTableInfo.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public IScore createScore(String name, int score) {
        IScore iScore = new Score(_nextId, name, score);
        _nextId++;
        return iScore;
    }

    /**
     * Get the max id of the current score in the database
     *
     * @return max id
     */
    private int getMaxID() {
        Cursor cursor = _dbReader.rawQuery(MAX_ID_QUERY, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }
}
