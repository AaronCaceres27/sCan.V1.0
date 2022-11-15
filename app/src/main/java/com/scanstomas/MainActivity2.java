package com.scanstomas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.scanstomas.adapter.PublicacionAdapter;
import com.scanstomas.model.Publicacion;

public class MainActivity2 extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView mRecycler;
    PublicacionAdapter mPublicacionAdapter;
    FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recycler1);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("publicacion");

        FirestoreRecyclerOptions<Publicacion> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Publicacion>().setQuery(query,Publicacion.class).build();

        mPublicacionAdapter = new PublicacionAdapter(firestoreRecyclerOptions,this,getSupportFragmentManager());
        mPublicacionAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mPublicacionAdapter);
        ImageView btnPerfil = (ImageView) findViewById(R.id.btnPerfil);
        Button btnAgregar = (Button) findViewById(R.id.btnCrear);
        mAuth = FirebaseAuth.getInstance();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity2.this,AgregarPublicacion.class));
                RegistroAnimal fm = new RegistroAnimal();
                fm.show(getSupportFragmentManager(), "navegar a fragment");
            }
        });
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity2.this,PerfilActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPublicacionAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPublicacionAdapter.stopListening();
    }
    //Item click
}