package com.scan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.scan.R;
import com.scan.model.Publicacion;

public class PublicacionAdapter extends FirestoreRecyclerAdapter<Publicacion, PublicacionAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PublicacionAdapter(@NonNull FirestoreRecyclerOptions<Publicacion> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Publicacion Publicacion) {
        holder.datos.setText(Publicacion.getDatos());
        holder.lugar.setText(Publicacion.getLugar());
        holder.descripcion.setText(Publicacion.getDescripcion());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_publicacion, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView datos ,lugar, descripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            datos = itemView.findViewById(R.id.datos);
            lugar = itemView.findViewById(R.id.place);
            descripcion = itemView.findViewById(R.id.descripcion);
        }
    }
}
