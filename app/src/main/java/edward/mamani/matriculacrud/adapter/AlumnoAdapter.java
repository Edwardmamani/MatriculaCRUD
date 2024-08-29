package edward.mamani.matriculacrud.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

import edward.mamani.matriculacrud.Alumno;
import edward.mamani.matriculacrud.databinding.ItemAlumnoBinding;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private List<Alumno> alumnos; // Lista de datos
    private Consumer<Alumno> update;
    private Consumer<Alumno> delete;

    public AlumnoAdapter(List<Alumno> alumnos, Consumer<Alumno> update, Consumer<Alumno> delete) {
        this.alumnos = alumnos;
        this.update = update;
        this.delete = delete;
    }

    public static class AlumnoViewHolder extends RecyclerView.ViewHolder {
        private ItemAlumnoBinding binding;
        private Consumer<Alumno> update;
        private Consumer<Alumno> delete;

        public AlumnoViewHolder(@NonNull ItemAlumnoBinding binding, Consumer<Alumno> update, Consumer<Alumno> delete) {
            super(binding.getRoot());
            this.binding = binding;
            this.update = update;
            this.delete = delete;
        }

        public void bind(Alumno alumno) {
            binding.textViewDNI.setText(alumno.getDni());
            binding.buttonEdit.setOnClickListener(v -> update.accept(alumno));
            binding.buttonDelete.setOnClickListener(v -> delete.accept(alumno));
        }
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout del elemento de la lista usando ViewBinding
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlumnoBinding binding = ItemAlumnoBinding.inflate(inflater, parent, false);
        return new AlumnoViewHolder(binding, update, delete);
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

