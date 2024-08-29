package edward.mamani.matriculacrud.DB;

import android.database.Cursor;

public class Index {
    // Índices de las columnas públicos
    public int idIndex;
    public int dniIndex;
    public int nameIndex;
    public int apellidoPaternoIndex;
    public int apellidoMaternoIndex;

    // Constructor que inicializa los índices de las columnas
    public Index(Cursor cursor) {
        // Verifica que el cursor no sea nulo antes de obtener los índices
        if (cursor != null) {
            idIndex = cursor.getColumnIndex("_id");
            dniIndex = cursor.getColumnIndex("dni");
            nameIndex = cursor.getColumnIndex("name");
            apellidoPaternoIndex = cursor.getColumnIndex("apellido_paterno");
            apellidoMaternoIndex = cursor.getColumnIndex("apellido_materno");
        }
    }
}
