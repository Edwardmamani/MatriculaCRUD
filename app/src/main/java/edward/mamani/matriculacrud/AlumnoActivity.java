package edward.mamani.matriculacrud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import edward.mamani.matriculacrud.DB.DatabaseManager;
import edward.mamani.matriculacrud.DB.Index;
import edward.mamani.matriculacrud.adapter.AlumnoAdapter;
import edward.mamani.matriculacrud.databinding.ActivityAlumnoBinding;



public class AlumnoActivity extends AppCompatActivity {

    private ActivityAlumnoBinding binding;
    private AlumnoAdapter adapter;
    private List<Alumno> listAlumnos; // Debes inicializar la lista de alumnos antes de usarla
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llama a super.onCreate antes de cualquier otra cosa

        // Inflar el diseño y obtener el binding
        binding = ActivityAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbManager = new DatabaseManager(this);
        listAlumnos = new ArrayList<>();

        // Configurar los ajustes de Edge to Edge y las barras de sistema
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initBtn();
        initRecycler();
    }

    private void initRecycler() {
        // Inicializa el LayoutManager y configura el RecyclerView
        adapter = new AlumnoAdapter(listAlumnos, this::update, this::delete);  // Asegúrate de que listAlumnos esté inicializada
        binding.recyclerAlumnos.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerAlumnos.setAdapter(adapter);
        getAllAllumnos();
    }
    private void update(Alumno alumno){

        Log.d("Database", "ID: " + alumno.getId() + ", DNI: " + alumno.getDni() + ", Nombre: " + alumno.getNombre()
                + ", Apellido Materno: " + alumno.getApellidoMaterno() + ", Apellido Paterno: " + alumno.getApellidoPaterno());


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id_alumno",alumno.getId());
        startActivity(intent);
    }

    private void delete(Alumno alumno){
        Log.d("Database", "ID: " + alumno.getId() + ", DNI: " + alumno.getDni() + ", Nombre: " + alumno.getNombre()
                + ", Apellido Materno: " + alumno.getApellidoMaterno() + ", Apellido Paterno: " + alumno.getApellidoPaterno());


        dbManager.deleteAlumno(alumno.getId());
        getAllAllumnos();
    }


    private void getAllAllumnos() {
        Cursor cursor = dbManager.getAllAlumnos();

        if (cursor != null && cursor.moveToFirst()) {
            listAlumnos.clear();
            Index i = new Index(cursor);
            do {
                // Leer datos del cursor
                long id = cursor.getLong(i.idIndex);
                String dni = cursor.getString(i.dniIndex);
                String name = cursor.getString(i.nameIndex);
                String apellidoMaterno = cursor.getString(i.apellidoMaternoIndex);
                String apellidoPaterno = cursor.getString(i.apellidoPaternoIndex);

                Alumno alumno = new Alumno(id,dni, name, apellidoPaterno, apellidoMaterno);
                listAlumnos.add(alumno);

                // Registrar los datos en el log
                Log.d("Database", "ID: " + alumno.getId() + ", DNI: " + alumno.getDni() + ", Nombre: " + alumno.getNombre()
                        + ", Apellido Materno: " + alumno.getApellidoMaterno() + ", Apellido Paterno: " + alumno.getApellidoPaterno());

            } while (cursor.moveToNext());

            // Notificar al adaptador que hubo cambios
            adapter.notifyDataSetChanged(); // Descomentar y adaptar según tu implementación
        }

        if (cursor != null) {
            cursor.close();
        }
    }


    private void initBtn() {
        binding.btnMatricula.setOnClickListener(v -> {
            Intent intent = new Intent(AlumnoActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
