package com.example.hotifi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "";
   // private Button btnGoogleSignUp, btnFacebookSignUp;
    private TextView tvPhoneSignUp;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //btnGoogleSignUp=findViewById(R.id.btnGoogleSignUp);
        signInButton = (SignInButton) findViewById(R.id.btnGoogleSignUp);
        // btnFacebookSignUp=findViewById(R.id.btnFacebookSignUp);
       // tvPhoneSignUp = findViewById(R.id.tvPhoneSignUp);


       /* findViewById(R.id.btnGoogleSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                }*/


    signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btnGoogleSignUp:
                        signIn();
                        break;
                    // ...
                    // startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    //finish();
                }
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

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

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
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

    @Override
    protected void onStart()
    {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
       // updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
    }
}
