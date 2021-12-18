package com.cardapp.cardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class JoinActivity extends AppCompatActivity {
    private Button btnJoin, btnBack, btnCheck;
    private EditText edtId, edtPass, edtRePass, edtEmail;
    private TextView edtDate;
    private FirebaseFirestore db;
    private boolean isCheck;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        initUI();
        db = FirebaseFirestore.getInstance();
        edtId.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isCheck = false;
                return false;
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDate.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                    }
                },1980,0,01);
                dialog.show();
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheck){
                    String id = edtId.getText().toString();
                    String email = edtEmail.getText().toString();
                    String pass = edtPass.getText().toString();
                    String date = edtDate.getText().toString();
                    if(TextUtils.isEmpty(pass) || TextUtils.isEmpty(email) || TextUtils.isEmpty(date) ||TextUtils.isEmpty(id)){
                        Toast.makeText(getApplicationContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show();
                    }else{
                        String repass = edtRePass.getText().toString();
                        if(!repass.equals(pass)){
                            Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                        }else{
                            User user = new User();
                            user.setId(id);
                            user.setEmail(email);
                            user.setPassword(pass);
                            user.setDateOfBirth(date);

                            db.collection("user")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference) {
                                            Toast.makeText(getApplicationContext(), "Sign up success!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(JoinActivity.this,LoginActivity.class));
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please check the Id!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkExistId();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JoinActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    private void checkExistId() {
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist = false;
                        String id = edtId.getText().toString();
                        if(TextUtils.isEmpty(id)){
                            Toast.makeText(getApplicationContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show();
                        }else{
                            if(task.isSuccessful()){
                                for(QueryDocumentSnapshot document: task.getResult()){
                                    if(document.getString("id").equalsIgnoreCase(id)){
                                        isExist = true;
                                        break;
                                    }
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(isExist){
                            Toast.makeText(getApplicationContext(), "Id is Exist!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Id is valid!", Toast.LENGTH_SHORT).show();
                            isCheck = true;
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initUI() {
        btnBack = findViewById(R.id.btnBack);
        btnJoin = findViewById(R.id.btnJoin);
        btnCheck = findViewById(R.id.btnCheck);

        edtId = findViewById(R.id.edtId);
        edtPass = findViewById(R.id.edtPassword);
        edtRePass = findViewById(R.id.edtRePassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtDate = findViewById(R.id.edtDateOfBirth);


    }
}