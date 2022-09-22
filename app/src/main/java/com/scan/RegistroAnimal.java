package com.scan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroAnimal extends DialogFragment {
    Button btn_agregar;
    EditText datos, lugar, descripcion;
    private FirebaseFirestore mfirestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registro_animal, container, false);
        mfirestore = FirebaseFirestore.getInstance();
        datos = v.findViewById(R.id.datosAnimal);
        lugar = v.findViewById(R.id.lugarAnimal);
        descripcion = v.findViewById(R.id.descripcionAnimal);
        btn_agregar = v.findViewById(R.id.btnSubir);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datosPersonales = datos.getText().toString().trim();
                String lugarEncontrado = lugar.getText().toString().trim();
                String descripcionAnimal = descripcion.getText().toString().trim();
                if(datosPersonales.isEmpty() && lugarEncontrado.isEmpty() && descripcionAnimal.isEmpty()){
                    Toast toast = Toast.makeText(getContext(),"Ingrese los datos" , Toast.LENGTH_LONG);toast.show();
                }else{
                    postAnimal(datosPersonales,lugarEncontrado,descripcionAnimal);
                }
            }
        });
        return v;
    }
    private void postAnimal(String datosPersonales, String lugarEncontrado, String descripcionAnimal) {
        Map<String, Object> map = new HashMap<>();
        map.put("datos",datosPersonales);
        map.put("lugar",lugarEncontrado);
        map.put("descripcion",descripcionAnimal);
        mfirestore.collection("publicacion").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast toast = Toast.makeText(getContext(),"Publicacion creada" , Toast.LENGTH_LONG);toast.show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(getContext(),"Error en publicar" , Toast.LENGTH_LONG);toast.show();
            }
        });
    }


}