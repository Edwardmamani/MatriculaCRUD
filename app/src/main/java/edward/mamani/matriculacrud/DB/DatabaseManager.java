package edward.mamani.matriculacrud.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    private static final String DATABASE_NAME = "alumnos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ALUMNOS = "alumnos";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DNI = "dni";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_APELLIDO_MATERNO = "apellido_materno";
    private static final String COLUMN_APELLIDO_PATERNO = "apellido_paterno";

    private static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ALUMNOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DNI + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_APELLIDO_MATERNO + " TEXT, " +
                    COLUMN_APELLIDO_PATERNO + " TEXT);";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUMNOS);
            onCreate(db);
        }
    }

    public long addAlumno(String dni, String name, String apellidoMaterno, String apellidoPaterno) {
        String insertSQL = "INSERT INTO " + TABLE_ALUMNOS + " (" +
                COLUMN_DNI + ", " +
                COLUMN_NAME + ", " +
                COLUMN_APELLIDO_MATERNO + ", " +
                COLUMN_APELLIDO_PATERNO + ") VALUES (?, ?, ?, ?)";

        Object[] params = new Object[]{dni, name, apellidoMaterno, apellidoPaterno};

        database.execSQL(insertSQL, params);

        return 0;
    }


    public Cursor getAllAlumnos() {
        String sqlGetAll = "SELECT * FROM " + TABLE_ALUMNOS;
        return database.rawQuery(sqlGetAll, null);
    }


    public Cursor getAlumno(long id) {
        String sqlGetAlumno = "SELECT * FROM " + TABLE_ALUMNOS + " WHERE " + COLUMN_ID + " = ?";
        return database.rawQuery(sqlGetAlumno, new String[]{String.valueOf(id)});
    }

    public int updateAlumno(long id, String dni, String name, String apellidoMaterno, String apellidoPaterno) {
        String sql = "UPDATE " + TABLE_ALUMNOS + " SET " +
                COLUMN_DNI + " = ?, " +
                COLUMN_NAME + " = ?, " +
                COLUMN_APELLIDO_MATERNO + " = ?, " +
                COLUMN_APELLIDO_PATERNO + " = ? " +
                "WHERE " + COLUMN_ID + " = ?";

        database.execSQL(sql, new Object[]{dni, name, apellidoMaterno, apellidoPaterno, id});
        return 1;
    }

    public void deleteAlumno(long id) {
        String sql = "DELETE FROM " + TABLE_ALUMNOS + " WHERE " + COLUMN_ID + " = ?";
        database.execSQL(sql, new Object[]{String.valueOf(id)});
    }


    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
