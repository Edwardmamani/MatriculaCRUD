package edward.mamani.matriculacrud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edward.mamani.matriculacrud.DB.DatabaseManager;
import edward.mamani.matriculacrud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflar el diseño y obtener el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbManager = new DatabaseManager(this);
        initBtn();
    }

    private void initBtn(){
        binding.btnMatricular.setOnClickListener(v -> {
            addAlumno();
        });

        binding.btnAlumnos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlumnoActivity.class);
            startActivity(intent);
        });
    }

    private void addAlumno() {
        // VALIDACION DE DATOS
        String dni = binding.dni.getText().toString().trim();
        String name = binding.nombres.getText().toString().trim();
        String apellidoPaterno = binding.apellidoParteno.getText().toString().trim();
        String apellidoMaterno = binding.apellidoMaterno.getText().toString().trim();

        if (dni.isEmpty() || name.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty()) {
            // Mostrar un mensaje de error si algún campo está vacío
            Toast.makeText(getApplicationContext(), "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
        } else {
            // Agregar el alumno a la base de datos
            dbManager.addAlumno(dni, name, apellidoPaterno, apellidoMaterno);
            // Mostrar un mensaje de éxito
            Toast.makeText(getApplicationContext(), "Alumno matriculado con éxito", Toast.LENGTH_SHORT).show();
        }
    }
}
