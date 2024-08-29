package edward.mamani.matriculacrud;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import edward.mamani.matriculacrud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el diseño y obtener el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar un listener para un botón
        binding.myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un mensaje cuando el botón es clickeado
                Toast.makeText(MainActivity.this, "Botón clickeado", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar un TextView
        binding.myTextView.setText("Texto actualizado desde el código");
    }
}
