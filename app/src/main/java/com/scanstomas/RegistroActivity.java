package com.scanstomas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    FirebaseFirestore mFirebase;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        EditText nombre =(EditText) findViewById(R.id.nombreRegistro);
        EditText correo =(EditText) findViewById(R.id.correoRegistro);
        EditText apellido = (EditText)findViewById(R.id.Apellido);
        EditText username=(EditText)findViewById(R.id.userName);
        EditText celular = (EditText)findViewById(R.id.telefono);
        Button registro = (Button) findViewById(R.id.btnRegistrado);
        EditText pass = (EditText) findViewById(R.id.pasRegistro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebase = FirebaseFirestore.getInstance();
                mAuth = FirebaseAuth.getInstance();
                String celularUser = celular.getText().toString().trim();
                String apellidoUser = apellido.getText().toString().trim();
                String nombreUsuario = username.getText().toString().trim();
                String nameUser = nombre.getText().toString().trim();
                String correos = correo.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if(nameUser.isEmpty() && password.isEmpty() && correos.isEmpty() && apellidoUser.isEmpty() && nombreUsuario.isEmpty()&& celularUser.isEmpty()){
                    Toast toast = Toast.makeText(RegistroActivity.this,"Rellene los datos" , Toast.LENGTH_LONG);toast.show();
                }
                else{
                    registerUser(nameUser,apellidoUser,correos,nombreUsuario,celularUser,password);
                    startActivity(new Intent(RegistroActivity.this,MainActivity.class));
                }

            }
        });

    }

    private void registerUser(String nameUser, String apellidoUser, String correos, String nombreUsuario, String celularUser, String password) {
        mAuth.createUserWithEmailAndPassword(correos,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id",id);
                map.put("name", nameUser);
                map.put("apellido",apellidoUser);
                map.put("correo", correos);
                map.put("contraseña", password);
                map.put("username", nombreUsuario);
                map.put("celular", celularUser);
                mFirebase.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegistroActivity.this,MainActivity.class));
                        Toast toast = Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT);toast.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(RegistroActivity.this,"Error al guardar" , Toast.LENGTH_SHORT);toast.show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(RegistroActivity.this,"Error al registrar" , Toast.LENGTH_SHORT);toast.show();
            }
        });
    }


}