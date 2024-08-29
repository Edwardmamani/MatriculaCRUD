package edward.mamani.matriculacrud.ui.matricula;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edward.mamani.matriculacrud.databinding.FragmentMatriculaBinding;

public class MatriculaFragment extends Fragment {

    private FragmentMatriculaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MatriculaViewModel homeViewModel =
                new ViewModelProvider(this).get(MatriculaViewModel.class);

        binding = FragmentMatriculaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}