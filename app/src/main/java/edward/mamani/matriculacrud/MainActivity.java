package edward.mamani.matriculacrud;

import android.os.Bundle;

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
        initBtn();
    }

    private void initBtn(){

    }
}
