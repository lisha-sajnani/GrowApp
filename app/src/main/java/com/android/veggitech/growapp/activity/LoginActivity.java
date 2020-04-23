package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.database.DbHandler;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView signUp, forgotPassword;
    Button login;
    FirebaseAuth firebaseAuth;
    GoogleApiClient mGoogleApiClient;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);

        email = findViewById(R.id.editTextUserName);
        email.setHint("Email");
        password = findViewById(R.id.editTextPassword);
        password.setHint("Password");
        signUp = findViewById(R.id.textViewSignup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(signupIntent);
                LoginActivity.this.finish();
            }
        });

        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
              /*  if(!validatePassword()| !validateUsername()){
                    return;
                }*/

                String uEmail = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(uEmail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth = FirebaseAuth.getInstance();
                //authenticate user
                firebaseAuth.signInWithEmailAndPassword(uEmail, pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        password.setError("Password too short, enter minimum 6 characters!");
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Authentication failed, check your email and password!", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
               /* DbHandler dbHandler = new DbHandler( LoginActivity.this);
                boolean isExist = dbHandler.checkUserExist(username.getText().toString(), password.getText().toString());

                if(isExist){
                    Intent loginIntent = new Intent(LoginActivity.this,HomeActivity.class);
                    LoginActivity.this.startActivity(loginIntent);
                    LoginActivity.this.finish();
                } else {
                    password.setText(null);
                    Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }*/

             /*   firebaseAuth.signInWithEmailAndPassword(uName, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                                    //progressBar.setVisibility(View.GONE);

                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                                   // progressBar.setVisibility(View.GONE);
                                }
                            }
                        });*/
            }
        });

        forgotPassword = findViewById(R.id.textViewForgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPasswordIntent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                LoginActivity.this.startActivity(forgotPasswordIntent);
                LoginActivity.this.finish();
            }
        });
    }

    private boolean validateUsername() {
        String inputUsername = email.getEditableText().toString().trim();

        if (inputUsername.isEmpty()) {
            email.setError("Field Cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;

        }
    }

    private boolean validatePassword() {
        String inputPassword = password.getEditableText().toString().trim();

        if (inputPassword.isEmpty()) {
            password.setError("Field Cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            return true;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }
}
