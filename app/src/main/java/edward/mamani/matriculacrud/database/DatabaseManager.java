package edward.mamani.matriculacrud.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        // Abrir la base de datos para escritura
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        // Cerrar la base de datos
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    // Método seguro para ejecutar una sentencia SQL INSERT
    public void insertAlumno(String nombre, String dni) {
        // SQL INSERT con marcadores de posición
        String insertSQL = "INSERT INTO alumnos (nombre, dni) VALUES (?, ?)";

        // Ejecutar la sentencia SQL utilizando execSQL con un array de parámetros
        database.execSQL(insertSQL, new Object[]{nombre, dni});
    }


    // Método seguro para ejecutar una sentencia SQL UPDATE
    public void updateAlumno(String dni, String nuevoNombre) {
        // SQL UPDATE con un marcador de posición
        String updateSQL = "UPDATE alumnos SET nombre = ? WHERE dni = ?";

        // Ejecutar la sentencia SQL utilizando execSQL con un array de parámetros
        database.execSQL(updateSQL, new Object[]{nuevoNombre, dni});
    }


    // Método seguro para ejecutar una sentencia SQL DELETE
    public void deleteAlumno(String dni) {
        // SQL DELETE con un marcador de posición
        String deleteSQL = "DELETE FROM alumnos WHERE dni = ?";

        // Ejecutar la sentencia SQL utilizando execSQL con un array de parámetros
        database.execSQL(deleteSQL, new Object[]{dni});
    }


    // Método seguro para ejecutar una sentencia SQL SELECT
    public List<String> getAllAlumnos() {
        List<String> alumnosList = new ArrayList<>();
        // SQL SELECT sin parámetros
        String selectSQL = "SELECT nombre, dni FROM alumnos";

        Cursor cursor = null;
        try {
            cursor = database.rawQuery(selectSQL, null);
            if (cursor.moveToFirst()) {
                do {
                    String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                    String dni = cursor.getString(cursor.getColumnIndexOrThrow("dni"));
                    alumnosList.add(nombre + " - " + dni);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Asegúrate de cerrar el cursor en un bloque finally
            }
        }
        return alumnosList;
    }

}
