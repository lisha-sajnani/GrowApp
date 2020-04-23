/*package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.database.DbHandler;
import com.android.veggitech.growapp.model.MyPlantModel;
import com.android.veggitech.growapp.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText name, email, userName, password, confirmPassword;
    Button signUp;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    CheckBox chkbox;
    ProgressBar progressBar;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\\\S+$).{6,}$");
    TextView errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar) findViewById(R.id.progressBarSignUp);
        errorMessage = findViewById(R.id.textViewError);
        name = findViewById(R.id.editTextName);
        name.setHint("Name");
        email = findViewById(R.id.editTextEmail);
        email.setHint("Email");
        password = findViewById(R.id.editTextPassword);
        password.setHint("Password");
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String inputPassword = password.getEditableText().toString().trim();

                if (!hasFocus) {
                    // Validate youredittext
                    if (!PASSWORD_PATTERN.matcher(inputPassword).matches()) {

                        errorMessage.setVisibility(View.VISIBLE);
                        errorMessage.setText("Password Should contain at least One Upper Case, Lower Case, Number & Special Charcter!!!");
                    }
                }

                else{
                    errorMessage.setVisibility(View.INVISIBLE);
                }
            }
        });
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        confirmPassword.setHint("Confirm Password");
        chkbox=findViewById(R.id.checkBoxCondition);

        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    signUp.setVisibility(View.VISIBLE);
                }
                else{
                    signUp.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignupActivity.this,"Please agree to the Terms and Conditions",Toast.LENGTH_LONG).show();
                }
            }
        });


        signUp = findViewById(R.id.buttonSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if (!validatePassword() | !validateEmail() |!validateusername()|!validatename()) {
                    return;
                }*/

          /*      String uName = name.getText().toString()+"\n";
                String uEmail = email.getText().toString();
                String uPassword = password.getText().toString();
                String confPassword = confirmPassword.getText().toString();

                if(TextUtils.isEmpty(uName)){
                    Toast.makeText(getApplicationContext(),"Enter your name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(uEmail)){
                    Toast.makeText(getApplicationContext(),"Enter email address!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(uPassword)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(uPassword.length()<6){
                    Toast.makeText(getApplicationContext(),"Password too short, enter minimum 6 characters!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(uPassword != confPassword){
                    Toast.makeText(getApplicationContext(), "Passwords don't match",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(uEmail,uPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

                DbHandler dbHandler = new DbHandler(SignupActivity.this);
                dbHandler.insertUserDetails(uName,uEmail,uPassword);
               /* addUser(uName,uEmail,phoneNumber,username,uPassword);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
                Intent signupIntent = new Intent(SignupActivity.this,HomeActivity.class);
                SignupActivity.this.startActivity(signupIntent);
                SignupActivity.this.finish();*/
     /*       }
        });

        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

    }

    private boolean validateEmail() {
        String emailInput = email.getEditableText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field Cannot be Empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String inputPassword = password.getEditableText().toString().trim();
        if(inputPassword.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);

            return true;
        }
    }

    private boolean validateusername() {
        String usernameInput=userName.getEditableText().toString().trim();
        if(usernameInput.isEmpty())
        {
            userName.setError("Field cannot be empty");
            return false;
        }
        else
        {
            userName.setError(null);
            return true;
        }
    }

    private boolean validatename() {
        String nameInput=name.getEditableText().toString().trim();

        if(nameInput.isEmpty())
        {
            name.setError("Field cannot be empty");
            return false;

        }
        else
        {
            name.setError(null);
            return true;
        }
    }

    private void addUser(String uName, String uEmail, int phoneNumber, String username, String uPassword) {

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        String id = databaseReference.push().getKey();
        UserModel userModel = new UserModel(id, uName, uEmail, phoneNumber, username, uPassword);
        databaseReference.child(id).setValue(userModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}*/

