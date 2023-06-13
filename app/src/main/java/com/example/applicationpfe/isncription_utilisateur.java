package com.example.applicationpfe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inscription_utilisateur extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword,signupnomuser;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_utilisateur);

        auth = FirebaseAuth.getInstance();
        signupnomuser= findViewById(R.id.signup_nomuser);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                if (user.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // L'inscription s'est terminée avec succès
                                FirebaseUser currentUser = auth.getCurrentUser();
                                String userUid = currentUser.getUid();
                                // Enregistrez les informations supplémentaires de l'utilisateur dans la base de données Firebase
                                saveUserToFirebaseDatabase(userUid, "nom", "motdepasse");
                                Toast.makeText(inscription_utilisateur.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(inscription_utilisateur.this, AuthentificationActivity.class));
                            } else {
                                Toast.makeText(inscription_utilisateur.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(inscription_utilisateur.this, AuthentificationActivity.class));
            }
        });
    }

    private void saveUserToFirebaseDatabase(String userUid, String nomUser, String motDePasse) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        user user = new user(userUid, nomUser, motDePasse);
        usersRef.child(userUid).setValue(user);
    }
}