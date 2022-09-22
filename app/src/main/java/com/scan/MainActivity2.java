package com.scan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.scan.adapter.PublicacionAdapter;
import com.scan.model.Publicacion;

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

        mPublicacionAdapter = new PublicacionAdapter(firestoreRecyclerOptions);
        mPublicacionAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mPublicacionAdapter);

        Button btnAgregar = (Button) findViewById(R.id.btnCrear);
        Button btnClose =  (Button) findViewById(R.id.btncerrar);
        mAuth = FirebaseAuth.getInstance();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity2.this,AgregarPublicacion.class));
                RegistroAnimal fm = new RegistroAnimal();
                fm.show(getSupportFragmentManager(), "navegar a fragment");
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
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
}