package edward.mamani.matriculacrud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edward.mamani.matriculacrud.DB.DatabaseManager;
import edward.mamani.matriculacrud.DB.Index;
import edward.mamani.matriculacrud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseManager dbManager;

    String dni;
    String name;
    String apellidoPaterno;
    String apellidoMaterno;

    private int id_alumno = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflar el diseño y obtener el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbManager = new DatabaseManager(this);
        id_alumno = getIntent().getExtras().getInt("id", -1);
        initUI();
        initBtn();
    }

    private void initUI() {
        if(id_alumno != -1){
            setDataUI();
        }
    }

    private void initBtn(){
        binding.btnMatricular.setOnClickListener(v -> {
            getDataUI();
            if(!validDataUI()) return;
            if(id_alumno != -1){
                // ACTUALIZAMOS AL ALUMNO
                dbManager.updateAlumno(id_alumno,dni,name,apellidoMaterno,apellidoPaterno);
            } else {
                dbManager.addAlumno(dni, name, apellidoPaterno, apellidoMaterno);
            }
            clearDataUI();
        });

        binding.btnAlumnos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlumnoActivity.class);
            startActivity(intent);
        });
    }
    private void setDataUI() {
        Cursor cursor = dbManager.getAlumno(id_alumno);
        Index i = new Index(cursor);
        if (cursor != null && cursor.moveToFirst()) {

            String dni = cursor.getString(i.dniIndex);
            String name = cursor.getString(i.nameIndex);
            String apellidoPaterno = cursor.getString(i.apellidoPaternoIndex);
            String apellidoMaterno = cursor.getString(i.apellidoMaternoIndex);

            binding.dni.setText(dni);
            binding.nombres.setText(name);
            binding.apellidoParteno.setText(apellidoPaterno);
            binding.apellidoMaterno.setText(apellidoMaterno);
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void clearDataUI() {
        binding.dni.setText("");
        binding.nombres.setText("");
        binding.apellidoParteno.setText("");
        binding.apellidoMaterno.setText("");
    }

    private boolean validDataUI() {
        return  dni.isEmpty() || name.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty();
    }

    private void getDataUI() {
        dni = binding.dni.getText().toString().trim();
        name = binding.nombres.getText().toString().trim();
        apellidoPaterno = binding.apellidoParteno.getText().toString().trim();
        apellidoMaterno = binding.apellidoMaterno.getText().toString().trim();

    }
}
