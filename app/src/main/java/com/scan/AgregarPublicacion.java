package com.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarPublicacion extends AppCompatActivity {
    Button btn_agregar;
    EditText datos, lugar, descripcion;
    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicacion);
        this.setTitle("Crear Publicacion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FirebaseFirestore.getInstance();
        datos = findViewById(R.id.datosAnimal);
        lugar = findViewById(R.id.lugarAnimal);
        descripcion = findViewById(R.id.descripcionAnimal);
        btn_agregar = findViewById(R.id.btnSubir);
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datosPersonales = datos.getText().toString().trim();
                String lugarEncontrado = lugar.getText().toString().trim();
                String descripcionAnimal = descripcion.getText().toString().trim();
                if(datosPersonales.isEmpty() && lugarEncontrado.isEmpty() && descripcionAnimal.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Ingrese los datos" , Toast.LENGTH_LONG);toast.show();
                }else{
                    postAnimal(datosPersonales,lugarEncontrado,descripcionAnimal);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    private void postAnimal(String datosPersonales, String lugarEncontrado, String descripcionAnimal) {
        Map<String, Object> map = new HashMap<>();
        map.put("datos",datosPersonales);
        map.put("lugar",lugarEncontrado);
        map.put("descripcion",descripcionAnimal);
        mfirestore.collection("publicacion").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast toast = Toast.makeText(getApplicationContext(),"Publicacion creada" , Toast.LENGTH_LONG);toast.show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(),"Error en publicar" , Toast.LENGTH_LONG);toast.show();
            }
        });
    }
}