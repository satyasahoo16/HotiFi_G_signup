package com.example.hotifi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "";
   // private Button btnGoogleSignUp, btnFacebookSignUp;
    private TextView tvPhoneSignUp;
    SignInButton signInButton;

    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //btnGoogleSignUp=findViewById(R.id.btnGoogleSignUp);
        signInButton = (SignInButton) findViewById(R.id.btnGoogleSignUp);
        tvPhoneSignUp=(TextView) findViewById(R.id.tvPhoneSignUp);
        // btnFacebookSignUp=findViewById(R.id.btnFacebookSignUp);
       // tvPhoneSignUp = findViewById(R.id.tvPhoneSignUp);


       /* findViewById(R.id.btnGoogleSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                }*/
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(LoginActivity.this,gSignInActivity.class));
                }
            }
        };

    signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btnGoogleSignUp:
                        signIn();
                        break;

                   /* case R.id.tvPhoneSignUp:
                        //signIn_with_Phn();
                        Intent intent=new Intent(LoginActivity.this,PhoneActivity.class);
                        startActivity(intent);
                        break;*/
                    // ...
                    // startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    //finish();
                }


            }

        });

        tvPhoneSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,PhoneActivity.class);
                startActivity(intent);
            }
        });
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        mAuth=FirebaseAuth.getInstance();

        /*btnFacebookSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });*/

        /*tvPhoneSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PhoneActivity.class));
                finish();
            }
        });*/
    }


private void signIn(){
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
        }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this,"Authentication Failed!",Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            //Task completedTask;
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
           // updateUI(account);
            Intent intent=new Intent(LoginActivity.this,gSignInActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error:", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    /*private  void signIn_with_Phn()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks)      // OnVerificationStateChangedCallbacks
    }*/
    @Override
    protected void onStart()
    {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
       // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
       // updateUI(account);
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void updateUI(GoogleSignInAccount account) {
    }
}