package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.database.DbHandler;
import com.android.veggitech.growapp.model.MyPlantModel;
import com.android.veggitech.growapp.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    String passwordpattern =("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$");
    Pattern passpattern= Pattern.compile(passwordpattern);

    //at least 1 digit
    //   "([a-z])" +         //at least 1 lower case letter
    //  "([A-Z])" +         //at least 1 upper case letter
    // "([@#$%^&+=])" +      //at least 1 spec char
    //"$");
    EditText name, email, password, confirmPassword;
    Button signUp;
    CheckBox chkbox;
    FirebaseAuth firebaseAuth;
    TextInputLayout textInputLayout,textInputLayoutconfirm;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       // getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.progressBarSignUp);
        textInputLayout=findViewById(R.id.textInputLayoutPassword);
        textInputLayoutconfirm=findViewById(R.id.textInputLayoutConfirmPassword);

        name = findViewById(R.id.editTextName);
        name.setHint("Name");
        email = findViewById(R.id.editTextEmail);
        email.setHint("Email");

        password = findViewById(R.id.editTextPassword);
        password.setHint("Password");
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        confirmPassword.setHint("Confirm Password");
        signUp = findViewById(R.id.buttonSignUp);
        chkbox=findViewById(R.id.checkBoxCondition);
        // final View  invisbleview=null;


        firebaseAuth=FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword() | !validateEmail() | !validatename() ) {
                    // Toast.makeText(SignupActivity.this, "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Toast.makeText(SignupActivity.this, "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
                String uName = name.getText().toString() + "\n";
                String uEmail = email.getText().toString();
                // String uPhone = phone.getText().toString() + "\n";
                //int phoneNumber = Integer.parseInt(phone.getText().toString());
                // String username = userName.getText().toString();
                String uPassword = password.getText().toString();
                // String confPassword = confirmPassword.getEditableText().toString();

              /* else if(uPassword!=confPassword) {
                    Toast.makeText(SignupActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                }*/
                //Toast.makeText(SignupActivity.this, "Details Inserted Successfully", Toast.LENGTH_SHORT).show();


                // DbHandler dbHandler = new DbHandler(SignupActivity.this);
                //   dbHandler.insertUserDetails(uName, uEmail, phoneNumber, username, uPassword);
                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(uEmail, uPassword).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
                            Intent signupIntent = new Intent(SignupActivity.this, HomeActivity.class);
                            SignupActivity.this.startActivity(signupIntent);
                            // SignupActivity.this.finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Registeration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    signUp.setVisibility(View.VISIBLE);
                }
                else{
                    signUp.setVisibility(View.INVISIBLE);
                    Toast.makeText(SignupActivity.this,"Please agree to the Terms and Conditions",Toast.LENGTH_LONG).show();
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayout.setPasswordVisibilityToggleEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //String password=

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayoutconfirm.setPasswordVisibilityToggleEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!passpattern.matcher(password.getEditableText().toString().trim()).matches())
                    textInputLayout.setError("Your password must be at least 6 characters long, contain at least one number and have a mixture of uppercase and lowercase letters");
            }
        });
    }

    private boolean validatePassword() {

        String inputPassword = password.getEditableText().toString().trim();
        String confPassword=confirmPassword.getEditableText().toString().trim();
        if(inputPassword.isEmpty()){
            textInputLayout.setPasswordVisibilityToggleEnabled(false);
            password.setError("Please enter your password");

            return false;
        }
        else if(confPassword.isEmpty())
        {
            textInputLayoutconfirm.setPasswordVisibilityToggleEnabled(false);
            confirmPassword.setError("Password must be confirmed");
            return false;
        }
        else if(!inputPassword.equals(confPassword)){
            Toast.makeText(SignupActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (!passpattern.matcher(inputPassword).matches()) {
            textInputLayout.setPasswordVisibilityToggleEnabled(false);
            Toast.makeText(SignupActivity.this, "Your password must be at least 6 characters long, contain at least one number and have a mixture of uppercase and lowercase letters", Toast.LENGTH_LONG).show();
            //  password.setError("weak");
            return false;
        }
        else {
            textInputLayout.setPasswordVisibilityToggleEnabled(true);
            password.setError(null);
            confirmPassword.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = email.getEditableText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Please enter your email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }




    private boolean validatename()
    {
        String nameInput=name.getEditableText().toString().trim();

        if(nameInput.isEmpty())
        {
            name.setError("Please enter your full name");
            return false;

        }
        else
        {
            name.setError(null);
            return true;
        }
    }

    private void sendEmailVerification() {
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Successfully registered. Verification mail has been sent", Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(SignupActivity.this, "Verifcation mail not sent", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
          /*  private boolean validateusername()
            {
               String usernameInput=userName.getEditableText().toString().trim();
                if(usernameInput.isEmpty())
                {
                    userName.setError("Field cannot be empty");
                    return false;
                }
                else
                {
                    userName.setError(null);
                    return true;
                }
            }
            private boolean validatephoneno()
            {
                String inputPhoneno=phone.getText().toString().trim();
                if(!Patterns.PHONE.matcher(inputPhoneno).matches()) {
                    phone.setError("Enter a valid phone no");
                    return false;
                }
                    else
                        return true;

            }*/


}



