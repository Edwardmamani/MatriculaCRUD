package edward.mamani.matriculacrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edward.mamani.matriculacrud.Alumno;
import edward.mamani.matriculacrud.R;
import edward.mamani.matriculacrud.databinding.ItemAlumnoBinding;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private List<Alumno> alumnos; // Lista de datos

    public AlumnoAdapter(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public static class AlumnoViewHolder extends RecyclerView.ViewHolder {
        private ItemAlumnoBinding binding;

        public AlumnoViewHolder(@NonNull ItemAlumnoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Alumno alumno) {
            binding.textViewDNI.setText(alumno.getDni());
        }
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del elemento de la lista usando ViewBinding
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlumnoBinding binding = ItemAlumnoBinding.inflate(inflater, parent, false);
        return new AlumnoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        // Obtener el alumno actual y enlazar los datos con las vistas
        Alumno alumno = alumnos.get(position);
        holder.bind(alumno);
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }
}

