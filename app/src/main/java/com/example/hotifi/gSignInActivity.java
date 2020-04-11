package com.example.hotifi;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class gSignInActivity extends AppCompatActivity {

    Button signOut;
    ImageView profile_pic;
    TextView name,email,id;
    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_signin);



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
       mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

       mAuth=FirebaseAuth.getInstance();

       mAuthListener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if(firebaseAuth.getCurrentUser()==null)
               {
                   startActivity(new Intent(gSignInActivity.this,LoginActivity.class));
               }
           }
       };


        profile_pic=findViewById(R.id.gsignin_profile_pic);
        name=findViewById(R.id.gsignin_name);
        email=findViewById(R.id.gsignIn_email);
        id=findViewById(R.id.gsignIn_id);
        signOut=findViewById(R.id.gsignOut_btn);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    signOut();
                }


        });



        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            Glide.with(this).load(String.valueOf(personPhoto)).into(profile_pic);
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(gSignInActivity.this,LoginActivity.class);
                        Toast.makeText(gSignInActivity.this,"Signed out Successfully",Toast.LENGTH_LONG).show();

                        startActivity(intent);

                        finish();



                    }

                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }





}
