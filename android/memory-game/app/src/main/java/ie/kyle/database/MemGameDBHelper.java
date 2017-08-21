package ie.kyle.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 ********************************************************************
 * Implementation of an SQLLiteOpenHelper for the MemoryGame
 *
 * Provides implementations for creating and destroying the database and its tables
 *
 * @extends {@link SQLiteOpenHelper}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class MemGameDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MemGame.db";

    private ArrayList<String> _createTableQueries;
    private ArrayList<String> _dropTableQueries;

    public MemGameDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _createTableQueries = new ArrayList<String>();
        _dropTableQueries = new ArrayList<String>();
        initCreateTableQueries();
        initDropTableQueries();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String query: _createTableQueries) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(String query: _dropTableQueries) {
            db.execSQL(query);
        }
        onCreate(db);
    }

    private void initCreateTableQueries() {
        _createTableQueries.add(ScoreTableData.CREATE_QUERY);
    }

    private void initDropTableQueries() {
        _dropTableQueries.add((ScoreTableData.DROP_QUERY));
    }
}
