package com.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        TextView username =(TextView) findViewById(R.id.usuario);
        TextView password1 =(TextView) findViewById(R.id.pass);
        Button login = (Button) findViewById(R.id.btnLogin);
        Button register = (Button) findViewById(R.id.btnRegister);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = username.getText().toString().trim();
                String password2 = password1.getText().toString().trim();

                if(emailUser.isEmpty() && password2.isEmpty()){
                    Toast toast = Toast.makeText(MainActivity.this,"Ingrese los datos" , Toast.LENGTH_LONG);toast.show();
                }else{
                    loginUser(emailUser,password2);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistroActivity.class));
            }
        });
    }

    private void loginUser(String emailUser, String password2) {
        mAuth.signInWithEmailAndPassword(emailUser, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this,MainActivity2.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast toast = Toast.makeText(MainActivity.this,"Usuario no registrado" , Toast.LENGTH_LONG);toast.show();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
            finish();
        }

    }
}