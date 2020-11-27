package com.example.seniorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button register_btn;
    String f_name, l_name, e_mail, pass;

    private FirebaseAuth mFirebaseAuth;

    private static final String TAG = "Register";

    private static final String FirstName_KEY = "first_name";
    private static final String LastName_KEY = "last_name";
    public static final String Username_KEY = "username";
    private static final String Password_KEY = "password";
    //private static final String PhoneNumber_KEY = "phone_number";

    private EditText first_name, last_name, password, phone_number;
    public EditText email;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    TextView has_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        has_account = (TextView) findViewById(R.id.has_account);

        has_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();

        first_name = (EditText) findViewById(R.id.fname_reg);
        last_name = (EditText) findViewById(R.id.lname_reg);
        email = (EditText) findViewById(R.id.email_reg);
        password = (EditText) findViewById(R.id.password_reg);
        //phone_number = (EditText) findViewById(R.id.phone_reg);

        register_btn = (Button) findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(view);
            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void saveUser(View v){
         f_name = first_name.getText().toString();
         l_name = last_name.getText().toString();
         e_mail = email.getText().toString();
         pass = password.getText().toString();
        //String phone_num = phone_number.getText().toString();

        if (!f_name.isEmpty() && !l_name.isEmpty() && !e_mail.isEmpty() && !pass.isEmpty()){
            mFirebaseAuth.createUserWithEmailAndPassword(e_mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        //User created


                        // Add to collection
                        Map<String, Object> user = new HashMap<>();
                        user.put(FirstName_KEY, f_name);
                        user.put(LastName_KEY, l_name);
                        user.put(Username_KEY, e_mail);
                        user.put(Password_KEY, pass);

                        first_name.getText().clear();
                        last_name.getText().clear();
                        email.getText().clear();
                        password.getText().clear();


                        db.collection("Users").document(e_mail).set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Register.this, "User saved", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                                        Log.d(TAG, e.toString());
                                    }
                                })
                        ;



                        // Redirect to the home page ------Importantttt
                        startActivity(new Intent(Register.this, Login.class));


                    }else {
                        Toast.makeText(Register.this, "something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register.this, "Error!" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnCanceledListener(new OnCanceledListener() {
                @Override
                public void onCanceled() {
                    Toast.makeText(Register.this, "Canceled, Try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "Please fill up all the fields!", Toast.LENGTH_SHORT).show();
        }

        /*
        Map<String, Object> user = new HashMap<>();
        user.put(FirstName_KEY, f_name);
        user.put(LastName_KEY, l_name);
        user.put(Username_KEY, user_name);
        user.put(Password_KEY, pass);
        //user.put(PhoneNumber_KEY, phone_num);
*/
        /*
        first_name.getText().clear();
        last_name.getText().clear();
        email.getText().clear();
        password.getText().clear();
        //phone_number.getText().clear();
*/


        /*
        db.collection("Users").document(user_name).set(user)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(Register.this, "User saved", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                }
            })
        ;*/
    }
}