package com.cardapp.cardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnJoin;
    EditText edtId, edtPass;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        firestore = FirebaseFirestore.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                finish();
            }
        });
    }

    private void checkLogin() {
        String id = edtId.getText().toString();
        String pass = edtPass.getText().toString();
        if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }else{
            firestore.collection("user")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            boolean okId = false;

                            if(task.isSuccessful()){
                                for (QueryDocumentSnapshot document: task.getResult()){
                                    if(id.equalsIgnoreCase(document.getString("id"))){
                                        if(pass.equals(document.getString("password"))){
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "Password is incorrect", Toast.LENGTH_SHORT).show();
                                        }
                                        okId = true;
                                    }
                                }
                                if(!okId){
                                    Toast.makeText(getApplicationContext(), "Id is incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private void initUI() {
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);
        edtId = findViewById(R.id.edtId);
        edtPass = findViewById(R.id.edtPassword);
    }
}