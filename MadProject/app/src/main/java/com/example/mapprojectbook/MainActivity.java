package com.example.mapprojectbook;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.*;
import android.os.*;
import android.util.Log;
import android.widget.*;



import android.view.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;

    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";


    SharedPreferences sharedpreferences;
    String email, password;

    private FirebaseAuth auth;
    private DatabaseReference db;
    private TextView txtPromptWarmingEmail, txtPromptWarmingPass, txtSignUp ;
    private EditText editTextEmail, editTextPass;

    private User foundUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("User");
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);


        email = sharedpreferences.getString("EMAIL_KEY", null);

        password = sharedpreferences.getString("PASSWORD_KEY", null);


        Button btnLogin = findViewById(R.id.button_login);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLogin();
            }
        });

    }

    private void initLogin() {
        Log.d(TAG, "initLogin: Started");

        txtPromptWarmingEmail.setVisibility(View.GONE);
        txtPromptWarmingPass.setVisibility(View.GONE);

        if(validateData()) {
            txtPromptWarmingEmail.setVisibility(View.GONE);
            txtPromptWarmingPass.setVisibility(View.GONE);
            authUser(editTextEmail.getText().toString(), editTextPass.getText().toString());
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }


    }

    private Void authUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
        return null;
    }


    private boolean validateData() {
        Log.d(TAG, "validateData: Started");

        txtPromptWarmingEmail.setTextColor(ContextCompat.getColor(this, R.color.dark_red));
        txtPromptWarmingPass.setTextColor(ContextCompat.getColor(this, R.color.dark_red));

        if (RegisterActivity.isInvalidEmail(editTextEmail.getText().toString())) {
            txtPromptWarmingEmail.setText("Invalid Email");
            txtPromptWarmingEmail.setVisibility(View.VISIBLE);
            return false;
        }

        if (editTextPass.getText().toString().equals("")) {
            txtPromptWarmingPass.setText("Invalid Password");
            txtPromptWarmingPass.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }



    private void initView() {
        Log.d(TAG, "initView: Started");
        
        txtPromptWarmingEmail = findViewById(R.id.textView_warnEmail);
        txtPromptWarmingPass = findViewById(R.id.textView_warnPass);
        txtSignUp = findViewById(R.id.textView_signup);

        editTextEmail = findViewById(R.id.editText_loginEmail);
        editTextPass = findViewById(R.id.editText_loginPass);

        txtPromptWarmingEmail.setVisibility(View.GONE);
        txtPromptWarmingPass.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: Started");
        super.onStart();
    }
    
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: started");
        super.onResume();
    }


}