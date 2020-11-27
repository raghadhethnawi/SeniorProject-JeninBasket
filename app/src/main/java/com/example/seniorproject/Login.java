package com.example.seniorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {


    EditText email_login;
    EditText password_login;

    Button login_btn;

    private FirebaseAuth mFirebaseAuth;
    //private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private CollectionReference userRef = db.collection("Users");


    //TextView havenot_acc;

 //   Button login_btn;
  //  EditText username_log, password_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
/*
        havenot_acc = (TextView) findViewById(R.id.havenot_acc);

        havenot_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReg();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser(view);
            }
        });*/

        email_login = (EditText) findViewById(R.id.email_log);
        password_login = (EditText) findViewById(R.id.password_log);

        login_btn = (Button) findViewById(R.id.login_btn);

        mFirebaseAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email_login.getText().toString();
                String pass = password_login.getText().toString();

                if (!em.isEmpty() && !pass.isEmpty()){
                    mFirebaseAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(Login.this, CategoriesActivity.class));
                            }else{
                                Toast.makeText(Login.this, " Something went wrong ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, " Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(Login.this, " Canceled! ", Toast.LENGTH_SHORT).show();
                        }
                    })
                    ;
                }else
                {
                    Toast.makeText(Login.this, " Please fill up all the values ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
/*
    public void openReg(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void  checkUser(View v){


        username_log = (EditText) findViewById(R.id.username_log);
        password_log = (EditText) findViewById(R.id.password_log);

         final String username_val = username_log.getText().toString();
        String password_val = password_log.getText().toString();

        DocumentReference userRef = db.collection("Users").document(username_val);


        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){



                        }else {
                            Toast.makeText(Login.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }*/
}