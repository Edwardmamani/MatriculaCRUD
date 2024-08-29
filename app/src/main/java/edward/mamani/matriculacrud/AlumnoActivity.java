package edward.mamani.matriculacrud;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edward.mamani.matriculacrud.databinding.ActivityAlumnoBinding;
import edward.mamani.matriculacrud.databinding.ActivityMainBinding;

public class AlumnoActivity extends AppCompatActivity {

    private ActivityAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Inflar el diseño y obtener el binding
        binding = ActivityAlumnoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alumno);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initBtn();
    }
    private void initBtn(){
        binding.btnMatricula.setOnClickListener(v -> {
            Intent intent = new Intent(AlumnoActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

}