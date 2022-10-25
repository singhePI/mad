package com.example.mapprojectbook;



import android.os.AsyncTask;
import android.os.Bundle;
import android.text.*;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private DatabaseReference db;
    private FirebaseAuth auth;

    private EditText editTxtUsername, editTxtEmail, editTxtPass, editTxtPassRe;
    private Button btnAddImg, btnRegister, btnSignUp;
    private TextView txtPromptWarnUsername, txtPromptWarnEmail, txtPromptWarnPass, txtPromptWarnPassRe, txtPromptWarnGender;
//    private RadioGroup rgGender;
    private ConstraintLayout parent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();

        db = FirebaseDatabase.getInstance().getReference().child("Users");
        auth = FirebaseAuth.getInstance();

        btnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");
        if(validateData()) {
            createUser();
        }
    }

    private void createUser() {
        Log.d(TAG, "createUser: Creating user");

        auth.createUserWithEmailAndPassword(editTxtEmail.getText().toString(), editTxtPass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            db.push().setValue(new User(
                                    editTxtEmail.getText().toString(),
                                    editTxtUsername.getText().toString(),
                                    "null",
                                    "null",
                                    BCrypt.hashpw(editTxtPass.getText().toString(), BCrypt.gensalt(15)),
                                    "null",
                                    "null"
                            ));
                            Log.d(TAG, "createUser: User created");
                            showSnackBar();
                        } else {
                            Log.w(TAG, "createUser: Failed", task.getException());
                            showSnackBar(task.getException());
                        }
                    }
                });
        Log.d(TAG, "createUser: User created");
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        txtPromptWarnUsername.setTextColor(ContextCompat.getColor(this, R.color.dark_red));
        txtPromptWarnEmail.setTextColor(ContextCompat.getColor(this, R.color.dark_red));
        txtPromptWarnPass.setTextColor(ContextCompat.getColor(this, R.color.dark_red));
        txtPromptWarnPassRe.setTextColor(ContextCompat.getColor(this, R.color.dark_red));

        String username = editTxtUsername.getText().toString(),
                email = editTxtEmail.getText().toString(),
                pass = editTxtPass.getText().toString(),
                passRe = editTxtPassRe.getText().toString();

        if (username.equals("")) {
            txtPromptWarnUsername.setText("Enter an username");

            txtPromptWarnUsername.setVisibility(View.VISIBLE);
            return false;
        }
        if (isInvalidEmail(email)){
            txtPromptWarnEmail.setText("Enter a proper email address");

            txtPromptWarnEmail.setVisibility(View.VISIBLE);
            return false;
        }
        if (pass.equals("")) {
            txtPromptWarnPass.setText("Enter a password");

            txtPromptWarnPass.setVisibility(View.VISIBLE);
            return false;
        }
        if (passRe.equals("")) {
            txtPromptWarnPassRe.setText("Re-enter password");

            txtPromptWarnPassRe.setVisibility(View.VISIBLE);
            return false;
        }

        if (pass == passRe) {
            txtPromptWarnPassRe.setText("Password does not match");

            txtPromptWarnPassRe.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtPromptWarnEmail.setVisibility(View.GONE);
        txtPromptWarnPassRe.setVisibility(View.GONE);
        txtPromptWarnPass.setVisibility(View.GONE);
        txtPromptWarnUsername.setVisibility(View.GONE);

        Snackbar.make(parent, "Registration Done", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

    }

    private void showSnackBar(Exception e) {
        Log.d(TAG, "showSnackBar: Started");
        txtPromptWarnEmail.setVisibility(View.GONE);
        txtPromptWarnPassRe.setVisibility(View.GONE);
        txtPromptWarnPass.setVisibility(View.GONE);
        txtPromptWarnUsername.setVisibility(View.GONE);

        Snackbar.make(parent, "Registration Failed: " + e.getMessage() , Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");
        editTxtEmail = findViewById(R.id.editText_emailAddress);
        editTxtPassRe = findViewById(R.id.editText_passwordRe);
        editTxtPass = findViewById(R.id.editText_password);
        editTxtUsername = findViewById(R.id.editText_username);

        btnAddImg = findViewById(R.id.button_addImage);
        btnRegister = findViewById(R.id.button_register);

        txtPromptWarnEmail = findViewById(R.id.textView_email);
        txtPromptWarnPass = findViewById(R.id.textView_password);
        txtPromptWarnGender = findViewById(R.id.textView_gender);
        txtPromptWarnPassRe = findViewById(R.id.textView_passwordRe);
        txtPromptWarnUsername = findViewById(R.id.textView_username);


        parent = findViewById(R.id.parent_signup);
    }


    public final static boolean isInvalidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return true;
        } else {
            return !Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

