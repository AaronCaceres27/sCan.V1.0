package com.scan.adapter;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.scan.PerfilActivity;
import com.scan.R;
import com.scan.RegistroActivity;
import com.scan.RegistroAnimal;
import com.scan.model.Publicacion;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PublicacionAdapter extends FirestoreRecyclerAdapter<Publicacion, PublicacionAdapter.ViewHolder> {
    private FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();
    Activity activity;
    FragmentManager fm;
    String fono;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uid;
    @SuppressLint("RestrictedApi")
    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

    public PublicacionAdapter(@NonNull FirestoreRecyclerOptions<Publicacion> options, Activity activity, FragmentManager fm) {
        super(options);
        this.activity = activity;
        this.fm = fm;
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Publicacion Publicacion) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();
        holder.datos.setText(Publicacion.getName());
        holder.apellido.setText(Publicacion.getApellido());
        holder.lugar.setText(Publicacion.getLugar());
        holder.descripcion.setText(Publicacion.getDescripcion());
        holder.celular.setText(Publicacion.getCelular());
        String fotoPublicacion = Publicacion.getFoto();
        try{
            if(!fotoPublicacion.equals("")){
                Picasso.with(activity.getApplicationContext())
                        .load(fotoPublicacion)
                        .resize(150,150)
                        .into(holder.fotoPublicacion);
            }
        }catch (Exception e){
            Log.d("Exception", "e: "+e);
        }
        uid = mAuth.getCurrentUser().getUid();
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePublicacion(id);
            }
        });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, RegistroActivity.class);
                i.putExtra("id_dato_p", id);
                RegistroAnimal registroAnimal = new RegistroAnimal();
                Bundle bundle  = new Bundle();
                bundle.putString("id_dato_p",id);
                registroAnimal.setArguments(bundle);
                registroAnimal.show(fm,"open fragment");
            }
        });
        }
    private void deletePublicacion(String id) {
        mFireStore.collection("publicacion").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                vibrator.vibrate(500);
                Toast.makeText(activity, "Publicacion eliminada", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar publicacion", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_publicacion, parent, false);
        return new ViewHolder(view);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView datos ,apellido,lugar, descripcion ,celular;
        ImageView btn_delete, btn_edit, fotoPublicacion;
        Button btn_subir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            apellido = itemView.findViewById(R.id.datosApellido);
            datos = itemView.findViewById(R.id.nombrePublicacion);
            lugar = itemView.findViewById(R.id.place);
            descripcion = itemView.findViewById(R.id.descripcion);
            celular = itemView.findViewById(R.id.celular2);
            btn_delete = itemView.findViewById(R.id.btn_eliminar);
            btn_edit = itemView.findViewById(R.id.btn_editar);
            btn_subir = itemView.findViewById(R.id.btnSubir);
            fotoPublicacion = itemView.findViewById(R.id.fotoPerfilUser);
        }
    }


}
