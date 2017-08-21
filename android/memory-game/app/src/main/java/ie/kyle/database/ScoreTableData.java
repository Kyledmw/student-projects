package ie.kyle.database;

import android.provider.BaseColumns;

/**
 ********************************************************************
 * Provides the structure of our Score Table as well as associated queries
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ScoreTableData {

    public static final String CREATE_QUERY = ISQLQueryConstants.CREATE_TABLE + ScoreTableInfo.TABLE_NAME + ISQLQueryConstants.OPEN_PARENTHESES
            + ScoreTableInfo.COLUMN_NAME_ID + ISQLQueryConstants.INTEGER_TYPE + ISQLQueryConstants.PRIMARY_KEY + ISQLQueryConstants.COMMA_SEP
            + ScoreTableInfo.COLUMN_NAME_NAME + ISQLQueryConstants.TEXT_TYPE + ISQLQueryConstants.COMMA_SEP
            + ScoreTableInfo.COLUMN_NAME_SCORE  +ISQLQueryConstants.INTEGER_TYPE
            + ISQLQueryConstants.CLOSE_PARENTHESES;

    public static final String DROP_QUERY = ISQLQueryConstants.DROP_TABLE + ScoreTableInfo.TABLE_NAME;

    public ScoreTableData() {}

    /**
     ********************************************************************
     * Actual implementation of {@link BaseColumns}
     *
     * Defines column names as well as the table name
     *
     * @author Kyle Williamson
     * @version 1.0.0
     ********************************************************************
     */
    public static abstract class ScoreTableInfo implements BaseColumns {
        public static final String TABLE_NAME = "scores";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SCORE = "score";
    }
}
