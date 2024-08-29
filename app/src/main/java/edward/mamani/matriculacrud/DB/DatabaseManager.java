package edward.mamani.matriculacrud.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
        ContentValues values = new ContentValues();
        values.put(COLUMN_DNI, dni);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_APELLIDO_MATERNO, apellidoMaterno);
        values.put(COLUMN_APELLIDO_PATERNO, apellidoPaterno);
        return database.insert(TABLE_ALUMNOS, null, values);
    }

    public Cursor getAllAlumnos() {
        return database.query(TABLE_ALUMNOS, null, null, null, null, null, null);
    }

    public Cursor getAlumno(long id) {
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return database.query(TABLE_ALUMNOS, null, selection, selectionArgs, null, null, null);
    }

    public int updateAlumno(long id, String dni, String name, String apellidoMaterno, String apellidoPaterno) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DNI, dni);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_APELLIDO_MATERNO, apellidoMaterno);
        values.put(COLUMN_APELLIDO_PATERNO, apellidoPaterno);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return database.update(TABLE_ALUMNOS, values, selection, selectionArgs);
    }

    public int deleteAlumno(long id) {
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return database.delete(TABLE_ALUMNOS, selection, selectionArgs);
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
