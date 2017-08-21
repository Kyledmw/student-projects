package ie.kyle.database;

/**
 ********************************************************************
 * List of constants used for SQL queries
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ISQLQueryConstants {

    String DROP_TABLE = "DROP TABLE IF EXISTS ";

    String CREATE_TABLE = "CREATE TABLE ";
    String OPEN_PARENTHESES = " (";
    String CLOSE_PARENTHESES = " )";

    String COMMA_SEP = ",";

    String INTEGER_TYPE = " INTEGER";
    String TEXT_TYPE = " TEXT";

    String PRIMARY_KEY = " PRIMARY KEY";

    String EQUALS = "= ?";

    String SELECT_MAX = "SELECT MAX";

    String FROM = " FROM ";

}
